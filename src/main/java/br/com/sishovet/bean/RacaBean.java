package br.com.sishovet.bean;

import br.com.sishovet.entidade.Raca;
import br.com.sishovet.rn.RacaRN;
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
@RequestScoped
@ManagedBean
public class RacaBean {

//    ENTIDADES
    private Raca raca = new Raca();

//    LISTAS
    private List<Raca> racas = new ArrayList<Raca>();

//    RN'S'
    private final RacaRN racaRN = new RacaRN();

    @PostConstruct
    public void init() {
        racas = racaRN.obterTodos();
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public List<Raca> getRacas() {
        return racas;
    }

    public void setRacas(List<Raca> racas) {
        this.racas = racas;
    }

    public void salvar() {
        if (racaRN.salvar(raca)) {
            UtilBean.criarMensagemDeInformacao("Raça salva!");
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar Raça, tente novamente!");
        }
    }

}
