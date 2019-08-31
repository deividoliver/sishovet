/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Proprietario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author BPMLAB-01
 */
public class ProprietarioDAO extends GenericDAO<Proprietario> {

    public Proprietario obterPorCpf(String cpf) {
        EntityManager em = getEntityManager();
        String sql = "SELECT p FROM Proprietario p WHERE "
                + "p.cpf = :cpf ";
        Query query = em.createQuery(sql);
        Proprietario resposta = null;
        try {
            List<Proprietario> proprietarios = (List<Proprietario>) query
                    .setParameter("cpf", cpf)
                    .getResultList();
            if (proprietarios != null
                    && proprietarios.size() == 1) {
                resposta = proprietarios.get(0);
                em.refresh(resposta);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    public List<Proprietario> pesquisarProprietariosPorCpf(String cpfSearch) {
        EntityManager em = getEntityManager();
        String sql = "SELECT p FROM Proprietario p "
                + "WHERE p.cpf like :cpf";
        Query query = em.createQuery(sql);

        List<Proprietario> resposta = new ArrayList<>();
        try {
            List<Proprietario> proprietarios = (List<Proprietario>) query
                    .setParameter("cpf", cpfSearch+"%")
                    .getResultList();
            if (proprietarios != null) {
                resposta = proprietarios;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

}
