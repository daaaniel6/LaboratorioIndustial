package Production;

import java.io.Serializable;
import java.util.List;
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
 * @author damiel, angelrg
 */
@Entity
@Table(name = "stage")
public class Stage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stage")
    private Integer idStage;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "production_id", referencedColumnName = "id_production")
    @ManyToOne(optional = false)
    private Production productionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stageId")
    private List<Step> stepList;

    public Stage() {
    }

    public Stage(Integer idStage) {
        this.idStage = idStage;
    }

    public Stage(Integer idStage, String name, String description) {
        this.idStage = idStage;
        this.name = name;
        this.description = description;
    }

    public Integer getIdStage() {
        return idStage;
    }

    public void setIdStage(Integer idStage) {
        this.idStage = idStage;
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

    public Production getProductionId() {
        return productionId;
    }

    public void setProductionId(Production productionId) {
        this.productionId = productionId;
    }

    
    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
}
