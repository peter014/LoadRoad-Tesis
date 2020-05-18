package app.dto;

public class RegistroEmpresaDTO {

	private String correo;
	private String clave;
	private String nombre;
	private String nit;

	private String aseguradora;
	private String maximoSeguro;
	private String minimoSeguro;	
	
    private String numeroCuenta;
    private String banco;

	public RegistroEmpresaDTO() {
		super();
	}


	public RegistroEmpresaDTO(String correo, String clave, String nombre, String nit, String aseguradora,
			String maximoSeguro, String minimoSeguro, String numeroCuenta, String banco) {
		super();
		this.correo = correo;
		this.clave = clave;
		this.nombre = nombre;
		this.nit = nit;
		this.aseguradora = aseguradora;
		this.maximoSeguro = maximoSeguro;
		this.minimoSeguro = minimoSeguro;
		this.numeroCuenta = numeroCuenta;
		this.banco = banco;
	}


	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public String getMaximoSeguro() {
		return maximoSeguro;
	}

	public void setMaximoSeguro(String maximoSeguro) {
		this.maximoSeguro = maximoSeguro;
	}

	public String getMinimoSeguro() {
		return minimoSeguro;
	}

	public void setMinimoSeguro(String minimoSeguro) {
		this.minimoSeguro = minimoSeguro;
	}
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}
