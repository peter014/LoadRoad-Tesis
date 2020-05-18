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
 * @author sergi
 */
@Entity
@Table(name = "CAMION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findById", query = "SELECT c FROM Camion c WHERE c.id = :id"),
    @NamedQuery(name = "Camion.findByPlaca", query = "SELECT c FROM Camion c WHERE c.placa = :placa"),
    @NamedQuery(name = "Camion.findByMarca", query = "SELECT c FROM Camion c WHERE c.marca = :marca"),
    @NamedQuery(name = "Camion.findByModelo", query = "SELECT c FROM Camion c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "Camion.findByAno", query = "SELECT c FROM Camion c WHERE c.ano = :ano"),
    @NamedQuery(name = "Camion.findByTipo", query = "SELECT c FROM Camion c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Camion.findByPeso", query = "SELECT c FROM Camion c WHERE c.peso = :peso"),
    @NamedQuery(name = "Camion.findByLargo", query = "SELECT c FROM Camion c WHERE c.largo = :largo"),
    @NamedQuery(name = "Camion.findByAncho", query = "SELECT c FROM Camion c WHERE c.ancho = :ancho"),
    @NamedQuery(name = "Camion.findByAlto", query = "SELECT c FROM Camion c WHERE c.alto = :alto")})
public class Camion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idSeq") 
    @SequenceGenerator(name="idSeq", sequenceName="SEQ_CAMION_ID")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "ANO")
    private String ano;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "LARGO")
    private BigDecimal largo;
    @Column(name = "ANCHO")
    private BigDecimal ancho;
    @Column(name = "ALTO")
    private BigDecimal alto;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CIUDAD")
    private String ciudad;
    @JoinColumn(name = "EMPRESA_FK", referencedColumnName = "ID")
    @ManyToOne
    private Empresa empresaFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "camionFk")
    private List<Trayecto> trayectoList;
    
    public Camion() {
    }

    
    
    public Camion(String placa, String marca, String modelo, String ano, String tipo, BigDecimal peso, BigDecimal largo,
			BigDecimal ancho, BigDecimal alto, String estado, String ciudad) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.tipo = tipo;
		this.peso = peso;
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
		this.estado = estado;
		this.ciudad = ciudad;
	}



	public Camion(BigDecimal id) {
        this.id = id;
    }

    public List<Trayecto> getTrayectoList() {
		return trayectoList;
	}

	public void setTrayectoList(List<Trayecto> trayectoList) {
		this.trayectoList = trayectoList;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Empresa getEmpresaFk() {
        return empresaFk;
    }

    public void setEmpresaFk(Empresa empresaFk) {
        this.empresaFk = empresaFk;
    }
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tesis.Camion[ id=" + id + " ]";
    }
    
}
