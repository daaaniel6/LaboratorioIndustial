package Inventory.facade;

import Inventory.objects.DesignUnits;
import Inventory.objects.ProductionUnits;
import Inventory.objects.SupplyQuantity;
import Production.Production;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author angelrg
 */
@Local
public interface InventoryLocal {

    /**
     * Base on the Production return the max possible units to produce, base on
     * the Necessary Supplies and the available Supplies
     *
     * If doesn't exist a Post Design use the initial Design to do the
     * calculation
     *
     * @param production
     * @return
     */
    public int maxUnitsByAvailableSupplies(Production production);

    /**
     * This method return the best Productions base on the best score by Product
     *
     * use their Necessary Supplies, what is the max units to produce with the
     * available supplies
     *
     * @return
     */
    public List<ProductionUnits> getBestProductsBaseOnAvailableMaterial();

    /**
     * Get the cost base on selected Design and Quantity of units to produce
     *
     * @param designUnits
     * @return
     */
    public List<SupplyQuantity> getNecessarySupplies(DesignUnits designUnits);

    /**
     * Return the cost of the Design by a unit
     *
     * @param designUnits
     * @return
     */
    public double unitCost(DesignUnits designUnits);

    /**
     * Return the cost of produce the units base on the Design
     *
     * @param designUnits
     * @return
     */
    public double totalCost(DesignUnits designUnits);

    /**
     * Return all Designs with the units variable as int, to set the quantity of
     * units required by the user
     *
     * This method enable to do search by different filters
     *
     * @param id
     * @param nameProduction
     * @return
     */
    public List<DesignUnits> DesignWithUnitsPlaces(Integer id, String nameProduction);

    /**
     * Return a DesignUnits with the design
     * 
     * verify if exist a Post Design, ether return the design
     *
     * @param productionUnit
     * @return
     */
    public DesignUnits returnDesignUnit(ProductionUnits productionUnit) ;
}
