package Production.repository;

import Production.NecessarySupply;
import static Production.repository.NecessarySupplyRepository.GET_ALL;
import static Production.repository.NecessarySupplyRepository.FIND_BY_ID;
import static Production.repository.NecessarySupplyRepository.FIND_BY_STEP;
import static Production.repository.NecessarySupplyRepository.FIND_BY_SUPPLY;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NecessarySupplyRepositoryTest {
    
    
    @Test
    public void getNecessarySupplyByIdWithResult() throws Exception {
        // Arrange
        int id = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        Mockito.when(
                entityManager.createQuery(FIND_BY_ID, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("id", id)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenReturn(necessarySupply);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyById(id);

        // Assert
        Assert.assertEquals(result.get(), necessarySupply);
    }
    
    
    
    @Test
    public void getNecessarySupplyByIdWithNoResult() throws Exception {
        // Arrange
        int id = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(FIND_BY_ID, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("id", id)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenThrow(exception);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyById(id);

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
    
    
    @Test
    public void getNecessarySupplyByStepWithResult() throws Exception {
        // Arrange
        int id_step = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        Mockito.when(
                entityManager.createQuery(FIND_BY_STEP, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("step", id_step)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenReturn(necessarySupply);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyByStep(id_step);

        // Assert
        Assert.assertEquals(result.get(), necessarySupply);
    }
    
    
    
    @Test
    public void getNecessarySupplyByStepWithNoResult() throws Exception {
        // Arrange
        int id_step = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(FIND_BY_STEP, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("step", id_step)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenThrow(exception);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyByStep(id_step);

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
    
    
    @Test
    public void getNecessarySupplyBySupplyWithResult() throws Exception {
        // Arrange
        int id_supply = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        Mockito.when(
                entityManager.createQuery(FIND_BY_SUPPLY, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("supply", id_supply)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenReturn(necessarySupply);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyBySupply(id_supply);

        // Assert
        Assert.assertEquals(result.get(), necessarySupply);
    }
    
    
    
    @Test
    public void getNecessarySupplyBySupplyWithNoResult() throws Exception {
        // Arrange
        int id_supply = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        NecessarySupply necessarySupply = new NecessarySupply();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(FIND_BY_SUPPLY, NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("supply", id_supply)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenThrow(exception);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<NecessarySupply> result = necessarySupplyRepository.getNecessarySupplyBySupply(id_supply);

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
    
    @Test
    public void getAllWithResult() throws Exception {
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        List<NecessarySupply> necessarySupply = new LinkedList<NecessarySupply>();
        Mockito.when(
                entityManager.createQuery(GET_ALL,NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.getResultList()).thenReturn(necessarySupply);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<List<NecessarySupply>> result = necessarySupplyRepository.getAll();

        // Assert
        Assert.assertEquals(result.get(), necessarySupply);
    }
    
    
    
    @Test
    public void getAllWithNoResult() throws Exception {
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        List<NecessarySupply> necessarySupply = new LinkedList<NecessarySupply>();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(GET_ALL,NecessarySupply.class)
        ).thenReturn(type);
        Mockito.when(type.getResultList()).thenThrow(exception);
        NecessarySupplyRepository necessarySupplyRepository = new NecessarySupplyRepository();
        necessarySupplyRepository.setEntityManager(entityManager);

        // Act        
        Optional<List<NecessarySupply>> result = necessarySupplyRepository.getAll();

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
}
