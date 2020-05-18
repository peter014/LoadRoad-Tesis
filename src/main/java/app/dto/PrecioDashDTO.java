package app.dto;

public class PrecioDashDTO {

	private double precio;
	private String mes;


	public PrecioDashDTO() {
		super();
	}


	public PrecioDashDTO(double precio, String mes) {
		super();
		this.precio = precio;
		this.mes = mes;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}

}
