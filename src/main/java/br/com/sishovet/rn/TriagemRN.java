/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.TriagemDAO;
import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.Triagem;
import java.util.List;

/**
 *
 * @author BPMLAB-04
 */
public class TriagemRN {

    private TriagemDAO TRIAGEM_DAO = new TriagemDAO();

    public boolean salvar(Triagem triagem) {
        if (triagem == null
                || triagem.getAnimal().getId() == null
                || triagem.getPeso() == 0.0
                || triagem.getDia() == null) {
            return false;
        } else {
            if (triagem.getId() == null || triagem.getId() == 0) {
                return TRIAGEM_DAO.criar(triagem);
            } else {
                return TRIAGEM_DAO.alterar(triagem);
            }
        }
    }
    
    public boolean excluir(Triagem triagem){
        if (triagem == null || 
            triagem.getId() == null || 
            triagem.getId() ==0) {
            return false;
        } else {
            return TRIAGEM_DAO.excluir(triagem);
        }
    }
    
    public Triagem obter(Integer id){
        if (id == null || id == 0) {
            return null;
        } else {
            return TRIAGEM_DAO.obter(Triagem.class, id);
        }
    }
    
    public List<Triagem> obterTodos(){
        return TRIAGEM_DAO.obterTodos(Triagem.class);
    }
    public List<Triagem> obterTriagensPorAnimal(Animal animal){
 
        if (animal == null || animal.getId() == null || animal.getId() == 0) {
            return null;
        } else {
        }
        return TRIAGEM_DAO.obterTriagensPorAnimal(animal);
    }
    
    public boolean salvarTriagemComTransacao(Triagem triagem, Atendimento atendimento){
        if (triagem == null || atendimento == null || atendimento.getId() == null) {
            return false;
        } else {
            return TRIAGEM_DAO.salvarTriagem(triagem, atendimento);
        }
    }
    
    
}
