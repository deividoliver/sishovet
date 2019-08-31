/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.ProcedimentoMedico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author GreenHand
 */
public class ProcedimentoMedicoDAO extends GenericDAO<ProcedimentoMedico>{
    
    public List<ProcedimentoMedico> obterProcedimentosPorAtendimento(Atendimento atendimento) {
        EntityManager em = getEntityManager();
        String sql = "SELECT p FROM ProcedimentoMedico p "
                + "WHERE p.atendimento = :atendimento";
        Query query = em.createQuery(sql);

        List<ProcedimentoMedico> resposta = new ArrayList<>();
        try {
            List<ProcedimentoMedico> procedimentos = (List<ProcedimentoMedico>) query
                    .setParameter("atendimento", atendimento)
                    .getResultList();
            if (procedimentos != null) {
                resposta = procedimentos;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    public boolean salvarTriagem(ProcedimentoMedico procedimento, Atendimento atendimento) {

        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            em.merge(atendimento);
            em.persist(procedimento);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}
