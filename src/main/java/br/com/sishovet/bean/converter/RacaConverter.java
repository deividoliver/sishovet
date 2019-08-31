package br.com.sishovet.bean.converter;
import br.com.sishovet.entidade.Raca;
import br.com.sishovet.rn.RacaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter(value = "racaConverter")

public class RacaConverter implements Converter{
    private RacaRN RACA_RN = new RacaRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return RACA_RN.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Raca)) {
            return null;
        }
        Raca raca = (Raca) o;
        if (raca.getId() == null) {
            return null;
        }
        return String.valueOf(raca.getId());
    }  
}
