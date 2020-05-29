package Supply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(
        name = "measure"
)
public class Measure implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_measure")
    private Integer idMeasure;
    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "measure")
    private List<Supply> SupplyCollection = new ArrayList<Supply>();

    public Measure() {
    }

    public Measure(Integer idMeasure, String name, List<Supply> SupplyCollection) {
        this.idMeasure = idMeasure;
        this.name = name;
        this.SupplyCollection.addAll(SupplyCollection);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measure)) return false;
        Measure measure = (Measure) o;
        return Objects.equals(getIdMeasure(), measure.getIdMeasure());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getIdMeasure());
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

    public List<Supply> getSupplyCollection() {
        return SupplyCollection;
    }

    public void setSupplyCollection(List<Supply> SupplyCollection) {
        this.SupplyCollection = SupplyCollection;
    }
}
