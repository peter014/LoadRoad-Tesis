package app.dto;

public class CalificacionDashDTO {

	private double calificacion;
	private String mes;


	public CalificacionDashDTO() {
		super();
	}


	public CalificacionDashDTO(double calificacion, String mes) {
		super();
		this.calificacion = calificacion;
		this.mes = mes;
	}


	public double getCalificacion() {
		return calificacion;
	}


	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}

}
