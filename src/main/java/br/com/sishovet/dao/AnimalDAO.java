/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Proprietario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author BPMLAB-01
 */
public class AnimalDAO extends GenericDAO<Animal> {

    public List<Animal> obterAnimaisProprietario(Proprietario proprietario) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Animal a "
                + "WHERE a.proprietario = :proprietario";
        Query query = em.createQuery(sql);

        List<Animal> resposta = null;
        try {
            List<Animal> animais = (List<Animal>) query
                    .setParameter("proprietario", proprietario)
                    .getResultList();
            if (animais != null) {
                resposta = animais;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    
    public Animal obterAnimaisPorFicha(int ficha) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Animal a "
                + "WHERE a.id = :id";
        Query query = em.createQuery(sql);

        Animal resposta = null;
        try {
            List<Animal> animais = (List<Animal>) query
                    .setParameter("id", ficha)
                    .getResultList();
            if (animais != null) {
                resposta = animais.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public boolean salvarAnimalComProprietario(Animal a, Proprietario p) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();

            em.persist(p);
            em.flush();
            a.setProprietario(p);
            em.persist(a);

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
