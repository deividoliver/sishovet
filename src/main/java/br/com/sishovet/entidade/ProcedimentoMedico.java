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
import javax.persistence.Lob;
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
@Table(name = "procedimento_medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedimentoMedico.findAll", query = "SELECT p FROM ProcedimentoMedico p")
    , @NamedQuery(name = "ProcedimentoMedico.findById", query = "SELECT p FROM ProcedimentoMedico p WHERE p.id = :id")
    , @NamedQuery(name = "ProcedimentoMedico.findByNome", query = "SELECT p FROM ProcedimentoMedico p WHERE p.nome = :nome")
    , @NamedQuery(name = "ProcedimentoMedico.findByDia", query = "SELECT p FROM ProcedimentoMedico p WHERE p.dia = :dia")
    , @NamedQuery(name = "ProcedimentoMedico.findByHashCode", query = "SELECT p FROM ProcedimentoMedico p WHERE p.hashCode = :hashCode")})
public class ProcedimentoMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dia;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hash_code")
    private int hashCode;
    @JoinColumn(name = "atendimento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atendimento atendimento;
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public ProcedimentoMedico() {
    }

    public ProcedimentoMedico(Integer id) {
        this.id = id;
    }

    public ProcedimentoMedico(Integer id, String nome, Date dia, String descricao, int hashCode) {
        this.id = id;
        this.nome = nome;
        this.dia = dia;
        this.descricao = descricao;
        this.hashCode = hashCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof ProcedimentoMedico)) {
            return false;
        }
        ProcedimentoMedico other = (ProcedimentoMedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sishovet.entidade.ProcedimentoMedico[ id=" + id + " ]";
    }
    
}
