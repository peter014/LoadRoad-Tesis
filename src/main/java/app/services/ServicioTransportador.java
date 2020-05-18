package app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.configurations.Constantes;
import app.dto.CoordenadasDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.RegistroTransportradorDTO;
import app.entities.Carga;
import app.entities.Empresa;
import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Trayecto;
import app.entities.Usuario;
import app.interfaces.ServicioTransportadorInterface;
import app.repository.PedidoRepository;
import app.repository.TransportadorRepository;
import app.repository.TrayectoRepository;
import app.repository.UsuarioRepository;

@Service("ServicioTransportador")
@Transactional
public class ServicioTransportador implements ServicioTransportadorInterface {

	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	TransportadorRepository transportadorRepo;

	@Autowired
	TrayectoRepository trayectoRepo;

	@Autowired
	PedidoRepository pedidoRepo;

	@Override
	public String ingresar(IngresoDTO ingreso) {
		Usuario u = usuarioRepo.findUsuario(ingreso.getUsuario());
		if (u != null && u.getTransportadorList().size() == 1) {
			// desencriptar password de ambos objetos
			if (ingreso.getClave().equals(u.getClave())) {
				return u.getTransportadorList().get(0).getId().toString();
			}
		}
		return "0";
	}

	@Override
	public Boolean registrarse(RegistroTransportradorDTO registro) {
		try {
			Usuario usuario = usuarioRepo.findUsuarioByName(registro.getNombreEmpresa());
			if (usuario == null) {
				return false;
			}
			List<Empresa> list = usuario.getEmpresaList();
			Usuario nuevoUsuario = new Usuario(registro.getCorreo(), registro.getClave(), registro.getNombre(), null,
					null, null);
			Transportador nuevoTransportador = new Transportador(registro.getNumeroLicencia(), registro.getCedula(),
					nuevoUsuario, list.get(0));
				usuarioRepo.saveAndFlush(nuevoUsuario);
				transportadorRepo.saveAndFlush(nuevoTransportador);
			} catch (DataIntegrityViolationException ex) {
				return false; 
			}
			catch (Exception e) {
				return false;
			}
		return true;
	}

	@Override
	public Boolean recibirCoordenadas(CoordenadasDTO coordenadas) {
		Pedido p = pedidoRepo.findOne(new BigDecimal(coordenadas.getIdPedido()));
		Transportador trans = transportadorRepo.findOne(new BigDecimal(coordenadas.getIdTransportador()));
		Trayecto tr = trayectoRepo.findByTransportadorPedido(trans,p);
		if (tr == null || tr.getMonitoreo().equals('0')) {
			return false;
		}
		tr.setLatitud(coordenadas.getLatitud());
		tr.setLongitud(coordenadas.getLongitud());
		trayectoRepo.saveAndFlush(tr);
		return true;
	}

	@Override
	public Boolean cambiarEstado(String idTransportador, String nuevoEstado, String idPedido) {
		try {
			Pedido p = pedidoRepo.findOne(new BigDecimal(idPedido));
			Transportador trans = transportadorRepo.findOne(new BigDecimal(idTransportador));
			Trayecto tr = trayectoRepo.findByTransportadorPedido(trans,p);
			if(nuevoEstado.equals(Constantes.getEnmarcha())){
				tr.setEstado('1');
			}
			if(nuevoEstado.equals(Constantes.getFinalizado())){
				tr.setEstado('0');
				tr.setMonitoreo('0');
			}
			p.setEstado(nuevoEstado);
			pedidoRepo.saveAndFlush(p);
			trayectoRepo.saveAndFlush(tr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean revisarMonitoreo(String idTransportador,String idPedido) {
		try{
			Pedido p = pedidoRepo.findOne(new BigDecimal(idPedido));
			Transportador trans = transportadorRepo.findOne(new BigDecimal(idTransportador));
			Trayecto tr = trayectoRepo.findByTransportadorPedido(trans,p);
			if(tr.getMonitoreo().equals('0')){
				return false;
			}
			else{
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<InfoCargaDTO> detalleSolicitud(BigDecimal idTransportador) {
		List<InfoCargaDTO> result = new ArrayList<>();
		Transportador trans = transportadorRepo.findOne(idTransportador);
		if(trans == null){
			return result;
		}
		for( Trayecto tr : trans.getTrayectoList()){
			Pedido p = tr.getPedidoFk();
			if(p.getCargaList().size() == 1){
				Carga carga = p.getCargaList().get(0);
				InfoCargaDTO info= new InfoCargaDTO(carga.getEmbalaje(), carga.getTipo(),carga.getPeso().toString(),carga.getAlto().toString(),
						carga.getAncho().toString(),carga.getLargo().toString(), p.getLugarCargaDir(),p.getLugarDescargaDir(),
						p.getLugarDescargaCord(),p.getLugarCargaCord(),p.getLugarCargaCiudad(),p.getLugarDescargaCiudad(), 
						p.getAuxiliarCarga().toString(),p.getAuxiliarDescarga().toString(),p.getFechaHoraSalida().toString(),
						p.getFechaHoraLlegada().toString(),carga.getDescripcion(), p.getAsegurada(),carga.getValor().toString(),
						p.getClienteFk().getId().toString(),p.getClienteFk().getUsuarioFk().getNombre(),p.getHoraPedido().toString(), 
						p.getEstado(),p.getId().toString());
				
				result.add(info);
			}
		}
		return result;
	}

	@Override
	public InfoUsuarioDTO getInfoTransportador(String idTransportador) {
		Transportador trans = transportadorRepo.findOne(new BigDecimal(idTransportador));
		if(trans != null){
			InfoUsuarioDTO info  = new InfoUsuarioDTO(trans.getUsuarioFk().getNombre(), trans.getCedula());
			return info;
		}
		return new InfoUsuarioDTO("0", "0");
	}

}
