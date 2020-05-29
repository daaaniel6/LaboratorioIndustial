package Production.service;

import Production.Product;
import Production.service.ProductService;
import Production.exceptions.MandatoryAttributeProductionException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductServiceTest {

    @Test
    public void createProductTest() throws MandatoryAttributeProductionException {
        // Arrange
        Product product = new Product();
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.doNothing().when(entityManager).persist(product);

        ProductService productService = new ProductService();
        productService.setEntityManager(entityManager);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        Assert.assertEquals(result, product);
    }

    @Test
    public void updateAllProductTest() {
        // Arrange
        Integer carnet = 1;
        String name = "name";
        String description = "a";
        
        Product product = new Product(carnet, description, name);
        Product productMod = new Product(carnet, name, description);
        
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        
        ProductService productService = new ProductService();
        productService.setEntityManager(entityManager);
        
        Mockito.when(entityManager.find(Product.class, carnet)).thenReturn(product);

        // Act
        Product result = productService.updateProduct(productMod);

        //Assert
        Assert.assertEquals(result, productMod);

    }

    @Test
    public void updateNullProductTest() {
        // Arrange
        Integer carnet = 1;
        String name = null;
        String description = null;
        
        Product product = new Product(carnet, "name", "desc");
        Product productNull = new Product(carnet, name, description);
        
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        
        ProductService productService = new ProductService();
        productService.setEntityManager(entityManager);
        
        Mockito.when(entityManager.find(Product.class, carnet)).thenReturn(product);

        // Act
        Product result = productService.updateProduct(productNull);

        //Assert
        Assert.assertEquals(result, product);

    }

}
