package User;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void setAndGetCarnet(){
        
        // Arrange
        User testUser=new User();
        int testVal=100;
        
        // Act
        testUser.setCarnet(testVal);
        int result=testUser.getCarnet();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetName(){
        
        // Arrange
        User testUser=new User();
        String testVal="name";
        
        // Act
        testUser.setName(testVal);
        String result=testUser.getName();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetEmail(){
        
        // Arrange
        User testUser=new User();
        String testVal="email";
        
        // Act
        testUser.setEmail(testVal);
        String result=testUser.getEmail();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetPhone(){
        
        // Arrange
        User testUser=new User();
        int testVal=48523658;
        
        // Act
        testUser.setPhone(testVal);
        int result=testUser.getPhone();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetPassword(){
        
        // Arrange
        User testUser=new User();
        String testVal="password";
        
        // Act
        testUser.setPassword(testVal);
        String result=testUser.getPassword();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetState(){
        
        // Arrange
        User testUser=new User();
        Boolean testVal=true;
        
        // Act
        testUser.setState(testVal);
        Boolean result=testUser.getState();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetRoluser(){
        
        // Arrange
        User testUser=new User();
        RolUser testVal=new RolUser();
        
        // Act
        testUser.setRolUser(testVal);
        RolUser result=testUser.getRolUser();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
    
    @Test
    public void setAndGetCareer(){
        
        // Arrange
        User testUser=new User();
        Career testVal=new Career();
        
        // Act
        testUser.setCareer(testVal);
        Career result=testUser.getCareer();
        
        //asert        
        Assert.assertEquals(result,testVal);
    }
}
