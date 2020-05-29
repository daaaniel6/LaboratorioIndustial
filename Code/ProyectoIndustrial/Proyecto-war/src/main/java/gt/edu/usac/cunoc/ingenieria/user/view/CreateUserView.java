package gt.edu.usac.cunoc.ingenieria.user.view;

import User.Career;
import User.RolUser;
import User.User;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CreateUserView implements Serializable {

    @EJB
    private UserFacadeLocal userFacade;

    List<Career> careers;
    List<RolUser> rolUsers;
    User user;

    @PostConstruct
    public void init() {
        getAllCareer();
        getAllRolUser();
        getUser().setState(Boolean.TRUE);
    }

    public Career getCareer(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("no id provided");
        }
        for (Career career : careers) {
            if (id.equals(career.getIdCareer())) {
                return career;
            }
        }
        return null;
    }

    public RolUser getRolUser(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("no id provided");
        }
        for (RolUser rolUser : rolUsers) {
            if (id.equals(rolUser.getIdRolUser())) {
                return rolUser;
            }
        }
        return null;
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void createUser(){
        try {
            userFacade.createUser(user);
            cleanUser();
            MessageUtils.addSuccessMessage("Se ha creado el usuario");
        } catch (UserException ex) {
            Logger.getLogger(CreateUserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAllCareer() {
        careers = userFacade.getAllCareer();
    }

    private void getAllRolUser() {
        rolUsers = userFacade.getAllRolUser();
    }
    
    private void cleanUser(){
        user=null;
    }

}
