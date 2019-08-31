/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean.tipo;

import br.com.sishovet.rn.tipo.TipoSexoAnimal;
import br.com.sishovet.rn.tipo.TipoSexoUsuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author BPMLAB-01
 */
@ManagedBean
@RequestScoped
public class ListasTipoBean {

    public ListasTipoBean() {
    }
    
    public TipoSexoAnimal[] listaTipoSexoAnimal() {
        return TipoSexoAnimal.values();
    }

    public TipoSexoUsuario[] listaTipoSexoUsuario() {
        return TipoSexoUsuario.values();
    }

}
