package User.repository;

import User.Career;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CareerRepositoryTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    @Test
    public void getCareer() {
        try {
            Career career = new Career();
            List<Career> careers = new ArrayList<>();
            careers.add(new Career());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<Career> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<Career> result = mockittoWhen(careers, predicate, criteriaQuery, null, career).getCareer(career);

            //asserte
            Assert.assertEquals(result, careers);
        } catch (UserException ex) {
            Logger.getLogger(CareerRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getCareerWithId() {
        try {
            Integer id = 1;
            Career career = new Career();
            career.setIdCareer(id);
            List<Career> careers = new ArrayList<>();
            careers.add(new Career());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<Career> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<Career> result = mockittoWhen(careers, predicate, criteriaQuery, "id_career", career).getCareer(career);

            //asserte
            Assert.assertEquals(result, careers);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = predicate;

            //verefy query
            Mockito.verify(criteriaQuery).where(predicates);
        } catch (UserException ex) {
            Logger.getLogger(CareerRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getCareerWithName() {
        try {
            String name = "name";
            Career career = new Career();
            career.setName(name);
            List<Career> careers = new ArrayList<>();
            careers.add(new Career());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<Career> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<Career> result = mockittoWhen(careers, predicate, criteriaQuery, "name", career).getCareer(career);

            //asserte
            Assert.assertEquals(result, careers);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = predicate;

            //verefy query
            Mockito.verify(criteriaQuery).where(predicates);
        } catch (UserException ex) {
            Logger.getLogger(CareerRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private CareerRepository mockittoWhen(List<Career> careers, Predicate predicate, CriteriaQuery<Career> criteriaQuery, String atribute, Career career) {

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Root<Career> careerR = Mockito.mock(Root.class);
        TypedQuery<Career> typeQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(Career.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(Career.class)).thenReturn(careerR);
        if (career.getIdCareer() != null) {
            Mockito.when(criteriaBuilder.equal(careerR.get(atribute), career.getIdCareer())).thenReturn(predicate);
        }
        if (career.getName()!= null) {
            Mockito.when(criteriaBuilder.like(careerR.get(atribute), "%" + career.getName() + "%")).thenReturn(predicate);
        }
        Mockito.when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(typeQuery);
        Mockito.when(typeQuery.getResultList()).thenReturn(careers);
        Mockito.when(criteriaQuery.where(Mockito.any(Predicate[].class))).thenReturn(criteriaQuery);

        CareerRepository careerRepository = new CareerRepository();
        careerRepository.setEntityManager(entityManager);
        return careerRepository;
    }

}
