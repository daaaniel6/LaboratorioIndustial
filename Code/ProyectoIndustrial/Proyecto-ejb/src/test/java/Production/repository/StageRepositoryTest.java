package Production.repository;


import Production.Stage;
import static Production.repository.StageRepository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author daniel
 */
public class StageRepositoryTest {
    int stageId = 1;
    List<Stage> stages= new ArrayList<>();
    StageRepository stageRepository = new StageRepository();
    
   
    
    private EntityManager getmanager(int stageId , Stage expected, boolean mockError) {
        
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Stage> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_FIND_BY_ID, Stage.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter(1, stageId)).thenReturn(typedQuery);
        if (mockError) {
            Mockito.when(typedQuery.getSingleResult()).thenThrow(new NoResultException());
            return entityManager;
        }
        Mockito.when(typedQuery.getSingleResult()).thenReturn(expected);
        return  entityManager;
    }
    
    @Test
    public void FindByIdProductionTest() throws Exception {
        // Arrange
        
        Stage stage = new Stage();
        EntityManager entityManager= getmanager( stageId, stage, false);
        
        stageRepository.setEntityManager(entityManager);

        // Act
        Optional<Stage> result = stageRepository.findByIdStage(stageId);
        // Assert
        Assert.assertEquals(result.get(), stage);
        
    }
    
    @Test
    public void FindByIdProductionNoResultExceptionTest() throws Exception {
        // Arrange
        Stage stage = new Stage();
        EntityManager entityManager= getmanager(stageId, stage, true);      
        stageRepository.setEntityManager(entityManager);

        // Act
        Optional<Stage> result = stageRepository.findByIdStage(stageId);
        // Assert
        Assert.assertFalse(result.isPresent(),"Expected optinal empty");
        
    }
    
    @Test
    public void AllProductionsTest() throws Exception {
        // Arrange
               
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Stage> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_ALL_STAGES, Stage.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(stages);
        stageRepository.setEntityManager(entityManager);

        // Act
        List<Stage> result = stageRepository.AllStages();
        // Assert
        Assert.assertEquals(result, stages);
        
    }
    
    
    @Test
    public void findProductionLikeNameTest() throws Exception {
        // Arrange
        String name= "nombre";
        
        
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Stage> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_LIKE_STAGES, Stage.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter(1, name)).thenReturn(typedQuery); 
        Mockito.when(typedQuery.getResultList()).thenReturn(stages);
        
        stageRepository.setEntityManager(entityManager);
        
        // Act
        List<Stage> result = stageRepository.findStageLikeName(name);
        // Assert
        Assert.assertEquals(result, stages);
        
    }
}
