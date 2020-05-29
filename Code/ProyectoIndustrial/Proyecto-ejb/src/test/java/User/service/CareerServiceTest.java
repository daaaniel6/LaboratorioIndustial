package User.service;

import User.Career;
import User.exception.UserException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CareerServiceTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    @Test
    public void createCareerTest() {
        String name = "Ingenieria Sistemas";
        Career career = new Career(name);
        CareerService careerService = new CareerService();
        careerService.setEntityManager(entityManager);
        Mockito.doNothing().when(entityManager).persist(Career.class);
        Career result = null;
        try {
            result=careerService.createCareer(career);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, career);
    }
    @Test
    public void createCareerNullTest() {
        Career career = null;
        CareerService careerService = new CareerService();
        careerService.setEntityManager(entityManager);
        Mockito.doNothing().when(entityManager).persist(Career.class);
        Career result = null;
        try {
            result=careerService.createCareer(career);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, career);
    }
    @Test
    public void updateCareerTest() {
        String name = "Ingenieria Sistemas";
        Career career = new Career(name);
        career.setIdCareer(1); 
        CareerService careerService = new CareerService();
        careerService.setEntityManager(entityManager);
        Mockito.when(entityManager.find(Career.class, career.getIdCareer())).thenReturn(career);
        Career result = null;
        try {
            result=careerService.updateCareer(career);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, career);
    }
    @Test
    public void updateCareerNullTest() {
        Career career = null;
        CareerService careerService = new CareerService();
        careerService.setEntityManager(entityManager);
        Career result=null;
        try {
            result=careerService.updateCareer(career);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, career);
    }
    
}
