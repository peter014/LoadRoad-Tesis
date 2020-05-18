package app.dto;

public class NotificacionCargaDTO {

	private String nombreEmpresa;
    private String ciudadOrigen;
    private String ciudadDestino;  
    private String fechaHoraPedido;
    private String descripcion;
    private String idPedido;
    private String estado;
    
    public NotificacionCargaDTO(){
    	super();
    }


	public NotificacionCargaDTO(String nombreEmpresa, String ciudadOrigen, String ciudadDestino, String fechaHoraPedido,
			String descripcion, String idPedido, String estado) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.fechaHoraPedido = fechaHoraPedido;
		this.descripcion = descripcion;
		this.idPedido = idPedido;
		this.estado = estado;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFechaHoraPedido() {
		return fechaHoraPedido;
	}


	public void setFechaHoraPedido(String fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}


	public String getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}


	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

