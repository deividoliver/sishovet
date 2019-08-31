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
public enum TipoPerfilUsuario {
    ADMINISTRADOR('A', "Administrador"),
    SECRETARIA('S', "Secretaria"),
    MEDICO('M', "Médico");

    private TipoPerfilUsuario(char valor, String descricao) {
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

    public static TipoPerfilUsuario obter(char valor) {
        TipoPerfilUsuario resposta = SECRETARIA;
        TipoPerfilUsuario[] values = values();
        for (TipoPerfilUsuario t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        values = null;
        return resposta;
    }
}
