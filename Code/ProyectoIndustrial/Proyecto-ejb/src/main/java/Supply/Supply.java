package Supply;

import Modify.ModifySupply;
import Production.NecessarySupply;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

@Entity
@Table(name = "supply")
public class Supply implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    @Column(name = "internal_code")
    private String internalCode;
    @Column(name = "name")
    private String name;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "date_of_admission")
    private LocalDate dateOfAdmission;
    @Column(name = "cost")
    private double cost;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "availability")
    private boolean availability;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "id_measure", referencedColumnName = "id_measure")
    @ManyToOne(optional = false)
    private Measure measure;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplyCode")
    private List<NecessarySupply> necessarySupplyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplyCode")
    private List<ModifySupply> modifySupplyList;
    
    public Supply() {
    }
    
    public Supply(Integer code, String internalCode, String name, LocalDate expirationDate, LocalDate dateOfAdmission, double cost, double quantity, boolean availability) {
        this.code = code;
        this.internalCode = internalCode;
        this.name = name;
        this.expirationDate = expirationDate;
        this.dateOfAdmission = dateOfAdmission;
        this.cost = cost;
        this.quantity = quantity;
        this.availability = availability;
    }
    
    public Supply(Integer code, String internalCode, String name, LocalDate expirationDate, LocalDate dateOfAdmission, double cost, double quantity, boolean availability, String description, Measure measure) {
        this.code = code;
        this.internalCode = internalCode;
        this.name = name;

        this.expirationDate = expirationDate;
        this.dateOfAdmission = dateOfAdmission;
        this.cost = cost;
        this.quantity = quantity;
        this.availability = availability;
        this.description = description;
        this.measure = measure;
    }
    
    public Supply(Integer code, String internalCode, String name, LocalDate dateOfAdmission, double cost, double quantity, boolean availability, String description, Measure measure) {
        this.code = code;
        this.internalCode = internalCode;
        this.name = name;
        this.dateOfAdmission = dateOfAdmission;
        this.cost = cost;
        this.quantity = quantity;
        this.availability = availability;
        this.description = description;
        this.measure = measure;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Supply)) {
            return false;
        }
        Supply supply = (Supply) o;
        return Objects.equals(getCode(), supply.getCode());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getInternalCode() {
        return internalCode;
    }
    
    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }
    
    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getQuantity() {
        return quantity;
    }

    /**
     * when the new Quantity is equals to 0, automatically the availability set
     * to false.
     *
     * @param quantity
     */
    public void setQuantity(double quantity) {
        setAvailability(quantity != 0);
        this.quantity = quantity;
    }
    
    public boolean isAvailability() {
        return availability;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Measure getMeasure() {
        return measure;
    }
    
    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
    
    public List<NecessarySupply> getNecessarySupplyList() {
        return necessarySupplyList;
    }
    
    public void setNecessarySupplyList(List<NecessarySupply> necessarySupplyList) {
        this.necessarySupplyList = necessarySupplyList;
    }
    
    public List<ModifySupply> getModifySupplyList() {
        return modifySupplyList;
    }
    
    public void setModifySupplyList(List<ModifySupply> modifySupplyList) {
        this.modifySupplyList = modifySupplyList;
    }
}
