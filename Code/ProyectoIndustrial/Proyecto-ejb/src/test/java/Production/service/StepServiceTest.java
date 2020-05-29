package Production.service;

import Production.Step;
import User.exception.UserException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class StepServiceTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);
    StepService stepService = new StepService();

    @Test
    public void editTest() {
        int id = 1;
        String name = "name";
        String description = "description";

        Step step = new Step(id, name, description);

        stepService.setEntityManager(entityManager);

        Mockito.when(entityManager.find(Step.class, id)).thenReturn(step);
        Step result = null;

        try {
            result = stepService.edit(step);
        } catch (UserException e) {
        }

        Assert.assertEquals(result, step);
    }

}
