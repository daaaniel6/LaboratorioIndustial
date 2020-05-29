/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production.facade;

import Design.Design;
import Design.DesignData;
import Production.ExtraCost;
import Production.Product;
import Production.NecessarySupply;
import Production.Production;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import User.exception.UserException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author daniel
 */
@Local
public interface ProductionFacadeLocal {

    public Step edit(Step oldStep) throws UserException;

    public void remove(Step step) throws UserException;

    public Step createStep(Step step) throws UserException;

    public void createProduction(Production production) throws MandatoryAttributeProductionException;

    public void editProduction(Production production) throws MandatoryAttributeProductionException;

    public List<Product> getProduct();

    public List<Production> AllProductions();

    public Optional<Production> getProductionById(Integer id);

    public void createDesign(Design design, DesignData designData, List<NecessarySupply> necessarySupplys);

    /**
     * Realiza una busqueda basado unicamente en el codigo del diseño
     *
     * @param idDesign
     * @return
     */
    public Optional<Design> findDesignByID(Integer idDesign);

    public List<Design> AllDesigns();

    public Optional<Product> getProductById(Integer id);

    public Design editDesign(Design design) throws MandatoryAttributeProductionException;

    /**
     * To get all results just set all with null
     *
     * startDate and endDate use to filter the startDate attribute of Production
     *
     * editable, return just the productions available to edit
     *
     * @param idProduction
     * @param name
     * @param startDate
     * @param endDate
     * @param editable
     * @return
     */
    public List<Production> findProduction(Integer idProduction, String name, LocalDate startDate, LocalDate endDate, boolean editable);

    /**
     * Can update Name, Quantity, the Design and Group
     *
     * @param production
     * @return
     * @throws UserException
     */
    public Production updateProduction(Production production) throws UserException;

    public void updateCommentayOfSteps(Production production);

    /**
     * Update and add al steps added to the production
     *
     * @param production
     */
//    public void updateSteps(Production production);
    public void updateExtraCost(List<ExtraCost> listExtraCost, Production production) throws MandatoryAttributeProductionException;

    public void addPostDedign(Design postDesign, Production production) throws MandatoryAttributeProductionException;

    public double initCost(Production production);

    public double finalCost(Production production);

    public double totalExtraCost(Production production);

}
