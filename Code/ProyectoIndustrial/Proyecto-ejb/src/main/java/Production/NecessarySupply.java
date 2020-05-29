package Production;

import Supply.Supply;
import Design.Design;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author angelrg
 */
@Entity
@Table(name = "necessary_supply")
public class NecessarySupply implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id_necessary_supply")
    private Integer idNecessarySupply;
    @Basic(optional = false)
    @Column(name = "quantity")
    private double quantity;
    @JoinColumn(name = "design_id", referencedColumnName = "id_design")
    @ManyToOne(optional = false)
    private Design designId;
    @JoinColumn(name = "supply_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Supply supplyCode;

    public NecessarySupply() {
    }

    public NecessarySupply(Integer idNecessarySupply) {
        this.idNecessarySupply = idNecessarySupply;
    }

    public NecessarySupply(Integer idNecessarySupply, double quantity) {
        this.idNecessarySupply = idNecessarySupply;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NecessarySupply)) {
            return false;
        }
        NecessarySupply necessarySupply = (NecessarySupply) o;
        return Objects.equals(getIdNecessarySupply(), necessarySupply.getIdNecessarySupply());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdNecessarySupply());

    }

    public Integer getIdNecessarySupply() {
        return idNecessarySupply;
    }

    public void setIdNecessarySupply(Integer idNecessarySupply) {
        this.idNecessarySupply = idNecessarySupply;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Design getDesignId() {
        return designId;
    }

    public void setDesignId(Design designId) {
        this.designId = designId;
    }

    public Supply getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(Supply supplyCode) {
        this.supplyCode = supplyCode;
    }
}
