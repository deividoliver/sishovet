/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.rn.UsuarioRN;
import br.com.sishovet.util.SisHovetUtil;
import br.com.sishovet.util.UtilBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author BPMLAB-04
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private Usuario novoUsuario = new Usuario();
    private String senhaDeConfirmacao;
    private String newSenha;
    private String senhaCorrente;
    private final UsuarioRN USUARIO_RN = new UsuarioRN();
    
    @PostConstruct
    public void init(){
        usuario = USUARIO_RN.obter(UtilBean.obterContaLogada().getId());
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }
    
    public String getNewSenha() {
        return newSenha;
    }

    public void setNewSenha(String newSenha) {
        this.newSenha = newSenha;
    }

    public String getSenhaCorrente() {
        return senhaCorrente;
    }

    public void setSenhaCorrente(String senhaCorrente) {
        this.senhaCorrente = senhaCorrente;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenhaDeConfirmacao() {
        return senhaDeConfirmacao;
    }

    public void setSenhaDeConfirmacao(String senhaDeConfirmacao) {
        this.senhaDeConfirmacao = senhaDeConfirmacao;
    }
    
    public boolean isAdministrador(){
        return UtilBean.isAdminstrador(UtilBean.obterContaLogada());
    }

    public void salvaUsuario() {
        if (novoUsuario == null) {
            UtilBean.criarMensagemDeErro("Erro", "Dados Inválidos");
        } else {
            novoUsuario.setSenha(SisHovetUtil.encriptarSHA256(senhaDeConfirmacao));
            if (USUARIO_RN.salvar(novoUsuario)) {
                UtilBean.criarMensagemDeInformacao("Sucesso!", "O usuario foi " + novoUsuario.getNome() + " foi salvo");
                novoUsuario = new Usuario();
            } else {
                UtilBean.criarMensagemDeErro("Erro", "Houve um problema ao salvar o usuario");
            }
        }

    }
    
    public void alterarSenha(){
        if (usuario.getSenha().equals(SisHovetUtil.encriptarSHA256(senhaCorrente))) {
            if (USUARIO_RN.alterarSenha(usuario, newSenha)) {
                UtilBean.criarMensagemDeInformacao("Senha Alterada!");
            } else {
                UtilBean.criarMensagemDeInformacao("Senha não alterada!");
            }
        } else {
            UtilBean.criarMensagemDeErro("Senha Corrente Inválida!");
        }
    }
}
