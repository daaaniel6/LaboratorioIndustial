package User;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "rol_user"
)
public class RolUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;
    @Column(name = "name_rol")
    private String name;

    public RolUser(Integer idRol, String name) {
        this.idRol = idRol;
        this.name = name;
    }

    public RolUser(String name) {
        this.name = name;
    }

    public RolUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolUser)) {
            return false;
        }
        RolUser rolUser = (RolUser) o;
        return Objects.equals(getIdRolUser(), rolUser.getIdRolUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRolUser());
    }

    @Override
    public String toString() {
        return "RolUser{" + "idRol=" + idRol + ", name=" + name + '}';
    }
    
    

    public Integer getIdRolUser() {
        return idRol;
    }

    public void setIdRolUser(Integer idRol) {
        this.idRol = idRol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
