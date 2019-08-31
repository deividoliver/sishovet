package br.com.sishovet.bean;

import br.com.sishovet.entidade.Especie;
import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.rn.EspecieRN;
import br.com.sishovet.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author greenhand
 */
@ManagedBean
@RequestScoped
public class EspecieBean {

//    ENTIDADES
    private Especie especie = new Especie();

//    LISTAS
    private List<Especie> especies = new ArrayList<>();

//    RN'S
    private final EspecieRN ESPECIE_RN = new EspecieRN();

    @PostConstruct
    public void init() {
        especies = ESPECIE_RN.obterTodas();
    }

//    GETERS E SETERS
    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public List<Especie> getEspecies() {
        return especies;
    }

    public void setEspecies(List<Especie> especies) {
        this.especies = especies;
    }

//    DEMAIS MÉTODOS
    public void salvar() {
        if (ESPECIE_RN.salvar(especie)) {
            UtilBean.criarMensagemDeInformacao("Especie salva!");
        } else {
            UtilBean.criarMensagemDeErro("Especie não foi salva, tente novamente!");
        }
    }

}
