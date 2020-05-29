package gt.edu.usac.cunoc.ingenieria.supply.supply;

import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.facade.SupplyFacadeLocal;
import static config.Constants.MAIN_PAGE;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class registerSupplyView implements Serializable {

    private static final String SUPPLY_CREATED = "Insumo Creado";

    @EJB
    private SupplyFacadeLocal supplyFacade;

    @Inject
    private ExternalContext externalContext;

    private Supply newSupply;
    private List<Measure> measures;

    @PostConstruct
    public void init() {
        getAllMeasure();
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Supply getNewSupply() {
        if (newSupply == null) {
            return new Supply();
        }
        return newSupply;
    }

    public void setNewSupply(Supply newSupply) {
        this.newSupply = newSupply;
    }

    private void getAllMeasure() {
        measures = supplyFacade.getAllMeasures();
        newSupply = new Supply();
    }

    public String todayDate() {
        return LocalDate.now().toString();
    }

    public void save() {
        try {
            supplyFacade.createSupply(this.newSupply);
            MessageUtils.addSuccessMessage(SUPPLY_CREATED);
            externalContext.getFlash().setKeepMessages(true);
            externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
        } catch (MandatoryAttributeSupplyException ex) {
            MessageUtils.addErrorMessage(ex.getMessage());
        } catch (IOException ex) {
            MessageUtils.addErrorMessage(ex.getMessage());
            //Logger.getLogger(registerSupplyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
