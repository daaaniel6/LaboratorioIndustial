package gt.edu.usac.cunoc.ingenieria.supply;

import Supply.Measure;
import Supply.facade.SupplyFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@Path("/measures")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class MeasureResource {

    @EJB
    SupplyFacadeLocal supplyFacade;

    @GET
    public Response getMeasures() {
        List<MeasureDTO> result = new ArrayList<>();
        supplyFacade.getAllMeasures().forEach((mod) -> {
            result.add(new MeasureDTO(mod));
        });
        return Response
                .status(Response.Status.FOUND)
                .entity(result).build();
    }

    @GET
    @Path("/{id}")
    public Response getMeasureByID(@PathParam("id") Integer id) {
        Optional<Measure> result = supplyFacade.getMeasureById(id);
        if (result.isPresent()) {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(
                            new MeasureDTO(
                                    supplyFacade.getMeasureById(id).get()
                            )).build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Response.Status.NOT_FOUND + ": Measure not found")
                .build();
    }
}
