package gt.edu.usac.cunoc.ingenieria.production.group.converter;

import Group.GroupIndustrial;
import Group.facade.GroupFacadelocal;
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
@FacesConverter(value = "groupIndustrialConverter", managed = true)
public class groupIndustrialConverter implements Converter {

    @EJB
    private GroupFacadelocal groupFacade;

    @Override
    public GroupIndustrial getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Optional<GroupIndustrial> groupI = groupFacade.findById(Integer.parseInt(value));
            if (groupI.isPresent()) {
                return groupI.get();
            } else {
                MessageUtils.addErrorMessage("Grupo desconocido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object groupI) {
        return ((GroupIndustrial) groupI).getIdGroup().toString();
    }

}
