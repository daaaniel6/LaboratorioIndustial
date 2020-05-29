package Production.facade;

import Production.Product;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.repository.ProductRepository;
import Production.service.ProductService;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author angelrg
 */
@Stateless
public class ProductFacade implements ProductFacadeLocal {

    private ProductService productService;
    private ProductRepository productRepository;

    @EJB
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @EJB
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> findProduct(Integer id, String name) {
        return productRepository.findProduct(id, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product createProduct(Product product) throws MandatoryAttributeProductionException {
        return productService.createProduct(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

}
