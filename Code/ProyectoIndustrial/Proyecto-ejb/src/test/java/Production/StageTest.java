package Production;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class StageTest {

    //stage builder test
    @Test
    public void stageBuilderTest() throws Exception {
        // Arrange
        Integer idStageTest = 1;
        String nameTest = "teset";
        String descriptionTest = "test";
        

        // Act
        Stage stageTest = new Stage(idStageTest, nameTest, descriptionTest);
        Stage result = stageTest;
        // Assert
        Assert.assertEquals(result, stageTest);
    }

    //set and get of idStage test
    @Test
    public void testSetAndGetIdStage() throws Exception {
        // Arrange
        Stage testStage = new Stage();
        int testVal = 1;

        // Act
        testStage.setIdStage(testVal);
        int result = testStage.getIdStage();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    //set and get of name test
    @Test
    public void testSetAndGetName() throws Exception {
        // Arrange
        Stage testStage = new Stage();
        String testVal = "test";

        // Act
        testStage.setName(testVal);
        String result = testStage.getName();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    //set and get of description test
    @Test
    public void testSetAndGetDescription() throws Exception {
        // Arrange
        Stage testStage = new Stage();
        String testVal = "test";

        // Act
        testStage.setDescription(testVal);
        String result = testStage.getDescription();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    

    //set and get of lineProductionId test
    @Test
    public void testSetAndGetProductionId() throws Exception {
        // Arrange
        Stage testStage = new Stage();
        Production production = new Production();
        

        // Act
        testStage.setProductionId(production);
        Production result = testStage.getProductionId();

        // Assert
        Assert.assertEquals(result, production);
    }

    
    @Test
    public void testEquals() throws Exception {
        // Arrange
        Stage testStage = new Stage();
        Stage result = testStage;

        // Act
        testStage.equals(result);

        // Assert
        Assert.assertEquals(result, testStage);
    }

}
