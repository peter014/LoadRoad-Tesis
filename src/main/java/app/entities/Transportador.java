/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 *
 * @author DAVID
 */
@Entity
@Table(name = "Transportador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportador.findAll", query = "SELECT t FROM Transportador t"),
    @NamedQuery(name = "Transportador.findById", query = "SELECT t FROM Transportador t WHERE t.id = :id"),
    @NamedQuery(name = "Transportador.findByNumeroLicencia", query = "SELECT t FROM Transportador t WHERE t.numeroLicencia = :numeroLicencia"),
    @NamedQuery(name = "Transportador.findByCedula", query = "SELECT t FROM Transportador t WHERE t.cedula = :cedula"),
    @NamedQuery(name = "Transportador.findByEstado", query = "SELECT t FROM Transportador t WHERE t.estado = :estado")})
public class Transportador implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_Transportador_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NUMERO_LICENCIA")
    private String numeroLicencia;
    @Column(name = "CEDULA")
    private String cedula;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CIUDAD")
    private String ciudad;
    @JoinColumn(name = "EMPRESA_FK", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa empresaFk;
    @JoinColumn(name = "USUARIO_FK", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transportadorFk")
    private List<Trayecto> trayectoList;
    
    public Transportador() {
    }

    
    public Transportador(String numeroLicencia, String cedula,Usuario usuarioFk,Empresa empresaFk) {
		super();
		this.numeroLicencia = numeroLicencia;
		this.cedula = cedula;
		this.usuarioFk = usuarioFk;
		this.empresaFk = empresaFk;
	}


    public Transportador(String numeroLicencia, String cedula, String estado, String ciudad, Usuario usuarioFk) {
		super();
		this.numeroLicencia = numeroLicencia;
		this.cedula = cedula;
		this.estado = estado;
		this.ciudad = ciudad;
		this.usuarioFk = usuarioFk;
	}


	public List<Trayecto> getTrayectoList() {
		return trayectoList;
	}


	public void setTrayectoList(List<Trayecto> trayectoList) {
		this.trayectoList = trayectoList;
	}


	public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
	public Transportador(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getEmpresaFk() {
        return empresaFk;
    }

    public void setEmpresaFk(Empresa empresaFk) {
        this.empresaFk = empresaFk;
    }

    public Usuario getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(Usuario usuarioFk) {
        this.usuarioFk = usuarioFk;
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
        if (!(object instanceof Transportador)) {
            return false;
        }
        Transportador other = (Transportador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.Transportador[ id=" + id + " ]";
    }
    
}
