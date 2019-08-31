/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.AtendimentoDAO;
import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author GreenHand
 */
public class AtendimentoRN {

    private final AtendimentoDAO ATENDIMENTO_DAO = new AtendimentoDAO();

    public boolean salvar(Atendimento atendimento) {
        if (atendimento == null
                || atendimento.getAnimal() == null
                || atendimento.getAnimal().getId() == null
                || atendimento.getAnimal().getId() == 0
                || atendimento.getProprietario() == null
                || atendimento.getProprietario().getId() == null
                || atendimento.getProprietario().getId() == 0) {
            return false;
        } else {
            if (atendimento.getId() == null) {
                if(atendimento.getCusto() == null){
                    atendimento.setCusto(0.0);
                }
                return ATENDIMENTO_DAO.criar(atendimento);
            } else {
                return ATENDIMENTO_DAO.alterar(atendimento);
            }
        }
    }
    
    public boolean marcarRetorno(Atendimento atendimento){
        if (atendimento == null || atendimento.getId() == null || atendimento.getRetorno() == null) {
            return false;
        } else {
            return salvar(atendimento);
        }
    }
    
    public boolean excluir(Atendimento atendimeto){
        if (atendimeto == null || atendimeto.getId() == null || atendimeto.getId() == 0) {
            return false;
        } else {
            return ATENDIMENTO_DAO.excluir(atendimeto);
        }
    }

    public Atendimento obter(Integer id) {
        if (id == null || id == 0) {
            return null;
        } else {
            return ATENDIMENTO_DAO.obter(Atendimento.class, id);
        }
    }
    public List<Atendimento> obterPorAnimal(Animal animal) {
        if (animal == null || animal.getId() == null || animal.getId() == 0) {
            return null;
        } else {
            return ATENDIMENTO_DAO.obterAtendimentosPorAnimal(animal);
        }
    }
    
    public List<Atendimento> obterTodos(){
        return ATENDIMENTO_DAO.obterTodos(Atendimento.class);
    }
    
    public List<Atendimento> atendimentosDeHoje(){
        return ATENDIMENTO_DAO.obterAtendimentosDoDia(new Date());
    }
    public List<Atendimento> atendimentosAdicionadosDeHoje(){
        return ATENDIMENTO_DAO.obterAtendimentosAdicionadosDoDia(new Date());
    }
    public List<Atendimento> atendimentosDeTriagemDeHoje(){
        return ATENDIMENTO_DAO.obterAtendimentosDeTriagemDoDia(new Date());
    }
    public List<Atendimento> atendimentosDeProcedimentoMedicoDeHoje(){
        return ATENDIMENTO_DAO.obterAtendimentosProcedimentoMedicoDoDia(new Date());
    }
    public List<Atendimento> atendimentosDeTriagemOuProcedimentoDoDia(){
        return ATENDIMENTO_DAO.obterAtendimentosDeTriagemOuProcedimentoDoDia(new Date());
    }

}
