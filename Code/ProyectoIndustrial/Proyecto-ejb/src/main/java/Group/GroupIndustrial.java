package Group;

import java.io.Serializable;
import java.util.List;
import Production.Production;
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
@Table(name = "`group`")
public class GroupIndustrial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Integer idGroup;
    @Column(name = "information")
    private String information;
    @Column(name = "name")
    private String name;
    @Column(name = "section")
    private String section;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private List<Production> productionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private List<GroupUser> groupUserList;

    public GroupIndustrial() {
    }

    public GroupIndustrial(String name, String section) {
        this.name = name;
        this.section = section;
    }

    public GroupIndustrial(String information, String name, String section) {
        this.information = information;
        this.name = name;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupIndustrial(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        section  = section.toUpperCase();
        this.section = section;
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

    public List<GroupUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<GroupUser> groupUserList) {
        this.groupUserList = groupUserList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof GroupIndustrial)) {
            return false;
        }

        GroupIndustrial groupI = (GroupIndustrial) o;
        return Objects.equals(getIdGroup(), groupI.getIdGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGroup());
    }

}
