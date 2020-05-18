package app.dto;

public class InfoUsuarioDTO {

	private String nombre;
	private String nit;

	public InfoUsuarioDTO() {
		super();
	}
	
	public InfoUsuarioDTO(String nombre, String nit) {
		super();
		this.nombre = nombre;
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}

	
	
}
