package gt.udu.usac.cunoc.ingenieria.inventory.view;

import Production.Production;
import Production.facade.ProductionFacadeLocal;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author angelrg
 */
@FacesConverter(value = "productionConverter", managed = true)
public class ProductionConverter implements Converter<Production> {

    @EJB
    private ProductionFacadeLocal productionFacadelocal;

    @Override
    public Production getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Production production = productionFacadelocal.getProductionById(Integer.parseInt(value)).get();
            System.out.println("Codigo: " + value);
            return production;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Production value) {
        System.out.println("As String: " + value.getIdProduction().toString());
        return value.getIdProduction().toString();
    }

}
