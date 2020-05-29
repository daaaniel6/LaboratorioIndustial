package gt.edu.usac.cunoc.ingenieria.design.converter;

import Design.Design;
import Production.facade.ProductionFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.util.Optional;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author angelrg
 */
@FacesConverter(value = "designConverter", managed = true)
public class designConverter implements Converter {

    @EJB
    private ProductionFacadeLocal productionFacade;

    @Override
    public Design getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Optional<Design> design = productionFacade.findDesignByID(Integer.parseInt(value));
            if (design.isPresent()) {
                return design.get();
            } else {
                MessageUtils.addErrorMessage("Diseño desconocido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object design) {
        return ((Design) design).getIdDesign().toString();
    }

}
