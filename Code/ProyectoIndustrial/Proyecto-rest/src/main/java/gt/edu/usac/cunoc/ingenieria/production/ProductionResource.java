/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Design.Design;
import Group.facade.GroupFacadelocal;
import Production.ExtraCost;
import Production.Production;
import Production.Stage;

import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
import User.exception.UserException;
import gt.edu.usac.cunoc.ingenieria.design.DesignDTO;
import java.time.LocalDate;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author daniel
 */
@Path("/productions")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class ProductionResource {

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;
    @EJB
    private GroupFacadelocal groupFacadelocal;

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ProductionDTO getProductionById(@PathParam("id") Integer id) {
//        return new ProductionDTO(productionFacadeLocal.getProductionById(id).get());
//    }
//
//    @GET
//    public List<ProductionDTO> getProductins() {
//        List<ProductionDTO> result = new ArrayList<>();
//        productionFacadeLocal.AllProductions().forEach((mod) -> {
//            result.add(new ProductionDTO(mod));
//        });
//        return result;
//    }
//
//    @GET
//    @Path("/search")
//    public List<ProductionDTO> findProductions(
//            @QueryParam("idProduction") Integer id,
//            @QueryParam("name") String name,
//            @QueryParam("startDate") LocalDate starDate,
//            @QueryParam("enDate") LocalDate endDate,
//            @QueryParam("editable") Boolean editable) {
//
//        List<ProductionDTO> result = new ArrayList<>();
//        productionFacadeLocal.findProduction(id, name, starDate, endDate, editable).forEach((mod) -> {
//            result.add(new ProductionDTO(mod));
//        });
//        return result;
//
//    }
//
//    @POST //crear produccion
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createProduction(ProductionDTO productionDTO) {
//
//        try {
//            ProductionDTO create = new ProductionDTO(
//                    productionFacadeLocal.createProduction(
//                            new Production(productionDTO.getIdProduction(), productionDTO.getName(),
//                                    productionDTO.getStartDate(), productionDTO.getState())
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
//
//    @PUT //modificar Produccion
//    public Response updateProduction(ProductionDTO productionDTO) {
//        try {
//            ProductionDTO update = new ProductionDTO(
//                    productionFacadeLocal.editProduction(
//                            new Production(
//                                    productionDTO.getIdProduction(), productionDTO.getName(),
//                                    productionDTO.getStartDate(), productionDTO.getState()
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
    /**
     * ---------------------Producto----------------------------------------
     *
     */
//    @GET
//    @Path("/product/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ProductODT getProductById(@PathParam("id") Integer id) {
//        return new ProductODT(productionFacadeLocal.getProductById(id).get());
//    }
//
//    @GET
//    @Path("/product")
//    public List<ProductODT> getProducts() {
//        List<ProductODT> result = new ArrayList<>();
//        productionFacadeLocal.getProduct().forEach((mod) -> {
//            result.add(new ProductODT(mod));
//        });
//        return result;
//    }
    /**
     * ------------------------------------STEP---------------------------------
     *
     * @param id
     * @return
     */
    @GET
    @Path("/steps/{idStep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStepById(@PathParam("idStep") Integer id) {
        try {
            StepDTO stepDTO = new StepDTO(productionFacadeLocal.findByIdStep(id).get());
            return Response
                    .ok()
                    .entity(stepDTO)
                    .build();

        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @PUT //modificar paso
//    @Path("/step")
//    public Response updateStep(StepDTO step) {
//        try {
//            StepDTO stepDTO = new StepDTO(
//                    productionFacadeLocal.edit(
//                            new Step(
//                                    step.getIdStep(),
//                                    step.getName(),
//                                    step.getDescription(),
//                                    productionFacadeLocal.findByIdStage(step.getStageId()).get()
//                            )
//                    ));
//            return Response
//                    .ok()
//                    .entity(stepDTO)
//                    .build();
//
//        } catch (UserException e) {
//
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//    @PUT //modificar un comentario de un paso
//    @Path("/step")
//    public Response updateCommentaryOfStep(ProductionDTO productionDTO) {
//        try {
//            ProductionDTO update = new ProductionDTO(
//                    productionFacadeLocal.updateCommentayOfSteps(
//                            new Production(
//                                    productionDTO.getIdProduction(),
//                                    productionDTO.getName(),
//                                    productionDTO.getStartDate(),
//                                    productionDTO.getEndDate(),
//                                    productionDTO.getState(),
//                                    productionDTO.getQualification(),
//                                    productionDTO.getQuantity(),
//                                    productionDTO.getInitCost(),
//                                    productionDTO.getFinalCost(),
//                                    productionFacadeLocal.findDesignByID(productionDTO.getDesignId()).get(),
//                                    productionFacadeLocal.findDesignByID(productionDTO.getPostDesign()).get(),
//                                    productionFacadeLocal.getProductionById(productionDTO.getIdProduction()).get().getGroupId()
//                            )
//                    )
//            );
//
//            return Response
//                    .ok()
//                    .entity(update)
//                    .build();
//
//        } catch (Exception e) {
//
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    @POST //crear paso
//    @Path("/step")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createStep(StepDTO stepDTO) {
//
////  
////         try {
////                Optional<Stage> stage = productionFacadeLocal.findByIdStage(stepDTO.getStageId());
////                return new StepDTO(
////                        productionFacadeLocal.createStep(
////                                new Step(stepDTO.getIdStep(), stepDTO.getName(), stepDTO.getDescription(), stage.get())
////                                )
////                );
////                
////               
////            } catch (Exception e) {
////                return null;
////            }
//        try {
//            Optional<Stage> stage = productionFacadeLocal.findByIdStage(stepDTO.getStageId());
//            StepDTO stepCreado = new StepDTO(
//                    productionFacadeLocal.createStep(
//                            new Step(
//                                    stepDTO.getIdStep(), stepDTO.getName(),
//                                    stepDTO.getDescription(), stage.get()
//                            )
//                    )
//            );
//
//            return Response
//                    .ok()
//                    .entity(stepCreado)
//                    .build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//    }
//
//    @DELETE
//    @Path("/step/{idStep}")
//    public Response removeStep(@PathParam("idStep") Integer id) {
//        try {
//
////            Step step = new Step(stepRemove.getName(), 
////                    stepRemove.getDescription(), 
////                    productionFacadeLocal.findByIdStage(stepRemove.getStageId()).get()
////            );
//            productionFacadeLocal.remove(productionFacadeLocal.findByIdStep(id).get());
//            return Response.ok().build();
//        } catch (UserException ex) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    /**
//     * ----------------------------STAGE------------------------------------
//     *
//     * @param id
//     * @return
//     */
    @GET
    @Path("/stages/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StageDTO getStageById(@PathParam("id") Integer id) {
        return new StageDTO(productionFacadeLocal.findByIdStage(id).get());
    }
//
//    //------------------------------- Calculos--------------------------------
//    @POST //actualizar los costos de una produccion 
//    @Path("/updateExtraCost")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateExtraCost(List<ExtraCostDTO> listExtraCost, ProductionDTO production) {
//        try {
//
//            List<ExtraCost> result = new ArrayList<>();
//            listExtraCost.forEach((mod) -> {
//                result.add(new ExtraCost(mod.getIdExtraCost(), mod.getDescription(), mod.getCost()));
//            });
//
//            Production productionParam = productionFacadeLocal.getProductionById(production.getIdProduction()).get();
//
//            productionFacadeLocal.updateExtraCost(result, productionParam);
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
//    
//    @POST //actualizar el post design de una produccion 
//    @Path("/addPostDesign")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addPostDesign(DesignDTO postDesign, ProductionDTO production) {
//        try {
//
//            Design postDesignParam =  productionFacadeLocal.findDesignByID(postDesign.getIdDesign()).get();
//            Production productionParam = productionFacadeLocal.getProductionById(production.getIdProduction()).get();
//
//            productionFacadeLocal.addPostDedign(postDesignParam, productionParam);
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
//    @GET
//    @Path("/{idProduction}/initCost")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Double getInitCost(@PathParam("idProduction") Integer id) {
//        return productionFacadeLocal.initCost(productionFacadeLocal.getProductionById(id).get());
//    }
//    
//    @GET
//    @Path("/{idProduction}/initCost")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Double getFinalCost(@PathParam("idProduction") Integer id) {
//        return productionFacadeLocal.finalCost(productionFacadeLocal.getProductionById(id).get());
//    }
//    
//    
//    @GET
//    @Path("/{idProduction}/initCost")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Double getTotalExtraCost(@PathParam("idProduction") Integer id) {
//        return productionFacadeLocal.totalExtraCost(productionFacadeLocal.getProductionById(id).get());
//    }
}
