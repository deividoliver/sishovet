package br.com.sishovet.bean.converter;
import br.com.sishovet.entidade.Especie;
import br.com.sishovet.rn.EspecieRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter(value = "especieConverter")

public class EspecieConverter implements Converter{
    private EspecieRN ESPECIE_RN = new EspecieRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return ESPECIE_RN.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Especie)) {
            return null;
        }
        Especie especie = (Especie) o;
        if (especie.getId() == null) {
            return null;
        }
        return String.valueOf(especie.getId());
    }  
}
