package gt.edu.usac.cunoc.ingenieria.modify;

import Modify.ModificationType;
import Modify.ModifySupply;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author angelrg
 */
public class ModifySupplyDTO implements Serializable {

    private Integer idModifySupply;
    private ModificationType modifyType;
    private double quantity;
    private LocalDate date;
    private String note;
    private Integer codeSupply;
    private Integer carnetUser;

    public ModifySupplyDTO(ModifySupply modifySupply) {
        this.idModifySupply = modifySupply.getIdModifySupply();
        this.modifyType = modifySupply.getModifyType();
        this.quantity = modifySupply.getQuantity();
        this.date = modifySupply.getDate();
        this.note = modifySupply.getNote();
        this.codeSupply = modifySupply.getSupplyCode().getCode();
        this.carnetUser = modifySupply.getCarnetUser().getCarnet();
    }

    public Integer getIdModifySupply() {
        return idModifySupply;
    }

    public void setIdModifySupply(Integer idModifySupply) {
        this.idModifySupply = idModifySupply;
    }

    public ModificationType getModifyType() {
        return modifyType;
    }

    public void setModifyType(ModificationType modifyType) {
        this.modifyType = modifyType;
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

    public Integer getCodeSupply() {
        return codeSupply;
    }

    public void setCodeSupply(Integer codeSupply) {
        this.codeSupply = codeSupply;
    }

    public Integer getCarnetUser() {
        return carnetUser;
    }

    public void setCarnetUser(Integer carnetUser) {
        this.carnetUser = carnetUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idModifySupply);
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
        final ModifySupplyDTO other = (ModifySupplyDTO) obj;
        if (!Objects.equals(this.idModifySupply, other.idModifySupply)) {
            return false;
        }
        return true;
    }

}