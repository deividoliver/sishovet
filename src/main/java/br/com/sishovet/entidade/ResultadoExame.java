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
@Table(name = "resultado_exame")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoExame.findAll", query = "SELECT r FROM ResultadoExame r")
    , @NamedQuery(name = "ResultadoExame.findById", query = "SELECT r FROM ResultadoExame r WHERE r.id = :id")
    , @NamedQuery(name = "ResultadoExame.findByColeta", query = "SELECT r FROM ResultadoExame r WHERE r.coleta = :coleta")
    , @NamedQuery(name = "ResultadoExame.findByResultado", query = "SELECT r FROM ResultadoExame r WHERE r.resultado = :resultado")
    , @NamedQuery(name = "ResultadoExame.findByValidade", query = "SELECT r FROM ResultadoExame r WHERE r.validade = :validade")
    , @NamedQuery(name = "ResultadoExame.findByDescricao", query = "SELECT r FROM ResultadoExame r WHERE r.descricao = :descricao")
    , @NamedQuery(name = "ResultadoExame.findByValor", query = "SELECT r FROM ResultadoExame r WHERE r.valor = :valor")
    , @NamedQuery(name = "ResultadoExame.findByCusto", query = "SELECT r FROM ResultadoExame r WHERE r.custo = :custo")
    , @NamedQuery(name = "ResultadoExame.findByDesconto", query = "SELECT r FROM ResultadoExame r WHERE r.desconto = :desconto")
    , @NamedQuery(name = "ResultadoExame.findByIsento", query = "SELECT r FROM ResultadoExame r WHERE r.isento = :isento")})
public class ResultadoExame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Column(name = "coleta")
    @Temporal(TemporalType.DATE)
    private Date coleta;
    @Column(name = "resultado")
    @Temporal(TemporalType.DATE)
    private Date resultado;
    @Column(name = "validade")
    @Temporal(TemporalType.DATE)
    private Date validade;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "custo")
    private Double custo;
    @Column(name = "desconto")
    private Double desconto;
    @Column(name = "isento")
    private Boolean isento;
    @JoinColumn(name = "animal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animal;
    @JoinColumn(name = "responsavel", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsavel;
    @JoinColumn(name = "atendimento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Atendimento atendimento;
    @JoinColumn(name = "exame", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exame exame;

    public ResultadoExame() {
    }

    public ResultadoExame(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getColeta() {
        return coleta;
    }

    public void setColeta(Date coleta) {
        this.coleta = coleta;
    }

    public Date getResultado() {
        return resultado;
    }

    public void setResultado(Date resultado) {
        this.resultado = resultado;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Boolean getIsento() {
        return isento;
    }

    public void setIsento(Boolean isento) {
        this.isento = isento;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
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
        if (!(object instanceof ResultadoExame)) {
            return false;
        }
        ResultadoExame other = (ResultadoExame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sishovet.entidade.ResultadoExame[ id=" + id + " ]";
    }
    
}
