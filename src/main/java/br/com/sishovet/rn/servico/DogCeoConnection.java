/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn.servico;


import br.com.sishovet.entidade.Especie;
import br.com.sishovet.entidade.Raca;
import br.com.sishovet.resultado.DogCeoResult;
import br.com.sishovet.rn.RacaRN;
import br.com.sishovet.util.InternetUtil;
import br.com.sishovet.util.JSONUtil;

/**
 *
 * @author BPMLAB-01
 */
public class DogCeoConnection {

    public static DogCeoResult salvarListaDeRacas() {
        DogCeoResult resposta = null;
        RacaRN racaRN = new RacaRN();
        Raca raca;
        try {
            String URL = "https://dog.ceo/api/breeds/list";
            String jsonString = InternetUtil.acessarURL(URL);
            resposta = JSONUtil.fromString(jsonString, DogCeoResult.class);
            System.out.println("Salvando no Banco ...");
            for (String r : resposta.getMessage()) {
                r = r.substring(0,1).toUpperCase().concat(r.substring(1));
                raca = new Raca(null, r);
                Especie especie = new Especie();
                especie.setId(1);
                raca.setEspecie(especie);
                if (!racaRN.salvar(raca)) {
                    break;
                }
            }
            System.out.println("Terminou");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }
    
    public static void main(String[] args) {
        salvarListaDeRacas();
    }

}
