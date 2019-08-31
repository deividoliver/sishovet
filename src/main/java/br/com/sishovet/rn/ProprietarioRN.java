/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.ProprietarioDAO;
import br.com.sishovet.entidade.Proprietario;
import java.util.List;

/**
 *
 * @author BPMLAB-01
 */
public class ProprietarioRN {

    private final ProprietarioDAO PROPRIETARIO_DAO = new ProprietarioDAO();

    public boolean salvar(Proprietario proprietario) {
        if (proprietario == null) {
            return false;
        } else if (proprietario.getId() == null || proprietario.getId() == 0) {
            return PROPRIETARIO_DAO.criar(proprietario);
        } else {
            return PROPRIETARIO_DAO.alterar(proprietario);
        }

    }

    public boolean excluir(Proprietario proprietario) {
        if (proprietario == null || proprietario.getId() == null) {
            return false;
        } else {
            return PROPRIETARIO_DAO.excluir(proprietario);
        }
    }

    public Proprietario obterPorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return null;
        } else {
            return PROPRIETARIO_DAO.obterPorCpf(cpf);
        }
    }

    public List<Proprietario> pesquisarPorCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return null;
        } else {
            return PROPRIETARIO_DAO.pesquisarProprietariosPorCpf(cpf);
        }
    }

    public List<Proprietario> obterTodos() {
        return PROPRIETARIO_DAO.obterTodos(Proprietario.class);
    }

    public Proprietario obter(Integer id) {
        if (id == null || id <= 0) {
            return null;
        } else {
            return PROPRIETARIO_DAO.obter(Proprietario.class, id);
        }
    }

}
