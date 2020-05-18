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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
    @NamedQuery(name = "Cuentas.findById", query = "SELECT c FROM Cuentas c WHERE c.id = :id"),
    @NamedQuery(name = "Cuentas.findByNumero", query = "SELECT c FROM Cuentas c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cuentas.findByBanco", query = "SELECT c FROM Cuentas c WHERE c.banco = :banco"),
    @NamedQuery(name = "Cuentas.findByTarjetaCredito", query = "SELECT c FROM Cuentas c WHERE c.tarjetaCredito = :tarjetaCredito"),
    @NamedQuery(name = "Cuentas.findByFechaVencimiento", query = "SELECT c FROM Cuentas c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Cuentas.findByCodigoSeguridad", query = "SELECT c FROM Cuentas c WHERE c.codigoSeguridad = :codigoSeguridad")})
public class Cuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_Cuentas_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "NOMBRETARJETA")
    private String nombreTarjeta;
    @Column(name = "BANCO")
    private String banco;
    @Column(name = "TARJETA_CREDITO")
    private String tarjetaCredito;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "CODIGO_SEGURIDAD")
    private String codigoSeguridad;
    @OneToMany(mappedBy = "cuentaFk",fetch = FetchType.LAZY)
    private List<Cliente> clienteList;

    public Cuentas() {
    }

    

	public Cuentas(String numero, String nombreTarjeta, String banco, String tarjetaCredito,
			Date fechaVencimiento, String codigoSeguridad, List<Cliente> clienteList) {
		super();
		this.numero = numero;
		this.nombreTarjeta = nombreTarjeta;
		this.banco = banco;
		this.tarjetaCredito = tarjetaCredito;
		this.fechaVencimiento = fechaVencimiento;
		this.codigoSeguridad = codigoSeguridad;
		this.clienteList = clienteList;
	}



	public String getNombreTarjeta() {
		return nombreTarjeta;
	}



	public void setNombreTarjeta(String nombreTarjeta) {
		this.nombreTarjeta = nombreTarjeta;
	}



	public Cuentas(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
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
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.Cuentas[ id=" + id + " ]";
    }
    
}
