/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Production.Production;
import Production.exceptions.MandatoryAttributeProductionException;
import Production.facade.ProductionFacadeLocal;
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

/**
 * Daniel Gonzalez
 *
 * @author daniel
 * productionQualificationView
 * productionQualificationView.java
 * 3/05/2020
 * 23:24:45
 *
 */
@Named
@ViewScoped
public class productionQualificationView implements Serializable {

    private static final String ERROR_QUALIFY_NULL = "No ha ingrsado ninguna calificacion";
    private static final String ERROR_ = "Error";
    private static final String SUBMIT_RATING = "lA NOTA SE AGREGO A LA PRODUCCION";

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    private List<Production> productions;
    private List<Production> productionsFilter;
    private Production productionSelect;
    //private double totalProduction;

    @PostConstruct
    public void init() {
        productions = new ArrayList<>();
        productionsFilter = new ArrayList<>();
        productionsFilter = productionFacadeLocal.AllProductions();
//        totalProduction = 0;

        for (int i = 0; i < productionsFilter.size(); i++) {
            if(productionsFilter.get(i).getEndDate() != null && productionsFilter.get(i).getState() == true){
                productions.add(productionsFilter.get(i));
            }
        }
    }

    public void selectProduction(Production production) {
        this.productionSelect = production;
        //totalProduction = productionFacadeLocal.finalCost(production);
    }

    /*
     *
     * Solo se referiere al total del costo por los insumos, sin incluir los
     * gastos extra
     */
    public double getTotalProduction() {
        return productionFacadeLocal.finalCost(productionSelect);
    }

    /**
     *
     * @return La suma de todos los gastos extra de una poduccion.
     */
    public double getTotalExtraCost() {
        return productionFacadeLocal.totalExtraCost(productionSelect);
    }

    public void qualify(Production production) {
        if (production.getQualification() != null ) {
            try {
                production.setState(false);
                productionFacadeLocal.editProduction(production);
                MessageUtils.addSuccessMessage(SUBMIT_RATING);
            } catch (MandatoryAttributeProductionException ex) {
                MessageUtils.addErrorMessage(ERROR_);
//Logger.getLogger(productionQualificationView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MessageUtils.addErrorMessage(ERROR_QUALIFY_NULL);
        }
    }

    //-------------------get and set ------------------------------------
    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public Production getProductionSelect() {
        return productionSelect;
    }

    public void setProductionSelect(Production productionSelect) {
        this.productionSelect = productionSelect;
    }

}
