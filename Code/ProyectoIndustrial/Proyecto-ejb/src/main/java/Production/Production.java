package Production;

import Design.Design;
import Group.GroupIndustrial;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author daniel
 * @edit angelrg
 */
@Entity
@Table(name = "production")
public class Production implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_production")
    private Integer idProduction;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Basic(optional = false)
    @Column(name = "state")
    private boolean state;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qualification")
    private Double qualification;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "init_cost")
    private Double initCost;
    @Column(name = "final_cost")
    private Double finalCost;
    @JoinColumn(name = "design_id", referencedColumnName = "id_design")
    @ManyToOne(optional = false)
    private Design designId;
    @JoinColumn(name = "post_design", referencedColumnName = "id_design")
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Design postDesign;
    @JoinColumn(name = "group_id", referencedColumnName = "id_group")
    @ManyToOne(optional = false)
    private GroupIndustrial groupId;
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    @ManyToOne(cascade = CascadeType.ALL)
    private Product productId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productionId")
    private List<Stage> stageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduction")
    private List<ExtraCost> extraCostList;

    public Production() {
    }

    public Production(Integer idProduction) {
        this.idProduction = idProduction;
    }

    public Production(Integer idProduction, String name, LocalDate startDate, boolean state) {
        this.idProduction = idProduction;
        this.name = name;
        this.startDate = startDate;
        this.state = state;
    }

    /**
     * returns true if EndDate is Null and state is false
     *
     * @return
     */
    public boolean isEditable() {
        return ((this.getEndDate() == null) && (!getState()));
    }

    public Integer getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(Integer idProduction) {
        this.idProduction = idProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getInitCost() {
        return initCost;
    }

    public void setInitCost(Double initCost) {
        this.initCost = initCost;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Design getDesignId() {
        return designId;
    }

    public void setDesignId(Design designId) {
        this.designId = designId;
    }

    public Design getPostDesign() {
        return postDesign;
    }

    public void setPostDesign(Design postDesign) {
        this.postDesign = postDesign;
    }

    public GroupIndustrial getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupIndustrial groupId) {
        this.groupId = groupId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public List<ExtraCost> getExtraCostList() {
        return extraCostList;
    }

    public void setExtraCostList(List<ExtraCost> extraCostList) {
        this.extraCostList = extraCostList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduction != null ? idProduction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Production)) {
            return false;
        }
        Production other = (Production) object;
        if ((this.idProduction == null && other.idProduction != null) || (this.idProduction != null && !this.idProduction.equals(other.idProduction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Production[ idProduction=" + idProduction + " ]";
    }

}
