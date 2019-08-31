/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author BPMLAB-04
 */
@ManagedBean
@RequestScoped
public class MenuBean {

    public MenuBean() {
    }

    /////////////USUÁRIO/////////////
    public String irInicio() {
        return "/usuario/inicio.xhtml";
    }

    public String irRelatorioFichaPet() {
        return "/usuario/relatorio/ficha.xhtml";
    }

    public String irAlterarSenha() {
        return "/sishovet/usuario/alterar-senha.xhtml";
    }
    /////////FIM-USUÁRIO/////////////

    /////////////SECRETARIA/////////////
    public String irCadastroEspecie() {
        return "/secretaria/especie/cadastro.xhtml";
    }

    public String irListarEspecie() {
        return "/secretaria/especie/listar.xhtml";
    }

    public String irCadastroRaca() {
        return "/secretaria/raca/cadastro.xhtml";
    }

    public String irListarRaca() {
        return "/secretaria/raca/listar.xhtml";
    }

    public String irCadastroUsuario() {
        return "/secretaria/usuario/cadastro_usuario.xhtml";
    }

    public String irCadastroProprietario() {
        return "/secretaria/proprietario/cadastro_proprietario.xhtml";
    }

    public String irAtendimento() {
        return "/secretaria/atendimento.xhtml";
    }

    public String irCadastroAnimal() {
        return "/secretaria/pet/cadastro_animal.xhtml";
    }

    public String irListaPet() {
        return "/secretaria/pet/lista_de_pet.xhtml";
    }

    public String irListaAtendimento() {
        return "/secretaria/lista_de_atendimento.xhtml";
    }

    public String irListaProprietario() {
        return "/secretaria/proprietario/lista_de_proprietario.xhtml";
    }
    /////////////FIM-SECRETARIA/////////////

    /////////////ADMINISTRADOR/////////////
    public String irAlterarPerfil() {
        return "/admin/alterar_perfil.xhtml";
    }

    public String irHabilitarDesabilitarPerfil() {
        return "/admin/habilitar_desabilitar.xhtml";
    }
    /////////////FIM-ADMINISTRADOR/////////////

    ///////////////MÉDICO//////////////////
    public String irTriagem() {
        return "/medico/triagem.xhtml";
    }

    public String irProcedimentoMedico() {
        return "/medico/procedimento_medico.xhtml";
    }
    ///////////FIM-MÉDICO//////////////////
}
