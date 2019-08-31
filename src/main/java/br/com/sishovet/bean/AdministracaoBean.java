/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.entidade.Usuario_;
import br.com.sishovet.rn.UsuarioRN;
import br.com.sishovet.util.SisHovetUtil;
import br.com.sishovet.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BPMLAB-04
 */
@ManagedBean
@ViewScoped
public class AdministracaoBean {

    private Usuario usuario = new Usuario();
    private String novaSenha;
    private String novaSenhaConfirma;
    private List<Usuario> UsuariosList = new ArrayList<Usuario>();
    private final UsuarioRN USUARIO_RN = new UsuarioRN();

    @PostConstruct
    public void init() {
        UsuariosList = USUARIO_RN.obterUsuarios();
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConfirma() {
        return novaSenhaConfirma;
    }

    public void setNovaSenhaConfirma(String novaSenhaConfirma) {
        this.novaSenhaConfirma = novaSenhaConfirma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuariosList() {
        return UsuariosList;
    }

    public void setUsuariosList(List<Usuario> UsuariosList) {
        this.UsuariosList = UsuariosList;
    }

    public String nomePerfil(char valor) {
        return SisHovetUtil.retornaNomePerfil(valor);
    }

    public String habilitacaoToString(Usuario usuario) {
        if (usuario.getHabilitado()) {
            return "HABILITADO";
        } else {
            return "DESABILITADO";
        }
    }

    public void salvaUsuario() {
        if (usuario == null) {
            UtilBean.criarMensagemDeErro("Erro", "Dados Inválidos");
        } else {
            if (USUARIO_RN.alterarPerfil(usuario)) {
                UtilBean.criarMensagemDeInformacao("Sucesso!", "O usuario foi " + usuario.getNome() + " teve seu perfil alterado");
            } else {
                UsuariosList = USUARIO_RN.obterUsuarios();
                UtilBean.criarMensagemDeErro("Erro", "Houve um problema ao salvar o usuario");
            }
        }

    }

    public void alterarSenha() {
        if (usuario == null) {
            UtilBean.criarMensagemDeErro("Erro", "Dados Inválidos");
        } else {

            if (novaSenha != null && novaSenha.equals(novaSenhaConfirma)) {
                usuario.setSenha(SisHovetUtil.encriptarSHA256(novaSenha));
                if (USUARIO_RN.alterarPerfil(usuario)) {
                    UtilBean.criarMensagemDeInformacao("Sucesso!", "O usuario foi " + usuario.getNome() + " teve seu perfil alterado");
                }
            } else {
                UtilBean.criarMensagemDeErro("Erro", "Houve um problema ao salvar o usuario");

            }
        }
    }

    public void habilitarDesabilitar() {
        if (usuario == null) {
            UtilBean.criarMensagemDeErro("Erro", "Dados Inválidos");
        } else {
            if (usuario.getHabilitado()) {
                usuario.setHabilitado(false);
            } else {
                usuario.setHabilitado(true);
            }
            salvaUsuario();
        }

    }

}
