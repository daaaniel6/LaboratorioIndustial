package User;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CareerTest {
    @Test
    public void testSetAndGetIdCareer(){
        // Arrange
        Career testCareer=new Career();
        int testVal=100;
        
        // Act
        testCareer.setIdCareer(testVal);
        int result=testCareer.getIdCareer();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void testSetAndGetName(){
        // Arrange
        Career testCareer=new Career();
        String testVal="name";
        
        // Act
        testCareer.setName(testVal);
        String result=testCareer.getName();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
}
