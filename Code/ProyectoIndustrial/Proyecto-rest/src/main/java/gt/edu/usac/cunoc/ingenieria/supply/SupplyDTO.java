package gt.edu.usac.cunoc.ingenieria.supply;

import Supply.Supply;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author angelrg
 */
public class SupplyDTO implements Serializable {

    private Integer code;
    private String internalCode;
    private String name;
    private LocalDate expirationDate;
    private LocalDate dateOfAdmission;
    private double cost;
    private double quantity;
    private boolean availability;
    private String description;
    private Integer measureID;

    public SupplyDTO() {
    }

    public SupplyDTO(Supply supply) {
        this.code = supply.getCode();
        this.internalCode = supply.getInternalCode();
        this.name = supply.getName();
        this.expirationDate = supply.getExpirationDate();
        this.dateOfAdmission = supply.getDateOfAdmission();
        this.cost = supply.getCost();
        this.quantity = supply.getQuantity();
        this.availability = supply.isAvailability();
        this.description = supply.getDescription();
        this.measureID = supply.getMeasure().getIdMeasure();
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

    public void setQuantity(double quantity) {
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

    public Integer getMeasureID() {
        return measureID;
    }

    public void setMeasureID(Integer measureID) {
        this.measureID = measureID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.code);
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
        final SupplyDTO other = (SupplyDTO) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
}
