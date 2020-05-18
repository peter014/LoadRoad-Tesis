package app.dto;


public class IngresoDTO {

	private String usuario;
	private String clave;
	
	
	
	public IngresoDTO() {
		super();
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public String toString() {
		return "IngresoDTO [usuario=" + usuario + ", clave=" + clave + "]";
	}
	public IngresoDTO(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}

	
}
