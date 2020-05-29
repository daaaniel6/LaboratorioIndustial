package Production;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class ProductionTest {

    //production builder test
    @Test
    public void productionBuilderTest() throws Exception {
        // Arrange
        Integer idLineProductionTest = 1;
        String nameTest = "test";
        boolean stateTest = true;
        int unityTest = 2;
        

         //Act
        Production productionTest = new Production(idLineProductionTest);
        Production result = productionTest;
        // Assert
        Assert.assertEquals(result, productionTest);
    }

    //set and get of idLineProduction test
    @Test
    public void testSetAndGetIdProduction() throws Exception {
        // Arrange
        Production testProduction = new Production();
        int testVal = 1;

        // Act
        testProduction.setIdProduction(testVal);
        int result = testProduction.getIdProduction();

        // Assert
        Assert.assertEquals(result, testVal);
    }
    
    //set and get of name test
    @Test
    public void testSetAndGetName() throws Exception {
        // Arrange
        Production testProduction = new Production();
        String testVal = "test";

        // Act
        testProduction.setName(testVal);
        String result = testProduction.getName();

        // Assert
        Assert.assertEquals(result, testVal);
    }
    
    //set and get of unity test
    @Test
    public void testSetAndGetUnity() throws Exception {
        // Arrange
        Production testProduction = new Production();
        boolean testVal = true;

        // Act
        testProduction.setState(testVal);
        boolean result = testProduction.getState();

        // Assert
        Assert.assertEquals(result, testVal);
    }
    
    
    //set and get of state test
    @Test
    public void testSetAndGetState() throws Exception {
        // Arrange
        Production testProduction = new Production();
        boolean testVal = true;

        // Act
        testProduction.setState(testVal);
        boolean result = testProduction.getState();

        // Assert
        Assert.assertEquals(result, testVal);
    }
    
    
    //set and get of unity test
    @Test
    public void testSetAndQuantit() throws Exception {
        // Arrange
        Production testProduction = new Production();
        int testVal = 1;

        // Act
        testProduction.setQuantity(testVal);
        int result = testProduction.getQuantity();

        // Assert
        Assert.assertEquals(result, testVal);
    }
    
    //set and get of ProductId test
    @Test
    public void testSetAndGetProduct() throws Exception {
        // Arrange
        Production testProduction = new Production();
        Product product = new Product();

        // Act
        testProduction.setProductId(product);
        Product result = testProduction.getProductId();

        // Assert
        Assert.assertEquals(result, product);
    }
    
    //equals test
    @Test
    public void testEquals() throws Exception {
        // Arrange
        Production testProduction = new Production();
        Production result = testProduction;

        // Act
        testProduction.equals(result);

        // Assert
        Assert.assertEquals(result, testProduction);
    }
    
    
    
}
