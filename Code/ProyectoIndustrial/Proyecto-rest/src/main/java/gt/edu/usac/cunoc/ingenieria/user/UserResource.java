package gt.edu.usac.cunoc.ingenieria.user;

import User.Career;
import User.RolUser;
import User.User;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
@Path("/user")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @EJB
    private UserFacadeLocal userFacade;

    @GET
    @Path("/search")
    public Response findUsers(
            @QueryParam("carnet") Integer carnet,
            @QueryParam("name") String name,
            @QueryParam("state") Boolean state,
            @QueryParam("rolID") Integer rolID,
            @QueryParam("careerID") Integer careerID) {
        System.out.println("1: " + carnet + ", 2: " + name + ", 3: " + state + ", 4: " + rolID + ", 5: " + careerID);
        try {

            User user = new User(carnet, name, null, null, null, state, new RolUser(rolID, null), new Career(careerID, null));

            return Response
                    .status(Response.Status.FOUND)
                    .entity(resultConverted(
                            userFacade.getUser(user)))
                    .build();

        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/search/students")
    public Response getAllStudents() {

        try {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(resultConverted(userFacade.getUserEstudent())).build();

        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/careers")
    public Response getAllCareers() {
        return Response
                .status(Response.Status.FOUND)
                .entity(userFacade.getAllCareer()).build();
    }

    @GET
    @Path("/rols")
    public Response getAllRolUser() {
        return Response
                .status(Response.Status.FOUND)
                .entity(userFacade.getAllRolUser()).build();
    }

    @GET
    @Path("/career/search")
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
    @Path("/rol/search")
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
    public Response getUser(@PathParam("id") Integer id) {
        Optional<User> result = userFacade.getUserByID(id);

        if (result.isPresent()) {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(new UserDTO(result.get()))
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(Response.Status.NOT_FOUND + ": User not found")
                .build();
    }

    @GET
    @Path("/career/{id}")
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

    @GET
    @Path("/rol/{id}")
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(NewUserDTO user) {
        try {
            Optional<RolUser> rolUserSelected = userFacade.findRolUserById(user.getRolUser().getIdRolUser());
            Optional<Career> carrearSelected = userFacade.findCareerById(user.getCareer().getIdCareer());
            if (rolUserSelected.isPresent() && carrearSelected.isPresent()) {
                return Response
                        .status(Response.Status.CREATED)
                        .entity(new UserDTO(
                                userFacade.createUser(
                                        new User(
                                                user.getCarnet(),
                                                user.getName(),
                                                user.getEmail(),
                                                user.getPhone(),
                                                user.getPassword(),
                                                user.getState(),
                                                rolUserSelected.get(),
                                                carrearSelected.get())
                                )))
                        .build();
            } else {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(Response.Status.BAD_REQUEST + ": Missing Params")
                        .build();
            }
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/career")
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
    public Response updateUser(UserDTO user) {
        try {

            if (user.getRolUser() != null && user.getRolUser().getIdRolUser() != null) {
                Optional<RolUser> rolUserSelected = userFacade.findRolUserById(user.getRolUser().getIdRolUser());
                user.setRolUser(rolUserSelected.get());
            }

            if (user.getCareer() != null && user.getCareer().getIdCareer() != null) {
                Optional<Career> carrearSelected = userFacade.findCareerById(user.getCareer().getIdCareer());
                user.setCareer(carrearSelected.get());
            }
            return Response
                    .status(Response.Status.OK)
                    .entity(new UserDTO(
                            userFacade.updateUser(
                                    new User(
                                            user.getCarnet(),
                                            user.getName(),
                                            user.getEmail(),
                                            user.getPhone(),
                                            null,
                                            user.getState(),
                                            user.getRolUser(),
                                            user.getCareer()))
                    )).build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/career")
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

    @PUT
    @Path("/resetPass/{carnet}")
    public Response resetPassword(@PathParam("carnet") Integer carnet) {
        try {
            User user = new User();
            user.setCarnet(carnet);
            return Response
                    .status(Response.Status.OK)
                    .entity(new UserDTO(userFacade.resetPassword(user)))
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/LogInResetPass")
    public Response logInResetPassword(UserDTO user) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(userFacade.resetPassword(user.getCarnet(), user.getEmail()))
                    .build();
        } catch (UserException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Response.Status.BAD_REQUEST + ": " + e.getMessage())
                    .build();
        }
    }

    private List<UserDTO> resultConverted(List<User> queryResult) {
        List<UserDTO> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new UserDTO(mod));
        });
        return result;
    }
}
