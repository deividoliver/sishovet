/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn.tipo;

/**
 *
 * @author BPMLAB-01
 */
public enum TipoSexoUsuario {
    
    FEMININO('F', "Feminino"),
    MASCULINO('M', "Masculino");

    private TipoSexoUsuario(char valor, String descricao) {
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

    public static TipoSexoUsuario obter(char valor) {
        TipoSexoUsuario resposta = FEMININO;
        TipoSexoUsuario[] values = values();
        for (TipoSexoUsuario t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        values = null;
        return resposta;
    }
}
