package Production;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommentaryTest {

    @Test
    public void testSetAndGetIdCommentary() throws Exception {
        // Arrange
        Commentary commentaryTest = new Commentary();
        int testVal = 50;

        // Act
        commentaryTest.setIdCommentary(testVal);
        int result = commentaryTest.getIdCommentary();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testSetAndGetText() throws Exception {
        // Arrange
        Commentary commentaryTest = new Commentary();
        String testVal = "Hola Mundo";

        // Act
        commentaryTest.setCommentary(testVal);
        String result = commentaryTest.getCommentary();

        // Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testSetAndGetPaso() throws Exception {
        // Arrange
        Commentary commentaryTest = new Commentary();
        Step testVal = new Step();

        // Act
        commentaryTest.setIdStep(testVal);
        Step result = commentaryTest.getIdStep();

        // Assert
        Assert.assertEquals(result, testVal);
    }

}
