package app.dto;

public class TransportadorDTO {

	private String cedula;
	private String nombre;
	private String idTransportador;
	
	public TransportadorDTO() {
		super();
	}

	public TransportadorDTO( String cedula, String nombre,String idTransportador) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.idTransportador = idTransportador;
	}


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdTransportador() {
		return idTransportador;
	}

	public void setIdTransportador(String idTransportador) {
		this.idTransportador = idTransportador;
	}



}
