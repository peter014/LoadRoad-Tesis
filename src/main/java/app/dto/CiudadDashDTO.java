package app.dto;

public class CiudadDashDTO {

	private String ciudad;
	private int idDestino; 
	private int veces;


	public CiudadDashDTO() {
		super();
	}


	public CiudadDashDTO(String ciudad, int veces, int idDestino) {
		super();
		this.ciudad = ciudad;
		this.veces = veces;
		this.idDestino = idDestino;
	}


	public int getIdDestino() {
		return idDestino;
	}


	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public int getVeces() {
		return veces;
	}


	public void setVeces(int veces) {
		this.veces = veces;
	}

}
