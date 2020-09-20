package User.facade;

import User.Career;
import User.RolUser;
import User.User;
import User.exception.UserException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserFacadeLocal {

    public List<User> getAuthenticatedUser() throws UserException;

    public User createUser(User user) throws UserException;

    public User updateUser(User user) throws UserException;

    public List<User> getUser(User user) throws UserException;

    public Career createCareer(Career career) throws UserException;

    public Career updateCareer(Career career) throws UserException;

    public List<Career> getCareer(Career career) throws UserException;

    public RolUser createRolUser(RolUser rolUser) throws UserException;

    public RolUser updateRolUser(RolUser rolUser) throws UserException;

    public List<RolUser> getRolUser(RolUser rolUser) throws UserException;

    public RolUser getRolUserById(int rolUser) throws UserException;

    /**
     * Require the user, to reset the password
     *
     * Use UUID strategy to generate the password
     *
     * @param user
     * @return
     * @throws UserException
     */
    public User resetPassword(User user) throws UserException;

    public List<Career> getAllCareer();

    public List<RolUser> getAllRolUser();

    public List<User> getUserEstudent() throws UserException;

    /**
     * require the user ID and the Mail to validate the user, to create a new
     * password is generated with UUID strategy.
     *
     * Designed to work al login page
     *
     * @param userID
     * @param userMail
     * @return
     * @throws UserException
     */
    public boolean resetPassword(Integer userID, String userMail) throws UserException;
}
