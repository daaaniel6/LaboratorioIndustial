package Modify;

import Supply.Supply;
import User.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "modify_supply")
public class ModifySupply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modify_supply")
    private Integer idModifySupply;
    @Column(name = "modify_type")
    private String modifyType;
    @Column(name = "cuantity")
    private double quantity;
    @Column(name = "date")
    private LocalDate date;
    @Lob
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "supply_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Supply supplyCode;
    @JoinColumn(name = "carnet_user", referencedColumnName = "carnet")
    @ManyToOne(optional = false)
    private User carnetUser;

    public ModifySupply() {
    }

    public ModifySupply(Integer idModifySupply) {
        this.idModifySupply = idModifySupply;
    }

    public ModifySupply(User carnetUser, Supply supplyCode, ModificationType modifyType, double quantity, LocalDate date, String note) {
        this.modifyType = modifyType.toString();
        this.quantity = quantity;
        this.date = date;
        this.note = note;
        this.supplyCode = supplyCode;
        this.carnetUser = carnetUser;
    }

    public Integer getIdModifySupply() {
        return idModifySupply;
    }

    public void setIdModifySupply(Integer idModifySupply) {
        this.idModifySupply = idModifySupply;
    }

    public ModificationType getModifyType() {
        return ModificationType.valueOf(modifyType);
    }

    public void setModifyType(ModificationType modifyType) {
        this.modifyType = modifyType.toString();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Supply getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(Supply supplyCode) {
        this.supplyCode = supplyCode;
    }

    public User getCarnetUser() {
        return carnetUser;
    }

    public void setCarnetUser(User carnetUser) {
        this.carnetUser = carnetUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idModifySupply);
        hash = 59 * hash + Objects.hashCode(this.modifyType);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.quantity) ^ (Double.doubleToLongBits(this.quantity) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.note);
        hash = 59 * hash + Objects.hashCode(this.supplyCode);
        hash = 59 * hash + Objects.hashCode(this.carnetUser);
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
        final ModifySupply other = (ModifySupply) obj;
        if (!Objects.equals(this.idModifySupply, other.idModifySupply)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
