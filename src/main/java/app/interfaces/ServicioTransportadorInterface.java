package app.interfaces;

import java.math.BigDecimal;
import java.util.List;

import app.dto.CoordenadasDTO;
import app.dto.InfoCargaDTO;
import app.dto.InfoUsuarioDTO;
import app.dto.IngresoDTO;
import app.dto.RegistroTransportradorDTO;

public interface ServicioTransportadorInterface {

	public String ingresar(IngresoDTO ingreso);
	
	public Boolean registrarse(RegistroTransportradorDTO registro);
	
	public Boolean recibirCoordenadas(CoordenadasDTO coordenadas);
	
	public Boolean cambiarEstado(String idTransportador, String nuevoEstado, String idPedido);
	
	public Boolean revisarMonitoreo(String idTransportador,String idPedido);

	public List<InfoCargaDTO> detalleSolicitud(BigDecimal idTransportador);

	public InfoUsuarioDTO getInfoTransportador(String idTransportador);
}
