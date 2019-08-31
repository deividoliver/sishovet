/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.entidade;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GreenHand
 */
@Entity
@Table(name = "atendimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atendimento.findAll", query = "SELECT a FROM Atendimento a")
    , @NamedQuery(name = "Atendimento.findById", query = "SELECT a FROM Atendimento a WHERE a.id = :id")
    , @NamedQuery(name = "Atendimento.findByDia", query = "SELECT a FROM Atendimento a WHERE a.dia = :dia")
    , @NamedQuery(name = "Atendimento.findByRetorno", query = "SELECT a FROM Atendimento a WHERE a.retorno = :retorno")
    , @NamedQuery(name = "Atendimento.findByCusto", query = "SELECT a FROM Atendimento a WHERE a.custo = :custo")
    , @NamedQuery(name = "Atendimento.findByStatus", query = "SELECT a FROM Atendimento a WHERE a.status = :status")})
public class Atendimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Column(name = "retorno")
    @Temporal(TemporalType.DATE)
    private Date retorno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "custo")
    private Double custo;
    @Column(name = "status")
    private Character status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atendimento")
    private List<Vacina> vacinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atendimento")
    private List<ProcedimentoMedico> procedimentoMedicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atendimento")
    private List<ResultadoExame> resultadoExameList;
    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;
    @JoinColumn(name = "proprietario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proprietario proprietario;

    public Atendimento() {
    }

    public Atendimento(Integer id) {
        this.id = id;
    }

    public Atendimento(Integer id, Date dia) {
        this.id = id;
        this.dia = dia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getRetorno() {
        return retorno;
    }

    public void setRetorno(Date retorno) {
        this.retorno = retorno;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @XmlTransient
    public List<Vacina> getVacinaList() {
        return vacinaList;
    }

    public void setVacinaList(List<Vacina> vacinaList) {
        this.vacinaList = vacinaList;
    }

    @XmlTransient
    public List<ProcedimentoMedico> getProcedimentoMedicoList() {
        return procedimentoMedicoList;
    }

    public void setProcedimentoMedicoList(List<ProcedimentoMedico> procedimentoMedicoList) {
        this.procedimentoMedicoList = procedimentoMedicoList;
    }

    @XmlTransient
    public List<ResultadoExame> getResultadoExameList() {
        return resultadoExameList;
    }

    public void setResultadoExameList(List<ResultadoExame> resultadoExameList) {
        this.resultadoExameList = resultadoExameList;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
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
        if (!(object instanceof Atendimento)) {
            return false;
        }
        Atendimento other = (Atendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sishovet.entidade.Atendimento[ id=" + id + " ]";
    }
    
}
