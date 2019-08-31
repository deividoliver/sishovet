/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.tipo.TipoStatusAtendimento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author GreenHand
 */
public class AtendimentoDAO extends GenericDAO<Atendimento>{
    
    public List<Atendimento> obterAtendimentosDoDia(Date dia) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.dia = :dia";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
                    .setParameter("dia", dia)
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
    public List<Atendimento> obterAtendimentosAdicionadosDoDia(Date dia) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.dia = :dia and a.status = :status";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
                    .setParameter("dia", dia)
                    .setParameter("status", TipoStatusAtendimento.ADICIONADO.getValor())
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
    public List<Atendimento> obterAtendimentosDeTriagemDoDia(Date dia) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.dia = :dia and a.status = :status";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
                    .setParameter("dia", dia)
                    .setParameter("status", TipoStatusAtendimento.TRIAGEM.getValor())
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
    public List<Atendimento> obterAtendimentosProcedimentoMedicoDoDia(Date dia) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.dia = :dia and a.status = :status";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
                    .setParameter("dia", dia)
                    .setParameter("status", TipoStatusAtendimento.PROCEDIMENTO.getValor())
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
    public List<Atendimento> obterAtendimentosDeTriagemOuProcedimentoDoDia(Date dia) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.dia = :dia and (a.status = :statusTriagem or a.status = :statusProcedimento)";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
                    .setParameter("dia", dia)
                    .setParameter("statusProcedimento", TipoStatusAtendimento.PROCEDIMENTO.getValor())
                    .setParameter("statusTriagem", TipoStatusAtendimento.TRIAGEM.getValor())
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
    
    public List<Atendimento> obterAtendimentosPorAnimal(Animal animal) {
        EntityManager em = getEntityManager();
        String sql = "SELECT a FROM Atendimento a "
                + "WHERE a.animal = :animal order by a.dia desc";
        Query query = em.createQuery(sql);

        List<Atendimento> resposta = new ArrayList<>();
        try {
            List<Atendimento> atendimentos = (List<Atendimento>) query
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
    
}
