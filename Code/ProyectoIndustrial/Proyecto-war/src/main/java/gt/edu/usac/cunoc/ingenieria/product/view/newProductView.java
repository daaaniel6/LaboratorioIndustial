package gt.edu.usac.cunoc.ingenieria.product.view;

import Production.Product;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class newProductView implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    Product product;

    public void createProduct() {
        try {
            productFacade.createProduct(product);
            cleanProduct();
            MessageUtils.addSuccessMessage("Producto agregado");
        } catch (MandatoryAttributeProductionException e) {
            MessageUtils.addErrorMessage(e.getMessage());
        }
    }

    public Product getProduct() {
        if (product == null) {
            product = new Product();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private void cleanProduct() {
        setProduct(null);
    }

}
