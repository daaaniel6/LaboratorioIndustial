package gt.edu.usac.cunoc.ingenieria.supply;

import gt.edu.usac.cunoc.ingenieria.modify.NewModifySupplyDTO;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author angelrg
 */
@Path("/supplies")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class SupplyResource {

    @EJB
    SupplyFacadeLocal supplyFacade;

    @GET
    @Path("/available")
    public Response getSupplies() {
        return Response
                .status(Response.Status.FOUND)
                .entity(resultConverted(
                        supplyFacade.getSupplyAvailable())
                ).build();
    }

    @GET
    @Path("/search")
    public Response findSupplies(
            @QueryParam("supplyid") Integer supplyId,
            @QueryParam("internalCode") String internalCode,
            @QueryParam("supplyname") String supplyname) {
        /*
        TO-DO add enum for search
         */
        return Response
                .status(Response.Status.FOUND)
                .entity(resultConverted(
                        supplyFacade.searchSupplies(
                                supplyId,
                                internalCode,
                                supplyname,
                                null, null))
                ).build();
    }

    @GET
    @Path("/{id}")
    public Response getSupplyByID(@PathParam("id") Integer id) {
        Optional<Supply> result = supplyFacade.findSupplyByID(id);

        if (result.isPresent()) {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(new SupplyDTO(result.get()))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Response.Status.NOT_FOUND + ": Supply not found")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSupply(SupplyDTO supplyDTO) {
        Optional<Measure> measure = supplyFacade.getMeasureById(supplyDTO.getMeasureID());

        if (measure.isPresent()) {
            try {
                return Response
                        .status(Response.Status.CREATED)
                        .entity(new SupplyDTO(
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
                                        measure.get())))
                        ).build();
            } catch (MandatoryAttributeSupplyException e) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                        .build();
            }
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Response.Status.NOT_FOUND + ": Supply not found")
                .build();
    }

    @POST
    @Path("/modify")
    public Response modifySupplyQuantity(NewModifySupplyDTO newModify) {
        Supply supply = new Supply();
        supply.setCode(newModify.getCodeSupply());
        User user = new User();
        user.setCarnet(newModify.getCarnetUser());
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(new SupplyDTO(
                            supplyFacade.modifyQuantity(
                                    supply,
                                    newModify.getQuantity(),
                                    user,
                                    newModify.getNote()))
                    ).build();
        } catch (MandatoryAttributeSupplyException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @PUT
    public Response updateSupply(updateSupplyDTO supply) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(new SupplyDTO(supplyFacade.modifySupply(
                            new Supply(
                                    supply.getCode(),
                                    supply.getName(),
                                    supply.getExpirationDate(),
                                    supply.getCost(),
                                    supply.getDescription())))
                    ).build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
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
