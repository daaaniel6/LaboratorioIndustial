package Production.repository;

import Production.Step;
import static Production.repository.StepRepository.*;
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
public class StepRepositoryTest {
    int stepId = 1;
    List<Step> steps= new ArrayList<>();
    StepRepository stepRepository = new StepRepository();
    
   
    
    private EntityManager getmanager(int stepId , Step expected, boolean mockError) {
        
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Step> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_FIND_BY_ID, Step.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter(1, stepId)).thenReturn(typedQuery);
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
        Step step = new Step();
        EntityManager entityManager= getmanager(stepId, step, false);
        
        stepRepository.setEntityManager(entityManager);

        // Act
        Optional<Step> result = stepRepository.findByIdStep(stepId);
        // Assert
        Assert.assertEquals(result.get(), step);
        
    }
    
    @Test
    public void FindByIdProductionNoResultExceptionTest() throws Exception {
        // Arrange
        Step step = new Step();
        EntityManager entityManager= getmanager(stepId, step, true);
        
        stepRepository.setEntityManager(entityManager);

        // Act
        Optional<Step> result = stepRepository.findByIdStep(stepId);
        // Assert
        Assert.assertFalse(result.isPresent(),"Expected optinal empty");
        
    }
    
    @Test
    public void AllProductionsTest() throws Exception {
        // Arrange
               
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Step> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_ALL_STEPS, Step.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(steps);
        stepRepository.setEntityManager(entityManager);

        // Act
        List<Step> result = stepRepository.AllSteps();
        // Assert
        Assert.assertEquals(result, steps);
        
    }
    
    
    @Test
    public void findProductionLikeNameTest() throws Exception {
        // Arrange
        String name= "nombre";
        
        
        EntityManager entityManager= Mockito.mock(EntityManager.class);
        TypedQuery<Step> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(QUERY_LIKE_STEPS, Step.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setParameter(1, name)).thenReturn(typedQuery); 
        Mockito.when(typedQuery.getResultList()).thenReturn(steps);
        
        stepRepository.setEntityManager(entityManager);
        // Act
        List<Step> result = stepRepository.findStepLikeName(name);
        // Assert
        Assert.assertEquals(result, steps);
        
    }
}
