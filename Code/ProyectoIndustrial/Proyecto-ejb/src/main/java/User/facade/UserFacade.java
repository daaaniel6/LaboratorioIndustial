package User.facade;

import User.Career;
import User.RolUser;
import User.User;
import User.exception.UserException;
import User.repository.CareerRepository;
import User.repository.RolUserRepository;
import User.repository.UserRepository;
import User.service.CareerService;
import User.service.RolUserService;
import User.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class UserFacade implements UserFacadeLocal {

    private UserService userService;
    private UserRepository userRepository;
    @EJB
    private CareerService careerService;
    @EJB
    private CareerRepository careerRepository;
    private RolUserService rolUserService;
    @EJB
    private RolUserRepository rolUserRepository;

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @EJB
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EJB
    public void setCareerService(CareerService careerService) {
        this.careerService = careerService;
    }

    @EJB
    public void setCareerRepository(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    @EJB
    public void setRolUserService(RolUserService rolUserService) {
        this.rolUserService = rolUserService;
    }

    @EJB
    public void setRolUserRepository(RolUserRepository rolUserRepository) {
        this.rolUserRepository = rolUserRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAuthenticatedUser() throws UserException {
        return userService.getAuthenticatedUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(User user) throws UserException {
        return userService.createUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User updateUser(User user) throws UserException {
        return userService.updateUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUser(User user) throws UserException {
        return userRepository.getUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Career createCareer(Career career) throws UserException {
        return careerService.createCareer(career);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Career updateCareer(Career career) throws UserException {
        return careerService.updateCareer(career);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Career> getCareer(Career career) throws UserException {
        return careerRepository.getCareer(career);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolUser createRolUser(RolUser rolUser) throws UserException {
        return rolUserService.createRolUser(rolUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolUser updateRolUser(RolUser rolUser) throws UserException {
        return rolUserService.updateRolUser(rolUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RolUser> getRolUser(RolUser rolUser) throws UserException {
        return rolUserRepository.getRolUser(rolUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolUser getRolUserById(int rolUser) throws UserException {
        return rolUserRepository.findRolUserById(rolUser).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUserEstudent() throws UserException {
        User user = new User();
        Optional<RolUser> rolUser = rolUserRepository.findByIdRolUser(1);
        user.setRolUser(rolUser.get());

        return userRepository.getUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Career> getAllCareer() {
        return careerRepository.getAllCareer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RolUser> getAllRolUser() {
        return rolUserRepository.getAllRolUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User resetPassword(User user) throws UserException {
        return userService.resetPassword(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean resetPassword(Integer userID, String userMail) throws UserException {
        return userService.resetPassword(userID, userMail);
    }
}
