package app.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.configurations.Constantes;
import app.dto.CalificacionDashDTO;
import app.dto.CamionDTO;
import app.dto.CamionDashDTO;
import app.dto.CiudadDashDTO;
import app.dto.CiudadesDashDTO;
import app.dto.GananciaDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.PrecioDashDTO;
import app.dto.RegistroEmpresaDTO;
import app.dto.TransportadorDTO;
import app.dto.TransportadorDashDTO;
import app.entities.Calificacion;
import app.entities.Camion;
import app.entities.Carga;
import app.entities.Empresa;
import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Trayecto;
import app.entities.Usuario;
import app.interfaces.ServicioEmpresaInterface;
import app.repository.CamionRepository;
import app.repository.EmpresaRepository;
import app.repository.PedidoRepository;
import app.repository.TransportadorRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;

@Service("ServicioEmpresa")
@Transactional
public class ServicioEmpresa implements ServicioEmpresaInterface {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	EmpresaRepository empresaRepo;

	@Autowired
	PedidoRepository pedidoRepo;

	@Autowired
	TrayectoRepository trayectoRepo;

	@Autowired
	TransportadorRepository transportadorRepo;

	@Autowired
	CamionRepository camionRepo;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Override
	public String ingresar(IngresoDTO ingreso) {
		Usuario u = usuarioRepo.findUsuario(ingreso.getUsuario());
		if (u != null && u.getEmpresaList().size() == 1) {
			// desencriptar password de ambos objetos
			if (ingreso.getClave().equals(u.getClave())) {
				return u.getEmpresaList().get(0).getId().toString();
			}
		}
		return "0";
	}

	@Override
	public Boolean registrarse(RegistroEmpresaDTO registro) {
		Usuario nuevoUsuario = new Usuario(registro.getCorreo(), registro.getClave(), registro.getNombre(), null, null,
				null);
		Empresa nuevaEmpresa = new Empresa(registro.getNit(), registro.getAseguradora(),
				new BigDecimal(registro.getMaximoSeguro()), new BigDecimal(registro.getMinimoSeguro()),
				registro.getNumeroCuenta(), registro.getBanco(), nuevoUsuario,new BigDecimal(1.5) );
		// create centro acopio
		try {
			usuarioRepo.saveAndFlush(nuevoUsuario);
		} catch (DataIntegrityViolationException ex) {
			ex.printStackTrace();
			return false;
		}
		empresaRepo.saveAndFlush(nuevaEmpresa);
		return true;
	}

	@Override
	public InfoCargaDTO detalleSolicitud(BigDecimal idPedido) {
		Pedido p = pedidoRepo.findOne(idPedido);
		InfoCargaDTO info = new InfoCargaDTO();
		if (p.getEstado().equals(Constantes.getPendiente()) && p.getCargaList().size() == 1) {
			Carga carga = p.getCargaList().get(0);
			info = new InfoCargaDTO(carga.getEmbalaje(), carga.getTipo(), carga.getPeso().toString(),
					carga.getAlto().toString(), carga.getAncho().toString(), carga.getLargo().toString(),
					p.getLugarCargaDir(), p.getLugarDescargaDir(), p.getLugarCargaCord(), p.getLugarDescargaCord(),
					p.getLugarCargaCiudad(), p.getLugarDescargaCiudad(), p.getAuxiliarCarga().toString(),
					p.getAuxiliarDescarga().toString(), p.getFechaHoraSalida().toString(),
					p.getFechaHoraLlegada().toString(), carga.getDescripcion(), p.getAsegurada(),
					carga.getValor().toString(), p.getClienteFk().getId().toString(),
					p.getClienteFk().getUsuarioFk().getNombre(), p.getHoraPedido().toString(), p.getEstado(),
					p.getId().toString());
			Camion camionSugerido = null;
			for (Camion ca : p.getEmpresaFk().getCamionList()) {
				if (confirmarCamion(ca, info)) {
					camionSugerido = ca;
					break;
				}
			}
			if (camionSugerido != null) {
				info.setTipoCamion(
						camionSugerido.getMarca() + " " + camionSugerido.getModelo() + " " + camionSugerido.getAno());
			} else {
				info = new InfoCargaDTO();
			}
		}
		return info;
	}

	private boolean confirmarCamion(Camion camion, InfoCargaDTO buscarDto) {
		long peso = Long.parseLong(buscarDto.getPeso());
		long alto = Long.parseLong(buscarDto.getAlto());
		long ancho = Long.parseLong(buscarDto.getAncho());
		long largo = Long.parseLong(buscarDto.getLargo());

		if (camion.getPeso().longValue() >= peso && camion.getAlto().longValue() >= alto
				&& camion.getAncho().longValue() >= ancho && camion.getLargo().longValue() >= largo
				&& camion.getEstado().equals(Constantes.getDisponible())
				&& buscarDto.getCiudadOrigen().equals(camion.getCiudad())) {
			return true;
		}
		return false;
	}

