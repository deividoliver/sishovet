/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.Especie;
import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.entidade.Raca;
import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.entidade.tipo.TipoStatusAtendimento;
import br.com.sishovet.rn.AnimalRN;
import br.com.sishovet.rn.AtendimentoRN;
import br.com.sishovet.rn.EspecieRN;
import br.com.sishovet.rn.ProprietarioRN;
import br.com.sishovet.rn.RacaRN;
import br.com.sishovet.rn.tipo.TipoSexoAnimal;
import br.com.sishovet.util.SisHovetUtil;
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

/**
 *
 * @author BPMLAB-01
 */
@ManagedBean
@ViewScoped
public class AnimalBean {

    //Entidade
    private Animal animal = new Animal();
    private Proprietario proprietario = new Proprietario();
    private Atendimento atendimento = new Atendimento();
    private Especie especie = new Especie();
    //RN
    private final AnimalRN ANIMAL_RN = new AnimalRN();
    private final RacaRN RACA_RN = new RacaRN();
    private final EspecieRN ESPECIE_RN = new EspecieRN();
    private final AtendimentoRN ATENDIMENTO_RN = new AtendimentoRN();
    private final ProprietarioRN PROPRIETARIO_RN = new ProprietarioRN();

    //VARIAVEIS
    private String nascimentoProprietario;
    private String nascimentoAnimal;
    private String cpfProprietario;
    private int ficha;

    //LISTAS
    private List<Animal> animais = new ArrayList<Animal>();
    private List<Especie> especies = new ArrayList<>();
    private List<Raca> racas = new ArrayList<>();

    @PostConstruct
    public void init() {
        especies = ESPECIE_RN.obterTodas();
        String idFicha = UtilBean.obterValor("f");
        if (idFicha != null) {
            try {
                animal = ANIMAL_RN.obter(Integer.parseInt(idFicha));
                if (animal != null) {
                    proprietario = PROPRIETARIO_RN.obter(animal.getProprietario().getId());
                } else {
                    animal = new Animal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (proprietario.getId() != null && proprietario.getId() > 0) {
            animais = ANIMAL_RN.obterAnimaisProprietario(proprietario);
        } else {
            animais = ANIMAL_RN.obterTodas();
        }
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public List<Raca> getRacas() {
        return racas;
    }

    public void setRacas(List<Raca> racas) {
        this.racas = racas;
    }

    public List<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(List<Especie> especies) {
        this.especies = especies;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public String getNascimentoProprietario() {
        return nascimentoProprietario;
    }

    public void setNascimentoProprietario(String nascimentoProprietario) {
        this.nascimentoProprietario = nascimentoProprietario;
    }

    public String getNascimentoAnimal() {
        return nascimentoAnimal;
    }

    public void setNascimentoAnimal(String nascimentoAnimal) {
        this.nascimentoAnimal = nascimentoAnimal;
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

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Date stringToDate(String texto) {
        return SisHovetUtil.stringToDate(texto);
    }

    public String removeSimbolosPontuacoes(String texto) {
        return SisHovetUtil.removeSimbolosPontuacoes(texto);
    }

    public void salvar() {

        Usuario usuario = UtilBean.obterContaLogada();

        animal.setUsuario(usuario);
        animal.setProprietario(proprietario);

        if (ANIMAL_RN.salvar(animal)) {
            UtilBean.criarMensagemDeAviso("Sucesso!", "Cadastro realizado");
        } else {
            UtilBean.criarMensagemDeErro("Erro!", "Cadastro não realizado");
        }
    }

    public void pesquisarPet() {
        animal = ANIMAL_RN.obterPetPorFicha(ficha);
        atendimento = new Atendimento();
        if (animal == null) {
            UtilBean.criarMensagemDeErro("Erro", "Animal não cadastrado");
        } else {
            UtilBean.criarMensagemDeInformacao("Sucesso", "Animal encontrado");
        }
    }

    public void registrarAtendimento() {
        atendimento.setDia(new Date());
        atendimento.setAnimal(animal);
        atendimento.setProprietario(animal.getProprietario());
        atendimento.setStatus(TipoStatusAtendimento.ADICIONADO.getValor());

        if (ATENDIMENTO_RN.salvar(atendimento)) {
            atendimento = new Atendimento();
            animal = new Animal();
            UtilBean.criarMensagemDeInformacao("Atendimento registrado!");
        } else {
            UtilBean.criarMensagemDeErro("Atendimento não registrado!");
        }

    }

    public void obterRacas() {
        racas = RACA_RN.obterPorEspecie(especie);
    }

    public void obterProprietario() {
        proprietario = PROPRIETARIO_RN.obterPorCpf(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getCpf()));
    }

    public List<Proprietario> pesquisarProprietarios(String cpf) {
        return PROPRIETARIO_RN.pesquisarPorCPF(cpf);
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proprietario ", ((Proprietario) event.getObject()).getNome() + " Selecionado(a)"));
    }

    public boolean isFemea() {
        if (TipoSexoAnimal.isFemea(animal.getSexo())) {
            return true;
        } else {
            return false;
        }
    }

    public String eCastrado(boolean castrado) {
        if (castrado) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public void findAnimaisProp() {
        if (cpfProprietario != null) {
            proprietario = PROPRIETARIO_RN.obterPorCpf(cpfProprietario);
            if (proprietario != null) {
                animais = ANIMAL_RN.obterAnimaisProprietario(proprietario);
                if (animais.size() > 0) {
                    UtilBean.criarMensagemDeInformacao("Pets selecionados");
                } else {
                    animais = new ArrayList<>();
                    UtilBean.criarMensagemDeErro("Proprietário não possui pets");
                }
            } else {
                animais = new ArrayList<>();
                UtilBean.criarMensagemDeErro("Proprietário não encontrado");
            }
        }
    }

}
