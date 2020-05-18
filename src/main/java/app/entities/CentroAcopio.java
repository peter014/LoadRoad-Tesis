/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DAVID
 */
@Entity
@Table(name = "CENTROACOPIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroAcopio.findAll", query = "SELECT c FROM CentroAcopio c"),
    @NamedQuery(name = "CentroAcopio.findById", query = "SELECT c FROM CentroAcopio c WHERE c.id = :id"),
    @NamedQuery(name = "CentroAcopio.findByCiudad", query = "SELECT c FROM CentroAcopio c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "CentroAcopio.findByDireccion", query = "SELECT c FROM CentroAcopio c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CentroAcopio.findByUbicacionCord", query = "SELECT c FROM CentroAcopio c WHERE c.ubicacionCord = :ubicacionCord")})
public class CentroAcopio implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_CentroAcopio_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "UBICACION_CORD")
    private String ubicacionCord;
    @Column(name = "NUMERO_AUXILIARES")
    private Short numeroAuxiliares;
    @JoinColumn(name = "EMPRESA_FK", referencedColumnName = "ID")
    @ManyToOne//(fetch = FetchType.LAZY)
    private Empresa empresaFk;

    public CentroAcopio() {
    }

    public CentroAcopio(BigDecimal id) {
        this.id = id;
    }

    public Short getNumeroAuxiliares() {
		return numeroAuxiliares;
	}

	public void setNumeroAuxiliares(Short numeroAuxiliares) {
		this.numeroAuxiliares = numeroAuxiliares;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacionCord() {
        return ubicacionCord;
    }

    public void setUbicacionCord(String ubicacionCord) {
        this.ubicacionCord = ubicacionCord;
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
        if (!(object instanceof CentroAcopio)) {
            return false;
        }
        CentroAcopio other = (CentroAcopio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.CentroAcopio[ id=" + id + " ]";
    }
    
}
