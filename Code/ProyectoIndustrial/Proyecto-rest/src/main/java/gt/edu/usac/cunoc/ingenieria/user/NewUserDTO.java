package gt.edu.usac.cunoc.ingenieria.user;

import java.io.Serializable;

/**
 *
 * @author angelrg
 */
public class NewUserDTO extends UserDTO implements Serializable {

    String password;

    public NewUserDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
