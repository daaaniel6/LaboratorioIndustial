package gt.udu.usac.cunoc.ingenieria.inventory.view;

import Inventory.facade.InventoryLocal;
import Inventory.objects.DesignUnits;
import Inventory.objects.ProductionUnits;
import Inventory.objects.SupplyQuantity;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class bestProductionsCalculatorView implements Serializable {
    
    @EJB
    private InventoryLocal inventoryLocal;
    
    List<ProductionUnits> productionUnitsList = new LinkedList<>();
    ProductionUnits productionUnitSelected;
    
    List<SupplyQuantity> supplyQuantity = new LinkedList<>();
    double unitCost;
    double totalCost;

    /**
     * Return the list of best Productions available
     */
    public void calculate() {
        setProductionUnitsList(inventoryLocal.getBestProductsBaseOnAvailableMaterial());
        if (getProductionUnitsList().isEmpty()) {
            MessageUtils.addSuccessMessage("Sin resultados en la busqueda");
        }
    }

    /**
     * Need a productionUnits to calculate the necessary Supplies to produce the
     * units
     *
     * @param productionUnit
     */
    public void calculateCost(ProductionUnits productionUnit) {
        setProductionUnitSelected(productionUnit);
        if (productionUnit != null) {
            DesignUnits designUnitsAux = inventoryLocal.returnDesignUnit(getProductionUnitSelected());
            setSupplyQuantity(inventoryLocal.getNecessarySupplies(designUnitsAux));
            if (!getSupplyQuantity().isEmpty()) {
                setUnitCost(inventoryLocal.unitCost(designUnitsAux));
                setTotalCost(inventoryLocal.totalCost(designUnitsAux));
            } else {
                MessageUtils.addWarningMessage("No requiere Insumos");
            }
        } else {
            MessageUtils.addWarningMessage("Debe seleccionar una produccion");
        }
    }
    
    public void cleanSearch() {
        setTotalCost(0);
        setUnitCost(0);
        getSupplyQuantity().clear();
    }
    
    public void cleanInfo() {
        setProductionUnitSelected(null);
    }
    
    public List<ProductionUnits> getProductionUnitsList() {
        return productionUnitsList;
    }
    
    public void setProductionUnitsList(List<ProductionUnits> productionUnitsList) {
        this.productionUnitsList.clear();
        this.productionUnitsList = productionUnitsList;
    }
    
    public ProductionUnits getProductionUnitSelected() {
        return productionUnitSelected;
    }
    
    public void setProductionUnitSelected(ProductionUnits productionUnitSelected) {
        this.productionUnitSelected = productionUnitSelected;
    }
    
    public List<SupplyQuantity> getSupplyQuantity() {
        return supplyQuantity;
    }
    
    public void setSupplyQuantity(List<SupplyQuantity> suplyQuantity) {
        this.supplyQuantity.clear();
        this.supplyQuantity = suplyQuantity;
    }
    
    public double getUnitCost() {
        return unitCost;
    }
    
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
}
