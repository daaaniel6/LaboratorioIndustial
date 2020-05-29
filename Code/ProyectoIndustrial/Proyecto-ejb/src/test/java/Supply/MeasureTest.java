
package Supply;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeasureTest {
    Measure testMeasure = new Measure();
    int testIdMeasure = 100;
    String testName = "name test of a Measure";
    Supply testSupply1 = new Supply();
    Supply testSupply2 = new Supply();
    List<Supply> testSupplyCollection = new ArrayList<Supply>();
    Measure testMeasureFullConstructor = new Measure(testIdMeasure, testName, testSupplyCollection);
    
          
    @Test
    public void testSetAndGetIdMeasure() throws Exception {
        testMeasure.setIdMeasure(testIdMeasure);
        int result = testMeasure.getIdMeasure();
        
        Assert.assertEquals(result,testIdMeasure);
    }
    
    @Test
    public void testSetAndGetName() throws Exception {
        testMeasure.setName(testName);
        String result = testMeasure.getName();
        
        Assert.assertEquals(result,testName);
    }
    
    @Test
    public void testSetAndGetCollectionSupply() throws Exception {
        testSupplyCollection.add(testSupply1);
        testSupplyCollection.add(testSupply2);
        testMeasure.setSupplyCollection(testSupplyCollection);
        List<Supply> result = testMeasure.getSupplyCollection();
      
        Assert.assertEquals(result,testSupplyCollection);
    }
}