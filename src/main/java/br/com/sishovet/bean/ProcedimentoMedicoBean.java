/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.ProcedimentoMedico;
import br.com.sishovet.entidade.tipo.TipoStatusAtendimento;
import br.com.sishovet.rn.AtendimentoRN;
import br.com.sishovet.rn.ProcedimentoMedicoRN;
import br.com.sishovet.util.UtilBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author GreenHand
 */
@ManagedBean
@ViewScoped
public class ProcedimentoMedicoBean {
    
    private ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
    private Atendimento atendimento = new Atendimento();
    private Animal animal = new Animal();
    
    private List<Atendimento> atendimentos = new ArrayList<>();
    
    
    private final AtendimentoRN ATENDIMENTO_RN = new AtendimentoRN();
    private final ProcedimentoMedicoRN PROCEDIMENTO_MEDICO_RN = new ProcedimentoMedicoRN();
    
    @PostConstruct
    public void init(){
        atendimentos = ATENDIMENTO_RN.atendimentosDeTriagemOuProcedimentoDoDia();
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
    
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public ProcedimentoMedico getProcedimentoMedico() {
        return procedimentoMedico;
    }

    public void setProcedimentoMedico(ProcedimentoMedico procedimentoMedico) {
        this.procedimentoMedico = procedimentoMedico;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
    
    
    public void onRowSelect(SelectEvent event) {
        animal = ((Atendimento) event.getObject()).getAnimal();
        UtilBean.criarMensagemDeInformacao("Atendimento Nº: "+((Atendimento) event.getObject()).getId()+" selecionado");
        procedimentoMedico.setDescricao(PROCEDIMENTO_MEDICO_RN.padraoProcedimento(UtilBean.obterContaLogada()));
//        FacesMessage msg = new FacesMessage("Atendimento: ", ((Atendimento) event.getObject()).toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Atendimento", ((Atendimento) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void salvar(){
        procedimentoMedico.setAtendimento(atendimento);
        procedimentoMedico.setDia(new Date());
        procedimentoMedico.setUsuario(UtilBean.obterContaLogada());
        atendimento.setStatus(TipoStatusAtendimento.PROCEDIMENTO.getValor());
        if (PROCEDIMENTO_MEDICO_RN.salvarProcedimentoComTransacao(procedimentoMedico, atendimento)) {
            UtilBean.criarMensagemDeInformacao("Procedimento médico registrado");
            procedimentoMedico = new ProcedimentoMedico();
            atendimentos = ATENDIMENTO_RN.atendimentosDeTriagemOuProcedimentoDoDia();
        } else {
            UtilBean.criarMensagemDeErro("Procedimento médico não registrado");
        }
    }
    
    public void marcarRetorno(){
        if (ATENDIMENTO_RN.marcarRetorno(atendimento)) {
            UtilBean.criarMensagemDeInformacao("Retorno Agendado!");
        } else {
            UtilBean.criarMensagemDeErro("Retorno não agendado!");
        }
    }

}
