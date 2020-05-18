/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DAVID
 */
@Entity
@Table(name = "Pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id"),
    @NamedQuery(name = "Pedido.findByFechaHoraSalida", query = "SELECT p FROM Pedido p WHERE p.fechaHoraSalida = :fechaHoraSalida"),
    @NamedQuery(name = "Pedido.findByFechaHoraLlegada", query = "SELECT p FROM Pedido p WHERE p.fechaHoraLlegada = :fechaHoraLlegada"),
    @NamedQuery(name = "Pedido.findByLugarCargaCord", query = "SELECT p FROM Pedido p WHERE p.lugarCargaCord = :lugarCargaCord"),
    @NamedQuery(name = "Pedido.findByLugarDescargaCord", query = "SELECT p FROM Pedido p WHERE p.lugarDescargaCord = :lugarDescargaCord"),
    @NamedQuery(name = "Pedido.findByLugarCargaDir", query = "SELECT p FROM Pedido p WHERE p.lugarCargaDir = :lugarCargaDir"),
    @NamedQuery(name = "Pedido.findByLugarDescargaDir", query = "SELECT p FROM Pedido p WHERE p.lugarDescargaDir = :lugarDescargaDir"),
    @NamedQuery(name = "Pedido.findByLugarCargaCiudad", query = "SELECT p FROM Pedido p WHERE p.lugarCargaCiudad = :lugarCargaCiudad"),
    @NamedQuery(name = "Pedido.findByLugarDescargaCiudad", query = "SELECT p FROM Pedido p WHERE p.lugarDescargaCiudad = :lugarDescargaCiudad"),    
    @NamedQuery(name = "Pedido.findByAsegurada", query = "SELECT p FROM Pedido p WHERE p.asegurada = :asegurada"),
    @NamedQuery(name = "Pedido.findByEstado", query = "SELECT p FROM Pedido p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pedido.findByPrecio", query = "SELECT p FROM Pedido p WHERE p.precio = :precio")
    })
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_Pedido_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA_HORA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSalida;
    @Column(name = "FECHA_HORA_LLEGADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraLlegada;
    @Column(name = "LUGAR_CARGA_CORD")
    private String lugarCargaCord;
    @Column(name = "LUGAR_DESCARGA_CORD")
    private String lugarDescargaCord;
    @Column(name = "LUGAR_CARGA_DIR")
    private String lugarCargaDir;
    @Column(name = "LUGAR_DESCARGA_DIR")
    private String lugarDescargaDir;
    @Column(name = "LUGAR_CARGA_CIUDAD")
    private String lugarCargaCiudad;
    @Column(name = "LUGAR_DESCARGA_CIUDAD")
    private String lugarDescargaCiudad;
    @Column(name = "ASEGURADA")
    private String asegurada;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "AUXILIAR_CARGA")
    private Short auxiliarCarga;
    @Column(name = "AUXILIAR_DESCARGA")
    private Short auxiliarDescarga;
    @Column(name = "HORA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaPedido;
    @OneToMany(mappedBy = "pedidoFk"/*,fetch = FetchType.LAZY*/)
    private List<Carga> cargaList;
    @JoinColumn(name = "CLIENTE_FK", referencedColumnName = "ID")
    @ManyToOne/*(fetch = FetchType.LAZY)*/
    private Cliente clienteFk;
    @JoinColumn(name = "EMPRESA_FK", referencedColumnName = "ID")
    @ManyToOne/*(fetch = FetchType.LAZY)*/
    private Empresa empresaFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoFk")
    private List<Trayecto> trayectoList;
    

	public Pedido(Date fechaHoraSalida, Date fechaHoraLlegada, String lugarCargaCord, String lugarDescargaCord,
			String lugarCargaDir, String lugarDescargaDir, String lugarCargaCiudad, String lugarDescargaCiudad,
			String asegurada, String estado, BigDecimal precio, Short auxiliarCarga, Short auxiliarDescarga, 
			Cliente cliente, Date horaPedido) {
		super();
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.lugarCargaCord = lugarCargaCord;
		this.lugarDescargaCord = lugarDescargaCord;
		this.lugarCargaDir = lugarCargaDir;
		this.lugarDescargaDir = lugarDescargaDir;
		this.lugarCargaCiudad = lugarCargaCiudad;
		this.lugarDescargaCiudad = lugarDescargaCiudad;
		this.asegurada = asegurada;
		this.estado = estado;
		this.precio = precio;
		this.auxiliarCarga = auxiliarCarga;
		this.auxiliarDescarga = auxiliarDescarga;
		this.clienteFk = cliente;
		this.horaPedido = horaPedido;
	}

	public Pedido() {
    }

    public Pedido(BigDecimal id) {
        this.id = id;
    }

    public List<Trayecto> getTrayectoList() {
		return trayectoList;
	}

	public void setTrayectoList(List<Trayecto> trayectoList) {
		this.trayectoList = trayectoList;
	}

	public Date getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Date horaPedido) {
		this.horaPedido = horaPedido;
	}

	public Short getAuxiliarCarga() {
		return auxiliarCarga;
	}

	public void setAuxiliarCarga(Short auxiliarCarga) {
		this.auxiliarCarga = auxiliarCarga;
	}

	public Short getAuxiliarDescarga() {
		return auxiliarDescarga;
	}

	public void setAuxiliarDescarga(Short auxiliarDescarga) {
		this.auxiliarDescarga = auxiliarDescarga;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public String getLugarCargaCord() {
        return lugarCargaCord;
    }

    public void setLugarCargaCord(String lugarCargaCord) {
        this.lugarCargaCord = lugarCargaCord;
    }

    public String getLugarDescargaCord() {
        return lugarDescargaCord;
    }

    public void setLugarDescargaCord(String lugarDescargaCord) {
        this.lugarDescargaCord = lugarDescargaCord;
    }

    public String getLugarCargaDir() {
        return lugarCargaDir;
    }

    public void setLugarCargaDir(String lugarCargaDir) {
        this.lugarCargaDir = lugarCargaDir;
    }

    public String getLugarDescargaDir() {
        return lugarDescargaDir;
    }

    public void setLugarDescargaDir(String lugarDescargaDir) {
        this.lugarDescargaDir = lugarDescargaDir;
    }

    public String getLugarCargaCiudad() {
        return lugarCargaCiudad;
    }

    public void setLugarCargaCiudad(String lugarCargaCiudad) {
        this.lugarCargaCiudad = lugarCargaCiudad;
    }

    public String getLugarDescargaCiudad() {
        return lugarDescargaCiudad;
    }

    public void setLugarDescargaCiudad(String lugarDescargaCiudad) {
        this.lugarDescargaCiudad = lugarDescargaCiudad;
    }
    public String getAsegurada() {
        return asegurada;
    }

    public void setAsegurada(String asegurada) {
        this.asegurada = asegurada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Carga> getCargaList() {
        return cargaList;
    }

    public void setCargaList(List<Carga> cargaList) {
        this.cargaList = cargaList;
    }

    public Cliente getClienteFk() {
        return clienteFk;
    }

    public void setClienteFk(Cliente clienteFk) {
        this.clienteFk = clienteFk;
    }

    public Empresa getEmpresaFk() {
        return empresaFk;
    }

    public void setEmpresaFk(Empresa empresaFk) {
        this.empresaFk = empresaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.Pedido[ id=" + id + " ]";
    }
    
}
