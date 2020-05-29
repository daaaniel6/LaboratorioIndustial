package User;

import Group.GroupUser;
import Modify.ModifySupply;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(
        name = "user"
)
public class User implements Serializable {

    @Id
    @Column(name = "carnet")
    private Integer carnet;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "password")
    private String password;
    @Column(name = "state")
    private Boolean state;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolUser rolUser;
    @ManyToOne
    @JoinColumn(name = "id_career", referencedColumnName = "id_career")
    private Career career;

    public User() {
    }

    public User(Integer carnet, String name, String email, Integer phone, String password, Boolean state, RolUser rolUser, Career career) {
        this.carnet = carnet;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.rolUser = rolUser;
        this.career = career;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getCarnet(), user.getCarnet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarnet());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMessageState() {
        if (getState()) {
            return "Desactivar";
        } else {
            return "Activar";
        }
    }

    public String getIconState() {
        if (getState()) {
            return "glyphicon glyphicon-remove";
        } else {
            return "glyphicon glyphicon-ok";
        }
    }

}
