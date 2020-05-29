package Production.service;

import Production.Stage;
import Production.exceptions.MandatoryAttributeProductionException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class StageServiceTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);
    StageService stageService = new StageService();

    @Test
    public void editProductionTest() throws MandatoryAttributeProductionException {
        //Arrange
        Stage stage = new Stage(null, "name", "description");
        stageService.setEntityManager(entityManager);

        Mockito.when(entityManager.merge(stage)).thenReturn(stage);

        //Act
        Stage result = stageService.edit(stage);
        //Assert
        Assert.assertEquals(result, stage);

    }

    //Test if editar 
    @Test(expectedExceptions = MandatoryAttributeProductionException.class,
            expectedExceptionsMessageRegExp = "Nombre nulo")
    public void MandatoryAttributeProductionExceptionNameEdit() throws Exception {
        //Arrange
        Stage stage = new Stage(null, null, "description");

        stageService.setEntityManager(entityManager);

        //Act
        stageService.edit(stage);

    }

    @Test(expectedExceptions = MandatoryAttributeProductionException.class,
            expectedExceptionsMessageRegExp = "Descripcion nula")
    public void MandatoryAttributeProductionExceptionUnityEdit() throws Exception {
        //Arrange
        Stage stage = new Stage(null, "name", null);
        stageService.setEntityManager(entityManager);
        //Act
        stageService.edit(stage);

    }

}
