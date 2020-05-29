package Modify.repository;

import Modify.ModifySupply;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class ModifySupplyRepositoryTest {

    @Test
    public void findByIdTest(){
        int modifySupplyId = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        ModifySupply modifySupply = new ModifySupply();
        
        Mockito.when(
                entityManager.find(ModifySupply.class, modifySupplyId)
        ).thenReturn(modifySupply);
        
        ModifySupplyRepository modifySupplyRepository = new ModifySupplyRepository();
        modifySupplyRepository.setEntityManager(entityManager);
        
        // Act
        Optional<ModifySupply> result = modifySupplyRepository.getById(modifySupplyId);
        
        // Assert
        Assert.assertEquals(result.get(), modifySupply);
    }
    
    @Test
    public void getMidificationByUserTest(){
        int userId = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(ModifySupplyRepository.FIND_MODIFICATION_BY_USER)
        ).thenReturn(query);
        Mockito.when(
                query.setParameter(ModifySupplyRepository.NAME_USER_ID, userId)
        ).thenReturn(query);
        
        List<ModifySupply> modifyList = new LinkedList<ModifySupply>();
        Mockito.when(query.getResultList()).thenReturn(modifyList);
        
        ModifySupplyRepository modifySupplyRepository = new ModifySupplyRepository();
        modifySupplyRepository.setEntityManager(entityManager);
        
        // Act
        List<ModifySupply> result = modifySupplyRepository.getModificationByUser(userId);
        
        // Assert
        Assert.assertEquals(result, modifyList);
    }
    
    @Test
    public void getMidificationBySupplyTest(){
        int supplyId = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(ModifySupplyRepository.FIND_MODIFICATION_BY_SUPPLY)
        ).thenReturn(query);
        Mockito.when(
                query.setParameter(ModifySupplyRepository.NAME_SUPPLY_ID, supplyId)
        ).thenReturn(query);
        
        List<ModifySupply> modifyList = new LinkedList<ModifySupply>();
        Mockito.when(query.getResultList()).thenReturn(modifyList);
        
        ModifySupplyRepository modifySupplyRepository = new ModifySupplyRepository();
        modifySupplyRepository.setEntityManager(entityManager);
        
        // Act
        List<ModifySupply> result = modifySupplyRepository.getModificationBySupply(supplyId);
        
        // Assert
        Assert.assertEquals(result, modifyList);
    }
    
    @Test
    public void getAllTest(){
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(ModifySupplyRepository.GET_ALL_SUPPLY)
        ).thenReturn(query);
        
        List<ModifySupply> modifyList = new LinkedList<ModifySupply>();
        Mockito.when(query.getResultList()).thenReturn(modifyList);
        
        ModifySupplyRepository modifySupplyRepository = new ModifySupplyRepository();
        modifySupplyRepository.setEntityManager(entityManager);
        
        // Act
        List<ModifySupply> result = modifySupplyRepository.getAll();
        
        // Assert
        Assert.assertEquals(result, modifyList);
    }
    
    
}
