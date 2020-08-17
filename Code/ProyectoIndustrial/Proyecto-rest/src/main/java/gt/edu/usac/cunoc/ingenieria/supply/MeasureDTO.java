package gt.edu.usac.cunoc.ingenieria.supply;

import Supply.Measure;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author angelrg
 */
public class MeasureDTO implements Serializable {

    private Integer idMeasure;
    private String name;

    public MeasureDTO() {
    }

    public MeasureDTO(Measure measure) {
        this.idMeasure = measure.getIdMeasure();
        this.name = measure.getName();
    }

    public Integer getIdMeasure() {
        return idMeasure;
    }

    public void setIdMeasure(Integer idMeasure) {
        this.idMeasure = idMeasure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idMeasure);
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
        final MeasureDTO other = (MeasureDTO) obj;
        if (!Objects.equals(this.idMeasure, other.idMeasure)) {
            return false;
        }
        return true;
    }

}
