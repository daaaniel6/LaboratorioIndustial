package Group;

import User.User;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class GroupUserTest {

    @Test
    public void testSetAndGetIdGroupUser() throws Exception {
        //Arrange
        GroupUser testGroupUser = new GroupUser();
        int testVal = 123;

        //Act
        testGroupUser.setIdGruopUser(testVal);
        int result = testGroupUser.getIdGruopUser();

        //Assert
        Assert.assertEquals(result, testVal);
    }

    @Test
    public void testSetAndGetUser() throws Exception {
        //Arrange
        GroupUser testGroupUser = new GroupUser();
        User user = new User();
        
        //Act
        testGroupUser.setUserCarnet(user);
        User result = testGroupUser.getUserCarnet();
        
        //Assert
        Assert.assertEquals(result, user);
    }

    @Test
    public void testSetAndGetGroup() throws Exception {
        //Arrange
        GroupUser testGroupUser = new GroupUser();
        GroupIndustrial group = new GroupIndustrial();

        //Act
        testGroupUser.setGroupId(group);
        GroupIndustrial result = testGroupUser.getGroupId();
        
        //Assert
        Assert.assertEquals(result, group);
    }

    @Test
    public void testSetAndGetIdGroupAdmissionDate() throws Exception {
        //Arrange
        GroupUser testGroupUser = new GroupUser();
        LocalDate testDate = LocalDate.now();

        //Act
        testGroupUser.setAdmissionDate(testDate);
        LocalDate result = testGroupUser.getAdmissionDate();

        //Assert
        Assert.assertEquals(result, testDate);
    }
}