	@Override
	public InfoUsuarioDTO getInfoEmpresa(String idEmpresa) {
		Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));
		if (em != null) {
			return new InfoUsuarioDTO(em.getUsuarioFk().getNombre(), em.getNit());
		}
		return new InfoUsuarioDTO("0", "0");
	}

	@Override
	public List<NotificacionCargaDTO> revisarSolicitudes(BigDecimal id) {
		List<NotificacionCargaDTO> result = new ArrayList<>();
		Empresa empresa = empresaRepo.findOne(id);
		for (Pedido p : empresa.getPedidoList()) {
			if ((p.getEstado().equals(Constantes.getPendiente()) || p.getEstado().equals(Constantes.getAceptado())
					|| p.getEstado().equals(Constantes.getEnmarcha())) && p.getCargaList().size() == 1) {
				NotificacionCargaDTO notificacion = new NotificacionCargaDTO(
						p.getClienteFk().getUsuarioFk().getNombre(), p.getLugarCargaCiudad(),
						p.getLugarDescargaCiudad(), format.format(p.getHoraPedido()),
						p.getCargaList().get(0).getDescripcion(), p.getId().toString(),"");
				if( p.getEstado().equals(Constantes.getEnmarcha())){
					notificacion.setEstado("Marcha");
				}
				else{
					notificacion.setEstado(p.getEstado());
				}
				result.add(notificacion);
			}
		}
		return result;
	}

	@Override
	public List<CamionDTO> getCamiones(BigDecimal idEmpresa, BigDecimal idPedido) {
		List<CamionDTO> camiones = new ArrayList<>();
		Pedido p = pedidoRepo.findOne(idPedido);
		Empresa empresa = empresaRepo.findOne(idEmpresa);
		for (Camion camion : empresa.getCamionList()) {
			if (camion.getEstado().equals(Constantes.getDisponible())
					&& p.getLugarCargaCiudad().equals(camion.getCiudad())) {
				CamionDTO cam = new CamionDTO(camion.getPlaca(), camion.getMarca(), camion.getModelo(), camion.getAno(),
						camion.getTipo(), camion.getPeso().toString(), camion.getLargo().toString(),
						camion.getAncho().toString(), camion.getAlto().toString(), camion.getId().toString());
				camiones.add(cam);
			}
		}
		return camiones;
	}

	@Override
	public List<TransportadorDTO> getTransportadores(BigDecimal idEmpresa, BigDecimal idPedido) {
		List<TransportadorDTO> transportadores = new ArrayList<>();
		Pedido p = pedidoRepo.findOne(idPedido);
		Empresa empresa = empresaRepo.findOne(idEmpresa);
		for (Transportador t : empresa.getTransportadorList()) {
			if (t.getEstado().equals(Constantes.getDisponible()) && p.getLugarCargaCiudad().equals(t.getCiudad())) {
				TransportadorDTO tansportador = new TransportadorDTO(t.getCedula(), t.getUsuarioFk().getNombre(),
						t.getId().toString());
				transportadores.add(tansportador);
			}
		}
		return transportadores;
	}

	@Override
	public Boolean rechazarPedido(String idPedido) {
		try {
			Pedido p = pedidoRepo.findOne(new BigDecimal(idPedido));
			p.setEstado(Constantes.getRechazado());
			pedidoRepo.saveAndFlush(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean aceptarPedido(String idPedido, String idTransportador, String idCamion) {
		try {
			Pedido p = pedidoRepo.findOne(new BigDecimal(idPedido));
			Transportador trans = transportadorRepo.findOne(new BigDecimal(idTransportador));
			Camion cam = camionRepo.findOne(new BigDecimal(idCamion));
			String[] coord = p.getLugarCargaCord().split(",");
			Trayecto trayecto = new Trayecto(coord[1], coord[0], '0', '0', cam, p, trans);
			trayectoRepo.saveAndFlush(trayecto);
			p.setEstado(Constantes.getAceptado());
			pedidoRepo.saveAndFlush(p);
			trans.setEstado(Constantes.getOcupado());
			transportadorRepo.saveAndFlush(trans);
			cam.setEstado(Constantes.getOcupado());
			camionRepo.saveAndFlush(cam);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CalificacionDashDTO> dashCalificacion(String idEmpresa) {
		Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));

		List<CalificacionDashDTO> list = new ArrayList<>();
		float calificacion = 0;
		Calendar cale = Calendar.getInstance();
		int mes = 0;
		if (em.getCalificacion().size() != 0) {
			cale.setTime(em.getCalificacion().get(0).getFecha());
			mes = cale.get(Calendar.MONTH);
		}
		int cont = 0;
		for (Calificacion cal : em.getCalificacion()) {
			cale = Calendar.getInstance();
			cale.setTime(cal.getFecha());
			int month = cale.get(Calendar.MONTH);
			if (mes == month) {
				calificacion += cal.getCalificacion().floatValue();
				cont++;
			} else {
				CalificacionDashDTO dto = new CalificacionDashDTO((calificacion / cont), "" + (mes));
				list.add(dto);
				mes = month;
				calificacion = cal.getCalificacion().floatValue();
				cont = 1;
			}
		}
		if (em.getCalificacion().size() != 0) {
			CalificacionDashDTO dto = new CalificacionDashDTO( (calificacion / cont), "" + (mes));
			list.add(dto);
		}
		return ordenarListCalificacion(list);
	}
	private List<CalificacionDashDTO> ordenarListCalificacion(List<CalificacionDashDTO> list){
		for(int i=0; i < list.size()-1; i++){
			if(i+1 == list.size()){
				break;
			}
			for(int j=i+1; j<list.size(); j++){
				if(list.get(i).getMes().equals(list.get(j).getMes())){
					CalificacionDashDTO aux = list.get(i);
					aux.setCalificacion((list.get(i).getCalificacion()+list.get(j).getCalificacion())/2);
					list.set(i, aux);
					list.remove(j);
				}
			}
		}
		return list;
	}

	@Override
	public List<PrecioDashDTO> precioDash(String idEmpresa) {
		List<Pedido> ped = empresaRepo.findOne(new BigDecimal(idEmpresa)).getPedidoList();

		List<PrecioDashDTO> list = new ArrayList<>();
		Calendar cale = Calendar.getInstance();
		int mes = 0;
		if (ped.size() != 0) {
			cale.setTime(ped.get(0).getHoraPedido());
			mes = cale.get(Calendar.MONTH);
		}
		double valor = 0;
		for (Pedido p : ped) {
			cale = Calendar.getInstance();
			cale.setTime(p.getHoraPedido());
			int month = cale.get(Calendar.MONTH);
			if (mes == month) {
				valor += p.getPrecio().doubleValue();
			} else {
				PrecioDashDTO dto = new PrecioDashDTO(valor, "" + (mes));
				list.add(dto);
				mes = month;
				valor = p.getPrecio().doubleValue();
			}
		}
		if (ped.size() != 0) {
			PrecioDashDTO dto = new PrecioDashDTO(valor, "" + (mes ));
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
	public List<InfoCargaDTO> todosPedidos(BigDecimal idEmpresa) {
		List<InfoCargaDTO> result = new ArrayList<>();
		Empresa empresa = empresaRepo.findOne(idEmpresa);
		if(empresa == null ){
			return result;
		}
		for(Pedido p : empresa.getPedidoList()){
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
	public List<CiudadesDashDTO> ciudadesDash(String idEmpresa) {
		List<CiudadesDashDTO> result = new ArrayList<>();
		List<Pedido> ped = empresaRepo.findOne(new BigDecimal(idEmpresa)).getPedidoList();
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
					CiudadDashDTO dto = new CiudadDashDTO(p.getLugarDescargaCiudad(), 1, i);
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
				CiudadDashDTO dto = new CiudadDashDTO(p.getLugarDescargaCiudad(), 1, i);
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
	public List<CamionDashDTO> camionesDash(String idEmpresa) {
		List<CamionDashDTO> result = new ArrayList<>();
		Empresa empresa = empresaRepo.findOne(new BigDecimal(idEmpresa));
		if (empresa == null) {
			return result;
		}
		List<Camion> camiones = empresa.getCamionList();
		for (Camion ca : camiones) {
			CamionDashDTO dto = new CamionDashDTO(ca.getPlaca() + " " + ca.getMarca() + " " + ca.getModelo(),
					ca.getTipo(), ca.getAlto() + "x" + ca.getLargo() + "x" + ca.getAncho(), ca.getEstado(),
					ca.getCiudad());
			result.add(dto);
		}

		return result;
	}

	@Override
	public List<TransportadorDashDTO> transportadoresDash(String idEmpresa) {
		List<TransportadorDashDTO> result = new ArrayList<>();
		Empresa empresa = empresaRepo.findOne(new BigDecimal(idEmpresa));
		if (empresa == null) {
			return result;
		}
		List<Transportador> trans = empresa.getTransportadorList();
		for (Transportador tra : trans) {
			TransportadorDashDTO dto = new TransportadorDashDTO(tra.getCedula(), tra.getUsuarioFk().getNombre(),
					tra.getEstado(), tra.getCiudad());
			result.add(dto);
		}

		return result;
	}

	@Override
	public GananciaDTO gananciasAño(String idEmpresa) {
		Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int año = cale.get(Calendar.YEAR);
		double total = 0;
		for(Pedido p : em.getPedidoList()){
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
	public GananciaDTO gananciasMes(String idEmpresa) {
		Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int mes = cale.get(Calendar.MONTH);
		double total = 0;
		for(Pedido p : em.getPedidoList()){
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
	public GananciaDTO gananciasDia(String idEmpresa) {
		Empresa em = empresaRepo.findOne(new BigDecimal(idEmpresa));
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date());
		int dia = cale.get(Calendar.DATE);
		double total = 0;
		for(Pedido p : em.getPedidoList()){
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
