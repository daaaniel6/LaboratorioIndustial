package User.facade;

import User.Career;
import User.User;
import User.exception.UserException;
import User.repository.CareerRepository;
import User.repository.UserRepository;
import User.service.CareerService;
import User.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserFacadeTest {

    UserFacade userFacade = new UserFacade();
    UserService userService = Mockito.mock(UserService.class);
    CareerService careerService = Mockito.mock(CareerService.class);
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    CareerRepository carrerRepository = Mockito.mock(CareerRepository.class);

    @Test
    public void createUserTest() {
        userFacade.setUserService(userService);
        User user = new User();
        User result = null;
        try {
            Mockito.when(userService.createUser(user)).thenReturn(user);
            result = userFacade.createUser(user);
        } catch (UserException ex) {
        }
        Assert.assertEquals(result, user);
    }

    @Test
    public void updateUserTest() {
        userFacade.setUserService(userService);
        User user = new User();
        User result = null;
        try {
            Mockito.when(userService.updateUser(user)).thenReturn(user);
            result = userFacade.updateUser(user);
        } catch (UserException ex) {

        }
        Assert.assertEquals(result, user);
    }

    @Test
    public void getUserTest() {
        userFacade.setUserRepository(userRepository);
        User user = new User();
        List<User> users = new ArrayList<>();
        List<User> result = null;
        try {
            Mockito.when(userRepository.getUser(user)).thenReturn(users);
            result = userFacade.getUser(user);
        } catch (UserException ex) {

        }
        Assert.assertEquals(result, users);
    }

    @Test
    public void createCareerTest() {
        userFacade.setCareerService(careerService);
        Career career = new Career();
        Career result = null;
        try {
            Mockito.when(careerService.createCareer(career)).thenReturn(career);
            result = userFacade.createCareer(career);
        } catch (Exception e) {
        }
        Assert.assertEquals(result, career);
    }

    @Test
    public void updateCareerTest() {
        userFacade.setCareerService(careerService);
        Career career = new Career();
        Career result = null;
        try {
            Mockito.when(careerService.updateCareer(career)).thenReturn(career);
            result = userFacade.updateCareer(career);
        } catch (Exception e) {
        }
        Assert.assertEquals(result, career);
    }

    @Test
    public void getCareerTest() {
        userFacade.setCareerRepository(carrerRepository);
        Career career = new Career();
        List<Career> careers = new ArrayList<>();
        List<Career> result = null;
        try {
            Mockito.when(carrerRepository.getCareer(career)).thenReturn(careers);
            result = userFacade.getCareer(career);
        } catch (UserException ex) {

        }
        Assert.assertEquals(result, careers);
    }

}
