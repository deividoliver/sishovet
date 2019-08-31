package br.com.sishovet.tipo;

/**
 *
 * @author GreenHand
 */
public enum TipoMes {
    JANEIRO(1, "Janeiro"),
    FEVEREIRO(2, "Fevereiro"),
    MARCO(3, "Março"),
    ABRIL(4, "Abril"),
    MAIO(5, "Maio"),
    JUNHO(6, "Junho"),
    JULHO(7, "Julho"),
    AGOSTO(8, "Agosto"),
    SETEMBRO(9, "Setembro"),
    OUTUBRO(10, "Outubro"),
    NOVEMBRO(11, "Novembro"),
    DEZEMBRO(12, "Dezembro");

    private TipoMes(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    private int valor;
    private String descricao;

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoMes obter(int valor) {
        TipoMes resposta = JANEIRO;
        TipoMes[] values = values();
        for (TipoMes t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        values = null;
        return resposta;
    }
}
