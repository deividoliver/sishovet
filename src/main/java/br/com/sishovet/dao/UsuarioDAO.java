/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.dao;

import br.com.sishovet.entidade.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author GreenHand
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public Usuario obter(String email) {
        EntityManager em = getEntityManager();
        String sql = "select u from Usuario u "
                + "where u.email = :email ";
        Query query = em.createQuery(sql);
        Usuario resposta = null;
        try {
            List<Usuario> contas = (List<Usuario>) query
                    .setParameter("email", email.toLowerCase())
                    .getResultList();
            if (contas != null
                    && contas.size() == 1) {
                resposta = contas.get(0);
                em.refresh(resposta);
            }
            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Usuario autenticar(String email, String senha) {
        EntityManager em = getEntityManager();
        Usuario resposta = null;
        String sql = "select u from Usuario u "
                + "where "
                + " u.email = :email "
                + " and u.senha = :senha ";
        try {
            Query query = em.createQuery(sql);
            query
                    .setParameter("email", email)
                    .setParameter("senha", senha);

            resposta = (Usuario) query.getSingleResult();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return resposta;
        } finally {
            em.close();
        }
    }

    /**
     * Verifica se o email já pertence à um usuario cadastrado
     *
     * @param email
     * @return true para email cadastrado no sistema e false caso não esteja
     * cadastrado.
     */
    public boolean emailCadastrado(String email) {
        EntityManager em = getEntityManager();
        String sql = "select u from Usuario u "
                + "where u.email = :email ";
        Query query = em.createQuery(sql);
        Usuario usuario = null;
        boolean resposta = false;
        try {
            List<Usuario> contas = (List<Usuario>) query
                    .setParameter("email", email.toLowerCase())
                    .getResultList();
            if (contas != null
                    && contas.size() == 1) {
                resposta = true;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

}
