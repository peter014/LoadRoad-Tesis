package app.dto;

public class CamionDTO {

	private String placa;
	private String marca;
	private String modelo;
	private String ano;
	private String tipo;
	private String peso;
	private String largo;
	private String ancho;
	private String alto;
	private String idCamion;

	public CamionDTO() {
		super();
	}


	public CamionDTO(String placa, String marca, String modelo, String ano, String tipo, String peso, String largo,
			String ancho, String alto,String idCamion) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.tipo = tipo;
		this.peso = peso;
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
		this.idCamion = idCamion;
	}


	public String getIdCamion() {
		return idCamion;
	}


	public void setIdCamion(String idCamion) {
		this.idCamion = idCamion;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String getLargo() {
		return largo;
	}


	public void setLargo(String largo) {
		this.largo = largo;
	}


	public String getAncho() {
		return ancho;
	}


	public void setAncho(String ancho) {
		this.ancho = ancho;
	}


	public String getAlto() {
		return alto;
	}


	public void setAlto(String alto) {
		this.alto = alto;
	}


	
	
}
