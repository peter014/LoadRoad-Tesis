package app.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DAVID
 */
@Entity
@Table(name = "Empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findById", query = "SELECT e FROM Empresa e WHERE e.id = :id"),
    @NamedQuery(name = "Empresa.findByNit", query = "SELECT e FROM Empresa e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empresa.findByAseguradora", query = "SELECT e FROM Empresa e WHERE e.aseguradora = :aseguradora"),
    @NamedQuery(name = "Empresa.findByValorMaximoSeguro", query = "SELECT e FROM Empresa e WHERE e.valorMaximoSeguro = :valorMaximoSeguro"),
    @NamedQuery(name = "Empresa.findByValorMinimoSeguro", query = "SELECT e FROM Empresa e WHERE e.valorMinimoSeguro = :valorMinimoSeguro")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_Empresa_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "ASEGURADORA")
    private String aseguradora;
    @Column(name = "VALOR_MAXIMO_SEGURO")
    private BigDecimal valorMaximoSeguro;
    @Column(name = "VALOR_MINIMO_SEGURO")
    private BigDecimal valorMinimoSeguro;
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Column(name = "BANCO")
    private String banco;
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @OneToMany(mappedBy = "empresaFk")
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "empresaFk")
    private List<Transportador> transportadorList;
    @OneToMany(mappedBy = "empresaFk")
    private List<Camion> camionList;
    @JoinColumn(name = "USUARIO_FK", referencedColumnName = "ID")
    @ManyToOne
    private Usuario usuarioFk;
    @OneToMany(mappedBy = "empresaFk"/*,fetch = FetchType.LAZY*/)
    private List<Pedido> pedidoList;
    @OneToMany(mappedBy = "empresaFk"/*,fetch = FetchType.LAZY*/)
    private List<CentroAcopio> centroAcopioList;

    public Empresa() {
    }
    

	public Empresa(String nit, String aseguradora, BigDecimal valorMaximoSeguro, BigDecimal valorMinimoSeguro,
			String numeroCuenta, String banco, Usuario usuarioFk, BigDecimal porcentaje) {
		super();
		this.nit = nit;
		this.aseguradora = aseguradora;
		this.valorMaximoSeguro = valorMaximoSeguro;
		this.valorMinimoSeguro = valorMinimoSeguro;
		this.numeroCuenta = numeroCuenta;
		this.banco = banco;
		this.usuarioFk = usuarioFk;
		this.porcentaje = porcentaje;
	}


	public Empresa(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}


	public List<Calificacion> getCalificacionList() {
		return calificacionList;
	}


	public void setCalificacionList(List<Calificacion> calificacionList) {
		this.calificacionList = calificacionList;
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

    public BigDecimal getValorMaximoSeguro() {
        return valorMaximoSeguro;
    }

    public void setValorMaximoSeguro(BigDecimal valorMaximoSeguro) {
        this.valorMaximoSeguro = valorMaximoSeguro;
    }

    public BigDecimal getValorMinimoSeguro() {
        return valorMinimoSeguro;
    }

    public void setValorMinimoSeguro(BigDecimal valorMinimoSeguro) {
        this.valorMinimoSeguro = valorMinimoSeguro;
    }

    @XmlTransient
    public List<Transportador> getTransportadorList() {
        return transportadorList;
    }

    public void setTransportadorList(List<Transportador> transportadorList) {
        this.transportadorList = transportadorList;
    }

    public Usuario getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(Usuario usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    @XmlTransient
    public List<Camion> getCamionList() {
        return camionList;
    }

    public void setCamionList(List<Camion> camionList) {
        this.camionList = camionList;
    }
    
    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @XmlTransient
    public List<CentroAcopio> getCentroAcopioList() {
        return centroAcopioList;
    }

    public void setCentroAcopioList(List<CentroAcopio> centroAcopioList) {
        this.centroAcopioList = centroAcopioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getNumeroCuenta() {
		return numeroCuenta;
	}


	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public List<Calificacion> getCalificacion() {
		return calificacionList;
	}


	public void setCalificacion(List<Calificacion> calificacion) {
		this.calificacionList = calificacion;
	}


	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.Empresa[ id=" + id + " ]";
    }
    
}