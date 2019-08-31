/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Especie;
import br.com.sishovet.entidade.Raca;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author BPM Lab
 */
public class RacaDAO extends GenericDAO<Raca>{
    
    
    public List<Raca> obterRacasPorEspecie(Especie especie) {
        EntityManager em = getEntityManager();
        String sql = "SELECT r FROM Raca r "
                + "WHERE r.especie = :especie";
        Query query = em.createQuery(sql);

        List<Raca> resposta = new ArrayList<>();
        try {
            List<Raca> racas = (List<Raca>) query
                    .setParameter("especie", especie)
                    .getResultList();
            if (racas != null) {
                resposta = racas;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
