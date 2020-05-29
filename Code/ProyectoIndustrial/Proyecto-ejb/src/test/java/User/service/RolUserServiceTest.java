package User.service;

import User.RolUser;
import User.exception.UserException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RolUserServiceTest {
    EntityManager entityManager = Mockito.mock(EntityManager.class);
    
    @Test
    public void createCareerTest() {
        String name = "Estudiante";
        RolUser rolUser = new RolUser(name);
        RolUserService rolUserService = new RolUserService();
        rolUserService.setEntityManager(entityManager);
        Mockito.doNothing().when(entityManager).persist(RolUser.class);
        RolUser result = null;
        try {
            result=rolUserService.createRolUser(rolUser);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, rolUser);
    }
    @Test
    public void createCareerNullTest() {
        RolUser rolUser = null;
        RolUserService rolUserService = new RolUserService();
        rolUserService.setEntityManager(entityManager);
        Mockito.doNothing().when(entityManager).persist(RolUser.class);
        RolUser result = null;
        try {
            result=rolUserService.createRolUser(rolUser);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, rolUser);
    }
    @Test
    public void updateCareerTest() {
        String name = "Estudiante";
        RolUser rolUser = new RolUser(name);
        rolUser.setIdRolUser(1); 
        RolUserService rolUserService = new RolUserService();
        rolUserService.setEntityManager(entityManager);
        Mockito.when(entityManager.find(RolUser.class, rolUser.getIdRolUser())).thenReturn(rolUser);
        RolUser result = null;
        try {
            result=rolUserService.updateRolUser(rolUser);
        } catch (UserException e) {
        }
        //asserte
        Assert.assertEquals(result, rolUser);
    }
    @Test
    public void updateCareerNullTest() {
        RolUser rolUser = null;
        RolUserService rolUserService = new RolUserService();
        rolUserService.setEntityManager(entityManager);
        RolUser result;
        try {
            result=rolUserService.updateRolUser(rolUser); 
        } catch (UserException e) {
            result=null;
        }
        //asserte
        Assert.assertEquals(result, rolUser);
    }
}
