package gt.edu.usac.cunoc.ingenieria.user;

import User.RolUser;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
@Path("/rols")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class RolUserResource {

    @EJB
    private UserFacadeLocal userFacade;

    @GET
    public Response getAllRolUser() {
        return Response
                .status(Response.Status.FOUND)
                .entity(userFacade.getAllRolUser()).build();
    }

    @GET
    @Path("/search")
    public Response findRolUser(
            @QueryParam("id") Integer idRol,
            @QueryParam("name") String nameRol) {

        try {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(userFacade.getRolUser(
                            new RolUser(idRol, nameRol)
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
    public Response getRolUSer(@PathParam("id") Integer id) {
        try {
            Optional<RolUser> result = userFacade.findRolUserById(id);

            if (result.isPresent()) {
                return Response
                        .status(Response.Status.FOUND)
                        .entity(result.get())
                        .build();
            }
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(Response.Status.NOT_FOUND + ": Rol user not found")
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

}
