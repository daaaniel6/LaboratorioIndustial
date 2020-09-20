package User.repository;

import User.Career;
import User.RolUser;
import java.lang.Exception;
import User.User;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRepositoryTest {

    private static String GET_USER_BY_CARNET = "SELECT u FROM user u WHERE u.carnet = :carnet";
    EntityManager entityManager = Mockito.mock(EntityManager.class);

    @Test
    public void getUserByCarnetTest() {
        Integer carnet = 201630875;
        TypedQuery<User> typeQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery(GET_USER_BY_CARNET, User.class)).thenReturn(typeQuery);
        Mockito.when(typeQuery.setParameter("carnet", carnet)).thenReturn(typeQuery);
        User user = new User();
        Mockito.when(typeQuery.getSingleResult()).thenReturn(user);

        UserRepository userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager);
        Optional<User> result = userRepository.getUserByCarnet(carnet);
        Assert.assertEquals(result.get(), user);
    }

    @Test
    public void getUserByCarnetEmptyTest() {
        Integer carnet = 201630875;
        TypedQuery<User> typeQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.createQuery(GET_USER_BY_CARNET, User.class)).thenReturn(typeQuery);
        Mockito.when(typeQuery.setParameter("carnet", carnet)).thenReturn(typeQuery);
        User user = new User();
        Mockito.when(typeQuery.getSingleResult()).thenThrow(NoResultException.class);

        UserRepository userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager);
        Optional<User> result = userRepository.getUserByCarnet(carnet);
        Assert.assertEquals(result, Optional.empty());
    }

    @Test
    public void getUseTest() {
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(new User());
        Predicate predicate = Mockito.mock(Predicate.class);
        CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        List<User> result = null;
        try {
            result = mockittoWhen(users, predicate, criteriaQuery, null, user).getUser(user);
        } catch (UserException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asserte
        Assert.assertEquals(result, users);
    }

    @Test
    public void getUserWithCarnetTest() {
        User user = new User();
        Integer carnet = 201630879;
        user.setCarnet(carnet);
        List<User> users = new ArrayList<>();
        users.add(new User());
        Predicate predicate = Mockito.mock(Predicate.class);
        CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        List<User> result = null;
        try {
            result = mockittoWhen(users, predicate, criteriaQuery, "carnet", user).getUser(user);
        } catch (UserException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asserte
        Assert.assertEquals(result, users);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = predicate;

        //verefy query
        Mockito.verify(criteriaQuery).where(predicates);
    }

    @Test
    public void getUserWithNameTest() {
        try {
            User user = new User();
            String name = "fulanito";
            user.setName(name);
            List<User> users = new ArrayList<>();
            users.add(new User());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<User> result = mockittoWhen(users, predicate, criteriaQuery, "carnet", user).getUser(user);

            //asserte
            Assert.assertEquals(result, users);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = predicate;

            //verefy query
            Mockito.verify(criteriaQuery).where(predicates);
        } catch (UserException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getUserWithStateTest() {
        try {
            User user = new User();
            Boolean state = true;
            user.setState(state);
            List<User> users = new ArrayList<>();
            users.add(new User());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<User> result = mockittoWhen(users, predicate, criteriaQuery, "state", user).getUser(user);

            //asserte
            Assert.assertEquals(result, users);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = predicate;

            //verefy query
            Mockito.verify(criteriaQuery).where(predicates);
        } catch (UserException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Test
//    public void getUserWithidRolTest() {
//        try {
//            User user = new User();
//            RolUser rolUser = new RolUser(1, "Admin");
//            user.setRolUser(rolUser);
//            List<User> users = new ArrayList<>();
//            users.add(new User());
//            Predicate predicate = Mockito.mock(Predicate.class);
//            CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
//            List<User> result = mockittoWhen(users, predicate, criteriaQuery, "id_rol", user).getUser(user);
//
//            //asserte
//            Assert.assertEquals(result, users);
//
//            Predicate[] predicates = new Predicate[1];
//            predicates[0] = predicate;
//
//            //verefy query
//            Mockito.verify(criteriaQuery).where(predicates);
//        } catch (UserException ex) {
//            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Test
    public void getUserWithIdCareer() {
        try {
            User user = new User();
            Career career = new Career(1, "sistemas");
            user.setCareer(career);
            List<User> users = new ArrayList<>();
            users.add(new User());
            Predicate predicate = Mockito.mock(Predicate.class);
            CriteriaQuery<User> criteriaQuery = Mockito.mock(CriteriaQuery.class);
            List<User> result = mockittoWhen(users, predicate, criteriaQuery, "id_Career", user).getUser(user);

            //asserte
            Assert.assertEquals(result, users);

            Predicate[] predicates = new Predicate[1];
            predicates[0] = predicate;

            //verefy query
            Mockito.verify(criteriaQuery).where(predicates);
        } catch (UserException ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private UserRepository mockittoWhen(List<User> users, Predicate predicate, CriteriaQuery<User> criteriaQuery, String atribute, User user) {

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Root<User> userR = Mockito.mock(Root.class);
        TypedQuery<User> typeQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(User.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(User.class)).thenReturn(userR);
        if (user.getCarnet() != null) {
            Mockito.when(criteriaBuilder.equal(userR.get(atribute), user.getCarnet())).thenReturn(predicate);
        }
        if (user.getName() != null) {
            Mockito.when(criteriaBuilder.like(userR.get(atribute), "%" + user.getName() + "%")).thenReturn(predicate);
        }
        if (user.getState() != null) {
            Mockito.when(criteriaBuilder.equal(userR.get(atribute), user.getState())).thenReturn(predicate);
        }
        if (user.getRolUser() != null) {
            Mockito.when(criteriaBuilder.equal(userR.get(atribute), user.getRolUser().getIdRolUser())).thenReturn(predicate);
        }
        if (user.getCareer() != null) {
            Mockito.when(criteriaBuilder.equal(userR.get(atribute), user.getCareer().getIdCareer())).thenReturn(predicate);
        }

        Mockito.when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(typeQuery);
        Mockito.when(typeQuery.getResultList()).thenReturn(users);
        Mockito.when(criteriaQuery.where(Mockito.any(Predicate[].class))).thenReturn(criteriaQuery);

        UserRepository userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager);
        return userRepository;
    }
}
