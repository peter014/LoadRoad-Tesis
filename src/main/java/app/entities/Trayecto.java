/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities;

import java.io.Serializable;
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
@Table(name = "TRAYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trayecto.findAll", query = "SELECT t FROM Trayecto t"),
    @NamedQuery(name = "Trayecto.findByLongitud", query = "SELECT t FROM Trayecto t WHERE t.longitud = :longitud"),
    @NamedQuery(name = "Trayecto.findByLatitud", query = "SELECT t FROM Trayecto t WHERE t.latitud = :latitud"),
    @NamedQuery(name = "Trayecto.findByEstado", query = "SELECT t FROM Trayecto t WHERE t.estado = :estado"),
    @NamedQuery(name = "Trayecto.findByMonitoreo", query = "SELECT t FROM Trayecto t WHERE t.monitoreo = :monitoreo"),
    @NamedQuery(name = "Trayecto.findById", query = "SELECT t FROM Trayecto t WHERE t.id = :id"),
    @NamedQuery(name = "Trayecto.findByPedido", query = "SELECT t FROM Trayecto t WHERE t.pedidoFk = :pedidoFk"),
    @NamedQuery(name = "Trayecto.findByTransportadorPedido", query = "SELECT t FROM Trayecto t "
    		+ "WHERE t.transportadorFk = :transportadorFk and t.pedidoFk = :pedidoFk")
})
public class Trayecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "LONGITUD")
    private String longitud;
    @Column(name = "LATITUD")
    private String latitud;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "MONITOREO")
    private Character monitoreo;
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_TRAYECTO_ID")
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CAMION_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Camion camionFk;
    @JoinColumn(name = "PEDIDO_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Pedido pedidoFk;
    @JoinColumn(name = "TRANSPORTADOR_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Transportador transportadorFk;

    public Trayecto() {
    }

    public Trayecto(String longitud, String latitud, Character estado, Character monitoreo, Camion camionFk,
			Pedido pedidoFk, Transportador transportadorFk) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
		this.estado = estado;
		this.monitoreo = monitoreo;
		this.camionFk = camionFk;
		this.pedidoFk = pedidoFk;
		this.transportadorFk = transportadorFk;
	}

	public Trayecto(Integer id) {
        this.id = id;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Character getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(Character monitoreo) {
        this.monitoreo = monitoreo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Camion getCamionFk() {
        return camionFk;
    }

    public void setCamionFk(Camion camionFk) {
        this.camionFk = camionFk;
    }

    public Pedido getPedidoFk() {
        return pedidoFk;
    }

    public void setPedidoFk(Pedido pedidoFk) {
        this.pedidoFk = pedidoFk;
    }

    public Transportador getTransportadorFk() {
        return transportadorFk;
    }

    public void setTransportadorFk(Transportador transportadorFk) {
        this.transportadorFk = transportadorFk;
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
        if (!(object instanceof Trayecto)) {
            return false;
        }
        Trayecto other = (Trayecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enti.Trayecto[ id=" + id + " ]";
    }
    
}
