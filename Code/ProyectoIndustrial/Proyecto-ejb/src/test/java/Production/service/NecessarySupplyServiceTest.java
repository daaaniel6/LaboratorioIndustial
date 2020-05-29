package Production.service;

import Production.NecessarySupply;
import Production.Step;
import Supply.Supply;
import Production.exceptions.MandatoryAttributeProductionException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NecessarySupplyServiceTest {
    
    
    @Test
    public void createNecessarySupply() throws MandatoryAttributeProductionException{
        // Arrange
        NecessarySupply necessarySupply = new NecessarySupply();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        
        Mockito.doNothing().when(entityManager).persist(necessarySupply);
                
        NecessarySupplyService necessarySupplyService = new NecessarySupplyService();
        necessarySupplyService.setEntityManager(entityManager);
        
        // Act
        NecessarySupply result = necessarySupplyService.createNecessarySupply(necessarySupply);
        
        // Assert
        Assert.assertEquals(result, necessarySupply);
    }
    
    
    @Test
    public void updateNecessarySupplyIdSupplyTest() throws MandatoryAttributeProductionException{
        // Arrange
        Step step = null;
        Supply supply = new Supply();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        NecessarySupply necessarySupply = Mockito.mock(NecessarySupply.class);
        
        Mockito.when(
                entityManager.merge(necessarySupply)
        ).thenReturn(necessarySupply);
        
        NecessarySupplyService necessarySupplyService = new NecessarySupplyService();
        necessarySupplyService.setEntityManager(entityManager);
        
        // Act
        NecessarySupply result = necessarySupplyService.updateNecessarySupply(necessarySupply, step, supply);
        
        //Assert
        Assert.assertEquals(result, necessarySupply);
        
    }
    
    
    @Test
    public void updateNecessarySupplyIdStepTest() throws MandatoryAttributeProductionException{
        // Arrange
        Step step = new Step();
        Supply supply = null;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        NecessarySupply necessarySupply = Mockito.mock(NecessarySupply.class);
        
        Mockito.when(
                entityManager.merge(necessarySupply)
        ).thenReturn(necessarySupply);
        
        NecessarySupplyService necessarySupplyService = new NecessarySupplyService();
        necessarySupplyService.setEntityManager(entityManager);
        
        // Act
        NecessarySupply result = necessarySupplyService.updateNecessarySupply(necessarySupply, step, supply);
        
        //Assert
        Assert.assertEquals(result, necessarySupply);
        
    }
    
    
}
