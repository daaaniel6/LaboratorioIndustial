/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.Step;
import Production.facade.ProductionFacadeLocal;
import User.exception.UserException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author daniel
 */
@Path("/production")
@Stateless
@Produces("application/json")
public class ProductionResource {

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ProductionDTO getProductionById(@PathParam("id") Integer id) {
        return new ProductionDTO(productionFacadeLocal.getProductionById(id).get());
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/step/{idStep}")
    @Produces("application/json")
    public StepDTO getStepById(@PathParam("idStep") Integer id) {
        return new StepDTO(productionFacadeLocal.findByIdStep(id).get());
    }

    @GET
    @Path("/step/{idStep}")
    @Produces("application/json")
    public StepDTO editStep(@PathParam("idStep") Integer id) {
        try {
            Step step = productionFacadeLocal.findByIdStep(id).get();
            productionFacadeLocal.edit(step);
            return new StepDTO(step);
        } catch (UserException ex) {
            //TODO: handle exception  088313006537

            Logger.getLogger(ProductionResource.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }

    @GET
    @Path("/step/{idStep}")
    @Produces("application/json")
    public StepDTO removeStep(@PathParam("idStep") Integer id) {
        try {
            Step step = productionFacadeLocal.findByIdStep(id).get();
            productionFacadeLocal.remove(step);
            return new StepDTO(step);
        } catch (UserException ex) {
            //TODO: handle exception  088313006537

            Logger.getLogger(ProductionResource.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @GET
    @Path("/stage/{id}")
    @Produces("application/json")
    public StageDTO getStageById(@PathParam("id") Integer id) {
        return new StageDTO(productionFacadeLocal.findByIdStage(id).get());
    }

}
