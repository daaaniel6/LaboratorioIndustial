package Modify;

import Supply.Supply;
import User.User;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class ModifySupplyTest {

    @Test
    public void testGetAndSetIdModifySupply() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        int testVal = 201912345;

        //Act
        modifySupply.setIdModifySupply(testVal);
        int result = modifySupply.getIdModifySupply();

        //Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testGetAndSetUser() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        User user = new User();

        //Act
        modifySupply.setCarnetUser(user);
        User result = modifySupply.getCarnetUser();
        
        //Assert
        Assert.assertEquals(result, user);
    }

    @Test
    public void testGetAndSetSupply() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        Supply supply = new Supply();

        //Act
        modifySupply.setSupplyCode(supply);
        Supply result = modifySupply.getSupplyCode();
        
        //Assert
        Assert.assertEquals(result, supply);
    }

    @Test
    public void testGetAndSetQuantity() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        double testVal = 23;

        //Act
        
        modifySupply.setQuantity((int) testVal);
        double result = modifySupply.getQuantity();

        //Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testGetAndSetDate() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        LocalDate testDate = LocalDate.now();

        //Act
        modifySupply.setDate(testDate);
        LocalDate result = modifySupply.getDate();

        //Assert
        Assert.assertEquals(result, testDate);
    }

    @Test
    public void testGetAndSetNota() throws Exception {
        //Arrange
        ModifySupply modifySupply = new ModifySupply();
        String testText = "Esta es una nota de prueba";

        //Act
        modifySupply.setNote(testText);
        String result = modifySupply.getNote();
        
        //Assert
        Assert.assertEquals(result, testText);
    }

}
