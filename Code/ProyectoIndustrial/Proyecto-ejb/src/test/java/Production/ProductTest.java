package Production;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ProductTest {
    
    
    @Test
    public void testSetAndGetIdProduct() throws Exception {
        // Arrange
        Product testProduct = new Product();
        int testVal = 100;
        
        // Act
        testProduct.setIdProduct(testVal);
        int result = testProduct.getIdProduct();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }
    
    
    @Test
    public void testSetAndGetName() throws Exception {
        // Arrange
        Product testProduct = new Product();
        String testVal = "Name 1";
        
        // Act
        testProduct.setName(testVal);
        String result = testProduct.getName();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }
    
    
    @Test
    public void testSetAndGetDescription() throws Exception {
        // Arrange
        Product testProduct = new Product();
        String testVal = "Description 1";
        
        // Act
        testProduct.setDescription(testVal);
        String result = testProduct.getDescription();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }
    
    
}
