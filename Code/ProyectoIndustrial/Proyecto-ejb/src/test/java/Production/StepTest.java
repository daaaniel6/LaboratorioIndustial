/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class StepTest {

    //stage builder test
    @Test
    public void stageBuilderTest() throws Exception {
        // Arrange
        Integer idStep = 1;
        String name = "test";
        String description = "test";
        
        // Act
        Step stepTest = new Step(idStep, name, description);
        Step result = stepTest;
        // Assert
        Assert.assertEquals(result, stepTest);
    }

    //set and get of idStep test
    @Test
    public void testSetAndGetIdStep() throws Exception {
        // Arrange
        Step testStep = new Step();
        int testVal = 1;

        // Act
        testStep.setIdStep(testVal);
        int result = testStep.getIdStep();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    //set and get of name test
    @Test
    public void testSetAndGetName() throws Exception {
        // Arrange
        Step testStep = new Step();
        String testVal = "test";

        // Act
        testStep.setName(testVal);
        String result = testStep.getName();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    //set and get of description test
    @Test
    public void testSetAndGetDescription() throws Exception {
        // Arrange
        Step testStep = new Step();
        String testVal = "test";

        // Act
        testStep.setDescription(testVal);
        String result = testStep.getDescription();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    //set and get of StageId test
    @Test
    public void testSetAndGetStageId() throws Exception {
        // Arrange
        Step testStep = new Step();
        Stage stage = new Stage();

        // Act
        testStep.setStageId(stage);
        Stage result = testStep.getStageId();

        // Assert
        Assert.assertEquals(result, stage);
    }

    

    //equals test
    @Test
    public void testEquals() throws Exception {
        // Arrange
        Step testStep = new Step();
        Step result = testStep;

        // Act
        testStep.equals(result);

        // Assert
        Assert.assertEquals(result, testStep);
    }
}
