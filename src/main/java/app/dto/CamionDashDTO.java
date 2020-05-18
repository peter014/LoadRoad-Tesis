package app.dto;

public class CamionDashDTO {

	private String placaMarcaModelo;
	private String tipo;
	private String dimensiones;
	private String estado;
	private String ciudad;

	public CamionDashDTO() {
		super();
	}

	public CamionDashDTO(String placaMarcaModelo, String tipo, String dimensiones,String estado,String ciudad) {
		super();
		this.placaMarcaModelo = placaMarcaModelo;
		this.tipo = tipo;
		this.dimensiones = dimensiones;
		this.estado = estado;
		this.ciudad = ciudad;
	}

	public String getPlacaMarcaModelo() {
		return placaMarcaModelo;
	}

	public void setPlacaMarcaModelo(String placaMarcaModelo) {
		this.placaMarcaModelo = placaMarcaModelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
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
