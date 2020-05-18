package app.interfaces;

import java.math.BigDecimal;
import java.util.List;

import app.dto.CalificacionDashDTO;
import app.dto.CamionDTO;
import app.dto.CamionDashDTO;
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

public interface ServicioEmpresaInterface {

	public String ingresar(IngresoDTO ingreso);
	
	public Boolean registrarse(RegistroEmpresaDTO registro);
	
	public InfoCargaDTO detalleSolicitud(BigDecimal idPedido);
	
	public List<NotificacionCargaDTO> revisarSolicitudes(BigDecimal id);
	
	public InfoUsuarioDTO getInfoEmpresa(String idEmpresa);
	
	public List<CamionDTO> getCamiones(BigDecimal idEmpresa, BigDecimal idPedido);
	
	public List<TransportadorDTO> getTransportadores(BigDecimal idEmpresa, BigDecimal idPedido);
	
	public Boolean rechazarPedido(String idPedido);
	
	public Boolean aceptarPedido(String idPedido,String idTransportador,String idCamion);

	public List<CalificacionDashDTO> dashCalificacion(String idEmpresa);

	public List<PrecioDashDTO> precioDash(String idEmpresa);
	
	public List<CiudadesDashDTO> ciudadesDash(String idEmpresa);
	
	public List<CamionDashDTO> camionesDash(String idEmpresa);
	
	public List<TransportadorDashDTO> transportadoresDash(String idEmpresa);
	
	public GananciaDTO gananciasAño(String idEmpresa);
	
	public GananciaDTO gananciasMes(String idEmpresa);
	
	public GananciaDTO gananciasDia(String idEmpresa);
	
	public List<InfoCargaDTO> todosPedidos(BigDecimal idEmpresa);
}
