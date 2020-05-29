package gt.edu.usac.cunoc.ingenieria.user.view;

import User.Career;
import User.RolUser;
import User.User;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class UserProfileView implements Serializable {
    
    @EJB
    private UserFacadeLocal userFacade;
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private ExternalContext externalContext;
    
    User user;
    List<Career> careers = new ArrayList<>();
    List<RolUser> rolUsers = new ArrayList<>();
    
    String password;
    String newPassword;
    
    @PostConstruct
    public void init() {
        getInitialData();
    }
    
    private void getInitialData() {
        careers = userFacade.getAllCareer();
        rolUsers = userFacade.getAllRolUser();
        try {
            user = userFacade.getAuthenticatedUser().get(0);
        } catch (UserException e) {
            MessageUtils.addSuccessMessage(e.getMessage());
        }
    }
    
    public void saveChanges() throws UserException {
        if (getUser().getCarnet() != null) {
            userFacade.updateUser(getUser());
            MessageUtils.addSuccessMessage("Se actualizo el usuario");
        }
    }
    
    public void changePassword(final String modalIdToClose) {
        if (getUser().getCarnet() != null && getPassword() != null) {
            Credential credential = new UsernamePasswordCredential(getUser().getCarnet().toString(), new Password(password));
            AuthenticationStatus status = securityContext.authenticate(
                    getRequest(),
                    getResponse(),
                    AuthenticationParameters.withParams().credential(credential));
            if (status == SUCCESS) {
                try {
                    user.setPassword(getNewPassword());
                    userFacade.updateUser(user);
                    MessageUtils.addSuccessMessage("Se actualizo la contraseña");
                } catch (UserException e) {
                    MessageUtils.addErrorMessage(e.getMessage());
                }
            } else {
                cleanPass();
                MessageUtils.addErrorMessage("Constraseña Incorrecta");
            }
        }
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }
    
    public void cleanPass() {
        setPassword(null);
        setNewPassword(null);
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Career> getCareers() {
        return careers;
    }
    
    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }
    
    public List<RolUser> getRolUsers() {
        return rolUsers;
    }
    
    public void setRolUsers(List<RolUser> rolUsers) {
        this.rolUsers = rolUsers;
    }
    
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) externalContext.getRequest();
    }
    
    private HttpServletResponse getResponse() {
        return (HttpServletResponse) externalContext.getResponse();
    }
}
