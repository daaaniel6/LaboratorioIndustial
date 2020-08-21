package gt.edu.usac.cunoc.ingenieria.inventory;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author orlan
 * @param <T>
 */
public class LabProdUnitsDTO<T> implements Serializable {

    private T LabProd;
    private Double units;

    public LabProdUnitsDTO() {
    }

    public LabProdUnitsDTO(T LabProd, Double units) {
        this.LabProd = LabProd;
        this.units = units;
    }

    public T getLabProd() {
        return LabProd;
    }

    public void setLabProd(T LabProd) {
        this.LabProd = LabProd;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.LabProd);
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
        final LabProdUnitsDTO<?> other = (LabProdUnitsDTO<?>) obj;
        if (!Objects.equals(this.LabProd, other.LabProd)) {
            return false;
        }
        return true;
    }

}
