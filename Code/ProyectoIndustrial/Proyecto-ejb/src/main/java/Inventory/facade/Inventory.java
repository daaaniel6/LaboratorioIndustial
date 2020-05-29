package Inventory.facade;

import Inventory.objects.DesignUnits;
import Inventory.objects.ProductionUnits;
import Inventory.objects.SupplyQuantity;
import Inventory.repository.InventoryRepository;
import Production.Production;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;

/**
 *
 * @author angelrg
 */
@Stateless
public class Inventory implements InventoryLocal {
    
    private InventoryRepository inventoryRepository;
    
    @EJB
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SupplyQuantity> getNecessarySupplies(DesignUnits productionUnits) {
        return inventoryRepository.getNecessarySupplies(productionUnits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double unitCost(DesignUnits designUnits) {
        return inventoryRepository.unitCost(designUnits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double totalCost(DesignUnits designUnits) {
        return inventoryRepository.totalCost(designUnits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DesignUnits> DesignWithUnitsPlaces(Integer id, String nameProduction) {
        return inventoryRepository.DesignWithUnitsPlaces(id, nameProduction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductionUnits> getBestProductsBaseOnAvailableMaterial() {
        return inventoryRepository.getBestProductsBaseOnAvailableMaterial();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int maxUnitsByAvailableSupplies(Production production) {
        return inventoryRepository.maxUnitsByAvailableSupplies(production);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DesignUnits returnDesignUnit(ProductionUnits productionUnit) {
        if (productionUnit.getProduction().getPostDesign() != null) {
            return (new DesignUnits(productionUnit.getProduction().getPostDesign(), productionUnit.getUnits()));
        } else {
            return (new DesignUnits(productionUnit.getProduction().getDesignId(), productionUnit.getUnits()));
        }
    }
    
}
