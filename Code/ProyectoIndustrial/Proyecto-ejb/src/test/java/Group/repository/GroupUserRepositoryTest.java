package Group.repository;

import Group.GroupIndustrial;
import Group.GroupUser;
import User.User;
import static Group.repository.GroupUserRepository.FIND_USERS_BY_GROUP;
import static Group.repository.GroupUserRepository.FIND_GROUP_OF_USERS;
import static Group.repository.GroupUserRepository.GROUP_PARAMETER_NAME;
import static Group.repository.GroupUserRepository.CARNET_PARAMETER_NAME;
import static Group.repository.GroupUserRepository.GET_ALL_GROUP_USERS;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class GroupUserRepositoryTest {

    @Test
    public void findByIdTestWithResult() {
        // Arrange
        int idGroupUser = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        GroupUser groupUser = new GroupUser();
        Mockito.when(
                entityManager.find(GroupUser.class, idGroupUser)
        ).thenReturn(groupUser);
        GroupUserRepository groupUserRepository = new GroupUserRepository();
        groupUserRepository.setEntityManager(entityManager);

        // Act
        Optional<GroupUser> result = groupUserRepository.findGroupUserById(idGroupUser);

        // Assert
        Assert.assertEquals(result.get(), groupUser);
    }
    
    public void findUsersByGroupTest() {
        
        // Arrange
        int groupId = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(FIND_USERS_BY_GROUP)
        ).thenReturn(query);
        Mockito.when(
                query.setParameter(GROUP_PARAMETER_NAME, groupId)
        ).thenReturn(query);
        
        List<User> users = new LinkedList<User>();
        Mockito.when(query.getResultList()).thenReturn(users);
        
        GroupUserRepository groupUserRepository = new GroupUserRepository();
        groupUserRepository.setEntityManager(entityManager);
        
        // Act
        List<User> result = groupUserRepository.findUsersByGroup(groupId);
        
        // Assert
        Assert.assertEquals(result, users);
    }
    
    public void findGroupOfUserTest() {
        
        // Arrange
        int carnet = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(FIND_GROUP_OF_USERS)
        ).thenReturn(query);
        Mockito.when(
                query.setParameter(CARNET_PARAMETER_NAME, carnet)
        ).thenReturn(query);
        
        List<GroupIndustrial> group = new LinkedList<GroupIndustrial>();
        Mockito.when(query.getResultList()).thenReturn(group);
        
        GroupUserRepository groupUserRepository = new GroupUserRepository();
        groupUserRepository.setEntityManager(entityManager);
        
        // Act
        List<GroupIndustrial> result = groupUserRepository.findGroupsOfUser(carnet);
        
        // Assert
        Assert.assertEquals(result, group);
    }
    
    public void getAllTest(){
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        
        Mockito.when(
                entityManager.createQuery(GET_ALL_GROUP_USERS)
        ).thenReturn(query);
        
        List<GroupUser> groupUser = new LinkedList<GroupUser>();
        Mockito.when(
                query.getResultList()
        ).thenReturn(groupUser);
        
        GroupUserRepository groupUserRepository = new GroupUserRepository();
        groupUserRepository.setEntityManager(entityManager);
        
        // Act
        List<GroupUser> result = groupUserRepository.getAllGroupUser();
        
        //Assert
        Assert.assertEquals(result, groupUser);
    }
}
