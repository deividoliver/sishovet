/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.entidade;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GreenHand
 */
@Entity
@Table(name = "triagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Triagem.findAll", query = "SELECT t FROM Triagem t")
    , @NamedQuery(name = "Triagem.findById", query = "SELECT t FROM Triagem t WHERE t.id = :id")
    , @NamedQuery(name = "Triagem.findByPeso", query = "SELECT t FROM Triagem t WHERE t.peso = :peso")
    , @NamedQuery(name = "Triagem.findByPatologia", query = "SELECT t FROM Triagem t WHERE t.patologia = :patologia")
    , @NamedQuery(name = "Triagem.findByDia", query = "SELECT t FROM Triagem t WHERE t.dia = :dia")})
public class Triagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private double peso;
    @Size(max = 500)
    @Column(name = "patologia")
    private String patologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dia;
    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;

    public Triagem() {
    }

    public Triagem(Integer id) {
        this.id = id;
    }

    public Triagem(Integer id, double peso, Date dia) {
        this.id = id;
        this.peso = peso;
        this.dia = dia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
        if (!(object instanceof Triagem)) {
            return false;
        }
        Triagem other = (Triagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sishovet.entidade.Triagem[ id=" + id + " ]";
    }
    
}
