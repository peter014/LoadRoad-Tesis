package app.services;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.configurations.Constantes;
import app.dto.CiudadDashDTO;
import app.dto.CiudadesDashDTO;
import app.dto.CoordenadasDTO;
import app.dto.EmpresasDTO;
import app.dto.EmpresasPedidoDTO;
import app.dto.GananciaDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.PrecioDashDTO;
import app.dto.RegistroClienteDTO;
import app.entities.Calificacion;
import app.entities.Camion;
import app.entities.Carga;
import app.entities.CentroAcopio;
import app.entities.Cliente;
import app.entities.Cuentas;
import app.entities.Empresa;
import app.entities.Fletes;
import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Trayecto;
import app.entities.Usuario;
import app.interfaces.ServicioClienteInterface;
import app.repository.CalificacionRepository;
import app.repository.CargaRepository;
import app.repository.CentroAcopioRepository;
import app.repository.ClienteRepository;
import app.repository.CuentasRepository;
import app.repository.EmpresaRepository;
import app.repository.FletesRepository;
import app.repository.PedidoRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;

@Service("ServicioCliente")
@Transactional
public class ServicioCliente implements ServicioClienteInterface{

	@Autowired 
	UsuarioRepository usuarioRepo;
	
	@Autowired 
	ClienteRepository clienteRepo;
	
	@Autowired 
	CuentasRepository cuentasRepo;
	
	@Autowired
	CentroAcopioRepository centroAcopioRepo;
	
	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	EmpresaRepository empresaRepo;
	
	@Autowired
	CargaRepository cargaRepo;
	
	@Autowired
	TrayectoRepository trayectoRepo;
	
	@Autowired
	CalificacionRepository calificacionRepo;
	
	@Autowired
	FletesRepository fletesRepo;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		
	@Override
	public String ingresar(IngresoDTO ingreso) {
		Usuario u = usuarioRepo.findUsuario(ingreso.getUsuario());
		if(u!=null && u.getClienteList().size()==1){
			if(ingreso.getClave().equals(u.getClave())){
				return u.getClienteList().get(0).getId().toString();
			}
		}
		return "0";
	}

