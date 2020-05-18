package app.dto;

public class RegistroClienteDTO {

	private String correo;
	private String clave;
	private String nombre;
	private String nit;
	
    private String numeroTarjeta;
    private String nombreTarjeta;
    private String banco;
    private String tarjetaCredito;
    private String fechaVencimiento;
    private String codigoSeguridad;
	
	
	
	
	public RegistroClienteDTO() {
		super();
	}





	public RegistroClienteDTO(String correo, String clave, String nombre, String nit, String numeroTarjeta,
			String nombreTarjeta, String banco, String tarjetaCredito, String fechaVencimiento,
			String codigoSeguridad) {
		super();
		this.correo = correo;
		this.clave = clave;
		this.nombre = nombre;
		this.nit = nit;
		this.numeroTarjeta = numeroTarjeta;
		this.nombreTarjeta = nombreTarjeta;
		this.banco = banco;
		this.tarjetaCredito = tarjetaCredito;
		this.fechaVencimiento = fechaVencimiento;
		this.codigoSeguridad = codigoSeguridad;
	}





	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}





	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}





	public String getNombreTarjeta() {
		return nombreTarjeta;
	}





	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}





	public String getBanco() {
		return banco;
	}



	public void setBanco(String banco) {
		this.banco = banco;
	}



	public String getTarjetaCredito() {
		return tarjetaCredito;
	}



	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}



	public String getFechaVencimiento() {
		return fechaVencimiento;
	}



	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}



	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}



	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
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
	
	
	
}
