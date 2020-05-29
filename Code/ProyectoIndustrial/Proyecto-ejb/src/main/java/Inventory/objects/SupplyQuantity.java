package Inventory.objects;

import Supply.Supply;
import java.io.Serializable;

/**
 *
 * @author angelrg
 */
public class SupplyQuantity implements Serializable{

    Supply supply;
    double quantity;

    public SupplyQuantity() {
    }

    public SupplyQuantity(Supply supply, double quantity) {
        this.supply = supply;
        this.quantity = quantity;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
