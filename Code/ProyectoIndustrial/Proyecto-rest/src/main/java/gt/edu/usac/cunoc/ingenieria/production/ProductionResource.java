/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.facade.ProductionFacadeLocal;
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
    @Path("/step/{idStep}")
    @Produces("application/json")
    public StepDTO getStepById(@PathParam("idStep") Integer id) {
        return new StepDTO(productionFacadeLocal.findByIdStep(id).get() );
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ProductionDTO getProductionById(@PathParam("id") Integer id) {
        return new ProductionDTO(productionFacadeLocal.getProductionById(id).get() );
    }
    
}
