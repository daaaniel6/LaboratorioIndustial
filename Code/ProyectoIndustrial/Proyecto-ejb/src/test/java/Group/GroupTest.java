package Group;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Proyecto-ejb
 *
 * @author jose - 18.09.2019
 * @Title: GroupTest
 * @Description: description
 *
 * Changes History
 */
public class GroupTest {

    @Test
    public void testSetAndGetIdGroup() throws Exception {
        // Arrange
        GroupIndustrial testGroup = new GroupIndustrial();
        int testVal = 100;

        // Act
        testGroup.setIdGroup(testVal);
        int result = testGroup.getIdGroup();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testSetAndGetInformation() throws Exception {
        //Arrange
        GroupIndustrial testGroup = new GroupIndustrial();
        String testText = "Este es un texto de prueba";

        //Act
        testGroup.setInformation(testText);
        String result = testGroup.getInformation();

        //Assert
        Assert.assertEquals(result, testText);
    }

    @Test
    public void testSetAndGetSection() throws Exception {
        //Arange
        GroupIndustrial testGroup = new GroupIndustrial();
        String testText = "A";

        //Act
        testGroup.setSection(testText);
        String result = testGroup.getSection();

        //Assert
        Assert.assertEquals(result, testText);
    }
}
