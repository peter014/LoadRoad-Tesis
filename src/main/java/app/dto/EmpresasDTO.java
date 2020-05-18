package app.dto;

import java.math.BigDecimal;

public class EmpresasDTO {

	private String nombre;
	private String camion;
	private float calificacion;
	private String distancia;
	private String precio;
	private BigDecimal id;
	
	public EmpresasDTO(String nombre, String camion,float calificacion, String distancia, String precio,BigDecimal id) {
		super();
		this.nombre = nombre;
		this.camion = camion;
		this.calificacion = calificacion;
		this.distancia = distancia;	
		this.precio = precio;
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EmpresasDTO [calificacion=" + calificacion + ", distancia=" + distancia + "]";
	}
	
	
}
