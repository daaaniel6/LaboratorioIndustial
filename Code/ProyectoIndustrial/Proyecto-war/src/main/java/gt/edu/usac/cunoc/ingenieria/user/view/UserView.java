package gt.edu.usac.cunoc.ingenieria.user.view;

import User.Career;
import User.RolUser;
import User.User;
import static config.Constants.ADMINISTRADOR;
import static config.Constants.ESTUDIANTE;
import static config.Constants.DOCENTE;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class UserView implements Serializable {

    @EJB
    private UserFacadeLocal userFacade;

    List<User> users = new ArrayList<>();
    List<Career> careers = new ArrayList<>();
    List<RolUser> rolUsers = new ArrayList<>();
    User currentUser = new User();

    @PostConstruct
    public void init() {
        getAllCareer();
        getAllRolUser();
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public List<Career> getCareers() {
        return this.careers;
    }

    public List<RolUser> getRolUsers() {
        return this.rolUsers;
    }

    public void setRolUsers(List<RolUser> rolUsers) {
        this.rolUsers = rolUsers;
    }

    private void getAllCareer() {
        careers = userFacade.getAllCareer();
    }

    private void getAllRolUser() {
        rolUsers = userFacade.getAllRolUser();
    }

    public UserFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = new User();
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private String carnetSearchCriteria;
    private String nameSearchCriteria;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCarnetSearchCriteria() {
        return carnetSearchCriteria;
    }

    public void setCarnetSearchCriteria(String carnetSearchCriteria) {
        this.carnetSearchCriteria = carnetSearchCriteria;
    }

    public String getNameSearchCriteria() {
        return nameSearchCriteria;
    }

    public void setNameSearchCriteria(String nameSearchCriteria) {
        this.nameSearchCriteria = nameSearchCriteria;
    }

    public void searchUsers() throws UserException {
        User user = new User();
        if (!getNameSearchCriteria().isEmpty()) {
            user.setName(getNameSearchCriteria());
        }
        if (!getCarnetSearchCriteria().isEmpty()) {
            user.setCarnet(Integer.parseInt(getCarnetSearchCriteria()));
        }
        setUsers(userFacade.getUser(user));
    }

    public void saveChanges(final String modalIdToClose) throws UserException {
        if (currentUser.getCarnet() != null) {
            userFacade.updateUser(currentUser);
            MessageUtils.addSuccessMessage("Se actualizo el usuario");
        }
        clearCurrentUser();
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }

    public void clearCurrentUser() {
        setCurrentUser(null);
    }

    public void changeUserState(final Integer carnet) throws UserException {
        User user = new User();
        user.setCarnet(carnet);
        user = userFacade.getUser(user).get(0);
        user.setState(!user.getState());
        userFacade.updateUser(user);
        changeLocalStateUser(carnet);
        clearCurrentUser();
    }

    public void resetPassword(final String modalIdToClose) {
        if (currentUser.getCarnet() != null) {
            try {
                userFacade.resetPassword(currentUser);
                MessageUtils.addSuccessMessage("Se actualizo el usuario");
            } catch (UserException e) {
                MessageUtils.addErrorMessage(e.getMessage());
            }
        }
        clearCurrentUser();
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }

    private void changeLocalStateUser(Integer carnet) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCarnet() == carnet) {
                users.get(i).setState(!users.get(i).getState());
                break;
            }
        }
    }

    public void cleanCriteria() {
        this.carnetSearchCriteria = "";
        this.nameSearchCriteria = "";
    }

    public String docente() {
        return DOCENTE;
    }

    public String estudiante() {
        return ESTUDIANTE;
    }

    public String admin() {
        return ADMINISTRADOR;
    }

    public String adminDocente() {
        return (ADMINISTRADOR + "," + DOCENTE);
    }
}
