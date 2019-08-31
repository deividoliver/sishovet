/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.Triagem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author BPMLAB-04
 */
public class TriagemDAO extends GenericDAO<Triagem>{
    
    public List<Triagem> obterTriagensPorAnimal(Animal animal) {
        EntityManager em = getEntityManager();
        String sql = "SELECT t FROM Triagem t "
                + "WHERE t.animal = :animal order by t.dia desc";
        Query query = em.createQuery(sql);

        List<Triagem> resposta = new ArrayList<>();
        try {
            List<Triagem> atendimentos = (List<Triagem>) query
                    .setParameter("animal", animal)
                    .getResultList();
            if (atendimentos != null) {
                resposta = atendimentos;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    
    public boolean salvarTriagem(Triagem triagem, Atendimento atendimento) {

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
            em.persist(triagem);
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
