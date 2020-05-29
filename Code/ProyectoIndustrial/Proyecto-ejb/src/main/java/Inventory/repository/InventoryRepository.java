package Inventory.repository;

import Design.Design;
import Inventory.objects.DesignUnits;
import Inventory.objects.ProductionUnits;
import Inventory.objects.SupplyQuantity;
import Production.NecessarySupply;
import Production.Product;
import Production.Production;
import Production.repository.DesignRepository;
import Production.repository.ProductRepository;
import Production.repository.ProductionRepository;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author angelrg
 */
@Stateless
@LocalBean
public class InventoryRepository {

    private DesignRepository designRepository;
    private ProductionRepository productionRepository;
    private ProductRepository productRepository;

    @EJB
    public void setDesignRepository(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    @EJB
    public void setProductionRepository(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @EJB
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This method return the best Productions base on the best score by Product
     *
     * use their Necessary Supplies, what is the max units to produce with the
     * available supplies
     *
     * @return
     */
    public List<ProductionUnits> getBestProductsBaseOnAvailableMaterial() {
        List<Product> products = productRepository.getAll().get();
        List<Production> productions = productionRepository.getBestProductions(products);

        List<ProductionUnits> result = new LinkedList<>();

        productions.forEach((production) -> {
            result.add(new ProductionUnits(production, maxUnitsByAvailableSupplies(production)));
        });

        return result;
    }

    /**
     * Return the cost of produce the Design units
     *
     * @param designUnits
     * @return
     */
    public double totalCost(DesignUnits designUnits) {
        return unitCost(designUnits) * designUnits.getUnits();
    }

    /**
     * Return the cost of produce a Design
     *
     * @param designUnits
     * @return
     */
    public double unitCost(DesignUnits designUnits) {
        Design design = designRepository.findDesignByID(designUnits.getDesign().getIdDesign()).get();
        double cost = 0;
        for (NecessarySupply necessarySupply : design.getNecessarySupplyList()) {
            cost += (necessarySupply.getSupplyCode().getCost() * necessarySupply.getQuantity());
        }
        return cost;
    }

    /**
     * Return the necessary supplies to produce the units of a Design
     *
     * @param designUnits
     * @return
     */
    public List<SupplyQuantity> getNecessarySupplies(DesignUnits designUnits) {
        Design design = designRepository.findDesignByID(designUnits.getDesign().getIdDesign()).get();
        ArrayList<SupplyQuantity> necesarySupplies = new ArrayList<>();

        for (NecessarySupply necessaryS : design.getNecessarySupplyList()) {
            necesarySupplies.add(new SupplyQuantity(necessaryS.getSupplyCode(), (necessaryS.getQuantity() * designUnits.getUnits())));
        }

        return necesarySupplies;
    }

    /**
     * Return all Designs and an Integer number to manage the quantity
     *
     * @param id
     * @param nameProduction
     * @return
     */
    public List<DesignUnits> DesignWithUnitsPlaces(Integer id, String nameProduction) {
        List<Design> designs = designRepository.getDesign(id, nameProduction);
        List<DesignUnits> designUnits = new ArrayList<>();
        for (Design design : designs) {
            designUnits.add(new DesignUnits(design, 1));
        }
        return designUnits;
    }

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
    public int maxUnitsByAvailableSupplies(Production production) {
        if (production.getPostDesign() == null) {
            return maxUnitsByDesign(production.getDesignId());
        } else {
            return maxUnitsByDesign(production.getPostDesign());
        }
    }

    private int maxUnitsByDesign(Design design) {
        if (!design.getNecessarySupplyList().isEmpty()) {
            int units = (int) (design.getNecessarySupplyList().get(0).getSupplyCode().getQuantity() / design.getNecessarySupplyList().get(0).getQuantity());
            for (NecessarySupply necessarySupply : design.getNecessarySupplyList()) {
                int intAux = (int) (necessarySupply.getSupplyCode().getQuantity() / necessarySupply.getQuantity());
                if (intAux < units) {
                    units = intAux;
                }
            }
            return units;
        } else {
            return 0;
        }
    }
}
