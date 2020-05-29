package Group;

import User.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "group_user")
public class GroupUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gruop_user")
    private Integer idGruopUser;
    @Column(name = "admission_date")
    private LocalDate admissionDate;
    @JoinColumn(name = "group_id", referencedColumnName = "id_group")
    @ManyToOne(optional = false)
    private GroupIndustrial groupId;
    @JoinColumn(name = "user_carnet", referencedColumnName = "carnet")
    @ManyToOne(optional = false)
    private User userCarnet;

    public GroupUser() {
    }

    public GroupUser(User userCarnet, GroupIndustrial groupId, LocalDate admissionDate) {
        this.admissionDate = admissionDate;
        this.groupId = groupId;
        this.userCarnet = userCarnet;
    }

    public Integer getIdGruopUser() {
        return idGruopUser;
    }

    public void setIdGruopUser(Integer idGruopUser) {
        this.idGruopUser = idGruopUser;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public GroupIndustrial getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupIndustrial groupId) {
        this.groupId = groupId;
    }

    public User getUserCarnet() {
        return userCarnet;
    }

    public void setUserCarnet(User userCarnet) {
        this.userCarnet = userCarnet;
    }

}
