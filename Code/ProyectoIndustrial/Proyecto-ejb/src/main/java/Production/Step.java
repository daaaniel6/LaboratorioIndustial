package Production;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daniel, angelrg
 */
@Entity
@Table(name = "step")
public class Step implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_step")
    private Integer idStep;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "stage_id", referencedColumnName = "id_stage")
    @ManyToOne(optional = false)
    private Stage stageId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStep")
    private List<Commentary> commentaryList;

    public Step() {
    }

    public Step(Integer idStep) {
        this.idStep = idStep;
    }

    public Step(String name, String description, Stage stageId) {
        this.name = name;
        this.description = description;
        this.stageId = stageId;
    }

    public Step(Integer idStep, String name, String description) {
        this.idStep = idStep;
        this.name = name;
        this.description = description;
    }

    public Integer getIdStep() {
        return idStep;
    }

    public void setIdStep(Integer idStep) {
        this.idStep = idStep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stage getStageId() {
        return stageId;
    }

    public void setStageId(Stage stageId) {
        this.stageId = stageId;
    }

    public List<Commentary> getCommentaryList() {
        return commentaryList;
    }

    public void setCommentaryList(List<Commentary> commentaryList) {
        this.commentaryList = commentaryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStep != null ? idStep.hashCode() : 0);
        return hash;
    }
}
