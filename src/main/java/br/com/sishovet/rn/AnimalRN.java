/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.AnimalDAO;
import br.com.sishovet.entidade.Animal;
import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.util.SisHovetUtil;
import java.util.List;

/**
 *
 * @author BPMLAB-01
 */
public class AnimalRN {

    private final AnimalDAO ANIMAL_DAO = new AnimalDAO();

    public boolean salvar(Animal animal) {
        if (animal == null) {
            return false;
        } else if (animal.getId() == null || animal.getId() == 0) {
            return ANIMAL_DAO.criar(animal);
        } else {
            return ANIMAL_DAO.alterar(animal);
        }
    }

    public boolean salvarAnimalComProprietario(Animal a, Proprietario p) {
        if (a == null && p == null) {
            return false;
        } else {
            p.setCpf(SisHovetUtil.removeSimbolosPontuacoes(p.getCpf()));
            p.setCep(SisHovetUtil.removeSimbolosPontuacoes(p.getCep()));
            p.setTelefone2(SisHovetUtil.removeSimbolosPontuacoes(p.getTelefone2()));
            p.setTelefone1(SisHovetUtil.removeSimbolosPontuacoes(p.getTelefone1()));

            return ANIMAL_DAO.salvarAnimalComProprietario(a, p);
        }
    }

    public boolean excluir(Animal animal) {
        if (animal == null || animal.getId() == null) {
            return false;
        } else {
            return ANIMAL_DAO.excluir(animal);
        }
    }

    public Animal obter(Integer id) {
        return ANIMAL_DAO.obter(Animal.class, id);
    }

    public List<Animal> obterAnimaisProprietario(Proprietario proprietario) {
        if (proprietario == null) {
            return null;
        } else {
            return ANIMAL_DAO.obterAnimaisProprietario(proprietario);
        }
    }

    public Animal obterPetPorFicha(int ficha) {
        if (ficha <= 0) {
            return null;
        } else {
            return ANIMAL_DAO.obterAnimaisPorFicha(ficha);
        }
    }

    public List<Animal> obterTodas() {
        return ANIMAL_DAO.obterTodos(Animal.class);
    }
}
