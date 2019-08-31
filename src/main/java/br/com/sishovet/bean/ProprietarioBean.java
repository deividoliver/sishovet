/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.rn.ProprietarioRN;
import br.com.sishovet.util.SisHovetUtil;
import br.com.sishovet.util.UtilBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author GreenHand
 */
@ManagedBean
@ViewScoped
public class ProprietarioBean {

    ///////ENTIDADES/////
    private Proprietario proprietario = new Proprietario();

    ///////FIM-ENTIDADES/////
    ///////LISTAS/////
    private List<Proprietario> proprietarios = new ArrayList<>();
    ///////FIM-LISTAS/////

    ///////VARIAVEIS////////
    ///////FIM-VARIAVEIS////////
    ///////REGRA DE NEGÓCIOS//////
    private final ProprietarioRN PROPRIETARIO_RN = new ProprietarioRN();

    ///////FIM-REGRA DE NEGÓCIOS//////
    @PostConstruct
    public void init() {
        proprietarios = PROPRIETARIO_RN.obterTodos();
    }

    //////GETERS E SETERS///////
    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    //////FIM-GETERS E SETERS///////
    ///////FUNÇÕES//////
    public void salvar() {

        Usuario usuario = UtilBean.obterContaLogada();

        proprietario.setTelefone1(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getTelefone1()));
        proprietario.setTelefone2(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getTelefone2()));
        proprietario.setCpf(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getCpf()));
        proprietario.setCep(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getCep()));
        proprietario.setUsuario(usuario);

        if (SisHovetUtil.isCPFouCNPJ(proprietario.getCpf())) {
            if (PROPRIETARIO_RN.salvar(proprietario)) {
                UtilBean.criarMensagemDeInformacao("Cadastro realizado");
            } else {
                UtilBean.criarMensagemDeErro("Cadastro não realizado");
            }
        } else {
            UtilBean.criarMensagemDeErro("CPF inválido!");
        }

    }

    public void obterProprietario() {
        Proprietario proprietarioTemp = new Proprietario();
        proprietarioTemp = PROPRIETARIO_RN.obterPorCpf(SisHovetUtil.removeSimbolosPontuacoes(proprietario.getCpf()));
        if (proprietarioTemp.getId() != null) {
            proprietario = proprietarioTemp;
        }
    }

    public Date dataCorrente() {
        return new Date();
    }

    ///////FIM-FUNÇÕES////////
}
