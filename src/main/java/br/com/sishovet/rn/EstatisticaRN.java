/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.EstatisticaDAO;
import br.com.sishovet.rn.dados.AtendimentosPorMes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author greenhand
 */
public class EstatisticaRN {

    private final EstatisticaDAO ESTATISTICA_DAO = new EstatisticaDAO();

    public int obterQuantidadeDeAnimais() {
        return ESTATISTICA_DAO.quantideDeAnimais();
    }

    public int obterQuantidadeDeProprietarios() {
        return ESTATISTICA_DAO.quantideDeProprietarios();
    }

    public int obterQuantidadeDeUsuarios() {
        return ESTATISTICA_DAO.quantideDeUsuarios();
    }

    public int obterQuantidadeDeMedicosAtivos() {
        return ESTATISTICA_DAO.quantideDeMedicosAtivos();
    }

    public List<AtendimentosPorMes> obterAtendimentosPorMesDoAno() {
        List<AtendimentosPorMes> resposta = new ArrayList<>();
        List<Object[]> busca = ESTATISTICA_DAO.quantideDeAtendimentosPorMesDoAno();
        for (Object[] o : busca) {
            AtendimentosPorMes temp = new AtendimentosPorMes();
            temp.setAtendimentos((Long) o[0]);
            temp.setMes((Integer) o[1]);
            resposta.add(temp);
        }
        return resposta;
    }

}
