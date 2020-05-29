
package Supply.repository;

import Supply.Supply;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SupplyRepositoryTest {
   
    private EntityManager configureEntityManager(Integer codeSupplyTest, String nameSupplyTest, AvailabilityFilter availabilitySupply, ExpirationDateFilter expirationDateSupply, List<Supply> list){
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Mockito.when(
                    entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        CriteriaQuery<Supply> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        Mockito.when(
                    criteriaBuilder.createQuery(Supply.class))
                .thenReturn(criteriaQuery);
        Root<Supply> supply = Mockito.mock(Root.class);
        Mockito.when(
                    criteriaQuery.from(Supply.class))
                .thenReturn(supply);
        
        if (codeSupplyTest != null){
            Predicate predicate = Mockito.mock(Predicate.class);
            Mockito.when(
                criteriaBuilder.like(supply.get("code"), "%" + codeSupplyTest + "%")
            ).thenReturn(predicate);
        }
        
        if (nameSupplyTest != null){
            Predicate predicate = Mockito.mock(Predicate.class);
            Mockito.when(
                criteriaBuilder.like(supply.get("name"), "%" + nameSupplyTest + "%")
            ).thenReturn(predicate);
        }
        
        if (availabilitySupply != null){
            if (availabilitySupply.equals(AvailabilityFilter.AVAILABLE)){
                Predicate predicate = Mockito.mock(Predicate.class);
                Mockito.when(
                    criteriaBuilder.equal(supply.get("availability"), "1")
                ).thenReturn(predicate);
            } else if (availabilitySupply.equals(AvailabilityFilter.NOT_AVAILABLE)){
                Predicate predicate = Mockito.mock(Predicate.class);
                Mockito.when(
                    criteriaBuilder.equal(supply.get("availability"), "0")
                ).thenReturn(predicate);
            }
        }
        
        if (expirationDateSupply != null){
            if (expirationDateSupply.equals(expirationDateSupply.EXPIRED)){
                Predicate predicate = Mockito.mock(Predicate.class);
                Mockito.when(
                    criteriaBuilder.lessThanOrEqualTo(supply.get("expiration_date"), LocalDate.now())
                ).thenReturn(predicate);
            } else if (expirationDateSupply.equals(expirationDateSupply.NOT_EXPIRED)){
                Predicate predicate = Mockito.mock(Predicate.class);
                Mockito.when(
                    criteriaBuilder.greaterThan(supply.get("expiration_date"), LocalDate.now())
                ).thenReturn(predicate);
            }
        }
        
        ArrayList<Predicate> predicates = new ArrayList<>();
        Mockito.when(
                    criteriaQuery.where(predicates.stream().toArray(Predicate[]::new)))
                .thenReturn(criteriaQuery);
        
        TypedQuery<Supply> query = Mockito.mock(TypedQuery.class);
        Mockito.when(
                    entityManager.createQuery(criteriaQuery))
                .thenReturn(query);  
        Mockito.when(
                    query.getResultList())
                .thenReturn(list);
        return entityManager;    
    }

//    @Test
//    public void getSupplyTestWithCode(){
//        //Arrange
//        Integer codeSupplyTest = 1;
//        String nameSupplyTest = null;
//        String interCodeSupplyTest = null;
//        AvailabilityFilter availabilityFilterTest = null;
//        ExpirationDateFilter expirationDateFilterTest = null;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
//    
//    @Test
//    public void getSupplyTestWithName(){
//        //Arrange
//        Integer codeSupplyTest = null;
//        String nameSupplyTest = "Prueba";
//        AvailabilityFilter availabilityFilterTest = null;
//        ExpirationDateFilter expirationDateFilterTest = null;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
//    
//    @Test
//    public void getSupplyTestWithAvailabilityFilterAvailable(){
//        //Arrange
//        Integer codeSupplyTest = null;
//        String nameSupplyTest = null;
//        AvailabilityFilter availabilityFilterTest = AvailabilityFilter.AVAILABLE;
//        ExpirationDateFilter expirationDateFilterTest = null;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
//    
//    @Test
//    public void getSupplyTestWithAvailabilityFilterNonAvailable(){
//        //Arrange
//        Integer codeSupplyTest = null;
//        String nameSupplyTest = null;
//        AvailabilityFilter availabilityFilterTest = AvailabilityFilter.NOT_AVAILABLE;
//        ExpirationDateFilter expirationDateFilterTest = null;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
//    
//    @Test
//    public void getSupplyTestWithExpirationDateFilterExpired(){
//        //Arrange
//        Integer codeSupplyTest = null;
//        String nameSupplyTest = null;
//        AvailabilityFilter availabilityFilterTest = null;
//        ExpirationDateFilter expirationDateFilterTest = ExpirationDateFilter.EXPIRED;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
//    
//    @Test
//    public void getSupplyTestWithExpirationDateFilterNotExpired(){
//        //Arrange
//        Integer codeSupplyTest = null;
//        String nameSupplyTest = null;
//        AvailabilityFilter availabilityFilterTest = null;
//        ExpirationDateFilter expirationDateFilterTest = ExpirationDateFilter.NOT_EXPIRED;
//        List<Supply> list = new ArrayList<>();
//        
//        EntityManager entityManager = configureEntityManager(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest, list);
//        SupplyRepository supplyRepository = new SupplyRepository();
//        supplyRepository.setEntityManager(entityManager);
//        //Act
//        
//        List<Supply> result = supplyRepository.getSupply(codeSupplyTest, nameSupplyTest, availabilityFilterTest, expirationDateFilterTest);
//     
//        //Assert
//        Assert.assertEquals(result, list);
//    }
}
