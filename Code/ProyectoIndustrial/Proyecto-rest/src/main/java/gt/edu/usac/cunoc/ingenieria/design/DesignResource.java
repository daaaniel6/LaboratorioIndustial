/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.design;

import Design.Design;
import Design.DesignData;
import Production.NecessarySupply;
import Production.Production;
import Production.facade.ProductionFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.production.ProductionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
@Path("/design")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class DesignResource {

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @GET
    public List<DesignDTO> getDesigns() {
        List<DesignDTO> result = new ArrayList<>();
        productionFacadeLocal.AllDesigns().forEach((mod) -> {
            result.add(new DesignDTO(mod));
        });
        return result;
    }

//    @POST //crear produccion
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createDesign(DesignDTO design, DesignDataDTO designData, List<NecessarySupplyDTO> necessarySupplys) {
//
//        try {
//            Design designParam = productionFacadeLocal.findDesignByID(design.getIdDesign()).get();
//            DesignData designDataParam = new DesignData(designData.getIddesignData(),
//                    designData.getName(), designData.getDescription());
//
//            List<NecessarySupply> result = new ArrayList<>();
//            necessarySupplys.forEach((mod) -> {
//                result.add(new NecessarySupply(mod.getIdNecessarySupply(), mod.getQuantity()));
//            });
//
//            productionFacadeLocal.createDesign(designParam, designDataParam, result);
//            
//            return Response
//                    .ok()
//                    .build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//    }
//
//    @PUT //modificar diseño
//    public Response updateDesign(DesignDTO designDTO) {
//        try {
//            DesignDTO update = new DesignDTO(
//                    productionFacadeLocal.editDesign(
//                            new Design(
//                                    designDTO.getIdDesign(),
//                                    productionFacadeLocal.findDesignByID(designDTO.getIdDesign()).get().getDesignData(),
//                                    productionFacadeLocal.findDesignByID(designDTO.getIdDesign()).get().getProductIdProduct()
//                            )
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

}
