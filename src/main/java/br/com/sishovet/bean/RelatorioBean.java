/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.ProcedimentoMedico;
import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.entidade.Triagem;
import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.rn.AnimalRN;
import br.com.sishovet.rn.AtendimentoRN;
import br.com.sishovet.rn.ProcedimentoMedicoRN;
import br.com.sishovet.rn.ProprietarioRN;
import br.com.sishovet.rn.TriagemRN;
import br.com.sishovet.rn.UsuarioRN;
import br.com.sishovet.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author GreenHand
 */
@ViewScoped
@ManagedBean
public class RelatorioBean {

    private Atendimento atendimento = new Atendimento();
    private Animal animal = new Animal();
    private Proprietario proprietario = new Proprietario();
    private Triagem triagem = new Triagem();
    private Usuario usuario = new Usuario();

    private int ficha;

    private List<Animal> animais = new ArrayList<>();
    private List<Atendimento> atendimentos = new ArrayList<>();
    private List<Triagem> triagens = new ArrayList<>();
    private List<Proprietario> proprietarios = new ArrayList<>();
    private List<ProcedimentoMedico> procedimentosMedicos = new ArrayList<>();

    private final AtendimentoRN ATENDIMENTO_RN = new AtendimentoRN();
    private final AnimalRN ANIMAL_RN = new AnimalRN();
    private final ProprietarioRN PROPRIETARIO_RN = new ProprietarioRN();
    private final TriagemRN TRIAGEM_RN = new TriagemRN();
    private final UsuarioRN USUARIO_RN = new UsuarioRN();
    private final ProcedimentoMedicoRN PROCEDIMENTO_MEDICO_RN = new ProcedimentoMedicoRN();

    public List<ProcedimentoMedico> getProcedimentosMedicos() {
        return procedimentosMedicos;
    }

    public void setProcedimentosMedicos(List<ProcedimentoMedico> procedimentosMedicos) {
        this.procedimentosMedicos = procedimentosMedicos;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
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

    public Triagem getTriagem() {
        return triagem;
    }

    public void setTriagem(Triagem triagem) {
        this.triagem = triagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public List<Triagem> getTriagens() {
        return triagens;
    }

    public void setTriagens(List<Triagem> triagens) {
        this.triagens = triagens;
    }

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public void pesquisarPet() {
        animal = ANIMAL_RN.obterPetPorFicha(ficha);
        if (animal == null) {
            UtilBean.criarMensagemDeErro("Erro", "Animal não cadastrado");
        } else {
            UtilBean.criarMensagemDeInformacao("Sucesso", "Animal encontrado");
            atendimentos = ATENDIMENTO_RN.obterPorAnimal(animal);
            triagens = TRIAGEM_RN.obterTriagensPorAnimal(animal);
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        procedimentosMedicos = PROCEDIMENTO_MEDICO_RN.obterPorAtendimento(((Atendimento) event.getObject()));
        UtilBean.criarMensagemDeInformacao("Atendimento Nº: "+((Atendimento) event.getObject()).getId()+" selecionado");
//        FacesMessage msg = new FacesMessage("Atendimento: ", ((Atendimento) event.getObject()).toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
