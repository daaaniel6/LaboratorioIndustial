/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.Production;
import Production.Stage;

import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@Path("/production")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class ProductionResource {

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductionDTO getProductionById(@PathParam("id") Integer id) {
        return new ProductionDTO(productionFacadeLocal.getProductionById(id).get());
    }

    @GET
    public List<ProductionDTO> getProductins() {
        List<ProductionDTO> result = new ArrayList<>();
        productionFacadeLocal.AllProductions().forEach((mod) -> {
            result.add(new ProductionDTO(mod));
        });
        return result;
    }

    @POST //crear produccion
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduction(ProductionDTO productionDTO) {

        try {
            ProductionDTO create = new ProductionDTO(
                    productionFacadeLocal.createProduction(
                            new Production(productionDTO.getIdProduction(), productionDTO.getName(),
                                    productionDTO.getStartDate(), productionDTO.getState())
                    )
            );

            return Response
                    .ok()
                    .entity(create)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @PUT //modificar Produccion
    public Response updateProduction(ProductionDTO productionDTO) {
        try {
            ProductionDTO update = new ProductionDTO(
                    productionFacadeLocal.editProduction(
                            new Production(
                                    productionDTO.getIdProduction(), productionDTO.getName(),
                                    productionDTO.getStartDate(), productionDTO.getState()
                            )
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

    /**---------------------Producto
     * 
     */
    
    @GET
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductODT getProductById(@PathParam("id") Integer id) {
        return new ProductODT(productionFacadeLocal.getProductById(id).get());
    }

    @GET
    @Path("/product")
    public List<ProductODT> getProducts() {
        List<ProductODT> result = new ArrayList<>();
        productionFacadeLocal.getProduct().forEach((mod) -> {
            result.add(new ProductODT(mod));
        });
        return result;
    }
    
    
    
    /**
     * ------------------------------------STEP
     *
     * @param id
     * @return
     */
    @GET
    @Path("/step/{idStep}")
    @Produces(MediaType.APPLICATION_JSON)
    public StepDTO getStepById(@PathParam("idStep") Integer id) {
        return new StepDTO(productionFacadeLocal.findByIdStep(id).get());
    }

    @PUT //modificar paso
    @Path("/step")
    public Response updateStep(StepDTO step) {
        try {
            StepDTO stepDTO = new StepDTO(
                    productionFacadeLocal.edit(
                            new Step(
                                    step.getIdStep(),
                                    step.getName(),
                                    step.getDescription(),
                                    productionFacadeLocal.findByIdStage(step.getStageId()).get()
                            )
                    ));
            return Response
                    .ok()
                    .entity(stepDTO)
                    .build();

        } catch (UserException e) {
            /*
            TO-DO throw error
             */
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST //crear paso
    @Path("/step")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStep(StepDTO stepDTO) {

//  
//         try {
//                Optional<Stage> stage = productionFacadeLocal.findByIdStage(stepDTO.getStageId());
//                return new StepDTO(
//                        productionFacadeLocal.createStep(
//                                new Step(stepDTO.getIdStep(), stepDTO.getName(), stepDTO.getDescription(), stage.get())
//                                )
//                );
//                
//               
//            } catch (Exception e) {
//                return null;
//            }
        try {
            Optional<Stage> stage = productionFacadeLocal.findByIdStage(stepDTO.getStageId());
            StepDTO stepCreado = new StepDTO(
                    productionFacadeLocal.createStep(
                            new Step(stepDTO.getIdStep(), stepDTO.getName(),
                                    stepDTO.getDescription(), stage.get())
                    )
            );

            return Response
                    .ok()
                    .entity(stepCreado)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("/step/{idStep}")
    public Response removeStep(@PathParam("idStep") Integer id) {
        try {

//            Step step = new Step(stepRemove.getName(), 
//                    stepRemove.getDescription(), 
//                    productionFacadeLocal.findByIdStage(stepRemove.getStageId()).get()
//            );
            productionFacadeLocal.remove(productionFacadeLocal.findByIdStep(id).get());
            return Response.ok().build();
        } catch (UserException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * ----------------------------STAGE------------------------------------
     *
     * @param id
     * @return
     */
    @GET
    @Path("/stage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StageDTO getStageById(@PathParam("id") Integer id) {
        return new StageDTO(productionFacadeLocal.findByIdStage(id).get());
    }

}
