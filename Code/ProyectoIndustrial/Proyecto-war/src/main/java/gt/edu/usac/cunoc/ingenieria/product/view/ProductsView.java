package gt.edu.usac.cunoc.ingenieria.product.view;

import Production.Product;
import Production.facade.ProductFacadeLocal;
import User.exception.UserException;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class ProductsView implements Serializable {

    @EJB
    private ProductFacadeLocal productFacade;

    List<Product> products = new ArrayList<>();
    Product selectedProduct = new Product();

    Integer code;
    String name;

    public void saveChanges(final String modalIdToClose) throws UserException {
        if (selectedProduct.getIdProduct() != null) {
            productFacade.updateProduct(selectedProduct);
            MessageUtils.addSuccessMessage("Se actualizo " + selectedProduct.getName());
        } else {
            MessageUtils.addErrorMessage("Debe elegir un Producto");
        }
        cleanSelectedProduct();
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }

    public void cleanSelectedProduct() {
        setSelectedProduct(null);
    }

    public void findProducts() {
        products.clear();
        setProducts(productFacade.findProduct(code, name));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        if (selectedProduct == null) {
            selectedProduct = new Product();
        }
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void cleanSearch() {
        setCode(null);
        setName(null);
    }

}
