package gt.edu.usac.cunoc.ingenieria.supply.converter;

import Supply.Measure;
import Supply.facade.SupplyFacadeLocal;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "measureConverter", managed = true)

public class measureConverter implements Converter {

    @EJB
    private SupplyFacadeLocal supplyFacade;

    @Override
    public Measure getAsObject(FacesContext context, UIComponent component, String measureId) {
        try {
            Measure measure = supplyFacade.getMeasureById(Integer.parseInt(measureId)).get();
            return measure;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object measure) {
        return ((Measure) measure).getIdMeasure().toString();
    }

}
