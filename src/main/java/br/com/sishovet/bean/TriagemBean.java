package br.com.sishovet.bean;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.Triagem;
import br.com.sishovet.entidade.tipo.TipoStatusAtendimento;
import br.com.sishovet.rn.AtendimentoRN;
import br.com.sishovet.rn.TriagemRN;
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
 * @author BPMLAB-04
 */
@ManagedBean
@ViewScoped
public class TriagemBean {
     
    ///////ENTIDADES////////
    private Animal animal = new Animal();
    private Atendimento atendimento = new Atendimento();
    private Triagem triagem = new Triagem();
    /////FIM-ENTIDADES//////
    
    ////////LISTAS/////////
    private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
    //////FIM-LISTAS///////
    
    ////RELAÇÕES DE NEGÓCIOS///
    private final TriagemRN TRIAGEM_RN = new TriagemRN();
    private final AtendimentoRN ATENDIMENTO_RN = new AtendimentoRN();
    ////FIM-RELAÇÕES DE NEGÓCIOS///

    
    @PostConstruct
    public void init(){
        atendimentos = ATENDIMENTO_RN.atendimentosAdicionadosDeHoje();
    }
    
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
    
    public void onRowSelect(SelectEvent event) {
        animal = ((Atendimento) event.getObject()).getAnimal();
        UtilBean.criarMensagemDeInformacao("Atendimento Nº: "+((Atendimento) event.getObject()).getId()+" selecionado");
//        FacesMessage msg = new FacesMessage("Atendimento: ", ((Atendimento) event.getObject()).toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Atendimento", ((Atendimento) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void registrarTriagem(){
        triagem.setDia(new Date());
        triagem.setAnimal(animal);
        atendimento.setStatus(TipoStatusAtendimento.TRIAGEM.getValor());
        if (TRIAGEM_RN.salvarTriagemComTransacao(triagem, atendimento)) {
            triagem = new Triagem();
            atualizarAtendimentosAdicionados();
            UtilBean.criarMensagemDeInformacao("Triagem registrada");
        } else {
            UtilBean.criarMensagemDeErro("Triagem não registrada");
        }
    }
    
    public void atualizarAtendimentosAdicionados(){
        atendimentos = ATENDIMENTO_RN.atendimentosAdicionadosDeHoje();
    }
    
}
