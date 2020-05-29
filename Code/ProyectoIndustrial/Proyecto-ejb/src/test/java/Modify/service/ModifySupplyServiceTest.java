package Modify.service;

import Modify.ModifySupply;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class ModifySupplyServiceTest {

    @Test
    public void createModifySupplyTest() {
        // Arrange
        ModifySupply modifySupply = new ModifySupply();
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.doNothing().when(entityManager).persist(modifySupply);

        ModifySupplyService modifySupplyService = new ModifySupplyService();
        modifySupplyService.setEntityManager(entityManager);

        // Act
        ModifySupply result = modifySupplyService.createModifySupply(modifySupply);

        // Assert
        Assert.assertEquals(result, modifySupply);
    }
}
