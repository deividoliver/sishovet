package br.com.sishovet.bean.converter;
import br.com.sishovet.entidade.Proprietario;
import br.com.sishovet.rn.ProprietarioRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter(value = "proprietarioConverter")

public class ProprietarioConverter implements Converter{
    private ProprietarioRN PROPRIETARIO_RN = new ProprietarioRN();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.trim().isEmpty() || "null".equalsIgnoreCase(string)) {
            return null;
        }
        try {
            return PROPRIETARIO_RN.obter(Integer.valueOf(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || !(o instanceof Proprietario)) {
            return null;
        }
        Proprietario proprietario = (Proprietario) o;
        if (proprietario.getId() == null) {
            return null;
        }
        return String.valueOf(proprietario.getId());
    }  
}
