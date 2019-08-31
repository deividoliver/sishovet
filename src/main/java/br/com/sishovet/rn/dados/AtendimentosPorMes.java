package br.com.sishovet.rn.dados;

/**
 *
 * @author greenhand
 */
public class AtendimentosPorMes {

    private Long atendimentos;
    private Integer mes;

    public AtendimentosPorMes() {
    }

    public AtendimentosPorMes(Long atendimentos, Integer mes) {
        this.atendimentos = atendimentos;
        this.mes = mes;
    }

    public Long getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Long atendimentos) {
        this.atendimentos = atendimentos;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

}
