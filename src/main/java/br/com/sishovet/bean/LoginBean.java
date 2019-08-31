/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.entidade.tipo.TipoPerfilUsuario;
import br.com.sishovet.util.SisHovetUtil;
import br.com.sishovet.util.UtilBean;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author AdminBPMLAB
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    public boolean erroAoAutenticar(Map<String, String> parametrosDaRequisicao) {
        boolean resposta = false;
        for (String chave : parametrosDaRequisicao.keySet()) {
            if (chave.equals("e") && parametrosDaRequisicao.get(chave).equals("-1")) {
                resposta = true;
                break;
            }
        }
        return resposta;
    }
    
    public Usuario getContaLogada() {
        return UtilBean.obterContaLogada();
    }

    public String renderizaMenu(){
        return SisHovetUtil.rederizaMenu(UtilBean.obterContaLogada().getPerfil());
    }
    
    public String getPerfil(){
        return TipoPerfilUsuario.obter(getContaLogada().getPerfil()).getDescricao();
    }
    
    public boolean isAdministrador(){
        return getContaLogada().getPerfil() == TipoPerfilUsuario.ADMINISTRADOR.getValor();
    }
    
    public boolean isMedico(){
        return getContaLogada().getPerfil() == TipoPerfilUsuario.MEDICO.getValor();
    }
    
    public boolean isSecretaria(){
        return getContaLogada().getPerfil() == TipoPerfilUsuario.SECRETARIA.getValor();
    }
}
