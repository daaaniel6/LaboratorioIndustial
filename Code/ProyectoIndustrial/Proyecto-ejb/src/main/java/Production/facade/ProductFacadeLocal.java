package Production.facade;

import Production.Product;
import Production.exceptions.MandatoryAttributeProductionException;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author angelrg
 */
@Local
public interface ProductFacadeLocal {

    /**
     * Return the product base on the ID
     *
     * @param id
     * @return
     */
    public Optional<Product> getProductById(Integer id);

    /**
     * use the ID must be equals and in case of the name must be like
     *
     * if id and name is empty return all objects in DB
     *
     * @param id
     * @param name
     * @return
     */
    public List<Product> findProduct(Integer id, String name);

    /**
     * Create the new product
     *
     * @param product
     * @return
     * @throws MandatoryAttributeProductionException
     */
    public Product createProduct(Product product) throws MandatoryAttributeProductionException;

    /**
     * update a product
     *
     * @param product
     * @return
     */
    public Product updateProduct(Product product);

}
