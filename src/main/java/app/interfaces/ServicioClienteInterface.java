package app.interfaces;

import java.math.BigDecimal;
import java.util.List;

import app.dto.CiudadesDashDTO;
import app.dto.CoordenadasDTO;
import app.dto.EmpresasPedidoDTO;
import app.dto.GananciaDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.NotificacionCargaDTO;
import app.dto.PrecioDashDTO;
import app.dto.RegistroClienteDTO;

public interface ServicioClienteInterface {
	
	public InfoUsuarioDTO getInfoCliente(String idCliente);

	public String ingresar(IngresoDTO ingreso);
	
	public Boolean registrarse(RegistroClienteDTO registro);
	
	public EmpresasPedidoDTO buscarEmpresa(InfoCargaDTO buscar);
	
	public Boolean seleccionarEmpresa(String idPedido, String idEmpresa);
	
	public List<NotificacionCargaDTO> revisarSolicitudes(BigDecimal id);
	
	public List<InfoCargaDTO> todosPedidos(BigDecimal idCliente);
	
	public CoordenadasDTO monitorear(BigDecimal idPedido);
	
	public void finMonitoreo(BigDecimal idPedido);
	
	public Boolean calificar(String idEmpresa,String calificacion);
	
	public List<PrecioDashDTO> precioDash(String idCliente);
	
	public List<CiudadesDashDTO> ciudadesDash(String idCliente);
	
	public GananciaDTO gastosAño(String idCliente);
	
	public GananciaDTO gastosMes(String idCliente);
	
	public GananciaDTO gastosDia(String idCliente);
}
