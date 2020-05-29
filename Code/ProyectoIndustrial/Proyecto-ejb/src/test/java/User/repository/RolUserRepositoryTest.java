package User.repository;

import User.RolUser;
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

public class RolUserRepositoryTest {

    EntityManager entityManager = Mockito.mock(EntityManager.class);

    @Test
    public void getRolUser() {
        RolUser rolUser = new RolUser();
        List<RolUser> rolUsers = new ArrayList<>();
        rolUsers.add(new RolUser());
        Predicate predicate = Mockito.mock(Predicate.class);
        CriteriaQuery<RolUser> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        List<RolUser> result=null;
        try {
            result = mockittoWhen(rolUsers, predicate, criteriaQuery, null, rolUser).getRolUser(rolUser);
        } catch (UserException ex) {
            Logger.getLogger(RolUserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asserte
        Assert.assertEquals(result, rolUsers);

    }

    @Test
    public void getRolUserWithId() {
        Integer id = 1;
        RolUser rolUser = new RolUser();
        rolUser.setIdRolUser(id);
        List<RolUser> rolUsers = new ArrayList<>();
        rolUsers.add(new RolUser());
        Predicate predicate = Mockito.mock(Predicate.class);
        CriteriaQuery<RolUser> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        List<RolUser> result=null;
        try {
            result = mockittoWhen(rolUsers, predicate, criteriaQuery, "id_rol", rolUser).getRolUser(rolUser);
        } catch (UserException ex) {
            Logger.getLogger(RolUserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asserte
        Assert.assertEquals(result, rolUsers);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = predicate;

        //verefy query
        Mockito.verify(criteriaQuery).where(predicates);
    }

    @Test
    public void getRolUserWithName() {
        String name = "name";
        RolUser rolUser = new RolUser();
        rolUser.setName(name);
        List<RolUser> rolUsers = new ArrayList<>();
        rolUsers.add(new RolUser());
        Predicate predicate = Mockito.mock(Predicate.class);
        CriteriaQuery<RolUser> criteriaQuery = Mockito.mock(CriteriaQuery.class);
        List<RolUser> result=null;
        try {
            result = mockittoWhen(rolUsers, predicate, criteriaQuery, "name", rolUser).getRolUser(rolUser);
        } catch (UserException ex) {
            Logger.getLogger(RolUserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //asserte
        Assert.assertEquals(result, rolUsers);

        Predicate[] predicates = new Predicate[1];
        predicates[0] = predicate;

        //verefy query
        Mockito.verify(criteriaQuery).where(predicates);
    }

    private RolUserRepository mockittoWhen(List<RolUser> rolUsers, Predicate predicate, CriteriaQuery<RolUser> criteriaQuery, String atribute, RolUser rolUser) {

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        Root<RolUser> rolUserR = Mockito.mock(Root.class);
        TypedQuery<RolUser> typeQuery = Mockito.mock(TypedQuery.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(RolUser.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(RolUser.class)).thenReturn(rolUserR);

        if (rolUser.getIdRolUser() != null) {
            Mockito.when(criteriaBuilder.equal(rolUserR.get(atribute), rolUser.getIdRolUser())).thenReturn(predicate);
        }
        if (rolUser.getName() != null) {
            Mockito.when(criteriaBuilder.like(rolUserR.get(atribute), "%" + rolUser.getName() + "%")).thenReturn(predicate);
        }

        Mockito.when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(typeQuery);
        Mockito.when(typeQuery.getResultList()).thenReturn(rolUsers);
        Mockito.when(criteriaQuery.where(Mockito.any(Predicate[].class))).thenReturn(criteriaQuery);

        RolUserRepository rolUserRepository = new RolUserRepository();
        rolUserRepository.setEntityManager(entityManager);
        return rolUserRepository;
    }
}
