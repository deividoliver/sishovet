/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn;

import br.com.sishovet.dao.ProcedimentoMedicoDAO;
import br.com.sishovet.entidade.Atendimento;
import br.com.sishovet.entidade.ProcedimentoMedico;
import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.util.SisHovetUtil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author GreenHand
 */
public class ProcedimentoMedicoRN {
    
    private final ProcedimentoMedicoDAO PROCEDIMENTO_MEDICO_DAO = new ProcedimentoMedicoDAO();
    
    
    public boolean salvar(ProcedimentoMedico procedimentoMedico) {
        if (procedimentoMedico == null) {
            return false;
        } else if (procedimentoMedico.getId() == null || procedimentoMedico.getId() == 0) {
            procedimentoMedico.setHashCode(procedimentoMedico.hashCode());
            return PROCEDIMENTO_MEDICO_DAO.criar(procedimentoMedico);
        } else {
            return PROCEDIMENTO_MEDICO_DAO.alterar(procedimentoMedico);
        }
    }

    public boolean excluir(ProcedimentoMedico procedimentoMedico) {
        if (procedimentoMedico == null || procedimentoMedico.getId() == null) {
            return false;
        } else {
            return PROCEDIMENTO_MEDICO_DAO.excluir(procedimentoMedico);
        }
    }

    public ProcedimentoMedico obter(Integer id) {
        return PROCEDIMENTO_MEDICO_DAO.obter(ProcedimentoMedico.class, id);
    }
    
    public List<ProcedimentoMedico> obterPorAtendimento(Atendimento atendimento) {
        return PROCEDIMENTO_MEDICO_DAO.obterProcedimentosPorAtendimento(atendimento);
    }

    public List<ProcedimentoMedico> obterTodas() {
        return PROCEDIMENTO_MEDICO_DAO.obterTodos(ProcedimentoMedico.class);
    }
    
    public boolean salvarProcedimentoComTransacao(ProcedimentoMedico procedimento, Atendimento atendimento){
        if (procedimento == null || atendimento == null || atendimento.getId() == null) {
            return false;
        } else {
            return PROCEDIMENTO_MEDICO_DAO.salvarTriagem(procedimento, atendimento);
        }
    }
    
    public String padraoProcedimento(Usuario usuario){
        return "<center><img src=\"/sishovet/resources/poseidon-layout/images/logo-sishovet-resized.png\"></center><center>UNIVERSIDADE FEDERAL RURAL DA AMAZÔNIA</center><center>HOSPITAL VETERINÁRIO</center><center><br></center><center style=\"text-align: right;\">"+SisHovetUtil.formatarData(new Date(), true)+"</center><center style=\"text-align: right;\">Dr. "+usuario.getNome()+"</center><center style=\"text-align: right;\">CRMVPA - "+usuario.getCrmvpa()+"</center><center style=\"text-align: left;\"><br></center><center style=\"text-align: left;\"><br></center><center style=\"text-align: left;\"><br></center>";
    }

}
