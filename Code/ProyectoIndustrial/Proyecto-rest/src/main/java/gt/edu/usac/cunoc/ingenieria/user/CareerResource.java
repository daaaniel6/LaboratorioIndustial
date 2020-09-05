package gt.edu.usac.cunoc.ingenieria.user;

import User.Career;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
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
@Path("/careers")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class CareerResource {
    
    @EJB
    private UserFacadeLocal userFacade;
    
    @GET
    public Response getAllCareers() {
        return Response
                .status(Response.Status.FOUND)
                .entity(userFacade.getAllCareer()).build();
    }
    
    @GET
    @Path("/search")
    public Response findCareer(
            @QueryParam("id") Integer idCareer,
            @QueryParam("name") String nameCareer) {

        try {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(userFacade.getCareer(
                            new Career(idCareer, nameCareer)
                    )).build();

        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/{id}")
    public Response getCareer(@PathParam("id") Integer id) {
        try {
            Optional<Career> result = userFacade.findCareerById(id);

            if (result.isPresent()) {
                return Response
                        .status(Response.Status.FOUND)
                        .entity(result.get())
                        .build();
            }
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(Response.Status.NOT_FOUND + ": Career not found")
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCareer(Career career) {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(userFacade.createCareer(career))
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }
    
    
    @PUT
    public Response updateCareer(Career career) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(userFacade.updateCareer(career))
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }
}
