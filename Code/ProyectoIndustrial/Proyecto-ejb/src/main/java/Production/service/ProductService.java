package Production.service;

import Production.ExtraCost;
import Production.Product;
import Production.Production;
import Production.exceptions.MandatoryAttributeProductionException;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ProductService {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @param product
     *
     * @return
     *
     * @throws MandatoryAttributeProductionException
     */
    public Product createProduct(Product product) throws MandatoryAttributeProductionException {
        if (product == null) {
            throw new MandatoryAttributeProductionException("Product is null");
        }
        entityManager.persist(product);
        return product;
    }

    
    
    /**
     *
     * @param product
     *
     * @return
     */
    public Product updateProduct(Product product) {
        Product foundProduct = entityManager.find(Product.class, product.getIdProduct());
        if ((product.getName() != null) && (!product.getName().isEmpty())) {
            foundProduct.setName(product.getName());
        }
        if ((product.getDescription() != null) && (!product.getDescription().isEmpty())) {
            foundProduct.setDescription(product.getDescription());
        }
        return foundProduct;
    }

}
