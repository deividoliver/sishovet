/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Especie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author BPM Lab
 */
public class EspecieDAO extends GenericDAO<Especie> {

    public List<Especie> obterPorNome(String nome) {
        EntityManager em = getEntityManager();
        String sql = "SELECT e FROM Especie e WHERE "
                + "e.nome like :nome";
        Query query = em.createQuery(sql);
        List<Especie> resposta = new ArrayList<>();
        try {
            List<Especie> especies = (List<Especie>) query
                    .setParameter("nome", nome)
                    .getResultList();
            if (especies != null) {
                resposta = especies;
                em.refresh(resposta);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
