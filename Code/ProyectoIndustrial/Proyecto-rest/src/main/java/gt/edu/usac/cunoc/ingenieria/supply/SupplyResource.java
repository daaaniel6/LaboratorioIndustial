package gt.edu.usac.cunoc.ingenieria.supply;

import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.facade.SupplyFacadeLocal;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelrg
 */
@Path("/supply")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class SupplyResource implements Serializable {

    @EJB
    SupplyFacadeLocal supplyFacade;

    @GET
    @Path("/measures")
    public List<MeasureDTO> getMeasures() {
        List<MeasureDTO> result = new ArrayList<>();
        supplyFacade.getAllMeasures().forEach((mod) -> {
            result.add(new MeasureDTO(mod));
        });
        return result;
    }

    @GET
    @Path("/measure/{id}")
    public MeasureDTO getMeasureByID(@PathParam("id") Integer id) {
        Optional<Measure> result = supplyFacade.getMeasureById(id);
        if (result.isPresent()) {
            return new MeasureDTO(supplyFacade.getMeasureById(id).get());
        }
        /*
        TO-DO add throw error
         */
        return null;
    }

    @GET
    @Path("/available")
    public List<SupplyDTO> getSupplies() {
        return resultConverted(supplyFacade.getSupplyAvailable());
    }

    @GET
    public List<SupplyDTO> findSupplies(
            @PathParam("supplyid") Integer supplyId,
            @PathParam("internalCode") String internalCode,
            @PathParam("supplyname") String supplyname) {
        /*
        TO-DO add enum for rearch
         */
        return resultConverted(supplyFacade.searchSupplies(supplyId, internalCode, supplyname, null, null));
    }

    @POST
    public SupplyDTO createSupply(SupplyDTO supplyDTO) {
        Optional<Measure> measure = supplyFacade.getMeasureById(supplyDTO.getMeasure().getIdMeasure());

        if (measure.isPresent()) {
            try {
                supplyFacade.createSupply(new Supply(
                        supplyDTO.getCode(),
                        supplyDTO.getInternalCode(),
                        supplyDTO.getName(),
                        supplyDTO.getExpirationDate(),
                        supplyDTO.getDateOfAdmission(),
                        supplyDTO.getCost(),
                        supplyDTO.getQuantity(),
                        supplyDTO.isAvailability(),
                        supplyDTO.getDescription(),
                        measure.get()));
            } catch (MandatoryAttributeSupplyException e) {
                /*
                TO-DO throw error
                 */
                return null;
            }
        }
        /*
        TO-DO throw error
         */
        return null;
    }

    @POST
    @Path("/modify")
    public SupplyDTO modifySupplyQuantity(NewModifySupplyDTO newModify) {

    }

    private List<SupplyDTO> resultConverted(List<Supply> queryResult) {
        List<SupplyDTO> result = new ArrayList<>();
        queryResult.forEach((mod) -> {
            result.add(new SupplyDTO(mod));
        });
        return result;
    }
}
