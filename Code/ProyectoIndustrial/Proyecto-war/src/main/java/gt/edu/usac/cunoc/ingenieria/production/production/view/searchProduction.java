package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Design.Design;
import Group.GroupIndustrial;
import Group.facade.GroupFacadelocal;
import Production.Production;
import Production.Stage;
import Production.Step;
import Production.facade.ProductionFacadeLocal;
import User.exception.UserException;
import gt.edu.usac.cunoc.ingenieria.config.Constants;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author angelrg
 */
@Named
@ViewScoped
public class searchProduction implements Serializable {

    @EJB
    private ProductionFacadeLocal productionFacade;

    @EJB
    private GroupFacadelocal groupFacade;

    Constants constantes = new Constants();

    List<Production> productions;
    List<Design> designs;
    List<GroupIndustrial> groups;

    Production selectedProduction = new Production();

    // criteria search
    Integer idProduction;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    boolean editable = false;

    //Stages
    Stage preProcess;
    Stage process;
    Stage postProcess;

    // Necesary to add and remove steps
    Stage selectedStage;
    String stepName;
    String stepComentary;

    // To show available comentaries
    Step selectedStep;

    /**
     * Use idProduction, name, dataRange and editable, to find the productions
     */
    public void searchProduction() {
        getInitData();
        setProductions(productionFacade.findProduction(idProduction, name, startDate, endDate, editable));
    }

    public void saveChanges(final String modalIdToClose) {
        try {
            if (selectedProduction != null) {
                productionFacade.updateProduction(selectedProduction);
                MessageUtils.addSuccessMessage("Se actualizo la Producción");
            } else {
                MessageUtils.addErrorMessage("Debe seleccionarse una Producción");
            }
        } catch (UserException e) {
            MessageUtils.addErrorMessage(e.getMessage());
        }
        cleanSelectedProduction();
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");

    }

    public void addStep(final String modalIdToClose) {
        if (selectedStage != null) {
            Step step = new Step(stepName, stepComentary, selectedStage);
            try {
                productionFacade.createStep(step);
                MessageUtils.addSuccessMessage("Paso Agregado");
            } catch (UserException e) {
                MessageUtils.addErrorMessage(e.getMessage());
            }
        } else {
            MessageUtils.addErrorMessage("Debe seleccionarse una Etapa");
        }
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");

    }

    public void selectedProductionFrontEnd(Production selectedProduction) {
        this.selectedProduction = selectedProduction;

        if (selectedProduction.getStageList() != null) {
            for (Stage stage : selectedProduction.getStageList()) {
                if (stage.getName().equals(constantes.preProceso())) {
                    setPreProcess(stage);
                }
                if (stage.getName().equals(constantes.proceso())) {
                    setProcess(stage);
                }
                if (stage.getName().equals(constantes.postProceso())) {
                    setPostProcess(stage);
                }
            }
        }
    }

    public void deleteStep(Step step) {
        if (step != null) {
            try {
                productionFacade.remove(step);
                MessageUtils.addSuccessMessage("Paso eliminado");
            } catch (UserException e) {
                MessageUtils.addErrorMessage(e.getMessage());
            }
        } else {
            MessageUtils.addErrorMessage("Debe seleccionarse un Paso");
        }
    }

    public void updateStep(final String modalIdToClose) {
        if (selectedStep != null) {
            try {
                productionFacade.edit(selectedStep);
                MessageUtils.addSuccessMessage("Se ha actualizado el paso");
            } catch (UserException e) {
                MessageUtils.addErrorMessage(e.getMessage());
            }
        } else {
            MessageUtils.addErrorMessage("Debe elegirse un Paso");
        }
        PrimeFaces.current().executeScript("PF('" + modalIdToClose + "').hide()");
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public List<Design> getDesigns() {
        return designs;
    }

    public void setDesigns(List<Design> designs) {
        this.designs = designs;
    }

    public List<GroupIndustrial> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupIndustrial> groups) {
        this.groups = groups;
    }

    public Production getSelectedProduction() {
        if (selectedProduction == null) {
            selectedProduction = new Production();
        }
        return selectedProduction;
    }

    public void setSelectedProduction(Production selectedProduction) {
        this.selectedProduction = selectedProduction;
    }

    private void getInitData() {
        setDesigns(productionFacade.AllDesigns());
        setGroups(groupFacade.getAll());
    }

    public Integer getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(Integer idProduction) {
        this.idProduction = idProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Stage getPreProcess() {
        if (preProcess == null) {
            preProcess = new Stage();
        }
        return preProcess;
    }

    public void setPreProcess(Stage preProcess) {
        this.preProcess = preProcess;
    }

    public Stage getProcess() {
        if (process == null) {
            process = new Stage();
        }
        return process;
    }

    public void setProcess(Stage process) {
        this.process = process;
    }

    public Stage getPostProcess() {
        if (postProcess == null) {
            postProcess = new Stage();
        }
        return postProcess;
    }

    public void setPostProcess(Stage postProcess) {
        this.postProcess = postProcess;
    }

    public Step getSelectedStep() {
        if (selectedStep == null) {
            selectedStep = new Step();
        }
        return selectedStep;
    }

    public void setSelectedStep(Step selectedStep) {
        this.selectedStep = selectedStep;
    }

    public Stage getSelectedStage() {
        if (selectedStage == null) {
            selectedStage = new Stage();
        }
        return selectedStage;
    }

    public void setSelectedStage(Stage selectedStage) {
        this.selectedStage = selectedStage;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepComentary() {
        return stepComentary;
    }

    public void setStepComentary(String stepComentary) {
        this.stepComentary = stepComentary;
    }

    public void cleanCriteriaSearch() {
        setIdProduction(null);
        setName(null);
        setStartDate(null);
        setEndDate(null);
        setEditable(false);
    }

    public void cleanSearchResult() {
        setProductions(null);
        setPreProcess(null);
        setProcess(null);
        setPostProcess(null);
    }

    public void cleanSelectedProduction() {
        setSelectedProduction(null);
    }

    public void cleanStep() {
        setSelectedStep(null);
    }

    public void cleanNewStepFilds() {
        setStepName(null);
        setStepComentary(null);
    }

}
