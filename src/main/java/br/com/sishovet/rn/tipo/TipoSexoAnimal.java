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
public enum TipoSexoAnimal {

    FEMEA('F', "Fêmea"),
    MACHO('M', "Macho");

    private TipoSexoAnimal(char valor, String descricao) {
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
    
    public static boolean isFemea(Character tipo) {
        if (tipo == null) {
            return false;
        } else {
            return FEMEA.equals(obter(tipo));
        }
    }

    public static TipoSexoAnimal obter(char tipo) {
        TipoSexoAnimal resposta = MACHO;
        for (TipoSexoAnimal v : values()) {
            if (v.valor == tipo) {
                resposta = v;
                break;
            }
        }
        return resposta;
    }
}
