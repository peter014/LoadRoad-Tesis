package app.dto;

public class TransportadorDashDTO {

	private String cedula;
	private String nombre;
	private String estado;
	private String ciudad;
	
	public TransportadorDashDTO() {
		super();
	}

	public TransportadorDashDTO( String cedula, String nombre,String estado,String ciudad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.estado = estado;
		this.ciudad = ciudad;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
