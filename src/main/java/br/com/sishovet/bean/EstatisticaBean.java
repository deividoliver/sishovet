/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.bean;

import br.com.sishovet.rn.EstatisticaRN;
import br.com.sishovet.rn.dados.AtendimentosPorMes;
import br.com.sishovet.tipo.TipoMes;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author GreenHand
 */
@ManagedBean
@RequestScoped
public class EstatisticaBean {

//    VARIÁVEIS
    private int quantidadeDeAnimais = 0;
    private int quantidadeDeProprietarios = 0;
    private int quantidadeDeUsuarios = 0;
    private int quantidadeDeMedicosAtivos = 0;
    private int maiorQtdAtendimento = 0;
    private int menorQtdAtendimento = 0;
    private LineChartModel atendimentosDoAnoMes;

//    RN'S
    private final EstatisticaRN ESTATISTICA_RN = new EstatisticaRN();

    @PostConstruct
    public void init() {
        quantidadeDeAnimais = ESTATISTICA_RN.obterQuantidadeDeAnimais();
        quantidadeDeProprietarios = ESTATISTICA_RN.obterQuantidadeDeProprietarios();
        quantidadeDeUsuarios = ESTATISTICA_RN.obterQuantidadeDeUsuarios();
        quantidadeDeMedicosAtivos = ESTATISTICA_RN.obterQuantidadeDeMedicosAtivos();
        criarModeloDeLinhas();
    }

//    GETERS E SETERS
    public LineChartModel getAtendimentosDoAnoMes() {
        return atendimentosDoAnoMes;
    }

    public void setAtendimentosDoAnoMes(LineChartModel atendimentosDoAnoMes) {
        this.atendimentosDoAnoMes = atendimentosDoAnoMes;
    }

    public int getQuantidadeDeAnimais() {
        return quantidadeDeAnimais;
    }

    public void setQuantidadeDeAnimais(int quantidadeDeAnimais) {
        this.quantidadeDeAnimais = quantidadeDeAnimais;
    }

    public int getQuantidadeDeProprietarios() {
        return quantidadeDeProprietarios;
    }

    public void setQuantidadeDeProprietarios(int quantidadeDeProprietarios) {
        this.quantidadeDeProprietarios = quantidadeDeProprietarios;
    }

    public int getQuantidadeDeUsuarios() {
        return quantidadeDeUsuarios;
    }

    public void setQuantidadeDeUsuarios(int quantidadeDeUsuarios) {
        this.quantidadeDeUsuarios = quantidadeDeUsuarios;
    }

    public int getQuantidadeDeMedicosAtivos() {
        return quantidadeDeMedicosAtivos;
    }

    public void setQuantidadeDeMedicosAtivos(int quantidadeDeMedicosAtivos) {
        this.quantidadeDeMedicosAtivos = quantidadeDeMedicosAtivos;
    }

//    MÉTODOS
    private void criarModeloDeLinhas() {
        atendimentosDoAnoMes = iniciarCategoriaDeModelo();
        atendimentosDoAnoMes.setTitle("Atendimentos por mês");
        atendimentosDoAnoMes.setLegendPosition("e");
        atendimentosDoAnoMes.setShowPointLabels(true);
        atendimentosDoAnoMes.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
        Axis yAxis = atendimentosDoAnoMes.getAxis(AxisType.Y);
        yAxis.setLabel("Atendimentos");
        yAxis.setMin(menorQtdAtendimento - 100);
        yAxis.setMax(maiorQtdAtendimento + 100);
    }

    private LineChartModel iniciarCategoriaDeModelo() {
        List<AtendimentosPorMes> listAtendimentosPorMes = ESTATISTICA_RN.obterAtendimentosPorMesDoAno();

        if (listAtendimentosPorMes.size() > 0) {
            maiorQtdAtendimento = listAtendimentosPorMes.get(0).getAtendimentos().intValue();
            menorQtdAtendimento = listAtendimentosPorMes.get(0).getAtendimentos().intValue();
        }
        LineChartModel model = new LineChartModel();

        ChartSeries meses = new ChartSeries();
        meses.setLabel("Atendimentos");

        for (AtendimentosPorMes a : listAtendimentosPorMes) {
            meses.set(TipoMes.obter(a.getMes()).getDescricao(), a.getAtendimentos().intValue());

            if (maiorQtdAtendimento < a.getAtendimentos().intValue()) {
                maiorQtdAtendimento = a.getAtendimentos().intValue();
            }

            if (menorQtdAtendimento > a.getAtendimentos().intValue()) {
                menorQtdAtendimento = a.getAtendimentos().intValue();
            }
        }

        model.addSeries(meses);
        return model;

    }

}
