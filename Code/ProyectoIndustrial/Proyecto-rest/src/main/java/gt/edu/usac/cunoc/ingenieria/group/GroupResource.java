/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.group;

import Group.GroupIndustrial;
import Group.facade.GroupFacadelocal;
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
@Path("/group")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

    @EJB
    private GroupFacadelocal groupFacadelocal;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GroupIndustrialDTO getGroupIndustrialById(@PathParam("id") Integer id) {
        return new GroupIndustrialDTO(groupFacadelocal.findById(id).get());
    }
    
    @GET
    public List<GroupIndustrialDTO> getGroupsIndustrial() {
        List<GroupIndustrialDTO> result = new ArrayList<>();
        groupFacadelocal.getAll().forEach((mod) -> {
            result.add(new GroupIndustrialDTO(mod));
        });
        return result;
    }
    
//    @POST //crear produccion
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createProduction(GroupIndustrialDTO groupIndustrialDTO) {
//
//        try {
//            GroupIndustrialDTO create = new GroupIndustrialDTO(
//                    groupFacadelocal.createGroup(
//                            new GroupIndustrial(groupIndustrialDTO.getIdGroup(), groupIndustrialDTO.getInformation(),
//                                    groupIndustrialDTO.getName(), groupIndustrialDTO.getSection(),listado)
//                    )
//            );
//
//            return Response
//                    .ok()
//                    .entity(create)
//                    .build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//    }
    @PUT //modificar Produccion
    public Response updateProduction(GroupIndustrialDTO groupIndustrialDTO, String information, String section) {
        try {
            GroupIndustrialDTO update = new GroupIndustrialDTO(
                    groupFacadelocal.updateGroup(
                            new GroupIndustrial(
                                    groupIndustrialDTO.getIdGroup(), groupIndustrialDTO.getInformation(),
                                    groupIndustrialDTO.getName(), groupIndustrialDTO.getSection()
                            ),
                            information,
                            section
                    )
            );
            return Response
                    .ok()
                    .entity(update)
                    .build();

        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    
    //----------------------------------------------------------------------------
    
    @DELETE
    @Path("/user/{idGroupUser}")
    public Response removeStep(@PathParam("idGroupUser") Integer id) {
        try {
            groupFacadelocal.removeUserFromGroup(groupFacadelocal.findGroupUserById(id).get());
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    
    
    

}
