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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "career")
public class Career implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_career")
    private Integer idCareer;
    @Column(name = "name_career")
    private String name;

    public Career() {
    }

    public Career(String name) {
        this.name = name;
    }

    public Career(Integer idCareer, String nameCareer) {
        this.idCareer = idCareer;
        this.name = nameCareer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Career)) {
            return false;
        }
        Career career = (Career) o;
        return Objects.equals(getIdCareer(), career.getIdCareer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCareer());
    }

    public Integer getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(Integer idCareer) {
        this.idCareer = idCareer;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameCareer) {
        this.name = nameCareer;
    }

}
