package gt.udu.usac.cunoc.ingenieria.inventory.view;

import Inventory.facade.InventoryLocal;
import Inventory.objects.DesignUnits;
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
public class supplyCalculatorView implements Serializable {

    @EJB
    private InventoryLocal inventoryLocal;

    //Search utilities
    List<DesignUnits> designList = new LinkedList<>();
    Integer id;
    String designName;

    //calculate cost
    List<SupplyQuantity> suplyQuantity = new LinkedList<>();
    double unitCost;
    double totalCost;
    DesignUnits selectedDesign;

    public void searchDesign() {
        setDesignList(inventoryLocal.DesignWithUnitsPlaces(id, designName));
        if (getDesignList().isEmpty()) {
            MessageUtils.addSuccessMessage("Sin resultados en la busqueda");
        }
    }

    public void calculateCost(DesignUnits designUnits) {
        selectedDesign = designUnits;
        if (selectedDesign == null) {
            MessageUtils.addWarningMessage("Debe seleccionar una diseño");
        } else {
            if (!inventoryLocal.getNecessarySupplies(selectedDesign).isEmpty()) {
                setSuplyQuantity(inventoryLocal.getNecessarySupplies(selectedDesign));
                System.out.println("Entro");
                setTotalCost(inventoryLocal.totalCost(designUnits));
                setUnitCost(inventoryLocal.unitCost(designUnits));
            } else {
                MessageUtils.addWarningMessage("No requiere Insumos");
            }
        }
    }

    public void cleanSearch() {
        setTotalCost(0);
        setUnitCost(0);
        setSuplyQuantity(null);
    }

    public void cleanCriteria() {
        id = null;
        designName = null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDesignName(String productionName) {
        this.designName = productionName;
    }

    public String getDesignName() {
        return designName;
    }

    public void setSelectedDesign(DesignUnits selectedProduction) {
        this.selectedDesign = selectedProduction;
    }

    public void setDesignList(List<DesignUnits> productionList) {
        this.designList.clear();
        this.designList = productionList;
    }

    public List<DesignUnits> getDesignList() {
        return designList;
    }

    public DesignUnits getSelectedDesign() {
        if (selectedDesign == null) {
            selectedDesign = new DesignUnits();
        }
        return selectedDesign;
    }

    public List<SupplyQuantity> getSuplyQuantity() {
        return suplyQuantity;
    }

    public void setSuplyQuantity(List<SupplyQuantity> suplyQuantity) {
        this.suplyQuantity = suplyQuantity;
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
