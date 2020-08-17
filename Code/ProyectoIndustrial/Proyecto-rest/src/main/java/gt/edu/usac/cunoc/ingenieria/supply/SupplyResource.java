package gt.edu.usac.cunoc.ingenieria.supply;

import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.facade.SupplyFacadeLocal;
import User.User;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelrg
 */
@Path("/supply")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class SupplyResource {

    @EJB
    SupplyFacadeLocal supplyFacade;

    @GET
    @Path("/measures")
    public List<MeasureDTO> getMeasures() {
        List<MeasureDTO> result = new ArrayList<>();
        supplyFacade.getAllMeasures().forEach((mod) -> {
            result.add(new MeasureDTO(mod));
        });
        return result;
    }

    @GET
    @Path("/measure/{id}")
    public MeasureDTO getMeasureByID(@PathParam("id") Integer id) {
        Optional<Measure> result = supplyFacade.getMeasureById(id);
        if (result.isPresent()) {
            return new MeasureDTO(supplyFacade.getMeasureById(id).get());
        }
        /*
        TO-DO add throw error
         */
        return null;
    }

    @GET
    @Path("/available")
    public List<SupplyDTO> getSupplies() {
        return resultConverted(supplyFacade.getSupplyAvailable());
    }

    @GET
    public List<SupplyDTO> findSupplies(
            @QueryParam("supplyid") Integer supplyId,
            @QueryParam("internalCode") String internalCode,
            @QueryParam("supplyname") String supplyname) {
        /*
        TO-DO add enum for search
         */
        return resultConverted(supplyFacade.searchSupplies(supplyId, internalCode, supplyname, null, null));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public SupplyDTO createSupply(SupplyDTO supplyDTO) {
        System.out.println("Code: " + supplyDTO + ", Internal: " + supplyDTO.getInternalCode() + ", name: "
                + supplyDTO.getName() + ", DateE: " + supplyDTO.getExpirationDate().toString() + ", DateA: "
                + supplyDTO.getDateOfAdmission().toString() + ", Cost: " + supplyDTO.getCost() + ", Quantity: "
                + supplyDTO.getQuantity() + ", Available: " + supplyDTO.isAvailability() + ", Desc: "
                + supplyDTO.getDescription() + ", Measure: " + supplyDTO.getMeasure().getName());
        Optional<Measure> measure = supplyFacade.getMeasureById(supplyDTO.getMeasure().getIdMeasure());

        if (measure.isPresent()) {
            try {
                supplyFacade.createSupply(new Supply(
                        supplyDTO.getCode(),
                        supplyDTO.getInternalCode(),
                        supplyDTO.getName(),
                        supplyDTO.getExpirationDate(),
                        supplyDTO.getDateOfAdmission(),
                        supplyDTO.getCost(),
                        supplyDTO.getQuantity(),
                        supplyDTO.isAvailability(),
                        supplyDTO.getDescription(),
                        measure.get()));
            } catch (MandatoryAttributeSupplyException e) {
                /*
                TO-DO throw error
                 */
                return null;
            }
        }
        /*
        TO-DO throw error
         */
        return null;
    }

    @POST
    @Path("/modify")
    public SupplyDTO modifySupplyQuantity(NewModifySupplyDTO newModify) {
        Supply supply = new Supply();
        supply.setCode(newModify.getCodeSupply());
        User user = new User();
        user.setCarnet(newModify.getCarnetUser());
        try {
            return new SupplyDTO(supplyFacade.modifyQuantity(supply, newModify.getQuantity(), user, newModify.getNote()));
        } catch (MandatoryAttributeSupplyException e) {
            /*
            TO-DO throw error
             */
            return null;
        }
    }

    @PUT
    @Path("/{id}")
    public SupplyDTO updateSupply(@PathParam("id") Integer id, updateSupplyDTO supply) {
        try {
            return new SupplyDTO(
                    supplyFacade.modifySupply(
                            new Supply(
                                    supply.getCode(),
                                    supply.getName(),
                                    supply.getExpirationDate(),
                                    supply.getCost(),
                                    supply.getDescription())
                    ));
        } catch (UserException e) {
            /*
            TO-DO throw error
             */
            return null;
        }
    }

    private List<SupplyDTO> resultConverted(List<Supply> queryResult) {
        List<SupplyDTO> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new SupplyDTO(mod));
        });
        return result;
    }
}
