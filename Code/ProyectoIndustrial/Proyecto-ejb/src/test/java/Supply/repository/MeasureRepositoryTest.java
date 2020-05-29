
package Supply.repository;

import Supply.Measure;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MeasureRepositoryTest {
    
    private EntityManager configureEntityManager(boolean isEmptyTest, List<Measure> list){
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Mockito.when(
                    entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        CriteriaQuery<Measure> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        Mockito.when(
                    criteriaBuilder.createQuery(Measure.class))
                .thenReturn(criteriaQuery);
        Root<Measure> measure = Mockito.mock(Root.class);
        Mockito.when(
                    criteriaQuery.from(Measure.class))
                .thenReturn(measure);
        Mockito.when(
                    criteriaQuery.select(measure))
                .thenReturn(criteriaQuery);
        TypedQuery<Measure> query = Mockito.mock(TypedQuery.class);
        Mockito.when(
                    entityManager.createQuery(criteriaQuery))
                .thenReturn(query);
        if (!isEmptyTest){
            Measure measureTest = new Measure();
            list.add(measureTest);
        }
        
        Mockito.when(
                    query.getResultList())
                .thenReturn(list);
        return entityManager;
        
    }
    
    
    @Test
    public void getMeasuresTestNoEmpty(){
        //Arrange
        List<Measure> list = new ArrayList<>();
        EntityManager entityManager = configureEntityManager(false,list);
        MeasureRepository measureRepository = new MeasureRepository();
        measureRepository.setEntityManager(entityManager);
        
        //Act
        List<Measure> result = measureRepository.getAllMeasures();
        
        //Assert
        Assert.assertEquals(result,list);
        
    }
    
    @Test
    public void getMeasuresTestEmpty(){
        //Arrange
        List<Measure> list = new ArrayList<>();
        EntityManager entityManager = configureEntityManager(true,list);
        MeasureRepository measureRepository = new MeasureRepository();
        measureRepository.setEntityManager(entityManager);
        
        //Act
        List<Measure> result = measureRepository.getAllMeasures();
        
        //Assert
        Assert.assertEquals(result,list);
    }
    
    
}
