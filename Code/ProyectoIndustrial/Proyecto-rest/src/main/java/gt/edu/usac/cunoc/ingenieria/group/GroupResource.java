/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.group;

import Group.GroupIndustrial;
import Group.GroupUser;
import Group.facade.GroupFacadelocal;
import User.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author daniel
 */
@Path("/groups")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @EJB
    private GroupFacadelocal groupFacadelocal;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupDTO getGroupIndustrialById(@PathParam("id") Integer id) {
        GroupIndustrialDTO groupIndustrialDTO = new GroupIndustrialDTO(groupFacadelocal.findById(id).get());
        List<GroupUser> listGroupUser = groupFacadelocal.findById(id).get().getGroupUserList();
        List<User> users = new ArrayList<>();
        for (GroupUser groupUser : listGroupUser) {
            users.add(groupUser.getUserCarnet());
        }
        return new GroupDTO(groupIndustrialDTO, users);
    }

    /**
     * devuelve un json con todos los grupos creados y su lista de integrantes
     *
     * @return List<GroupDTO>
     */
    @GET
    public List<GroupDTO> getGroupsIndustrial() {
        List<GroupDTO> result = new ArrayList<>();
        List<GroupIndustrial> groupIndustrialList = new ArrayList<>();
        groupIndustrialList = groupFacadelocal.getAll();

        for (GroupIndustrial groupIndustrial : groupIndustrialList) {
            List<User> users = new ArrayList<>();
            for (GroupUser user : groupIndustrial.getGroupUserList()) {
                users.add(user.getUserCarnet());
            }

            result.add(new GroupDTO(new GroupIndustrialDTO(groupIndustrial), users));

        }
        return result;
    }

//    @POST //crear Grupo
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createProduction(GroupDTO groupDTO) {
//
//        try {
//
//            GroupIndustrial groupIndustrial = new GroupIndustrial(
//                    groupDTO.getGroupIndustrialDTO().getIdGroup(),
//                    groupDTO.getGroupIndustrialDTO().getInformation(),
//                    groupDTO.getGroupIndustrialDTO().getName(),
//                    groupDTO.getGroupIndustrialDTO().getSection()
//            );
//            
//            groupFacadelocal.createGroup( groupIndustrial,groupDTO.getUsers());
//
//            return Response
//                    .ok()
//                    .entity(groupDTO)
//                    .build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//    }

//    @PUT //modificar Produccion
//    public Response updateProduction(GroupIndustrialDTO groupIndustrialDTO, String information, String section) {
//        try {
//            GroupIndustrialDTO update = new GroupIndustrialDTO(
//                    groupFacadelocal.updateGroup(
//                            new GroupIndustrial(
//                                    groupIndustrialDTO.getIdGroup(), groupIndustrialDTO.getInformation(),
//                                    groupIndustrialDTO.getName(), groupIndustrialDTO.getSection()
//                            ),
//                            information,
//                            section
//                    )
//            );
//            return Response
//                    .ok()
//                    .entity(update)
//                    .build();
//
//        } catch (Exception ex) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//    }

    //----------------------------------------------------------------------------
//    @DELETE
//    @Path("/users/{idGroupUser}")
//    public Response removeStep(@PathParam("idGroupUser") Integer id) {
//        try {
//            groupFacadelocal.removeUserFromGroup(groupFacadelocal.findGroupUserById(id).get());
//            return Response.ok().build();
//        } catch (Exception ex) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