	@Override
	public Boolean registrarse(RegistroClienteDTO registro) {
		Date f;
		try {
			f = format.parse(registro.getFechaVencimiento());
			Cuentas nuevaCuenta = new Cuentas(registro.getNumeroTarjeta(), registro.getNombreTarjeta(), registro.getBanco(),
					registro.getTarjetaCredito(),f, registro.getCodigoSeguridad(), null);

			Usuario nuevoUsuario = new Usuario(registro.getCorreo(), registro.getClave(), registro.getNombre(), null, null, null);
			Cliente nuevoCliente = new Cliente(registro.getNit(), null, nuevaCuenta, nuevoUsuario);

			usuarioRepo.saveAndFlush(nuevoUsuario);
			cuentasRepo.saveAndFlush(nuevaCuenta);
			clienteRepo.saveAndFlush(nuevoCliente);
		}catch(DataIntegrityViolationException ex){
			ex.printStackTrace();
			return false;
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public EmpresasPedidoDTO buscarEmpresa(InfoCargaDTO buscar) {
		boolean b = false, guardarPedido=false;
		BigDecimal precio = new BigDecimal(0);
		Camion camionSugerido = null;
		Cliente cliente = clienteRepo.findOne(new BigDecimal(buscar.getIdCliente()));
		if(cliente == null){
			return null;
		}
		List<EmpresasDTO> empresaDto = new ArrayList<>();
		List<CentroAcopio> centros = centroAcopioRepo.findCentroCiudad(buscar.getCiudadOrigen());
		for(CentroAcopio centro:centros){
			Empresa empresa = centro.getEmpresaFk();
			for(Transportador transportador:empresa.getTransportadorList()){
				if(transportador.getEstado().equals(Constantes.getDisponible()) && transportador.getCiudad().equals(buscar.getCiudadOrigen())){
					b=true;
					break;
				}
				else{
					b=false;
				}
			}
			if(!b){
				continue;
			}
			b=false;
			for(Camion camion:empresa.getCamionList()){
				if(confirmarCamion(camion, buscar) ){
					b=true;
					camionSugerido = camion;
					break;
				}
				else{
					b=false;
				}
			}
			if(centro.getNumeroAuxiliares() < Short.parseShort(buscar.getAuxiliarCargue()) + Short.parseShort(buscar.getAuxiliarDescargue()) ){
				b=false;
			}
			if(b){
				precio = calcularPrecio(buscar.getCiudadOrigen(),buscar.getCiudadDestino(),empresa);
				empresaDto = crearListaRetorno(centro,empresaDto,camionSugerido,buscar.getCoordenadasOrigen(),precio);
				guardarPedido=true;
			}
		}
		Pedido p = new Pedido();
		if(guardarPedido){
			p = guardarPedido(buscar,cliente,precio);
		}
		EmpresasPedidoDTO emPedido = new EmpresasPedidoDTO(p.getId(), empresaDto);
		return emPedido;
	}
	
	private Pedido guardarPedido(InfoCargaDTO buscar, Cliente cli, BigDecimal precio){
		Date fechaCargue = null;
		Date fechaDescargue = null;
		try {
			fechaCargue = dateHourFormat.parse(buscar.getFechaHoraCargue());
			fechaDescargue = dateHourFormat.parse(buscar.getFechaHoraDescargue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Pedido p = new Pedido(fechaDescargue, fechaCargue, buscar.getCoordenadasOrigen(), buscar.getCoordenadasDestino(), buscar.getDireccionOrigen(), 
				buscar.getDireccionDestino(), buscar.getCiudadOrigen(), buscar.getCiudadDestino(), buscar.getAsegurada(), Constantes.getPendiente(), 
				precio, Short.parseShort(buscar.getAuxiliarCargue()),Short.parseShort(buscar.getAuxiliarDescargue()),cli,new Date());
		p = pedidoRepo.saveAndFlush(p);
		Carga c = new Carga(buscar.getDescripcion(), new BigDecimal(buscar.getValor()), new BigDecimal(buscar.getPeso()), new BigDecimal(buscar.getLargo()),
				new BigDecimal(buscar.getAncho()), new BigDecimal(buscar.getAlto()), buscar.getTipoPaquete(), buscar.getTipoCarga(), p);
		cargaRepo.saveAndFlush(c);
		return p;
	}
	
	private List<EmpresasDTO> crearListaRetorno(CentroAcopio centro, List<EmpresasDTO> empresasDto,Camion camionSugerido, String coordenadaOrigen, BigDecimal precio){
		Empresa empresa = centro.getEmpresaFk();
		String[] coordAcopio = centro.getUbicacionCord().split(",");
		String[] coordOrigen = coordenadaOrigen.split(",");
		double distancia = distanciaCoord(Double.parseDouble(coordAcopio[0]), Double.parseDouble(coordAcopio[1]), 
				Double.parseDouble(coordOrigen[0]), Double.parseDouble(coordOrigen[1]));
		String distanciaString= String.valueOf(distancia);
		int index =  distanciaString.indexOf('.');
		String camion=camionSugerido.getMarca()+" "+camionSugerido.getModelo()+" "+ camionSugerido.getAno();
		EmpresasDTO nuevaEmp = new EmpresasDTO(empresa.getUsuarioFk().getNombre(), camion, getCalificacion(empresa.getCalificacion()),
				distanciaString.substring(0, index+3),precio.toString(), empresa.getId());
		
		empresasDto.add(nuevaEmp);
		Comparator<EmpresasDTO> defaultComparator = new Comparator<EmpresasDTO>() {
			   @Override
			   public int compare(EmpresasDTO e1, EmpresasDTO e2) {
			       if(e1.getCalificacion() > e2.getCalificacion()){
			    	   return 1;
			       }
			       if(e1.getCalificacion() < e2.getCalificacion()){
			    	   return -1;
			       }
			       if(e1.getCalificacion() == e2.getCalificacion()){
			    	   if(Double.parseDouble(e1.getDistancia()) > Double.parseDouble(e2.getDistancia())){
				    	   return 1;
				       }
				       if(Double.parseDouble(e1.getDistancia()) < Double.parseDouble(e2.getDistancia())){
				    	   return -1;
				       }
			       }
			       return 0;
			   }
			};
			Collections.sort(empresasDto,defaultComparator);
		
		return empresasDto;
	}

	public float getCalificacion(List<Calificacion> calificaciones){
		if(calificaciones.size() == 0){
			return 0;
		}
		float cali = 0;
		for(Calificacion ca : calificaciones){
			cali+=ca.getCalificacion().floatValue();
		}
		return cali/calificaciones.size();
	}
	
	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        
        return distancia;  
    }
	
	private boolean confirmarCamion(Camion camion,InfoCargaDTO buscarDto){
		long peso=Long.parseLong(buscarDto.getPeso());
		long alto=Long.parseLong(buscarDto.getAlto());
		long ancho=Long.parseLong(buscarDto.getAncho());
		long largo=Long.parseLong(buscarDto.getLargo());
		
		if(camion.getTipo().equals(buscarDto.getTipoCamion()) && camion.getPeso().longValue() >= peso && camion.getAlto().longValue() >= alto && 
				camion.getAncho().longValue() >= ancho && camion.getLargo().longValue() >= largo && 
				camion.getEstado().equals(Constantes.getDisponible()) && buscarDto.getCiudadOrigen().equals(camion.getCiudad())){
			return true;
		}
		return false;
	}
	
	private BigDecimal calcularPrecio(String origen, String destino, Empresa empresa){
		Fletes f = fletesRepo.findCiudad(origen,destino);
		if(f == null){
			f = fletesRepo.findCiudad(destino,origen);
			if(f == null){
				return new BigDecimal(-1);
			}
		}
		BigDecimal porcentaje = (f.getValor().multiply(empresa.getPorcentaje())).divide(new BigDecimal(100));
		return f.getValor().add(porcentaje);
	}

	@Override
	public Boolean seleccionarEmpresa(String idPedido, String idEmpresa) {
		Pedido pedido = pedidoRepo.findOne(new BigDecimal(idPedido));
		Empresa empresa = empresaRepo.findOne(new BigDecimal(idEmpresa));
		if(pedido != null && empresa != null && pedido.getEmpresaFk() == null){
			pedido.setEmpresaFk(empresa);
			pedidoRepo.saveAndFlush(pedido);
			return true;
		}
		return false;
	}

	@Override
	public InfoUsuarioDTO getInfoCliente(String idCliente) {
		Cliente cl = clienteRepo.findOne(new BigDecimal(idCliente)); 
		if(cl != null){
			return new InfoUsuarioDTO(cl.getUsuarioFk().getNombre(),cl.getNit());
		}
		return new InfoUsuarioDTO("0","0");
	}
	
	@Override
	public List<NotificacionCargaDTO> revisarSolicitudes(BigDecimal id) {
		List<NotificacionCargaDTO> result = new ArrayList<>();
		Cliente cliente = clienteRepo.findOne(id);
		if(cliente == null ){
			return result;
		}
		for(Pedido p : cliente.getPedidoList()){
			if( !p.getEstado().equals(Constantes.getFinalizado()) && p.getCargaList().size() == 1){
				
				NotificacionCargaDTO notificacion = new NotificacionCargaDTO(p.getEmpresaFk().getUsuarioFk().getNombre(), 
						p.getLugarCargaCiudad(), p.getLugarDescargaCiudad(), format.format(p.getHoraPedido()), 
						p.getCargaList().get(0).getDescripcion(), p.getId().toString(),p.getEstado());
				result.add(notificacion);
			}
		}
		return result;
	}

	@Override
	public CoordenadasDTO monitorear(BigDecimal idPedido) {
		CoordenadasDTO dto = new CoordenadasDTO();
		Pedido p = pedidoRepo.findOne(idPedido);
		Trayecto tr = trayectoRepo.findByPedido(p);
		if(tr.getEstado().equals('0')){
			dto.setLatitud("Undefined");
			dto.setLongitud("Undefined");
			return dto;
		}
		if(tr.getMonitoreo().equals('0')){
			tr.setMonitoreo('1');
			trayectoRepo.saveAndFlush(tr);
			dto.setLatitud("Undefined");
			dto.setLongitud("Undefined");
			return dto;
		}
		if (tr.getLatitud()==null && tr.getLongitud()==null){
			dto.setLatitud("Undefined");
			dto.setLongitud("Undefined");
			return dto;			
		}
		dto.setLatitud(tr.getLatitud());
		dto.setLongitud(tr.getLongitud());
		return dto;
	}

	@Override
	public void finMonitoreo(BigDecimal idPedido) {
		Pedido p = pedidoRepo.findOne(idPedido);
		Trayecto tr = trayectoRepo.findByPedido(p);
		tr.setMonitoreo('0');
		trayectoRepo.saveAndFlush(tr);
	}

	@Override
	public List<InfoCargaDTO> todosPedidos(BigDecimal idCliente) {
		List<InfoCargaDTO> result = new ArrayList<>();
		Cliente cliente = clienteRepo.findOne(idCliente);
		if(cliente == null ){
			return result;
		}
		for(Pedido p : cliente.getPedidoList()){
			if(p.getCargaList().size() == 1){
				Carga c = p.getCargaList().get(0);
				InfoCargaDTO info = new InfoCargaDTO(c.getEmbalaje(),c.getTipo(),c.getPeso().toString(),c.getAlto().toString(),
						c.getAncho().toString(),c.getLargo().toString(),p.getLugarCargaDir(),p.getLugarDescargaDir(),
						p.getLugarCargaCord(),p.getLugarDescargaCord(),p.getLugarCargaCiudad(), p.getLugarDescargaCiudad(),
						p.getAuxiliarCarga().toString(),p.getAuxiliarDescarga().toString(),p.getFechaHoraSalida().toString(),
						p.getFechaHoraLlegada().toString(),c.getDescripcion(),p.getAsegurada(),p.getPrecio().toString(),
						p.getClienteFk().getId().toString(),p.getEmpresaFk().getUsuarioFk().getNombre(),
						format.format(p.getHoraPedido()),p.getEstado(),p.getId().toString(),p.getPrecio().toString(),
						p.getEmpresaFk().getId().toString(),p.getEmpresaFk().getCalificacion().size()+"");
				result.add(info);
			}
		}
		return result;
	}

	@Override
	public Boolean calificar(String idEmpresa, String calificacion) {
		try{
			Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));
			Calificacion cal = new Calificacion(new BigDecimal(calificacion), new Date(), em);
			calificacionRepo.saveAndFlush(cal);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<PrecioDashDTO> precioDash(String idCliente) {
		List<Pedido> ped = clienteRepo.findOne(new BigDecimal(idCliente)).getPedidoList();
		
		List<PrecioDashDTO> list= new ArrayList<>();
		Calendar cale = Calendar.getInstance();
		int mes = 0;
		if(ped.size() != 0){
			cale.setTime(ped.get(0).getHoraPedido());
			mes = cale.get(Calendar.MONTH);
		}
		double valor = 0;
		for(Pedido p : ped){
			cale = Calendar.getInstance();
			cale.setTime(p.getHoraPedido());
			int month = cale.get(Calendar.MONTH);
			if(mes == month){
				valor += p.getPrecio().doubleValue();
			}
			else{
				PrecioDashDTO dto= new PrecioDashDTO(valor,""+(mes));
				list.add(dto);
				mes=month;
				valor = p.getPrecio().doubleValue();
			}
		}
		if(ped.size()!=0){
			PrecioDashDTO dto= new PrecioDashDTO(valor,""+(mes));
			list.add(dto);
		}
		
		return ordenarList(list);
	}
	
	private List<PrecioDashDTO> ordenarList(List<PrecioDashDTO> list){
		for(int i=0; i < list.size()-1; i++){
			if(i+1 == list.size()){
				break;
			}
			for(int j=i+1; j<list.size(); j++){
				if(list.get(i).getMes().equals(list.get(j).getMes())){
					PrecioDashDTO aux = list.get(i);
					aux.setPrecio(list.get(i).getPrecio()+list.get(j).getPrecio());
					list.set(i, aux);
					list.remove(j);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public List<CiudadesDashDTO> ciudadesDash(String idCliente) {
		List<CiudadesDashDTO> result = new ArrayList<>();
		List<Pedido> ped = clienteRepo.findOne(new BigDecimal(idCliente)).getPedidoList();
		HashMap<String, List<CiudadDashDTO>> map = new HashMap<>();
		for (Pedido p : ped) {
			boolean b = true;
			if (map.containsKey(p.getLugarCargaCiudad())) {
				for (CiudadDashDTO dto : map.get(p.getLugarCargaCiudad())) {
					if (dto.getCiudad().equals(p.getLugarDescargaCiudad())) {
						b = false;
						dto.setVeces(dto.getVeces() + 1);
						break;
					}
				}
				if (b) {
					List<CiudadDashDTO> l = map.get(p.getLugarCargaCiudad());
					int i = 0;
					for (String ciud : Constantes.getCiudades()) {
						if (ciud.equals(p.getLugarDescargaCiudad())) {
							break;
						}
						i++; 
					}
					CiudadDashDTO dto = new CiudadDashDTO(p.getLugarDescargaCiudad(), 1,i);
					l.add(dto);
					map.put(p.getLugarCargaCiudad(), l);
				}
			} else {
				int i = 0;
				for (String ciud : Constantes.getCiudades()) {
					if (ciud.equals(p.getLugarDescargaCiudad())) {
						break;
					}
					i++;
				}
				CiudadDashDTO dto = new CiudadDashDTO(p.getLugarDescargaCiudad(), 1,i);
				List<CiudadDashDTO> l = new ArrayList<>();
				l.add(dto);
				map.put(p.getLugarCargaCiudad(), l);
			}
		}
		for (Map.Entry<String, List<CiudadDashDTO>> entry : map.entrySet()) {
			int i = 0;
			for (String ciud : Constantes.getCiudades()) {
				if (ciud.equals(entry.getKey())) {
					break;
				}
				i++;
			}
			CiudadesDashDTO dto = new CiudadesDashDTO(entry.getKey(), entry.getValue(),i);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public GananciaDTO gastosAño(String idCliente) {
		Cliente cl = clienteRepo.findOne(new BigDecimal(idCliente));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int año = cale.get(Calendar.YEAR);
		double total = 0;
		for(Pedido p : cl.getPedidoList()){
			cale = Calendar.getInstance();
			cale.setTime(p.getHoraPedido());
			int year = cale.get(Calendar.YEAR);
			if(año == year){
				total += p.getPrecio().doubleValue();
			}
		}
		return new GananciaDTO(String.valueOf(total));
	}
	
	@Override
	public GananciaDTO gastosMes(String idCliente) {
		Cliente cl = clienteRepo.findOne(new BigDecimal(idCliente));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int mes = cale.get(Calendar.MONTH);
		double total = 0;
		for(Pedido p : cl.getPedidoList()){
			cale = Calendar.getInstance();
			cale.setTime(p.getHoraPedido());
			int month = cale.get(Calendar.MONTH);
			if(mes == month){
				total += p.getPrecio().doubleValue();
			}
		}
		return new GananciaDTO(String.valueOf(total));
	}
	
	@Override
	public GananciaDTO gastosDia(String idCliente) {
		Cliente cl = clienteRepo.findOne(new BigDecimal(idCliente));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int dia = cale.get(Calendar.DATE);
		double total = 0;
		for(Pedido p : cl.getPedidoList()){
			cale = Calendar.getInstance();
			cale.setTime(p.getHoraPedido());
			int day = cale.get(Calendar.DATE);
			if(dia == day){
				total += p.getPrecio().doubleValue();
			}
		}
		return new GananciaDTO(String.valueOf(total));
	}
}

