package Production.service;

import Design.Design;
import Production.ExtraCost;
import Production.Production;
import Production.Stage;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import User.exception.UserException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static config.Constants.*;
import java.util.List;

/**
 *
 * @author daniel
 */
@Stateless
@LocalBean
public class ProductionService {

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @param production
     *
     * @return
     *
     * @throws MandatoryAttributeProductionException
     */
    public Production create(Production production) throws MandatoryAttributeProductionException {

        if (production.getName() == null) {
            throw new MandatoryAttributeProductionException("Nombre nulo");
        }

        if (production.getStartDate() == null) {
            throw new MandatoryAttributeProductionException("Fecha de creacion nula");
        }
        for (int i = 0; i < production.getStageList().size(); i++) {
            entityManager.persist(production.getStageList().get(i));
        }

        entityManager.persist(production);
        return production;
    }

    /**
     *
     * @param production
     */
    public void updateCommentayOfSteps(Production production) {
        for (int i = 0; i < production.getStageList().size(); i++) {
            for (int j = 0; j < production.getStageList().get(i).getStepList().size(); j++) {
                entityManager.merge(production);
                entityManager.merge(production.getStageList().get(i).getStepList().get(j));
            }
        }
    }

    /**
     *
     * @param listExtraCost
     * @param production
     *
     * @throws MandatoryAttributeProductionException
     */
    public void updateExtraCost(List<ExtraCost> listExtraCost, Production production) throws MandatoryAttributeProductionException {
        if (production == null) {
            throw new MandatoryAttributeProductionException("Produccion no seleccionada");
        }
        production.setExtraCostList(listExtraCost);
        for (int i = 0; i < listExtraCost.size(); i++) {
            listExtraCost.get(i).setIdProduction(production);
        }

        entityManager.merge(production);

    }

    /**
     *
     * @param postDesign
     * @param production
     * @throws MandatoryAttributeProductionException
     */
    public void addPostDedign(Design postDesign, Production production) throws MandatoryAttributeProductionException {
        if (production == null) {
            throw new MandatoryAttributeProductionException("Produccion no seleccionada");
        }
        if (postDesign == null) {
            throw new MandatoryAttributeProductionException("Produccion no seleccionada");
        }

//        List<Production> lista = new ArrayList<Production>();
//        lista.add(production);
//                
//        postDesign.setProductionList1(lista);
        for (int i = 0; i < production.getExtraCostList().size(); i++) {
            production.getExtraCostList().get(i).setIdProduction(production);
        }

        production.setPostDesign(postDesign);
        entityManager.merge(production);

    }

    /**
     *
     * @param Production
     *
     * @return
     *
     * @throws MandatoryAttributeProductionException
     */
    public Production edit(Production Production) throws MandatoryAttributeProductionException {

        if (Production.getName() == null) {
            throw new MandatoryAttributeProductionException("Nombre nulo");
        }

        if (Production.getStartDate() == null) {
            throw new MandatoryAttributeProductionException("Fecha de creacion nula");
        }

        entityManager.merge(Production);

        return Production;
    }

    /**
     * Can update Name, Quantity, the Design, Group and Stage List
     *
     * @param production
     * @return
     * @throws UserException
     */
    public Production updateProduction(Production production) throws UserException {
        if (production == null) {
            throw new UserException("No se ha indicado la producción");
        }

        Production updateProduction = entityManager.find(Production.class, production.getIdProduction());

        if (updateProduction == null) {
            throw new UserException("No existe la Producción");
        }

        if (!production.getName().isEmpty()) {
            updateProduction.setName(production.getName());
        }

        if (production.getQuantity() > 0) {
            updateProduction.setQuantity(production.getQuantity());
        }

        if (production.getDesignId() != null) {
            updateProduction.setDesignId(production.getDesignId());
        }

        if (production.getGroupId() != null) {
            updateProduction.setGroupId(production.getGroupId());
        }

        return updateProduction;

    }

}
