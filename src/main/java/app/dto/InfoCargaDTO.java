package app.dto;

public class InfoCargaDTO {

	private String tipoPaquete;
	private String tipoCarga;
	private String peso;
	private String alto;
	private String ancho;
	private String largo;	
    private String tipoCamion;
    private String direccionOrigen;
    private String direccionDestino;
    private String coordenadasOrigen;
    private String coordenadasDestino;
    private String ciudadOrigen;
    private String ciudadDestino;    
    private String auxiliarCargue;
    private String auxiliarDescargue;
    private String fechaHoraCargue;
    private String fechaHoraDescargue;
    private String descripcion;
    private String asegurada;
    private String valor;
    private String idCliente;
    private String nombreEmpresa;
    private String fechaHoraPedido;
    private String estado;
    private String idPedido;
    private String idEmpresa;
    private String calificado;
    
    public InfoCargaDTO(){
    	super();
    }


	public InfoCargaDTO(String tipoPaquete, String tipoCarga, String peso, String alto, String ancho, String largo,
			String direccionOrigen, String direccionDestino, String coordenadasOrigen,String coordenadasDestino,
			String ciudadOrigen, String ciudadDestino, String auxiliarCargue,String auxiliarDescargue, String fechaCargue, 
			String fechaDescargue, String descripcion,String asegurada, String valor, String idCliente, String nombreEmpresa,
			String fechaHoraPedido,String estado, String idPedido, String tipoCamion,String idEmpresa,String calificado){
		super();
		this.tipoPaquete = tipoPaquete;
		this.tipoCarga = tipoCarga;
		this.peso = peso;
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		this.direccionOrigen = direccionOrigen;
		this.direccionDestino = direccionDestino;
		this.coordenadasOrigen = coordenadasOrigen;
		this.coordenadasDestino = coordenadasDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.auxiliarCargue = auxiliarCargue;
		this.auxiliarDescargue = auxiliarDescargue;
		this.fechaHoraCargue = fechaCargue;
		this.fechaHoraDescargue = fechaDescargue;
		this.descripcion = descripcion;
		this.asegurada = asegurada;
		this.valor = valor;
		this.idCliente = idCliente;
		this.nombreEmpresa = nombreEmpresa;
		this.fechaHoraPedido = fechaHoraPedido;
		this.estado = estado;
		this.idPedido = idPedido;
		this.tipoCamion = tipoCamion;
		this.idEmpresa = idEmpresa;
		this.calificado = calificado;
	}


	public String getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public InfoCargaDTO(String tipoPaquete, String tipoCarga,String peso, String alto,String ancho, String largo, 
			String lugarCargaDir,String lugarDescargaDir, String lugarCargaCord,String lugarDescargaCord,String lugarCargaCiudad, 
			String lugarDescargaCiudad,String auxiliarCarga,String auxiliarDescarga,String fechaHoraSalida,String fechaHoraLlegada, 
			String descripcion,String asegurada, String valor, String idCliente,String nombreEmpresa,String horaPedido,String estado, 
			String idPedido) {
		this.tipoPaquete = tipoPaquete;
		this.tipoCarga = tipoCarga;
		this.peso = peso;
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		this.direccionOrigen = lugarCargaDir;
		this.direccionDestino = lugarDescargaDir;
		this.coordenadasDestino = lugarCargaCord;
		this.coordenadasOrigen = lugarDescargaCord;
		this.ciudadOrigen = lugarCargaCiudad;
		this.ciudadDestino = lugarDescargaCiudad;
		this.auxiliarCargue = auxiliarCarga;
		this.auxiliarDescargue = auxiliarDescarga;
		this.fechaHoraCargue = fechaHoraSalida;
		this.fechaHoraDescargue = fechaHoraLlegada;
		this.descripcion = descripcion;
		this.asegurada = asegurada;
		this.valor = valor;
		this.idCliente = idCliente;
		this.nombreEmpresa = nombreEmpresa;
		this.fechaHoraPedido = horaPedido;
		this.estado = estado;
		this.idPedido = idPedido;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getCalificado() {
		return calificado;
	}


	public void setCalificado(String calificado) {
		this.calificado = calificado;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}


	public String getFechaHoraPedido() {
		return fechaHoraPedido;
	}


	public void setFechaHoraPedido(String fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getTipoCarga() {
		return tipoCarga;
	}


	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	
	public String getDireccionDestino() {
		return direccionDestino;
	}


	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public String getFechaHoraCargue() {
		return fechaHoraCargue;
	}


	public void setFechaHoraCargue(String fechaHoraCargue) {
		this.fechaHoraCargue = fechaHoraCargue;
	}


	public String getFechaHoraDescargue() {
		return fechaHoraDescargue;
	}


	public void setFechaHoraDescargue(String fechaHoraDescargue) {
		this.fechaHoraDescargue = fechaHoraDescargue;
	}


	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getCoordenadasOrigen() {
		return coordenadasOrigen;
	}

	public void setCoordenadasOrigen(String coordenadasOrigen) {
		this.coordenadasOrigen = coordenadasOrigen;
	}

	public String getCoordenadasDestino() {
		return coordenadasDestino;
	}

	public void setCoordenadasDestino(String coordenadasDestino) {
		this.coordenadasDestino = coordenadasDestino;
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

	public String getAsegurada() {
		return asegurada;
	}
	public void setAsegurada(String asegurada) {
		this.asegurada = asegurada;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipoPaquete() {
		return tipoPaquete;
	}
	public void setTipoPaquete(String tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAlto() {
		return alto;
	}
	public void setAlto(String alto) {
		this.alto = alto;
	}
	public String getAncho() {
		return ancho;
	}
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	public String getLargo() {
		return largo;
	}
	public void setLargo(String largo) {
		this.largo = largo;
	}
	public String getTipoCamion() {
		return tipoCamion;
	}
	public void setTipoCamion(String tipoCamion) {
		this.tipoCamion = tipoCamion;
	}
	public String getAuxiliarCargue() {
		return auxiliarCargue;
	}
	public void setAuxiliarCargue(String auxiliarCargue) {
		this.auxiliarCargue = auxiliarCargue;
	}
	public String getAuxiliarDescargue() {
		return auxiliarDescargue;
	}
	public void setAuxiliarDescargue(String auxiliarDescargue) {
		this.auxiliarDescargue = auxiliarDescargue;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

