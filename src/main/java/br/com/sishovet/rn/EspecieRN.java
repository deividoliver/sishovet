/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.EspecieDAO;
import br.com.sishovet.entidade.Especie;
import java.util.List;

/**
 *
 * @author BPM Lab
 */
public class EspecieRN {

    private final EspecieDAO ESPECIE_DAO = new EspecieDAO();

    public boolean salvar(Especie especie) {
        if (especie == null) {
            return false;
        } else if (especie.getId() == null || especie.getId() == 0) {
            return ESPECIE_DAO.criar(especie);
        } else {
            return ESPECIE_DAO.alterar(especie);
        }
    }

    public boolean excluir(Especie especie) {
        if (especie == null || especie.getId() == null) {
            return false;
        } else {
            return ESPECIE_DAO.excluir(especie);
        }
    }

    public Especie obter(Integer id) {
        return ESPECIE_DAO.obter(Especie.class, id);
    }

    public List<Especie> obterTodas() {
        return ESPECIE_DAO.obterTodos(Especie.class);
    }

    public List<Especie> obterPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return null;
        } else {
            return ESPECIE_DAO.obterPorNome(nome);
        }
    }

}
