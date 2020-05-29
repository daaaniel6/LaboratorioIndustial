package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Production.NecessarySupply;
import Production.Product;
import Production.Production;
import Production.Stage;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
import Supply.Supply;
import User.User;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
//@EJB
public class ProductionWizard implements Serializable {

    private boolean skip;
    
    private  List<Stage> stages = new ArrayList<>();
    
    private List<Product> products;
    private List<User> allStudents;

    private String unity = "1";

    private List<User> group;

    

    private Step step;

    private Production production;
    private Product productSelect;

    private Stage stagePreProduction;
    private Stage stageProduction;
    private Stage stagePostProduction;
    
    private List<Step> stepsPreProduction;
    private List<Step> stepsProduction;
    private List<Step> stepsPostProduction;
    
    
    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @EJB
    private UserFacadeLocal userFacadeLocal;

    @PostConstruct
    public void init() {
        products = productionFacadeLocal.getProduct();
        try {
            allStudents = userFacadeLocal.getUserEstudent();
        } catch (UserException ex) {
            Logger.getLogger(ProductionWizard.class.getName()).log(Level.SEVERE, null, ex);
        }

        stepsPreProduction = new ArrayList<>();
        stepsProduction = new ArrayList<>();
        stepsPostProduction = new ArrayList<>();
        
        stagePreProduction = new Stage();
        stageProduction = new Stage();
        stagePostProduction = new Stage();

        
        group = new ArrayList<>();
       
    }

    public void AddStudent(User user) {
        group.add(user);
        allStudents.remove(user);
    }

    public void CreateProduction() {
        try {
            // this.getProduction().setUnity(6);
            // production.setProductId(productSelect);
            //int aux;
            //production.setUnity(aux);
            //aux = Integer.valueOf(getUnity());
            
            productionFacadeLocal.createProduction(production);
        } catch (MandatoryAttributeProductionException ex) {
            Logger.getLogger(ProductionWizard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
       public List<Supply> SupplyByStep(Step step){
        List<Supply> list = new ArrayList<Supply>();
        return list;
    }
    
//    public void StepByStage(){
//        
//    }
    
    public void newStepPreProduction() {
        stepsPreProduction.add(step);
        
        List<NecessarySupply> list = new ArrayList<>();
        //step.setNecessarySupplyList(list);
        step.setStageId(stagePreProduction);
        
        step = null;
    }
    

    public void newStepProduction() {
        stepsProduction.add(step);
        step = null;
    }

    public void newStepPostProduction() {
        stepsPostProduction.add(step);
        step = null;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String getUnity() {
        if (unity == null) {
            unity = "1";
        }
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public Production getProduction() {
        if (production == null) {
            Product product = new Product(null, "nombreProduct", "desc");
            production = new Production();
            //production = new Production(null, "", true, 1 , LocalDate.now());
            production.setProductId(product);
        }
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Product getProductSelect() {
        if (productSelect == null) {
            productSelect = new Product();
        }

        return productSelect;
    }

    public void setProductSelect(Product productSelect) {
        this.productSelect = productSelect;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public List<User> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<User> allStudents) {
        this.allStudents = allStudents;
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

    public List<User> getGroup() {
        return group;
    }

    public void setGroup(List<User> group) {
        this.group = group;
    }

}
