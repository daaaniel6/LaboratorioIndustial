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
    public ModifySupplyDTO getModifySupplyById(@PathParam("id") Integer id) {
        return new ModifySupplyDTO(modifyFacade.getById(id).get());
    }

    @GET
    @Path("/user/{carnet}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModifySupplyDTO> getModificationByUser(@PathParam("carnet") Integer carnet) {
        return resultConverted(modifyFacade.getModificationByUser(carnet));
    }

    @GET
    @Path("/supply/{supply}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModifySupplyDTO> getModificationBySupply(@PathParam("supply") Integer supply) {
        return resultConverted(modifyFacade.getModificationBySupply(supply));
    }

    private List<ModifySupplyDTO> resultConverted(List<ModifySupply> queryResult) {
        List<ModifySupplyDTO> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new ModifySupplyDTO(mod));
        });
        return result;
    }
}
