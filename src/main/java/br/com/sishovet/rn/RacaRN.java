/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.RacaDAO;
import br.com.sishovet.entidade.Especie;
import br.com.sishovet.entidade.Raca;
import java.util.List;

/**
 *
 * @author BPMLAB-02
 */
public class RacaRN {

    private final RacaDAO DAO = new RacaDAO();

    public boolean salvar(Raca raca) {
        if (raca == null) {
            return false;
        } else {
            if (raca.getId() == null || raca.getId() == 0) {
                return DAO.criar(raca);
            } else {
                return DAO.alterar(raca);
            }
        }
    }

    public boolean excluir(Raca raca) {
        if (raca.getId() == null || raca.getId() == 0) {
            return false;
        } else {
            return DAO.alterar(raca);
        }
    }

    public List<Raca> obterTodos() {
        return DAO.obterTodos(Raca.class);
    }

    public List<Raca> obterPorEspecie(Especie especie) {
        if (especie == null || especie.getId() == null || especie.getId() == 0) {
            return null;
        } else {
            return DAO.obterRacasPorEspecie(especie);
        }
    }

    public Raca obter(Integer id) {
        return (Raca) DAO.obter(Raca.class, id);
    }
}
