package app.dto;

public class CoordenadasDTO {

	private String longitud;
	private String latitud;
	private String idTransportador;
	private String idPedido;
	
	
	
	public CoordenadasDTO(String longitud, String latitud, String idTransportador, String idPedido) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
		this.idTransportador = idTransportador;
		this.idPedido = idPedido;
	}
	public CoordenadasDTO() {
		super();
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getIdTransportador() {
		return idTransportador;
	}
	public void setIdTransportador(String idTransportador) {
		this.idTransportador = idTransportador;
	}
	
	public String getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	@Override
	public String toString() {
		return "CoordenadasDTO [longitud=" + longitud + ", latitud=" + latitud + ", idTransportador=" + idTransportador
				+ "]";
	}

	
}
