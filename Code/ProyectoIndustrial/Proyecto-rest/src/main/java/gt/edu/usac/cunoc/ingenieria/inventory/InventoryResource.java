package gt.edu.usac.cunoc.ingenieria.inventory;

import Design.Design;
import Inventory.facade.InventoryLocal;
import Inventory.objects.DesignUnits;
import Inventory.objects.ProductionUnits;
import Inventory.objects.SupplyQuantity;
import Production.Production;
import gt.edu.usac.cunoc.ingenieria.design.DesignDTO;
import gt.edu.usac.cunoc.ingenieria.production.ProductionDTO;
import gt.edu.usac.cunoc.ingenieria.supply.SupplyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author orlan
 */
@Path("/inventory")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @EJB
    private InventoryLocal inventory;

    @GET
    @Path("/production/{id}/maxUnit")
    public Response maxUnitsByAvailableSupplies(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.OK)
                .entity(inventory.maxUnitsByAvailableSupplies(
                        new Production(id)
                )).build();
    }

    @GET
    @Path("/BestPosibleProducts")
    public Response getBestProductsBaseOnAvailableMaterial() {
        return Response
                .status(Response.Status.OK)
                .entity(resultConvertedProdUnits(
                        inventory.getBestProductsBaseOnAvailableMaterial()
                )).build();
    }

    @GET
    @Path("/design/{idDesign}/necessarySupplies/{units}")
    public Response getNecessarySupplies(@PathParam("idDesign") Integer idDesign,
            @PathParam("units") Integer quantity) {
        DesignUnits designU = new DesignUnits(new Design(idDesign), quantity);

        return Response
                .status(Response.Status.FOUND)
                .entity(resultToSupplyQuantity(
                        inventory.getNecessarySupplies(designU))
                ).build();
    }

    @GET
    @Path("/design/{idDesign}/unitCost")
    public Response unitCost(@PathParam("idDesign") Integer idDesign) {
        DesignUnits designU = new DesignUnits(new Design(idDesign), 0);

        return Response
                .status(Response.Status.OK)
                .entity(inventory.unitCost(designU))
                .build();
    }

    @GET
    @Path("/design/{idDesign}/totalCost/{units}")
    public Response totalCost(@PathParam("idDesign") Integer idDesign,
            @PathParam("units") Integer quantity) {
        DesignUnits designU = new DesignUnits(new Design(idDesign), quantity);

        return Response
                .status(Response.Status.OK)
                .entity(inventory.totalCost(designU))
                .build();
    }

//    @GET
//    @Path("/designCalc/{id}+{name}")
//    public Response designCalc(@PathParam("id") Integer idDesign,
//            @PathParam("name") String name) {
//
//        return Response
//                .status(Response.Status.OK)
//                .entity(resultToDesignUnits(
//                        inventory.DesignWithUnitsPlaces(idDesign, name)
//                )).build();
//    }
    
    

    private List<LabProdUnitsDTO<ProductionDTO>> resultConvertedProdUnits(List<ProductionUnits> queryResult) {
        List<LabProdUnitsDTO<ProductionDTO>> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new LabProdUnitsDTO<>(
                    new ProductionDTO(
                            mod.getProduction()),
                    Double.valueOf(mod.getUnits())
            ));
        });
        return result;
    }

    private List<LabProdUnitsDTO<SupplyDTO>> resultToSupplyQuantity(List<SupplyQuantity> queryResult) {
        List<LabProdUnitsDTO<SupplyDTO>> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new LabProdUnitsDTO<>(
                    new SupplyDTO(mod.getSupply()), mod.getQuantity()
            ));
        });
        return result;
    }

//    private List<LabProdUnitsDTO<DesignDTO>> resultToDesignUnits(List<DesignUnits> queryResult) {
//        List<LabProdUnitsDTO<DesignDTO>> result = new ArrayList<>();
//        queryResult.forEach((mod) -> {
//            result.add(new LabProdUnitsDTO<>(
//                    new DesignDTO(mod.getDesign()), Double.valueOf(mod.getUnits())
//            ));
//        });
//        return result;
//    }
}
