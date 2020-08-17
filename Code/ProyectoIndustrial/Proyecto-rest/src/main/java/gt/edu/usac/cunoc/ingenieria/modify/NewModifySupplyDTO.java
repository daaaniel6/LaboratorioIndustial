package gt.edu.usac.cunoc.ingenieria.modify;

import java.util.Objects;

/**
 *
 * @author angelrg
 */
public class NewModifySupplyDTO {

    private double quantity;
    private String note;
    private Integer codeSupply;
    private Integer carnetUser;

    public NewModifySupplyDTO() {
    }

    public NewModifySupplyDTO(double quantity, String note, Integer codeSupply, Integer carnetUser) {
        this.quantity = quantity;
        this.note = note;
        this.codeSupply = codeSupply;
        this.carnetUser = carnetUser;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.codeSupply);
        hash = 13 * hash + Objects.hashCode(this.carnetUser);
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
        final NewModifySupplyDTO other = (NewModifySupplyDTO) obj;
        if (!Objects.equals(this.codeSupply, other.codeSupply)) {
            return false;
        }
        if (!Objects.equals(this.carnetUser, other.carnetUser)) {
            return false;
        }
        return true;
    }

}
