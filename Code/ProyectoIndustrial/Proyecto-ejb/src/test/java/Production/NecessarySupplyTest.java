package Production;

import Design.Design;
import Supply.Supply;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NecessarySupplyTest {
    
    @Test
    public void testSetAndGetIdNecessarySupply() throws Exception {
        // Arrange
        NecessarySupply testNecessarySupply = new NecessarySupply();
        int testVal = 100;
        
        // Act
        testNecessarySupply.setIdNecessarySupply(testVal);
        int result = testNecessarySupply.getIdNecessarySupply();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }
    
    @Test
    public void testSetAndGetIdDesign() throws Exception {
        // Arrange
        NecessarySupply testNecessarySupply = new NecessarySupply();
        Design testVal = new Design();
        
        // Act
        testNecessarySupply.setDesignId(testVal);
        Design result = testNecessarySupply.getDesignId();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }
    
        
    /*@Test
    
    public void testSetAndGetIdSupply() throws Exception {
        // Arrange
        NecessarySupply testNecessarySupply = new NecessarySupply();
        Supply testVal = new Supply();
        
        // Act
        testNecessarySupply.setSupplyCode(testVal);
        Supply result = testNecessarySupply.getStep();
        
        // Assert
        Assert.assertEquals(result, testVal);
        
    }*/
    
    
}
