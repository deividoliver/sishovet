/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.entidade.tipo;

/**
 *
 * @author BPMLAB-04
 */
public enum TipoStatusAtendimento {
    ADICIONADO('A', "Adicionado"),
    TRIAGEM('T', "Triagem"),
    PROCEDIMENTO('P', "Procedimento Médico");

    private TipoStatusAtendimento(char valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    private char valor;
    private String descricao;

    public char getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoStatusAtendimento obter(char valor) {
        TipoStatusAtendimento resposta = ADICIONADO;
        TipoStatusAtendimento[] values = values();
        for (TipoStatusAtendimento t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        values = null;
        return resposta;
    }
}
