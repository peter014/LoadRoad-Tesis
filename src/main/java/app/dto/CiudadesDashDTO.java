package app.dto;

import java.util.List;

public class CiudadesDashDTO {

	private String origen;
	private int idOrigen; 
	private List<CiudadDashDTO> destinos;
	
	
	public CiudadesDashDTO() {
		super();
	}
	public CiudadesDashDTO(String ciudad, List<CiudadDashDTO> ciudades,int idOrigen) {
		super();
		this.origen = ciudad;
		this.destinos = ciudades;
		this.idOrigen = idOrigen;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public List<CiudadDashDTO> getDestinos() {
		return destinos;
	}
	public void setDestinos(List<CiudadDashDTO> destinos) {
		this.destinos = destinos;
	}
	public int getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(int idOrigen) {
		this.idOrigen = idOrigen;
	}

}
