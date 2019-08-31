/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.rn.dados.AtendimentosPorMes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author greenhand
 */
public class EstatisticaDAO extends GenericDAO<Object> {

    public int quantideDeAnimais() {
        EntityManager em = getEntityManager();
        String sql = "select count(a) from Animal a";
        Query query = em.createQuery(sql);
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List<Long> resposta = null;
        int quantidade = 0;
        try {
            resposta = (List<Long>) query
                    .getResultList();
            if (!resposta.isEmpty()) {
                quantidade = resposta.get(0).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return quantidade;
    }

    public int quantideDeProprietarios() {
        EntityManager em = getEntityManager();
        String sql = "select count(p) from Proprietario p";
        Query query = em.createQuery(sql);
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List<Long> resposta = null;
        int quantidade = 0;
        try {
            resposta = (List<Long>) query
                    .getResultList();
            if (!resposta.isEmpty()) {
                quantidade = resposta.get(0).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return quantidade;
    }

    public int quantideDeUsuarios() {
        EntityManager em = getEntityManager();
        String sql = "select count(u) from Usuario u";
        Query query = em.createQuery(sql);
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List<Long> resposta = null;
        int quantidade = 0;
        try {
            resposta = (List<Long>) query
                    .getResultList();
            if (!resposta.isEmpty()) {
                quantidade = resposta.get(0).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return quantidade;
    }

    public int quantideDeMedicosAtivos() {
        EntityManager em = getEntityManager();
        String sql = "select count(u) from Usuario u where u.perfil = 'M' and u.habilitado = true";
        Query query = em.createQuery(sql);
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List<Long> resposta = null;
        int quantidade = 0;
        try {
            resposta = (List<Long>) query
                    .getResultList();
            if (!resposta.isEmpty()) {
                quantidade = resposta.get(0).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return quantidade;
    }

    public List<Object[]> quantideDeAtendimentosPorMesDoAno() {
        EntityManager em = getEntityManager();
        String sql = "select count(*) as atendimentos, MONTH(a.dia) as mes from atendimento a where YEAR(a.dia) = year(curdate()) group by month(a.dia)";
        Query query = em.createNativeQuery(sql);
        List<Object[]> resposta = new ArrayList<>();

        try {
            resposta = (List<Object[]>) query.getResultList();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

}
