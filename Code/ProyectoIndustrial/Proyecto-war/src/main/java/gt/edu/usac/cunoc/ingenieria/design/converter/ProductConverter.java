/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.design.converter;

import Production.Product;
import Production.Production;
import Production.facade.ProductionFacadeLocal;
import java.util.Optional;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author daniel
 */
@FacesConverter(value = "productConverter", managed = true)
public class ProductConverter implements Converter {

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String idProduct) {
        try {
            Product product = productionFacadeLocal.getProductById(Integer.parseInt(idProduct)).get();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object product) {
        return ((Product) product).getIdProduct().toString();
    }

}
