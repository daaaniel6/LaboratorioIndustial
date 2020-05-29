package User;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RolUserTest {
    
    @Test
    public void testSetAndGetIdRolUser(){
         // Arrange
        RolUser testRolUser=new RolUser();
        int testVal=100;
        
        // Act
        testRolUser.setIdRolUser(testVal);
        int result=testRolUser.getIdRolUser();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void testSetAndGetName(){
         // Arrange
        RolUser testRolUser=new RolUser();
        String testVal="name";
        
        // Act
        testRolUser.setName(testVal);
        String result=testRolUser.getName();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
}
