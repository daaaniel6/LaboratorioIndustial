package Group.service;

import Group.GroupIndustrial;
import Group.GroupUser;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class GroupUserServiceTest {

    @Test
    public void createGroupUserTest() {
        // Arrange
        GroupUser groupUser = new GroupUser();
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.doNothing().when(entityManager).persist(groupUser);

        GroupUserService groupUserService = new GroupUserService();
        groupUserService.setEntityManager(entityManager);

        // Act
        GroupUser result = groupUserService.createGroupUser(groupUser);

        // Assert
        Assert.assertEquals(result, groupUser);
    }

    @Test
    public void updateUserGroupTest() {
        // Arrange
        GroupUser groupUser = new GroupUser();
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.when(
                entityManager.merge(groupUser)
        ).thenReturn(groupUser);

        GroupUserService groupUserService = new GroupUserService();
        groupUserService.setEntityManager(entityManager);

        // Act
        GroupIndustrial group = new GroupIndustrial();
        GroupUser result = groupUserService.updateUserGroup(groupUser, group);

        //Assert
        Assert.assertEquals(result, groupUser);

    }
    
    @Test
    public void removeUserFromGroupSuccessTest(){
        // Arrange
        GroupUser groupUser = new GroupUser();
        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.doNothing().when(entityManager).remove(groupUser);

        GroupUserService groupUserService = new GroupUserService();
        groupUserService.setEntityManager(entityManager);
        
        // Act
        Optional<GroupUser> result = groupUserService.removeUserFromGroup(groupUser);
        
        // Assert
        Assert.assertEquals(result.get(), groupUser);
    }
    
//    @Test
//    public void removeUserFromGroupErrorTest(){
//        // Arrange
//        GroupUser groupUser = new GroupUser();
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//
//        Mockito.doThrow(TransactionRequiredException.class).when(entityManager).remove(groupUser);
//
//        GroupUserService groupUserService = new GroupUserService();
//        groupUserService.setEntityManager(entityManager);
//        
//        // Act
//        Optional<GroupUser> result = groupUserService.removeUserFromGroup(groupUser);
//        
//        // Assert
//        Assert.assertFalse(result.isPresent(), "Expected error");
//    }
}
