package gt.edu.usac.cunoc.ingenieria.modify;

import Modify.ModifySupply;
import Modify.facade.ModifyFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author angelrg
 */
@Path("/modify")
@Stateless
@Produces("application/json")
public class ModifyResource {

    @EJB
    private ModifyFacadeLocal modifyFacade;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ModifySupplyDTO getModifySupplyById(@PathParam("id") Integer id) {
        return new ModifySupplyDTO(modifyFacade.getById(id).get());
    }

    @GET
    @Path("/user/{carnet}")
    @Produces("application/json")
    public List<ModifySupply> getModificationByUser(@PathParam("carnet") Integer carnet) {
        return modifyFacade.getModificationByUser(carnet);
    }

    @GET
    @Path("/supply/{supply}")
    @Produces("application/json")
    public List<ModifySupply> getModificationBySupply(@PathParam("supply") Integer supply) {
        return modifyFacade.getModificationBySupply(supply);
    }
}
