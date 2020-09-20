package gt.edu.usac.cunoc.ingenieria.modify;

import Modify.ModifySupply;
import Modify.facade.ModifyFacadeLocal;
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
 * @author angelrg
 */
@Path("/modify")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class ModifyResource {

    @EJB
    private ModifyFacadeLocal modifyFacade;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModifySupplyById(@PathParam("id") Integer id) {
        return Response
                .status(Response.Status.FOUND)
                .entity(new ModifySupplyDTO(
                        modifyFacade.getById(id).get())
                ).build();
    }

    @GET
    @Path("/user/{carnet}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModificationByUser(@PathParam("carnet") Integer carnet) {
        return Response
                .status(Response.Status.FOUND)
                .entity(resultConverted(
                        modifyFacade.getModificationByUser(carnet))
                ).build();
    }

    @GET
    @Path("/supply/{supply}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModificationBySupply(@PathParam("supply") Integer supply) {
        return Response
                .status(Response.Status.FOUND)
                .entity(resultConverted(
                        modifyFacade.getModificationBySupply(supply))
                ).build();
    }

    private List<ModifySupplyDTO> resultConverted(List<ModifySupply> queryResult) {
        List<ModifySupplyDTO> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new ModifySupplyDTO(mod));
        });
        return result;
    }
}
