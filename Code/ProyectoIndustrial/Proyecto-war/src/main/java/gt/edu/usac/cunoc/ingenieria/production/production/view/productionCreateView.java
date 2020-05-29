/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Design.Design;
import Group.GroupIndustrial;
import Group.facade.GroupFacadelocal;
import Production.Product;
import Production.Production;
import Production.Stage;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
import Production.repository.DesignRepository;
import User.exception.UserException;
import static config.Constants.MAIN_PAGE;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class productionCreateView implements Serializable {

   
//     <p:button  href="#" onclick="#{productionCreateView.createProduction()}" 
//                                       styleClass="btn btn-success"
//                                       value="Crear Produccion" 
//                                       icon="ui-icon-plusthick" 
//                                       >
//                                
//                                <span class="glyphicon glyphicon-hand-left"></span> 
//                            </p:button>
//    
    private static final String ERROR_EMPY_DESIGN = "No se ha seleccionado ningun diseño";
    private static final String ERROR_EMPY_GROUP = "No se ha seleccionado ningun grupo";
    private static final String CREATE_PRODUCTION = "La produccion se ha creado correctamente";

    private static final String ERROR_EMPTY_NAME = "La produccion No tiene un nombre";
    private static final String GROUP_SELECTED = "Grupo seleccionado";
    private static final String DESIGN_SELECTED = "Diseño seleccionado";

    private Production production;

    private List<Stage> stages;
    private Stage stagePreProduction;
    private Stage stageProduction;
    private Stage stagePostProduction;

    private List<Step> stepsPreProduction;
    private List<Step> stepsProduction;
    private List<Step> stepsPostProduction;
    private Step step;

    private List<Product> products;
    private List<Design> designs;
    private List<GroupIndustrial> groups;

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;
    @EJB
    private GroupFacadelocal groupFacadelocal;
    @Inject
    private ExternalContext externalContext;

    @PostConstruct
    public void init() {

        stagePreProduction = new Stage(null, "Pre-Proceso", "La descripcion no puede ser nula");
        stageProduction = new Stage(null, "Proceso", "La descripcion no puede ser nula");
        stagePostProduction = new Stage(null, "Post-Proceso", "La descripcion no puede ser nula");

        stepsPreProduction = new ArrayList<>();
        stepsProduction = new ArrayList<>();
        stepsPostProduction = new ArrayList<>();

        production = new Production(null, null, LocalDate.now(), false);

        stagePreProduction.setStepList(stepsPreProduction);
        stageProduction.setStepList(stepsProduction);
        stagePostProduction.setStepList(stepsPostProduction);

        stagePreProduction.setProductionId(production);
        stageProduction.setProductionId(production);
        stagePostProduction.setProductionId(production);

        stages = new ArrayList<>();
        stages.add(stagePreProduction);
        stages.add(stageProduction);
        stages.add(stagePostProduction);

        production.setStageList(stages);

        step = new Step();

        groups = new ArrayList<>();
        designs = new ArrayList<>();

        designs = productionFacadeLocal.AllDesigns();
        groups = groupFacadelocal.getAll();

    }

    /**
     * 
     */
    public void createProduction() {
        if (production.getDesignId() != null) {
            if (production.getGroupId() != null) {
                try {
                    production.setInitCost(productionFacadeLocal.initCost(production));
                    productionFacadeLocal.createProduction(production);
                    MessageUtils.addSuccessMessage(CREATE_PRODUCTION);
                    System.out.println("production create");
                    externalContext.getFlash().setKeepMessages(true);
                    externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
                } catch (MandatoryAttributeProductionException | IOException ex) {
                    Logger.getLogger(productionCreateView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageUtils.addErrorMessage(ERROR_EMPY_GROUP);
            }
        } else {
            MessageUtils.addErrorMessage(ERROR_EMPY_DESIGN);
        }
    }

    /**
     * 
     * @param design 
     */
    public void addDesign(Design design) {
        production.setDesignId(design);
        MessageUtils.addSuccessMessage(DESIGN_SELECTED);
    }

    /**
     * 
     * @param group 
     */
    public void addGroup(GroupIndustrial group) {
        production.setGroupId(group);
        MessageUtils.addSuccessMessage(GROUP_SELECTED);
    }

    public void newStepPreProduction() {
        stepsPreProduction.add(step);
        step.setStageId(stagePreProduction);
        step = null;
    }

    public void newStepProduction() {
        stepsProduction.add(step);
        step.setStageId(stageProduction);
        step = null;
    }

    public void newStepPostProduction() {
        stepsPostProduction.add(step);
        step.setStageId(stagePostProduction);
        step = null;
    }

    /**
     * 
     * @param bytes
     * @return 
     */
    public StreamedContent convertFichier(byte[] bytes) {

        InputStream is = new ByteArrayInputStream(bytes);
        System.out.println("size file : " + bytes.length);
        StreamedContent image = new DefaultStreamedContent(is);
        System.out.println("dans le convertisseur : " + image.getContentType());
        return image;

    }
    
    //------------- get and set -------------------------------------------------
    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public Stage getStagePreProduction() {
        return stagePreProduction;
    }

    public void setStagePreProduction(Stage stagePreProduction) {
        this.stagePreProduction = stagePreProduction;
    }

    public Stage getStageProduction() {
        return stageProduction;
    }

    public void setStageProduction(Stage stageProduction) {
        this.stageProduction = stageProduction;
    }

    public Stage getStagePostProduction() {
        return stagePostProduction;
    }

    public void setStagePostProduction(Stage stagePostProduction) {
        this.stagePostProduction = stagePostProduction;
    }

    public List<Step> getStepsPreProduction() {
        return stepsPreProduction;
    }

    public void setStepsPreProduction(List<Step> stepsPreProduction) {
        this.stepsPreProduction = stepsPreProduction;
    }

    public List<Step> getStepsProduction() {
        return stepsProduction;
    }

    public void setStepsProduction(List<Step> stepsProduction) {
        this.stepsProduction = stepsProduction;
    }

    public List<Step> getStepsPostProduction() {
        return stepsPostProduction;
    }

    public void setStepsPostProduction(List<Step> stepsPostProduction) {
        this.stepsPostProduction = stepsPostProduction;
    }

    public Step getStep() {
        if (step == null) {
            step = new Step();
        }
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

}
