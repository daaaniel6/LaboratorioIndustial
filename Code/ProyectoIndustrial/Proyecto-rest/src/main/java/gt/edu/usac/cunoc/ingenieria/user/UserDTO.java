package gt.edu.usac.cunoc.ingenieria.user;

import User.Career;
import User.RolUser;
import User.User;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author angelrg
 */
public class UserDTO implements Serializable {

    private Integer carnet;
    private String name;
    private String email;
    private Integer phone;
    private Boolean state;
    private RolUser rolUser;
    private Career career;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.carnet = user.getCarnet();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.state = user.getState();
        this.rolUser = user.getRolUser();
        this.career = user.getCareer();
    }

    public Integer getCarnet() {
        return carnet;
    }

    public void setCarnet(Integer carnet) {
        this.carnet = carnet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public RolUser getRolUser() {
        return rolUser;
    }

    public void setRolUser(RolUser rolUser) {
        this.rolUser = rolUser;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.carnet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.carnet, other.carnet)) {
            return false;
        }
        return true;
    }

}
