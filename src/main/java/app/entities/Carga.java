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
import javax.persistence.FetchType;
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
@Table(name = "CARGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carga.findAll", query = "SELECT c FROM Carga c"),
    @NamedQuery(name = "Carga.findById", query = "SELECT c FROM Carga c WHERE c.id = :id"),
    @NamedQuery(name = "Carga.findByDescripcion", query = "SELECT c FROM Carga c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Carga.findByValor", query = "SELECT c FROM Carga c WHERE c.valor = :valor"),
    @NamedQuery(name = "Carga.findByPeso", query = "SELECT c FROM Carga c WHERE c.peso = :peso"),
    @NamedQuery(name = "Carga.findByLargo", query = "SELECT c FROM Carga c WHERE c.largo = :largo"),
    @NamedQuery(name = "Carga.findByAncho", query = "SELECT c FROM Carga c WHERE c.ancho = :ancho"),
    @NamedQuery(name = "Carga.findByAlto", query = "SELECT c FROM Carga c WHERE c.alto = :alto"),
    @NamedQuery(name = "Carga.findByEmbalaje", query = "SELECT c FROM Carga c WHERE c.embalaje = :embalaje"),
    @NamedQuery(name = "Carga.findByTipo", query = "SELECT c FROM Carga c WHERE c.tipo = :tipo")})
public class Carga implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_CARGA_ID")
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "LARGO")
    private BigDecimal largo;
    @Column(name = "ANCHO")
    private BigDecimal ancho;
    @Column(name = "ALTO")
    private BigDecimal alto;
    @Column(name = "EMBALAJE")
    private String embalaje;
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "PEDIDO_FK", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedidoFk;

    public Carga() {
    }

    public Carga(String descripcion, BigDecimal valor, BigDecimal peso, BigDecimal largo, BigDecimal ancho,
			BigDecimal alto, String embalaje, String tipo, Pedido pedidoFk) {
		super();
		this.descripcion = descripcion;
		this.valor = valor;
		this.peso = peso;
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
		this.embalaje = embalaje;
		this.tipo = tipo;
		this.pedidoFk = pedidoFk;
	}

	public Carga(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getLargo() {
        return largo;
    }

    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }

    public BigDecimal getAncho() {
        return ancho;
    }

    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }

    public BigDecimal getAlto() {
        return alto;
    }

    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedidoFk() {
        return pedidoFk;
    }

    public void setPedidoFk(Pedido pedidoFk) {
        this.pedidoFk = pedidoFk;
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
        if (!(object instanceof Carga)) {
            return false;
        }
        Carga other = (Carga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiestesis.Carga[ id=" + id + " ]";
    }
    
}
