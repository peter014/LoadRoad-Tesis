package app.dto;

public class RegistroTransportradorDTO {

	private String correo;
	private String clave;
	private String nombre;

    private String numeroLicencia;
    private String cedula;
    private String nombreEmpresa;
    private String ciudad;

	public RegistroTransportradorDTO() {
		super();
	}


	public RegistroTransportradorDTO(String correo, String clave, String nombre, String numeroLicencia, String cedula,
			String nombreEmpresa, String ciudad) {
		super();
		this.correo = correo;
		this.clave = clave;
		this.nombre = nombre;
		this.numeroLicencia = numeroLicencia;
		this.cedula = cedula;
		this.nombreEmpresa = nombreEmpresa;
		this.ciudad = ciudad;
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

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
