package gt.edu.usac.cunoc.ingenieria.supply.supply;

import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.facade.SupplyFacadeLocal;
import Supply.repository.AvailabilityFilter;
import Supply.repository.ExpirationDateFilter;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class searchSupplyView implements Serializable {

    private static final String EMPTY_SEARCH = "No existen Insumos con esas caracteristicas";

    @EJB
    private SupplyFacadeLocal supplyFacade;

    @EJB
    private SupplyFacadeLocal supplyFacadeLocal;

    @EJB
    private UserFacadeLocal userFacade;

    List<Supply> supplies = new ArrayList<>();
    List<Measure> measures = new ArrayList<>();

    //Search Criterias
    Integer codeSearchCriteria;
    String nameSearchCriteria;
    String internCodeSearchCriteria;
    AvailabilityFilter availabilitySearchCriteria;
    ExpirationDateFilter expirationDateSearchCriteria;

    //Selected Supply
    Supply selectedSupply = new Supply();
    Double quantity;
    String note;

    @PostConstruct
    public void init() {
        getAllMeasure();
    }

    public void saveChangesByMissing(final String modalIdToClose) {
        try {
            if (selectedSupply != null && !userFacade.getAuthenticatedUser().isEmpty() && (userFacade.getAuthenticatedUser().size() == 1)) {
                supplyFacadeLocal.modifyQuantity(selectedSupply, quantity, userFacade.getAuthenticatedUser().get(0), note);
            }
            cleanActualSupply();
            PrimeFaces.current().executeScript("PF('" + modalIdToClose + ").hide()");
        } catch (MandatoryAttributeSupplyException ex) {
            Logger.getLogger(searchSupplyView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserException e) {
            MessageUtils.addErrorMessage("Usuario Invalido o nulo");
        }
    }

    public void updateSupply(final String modalIdToClose) throws UserException {
        if (selectedSupply != null) {
            supplyFacade.modifySupply(selectedSupply);
            MessageUtils.addSuccessMessage("Se actualizo el Insumo");
        }
        cleanActualSupply();
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }

    public Supply getSelectedSupply() {
        if (selectedSupply == null) {
            selectedSupply = new Supply();
        }
        return selectedSupply;
    }

    public void setSelectedSupply(final Supply selectedSupply) {
        this.selectedSupply = selectedSupply;
    }

    public void searchSupplies() {
        System.out.println("Inicia la busqueda");
        this.setSupplies(supplyFacade.searchSupplies(codeSearchCriteria, internCodeSearchCriteria, nameSearchCriteria, availabilitySearchCriteria, expirationDateSearchCriteria));
        if (getSupplies().isEmpty()) {
            MessageUtils.addSuccessMessage(EMPTY_SEARCH);
        }
    }

    public void cleanCriteria() {
        codeSearchCriteria = null;
        nameSearchCriteria = null;
        availabilitySearchCriteria = null;
        expirationDateSearchCriteria = null;
    }

    public void cleanActualSupply() {
        setSelectedSupply(null);
    }

    private void getAllMeasure() {
        measures = supplyFacade.getAllMeasures();
    }

    public SupplyFacadeLocal getSupplyFacade() {
        return supplyFacade;
    }

    public void setSupplyFacade(final SupplyFacadeLocal supplyFacade) {
        this.supplyFacade = supplyFacade;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(final List<Supply> supplies) {
        this.supplies = supplies;
    }

    public Integer getCodeSearchCriteria() {
        return codeSearchCriteria;
    }

    public void setCodeSearchCriteria(final Integer codeSearchCriteria) {
        this.codeSearchCriteria = codeSearchCriteria;
    }

    public String getNameSearchCriteria() {
        return nameSearchCriteria;
    }

    public void setNameSearchCriteria(final String nameSearchCriteria) {
        this.nameSearchCriteria = nameSearchCriteria;
    }

    public AvailabilityFilter getAvailabilitySearchCriteria() {
        return availabilitySearchCriteria;
    }

    public void setAvailabilitySearchCriteria(final AvailabilityFilter availabilitySearchCriteria) {
        this.availabilitySearchCriteria = availabilitySearchCriteria;
    }

    public ExpirationDateFilter getExpirationDateSearchCriteria() {
        return expirationDateSearchCriteria;
    }

    public void setExpirationDateSearchCriteria(final ExpirationDateFilter expirationDateSearchCriteria) {
        this.expirationDateSearchCriteria = expirationDateSearchCriteria;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInternCodeSearchCriteria() {
        return internCodeSearchCriteria;
    }

    public void setInternCodeSearchCriteria(String internCodeSearchCriteria) {
        this.internCodeSearchCriteria = internCodeSearchCriteria;
    }

}
