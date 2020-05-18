package app.dto;

import java.math.BigDecimal;

public class UsuarioDTO {

    private BigDecimal id;
    private String usuario;
    private String clave;
    private String nombre;
    
    
    
	public UsuarioDTO(BigDecimal id, String usuario, String clave, String nombre) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    
    
}
