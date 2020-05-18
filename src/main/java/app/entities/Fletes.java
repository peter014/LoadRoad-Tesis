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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DAVID
 */
@Entity
@Table(name = "FLETES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fletes.findAll", query = "SELECT f FROM Fletes f"),
    @NamedQuery(name = "Fletes.findById", query = "SELECT f FROM Fletes f WHERE f.id = :id"),
    @NamedQuery(name = "Fletes.findByOrigen", query = "SELECT f FROM Fletes f WHERE f.origen = :origen"),
    @NamedQuery(name = "Fletes.findByDestino", query = "SELECT f FROM Fletes f WHERE f.destino = :destino"),
    @NamedQuery(name = "Fletes.findByValor", query = "SELECT f FROM Fletes f WHERE f.valor = :valor"),
    @NamedQuery(name = "Fletes.findByOrigenDestino", query = "SELECT f FROM Fletes f WHERE f.origen = :origen and f.destino = :destino")
    })
public class Fletes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ORIGEN")
    private String origen;
    @Column(name = "DESTINO")
    private String destino;
    @Column(name = "VALOR")
    private BigDecimal valor;

    public Fletes() {
    }

    public Fletes(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof Fletes)) {
            return false;
        }
        Fletes other = (Fletes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enti.Fletes[ id=" + id + " ]";
    }
    
}
