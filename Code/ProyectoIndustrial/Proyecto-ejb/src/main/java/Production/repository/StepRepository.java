package Production.repository;

import Production.Step;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author daniel
 */
@Stateless
@LocalBean
public class StepRepository {

    private EntityManager entityManager;
    public static final String QUERY_FIND_BY_ID = "SELECT s FROM Step s WHERE s.idStep = ?";
    public static final String QUERY_ALL_STEPS = "SELECT s FROM Step s";
    public static final String QUERY_LIKE_STEPS = "SELECT s FROM Step s WHERE s.name LIKE  ?";

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Busca un Step por el id si no encuentra nada devulve un Optional vacio
     *
     * @param idStep
     * @return Step
     */
    public Optional<Step> findByIdStep(int idStep) {

        TypedQuery<Step> typedQuery = entityManager.createQuery(QUERY_FIND_BY_ID, Step.class)
                .setParameter(1, idStep);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public List<Step> AllSteps() {

        TypedQuery<Step> typedQuery = entityManager.createQuery(QUERY_ALL_STEPS, Step.class);
        return typedQuery.getResultList();

    }

    public List<Step> findStepLikeName(String nameStep) {

        TypedQuery<Step> typedQuery = entityManager.createQuery(QUERY_LIKE_STEPS, Step.class)
                .setParameter(1, nameStep);

        return typedQuery.getResultList();
    }

    /**
     * Find Steps associated to a Stage
     *
     * 
     *
     * @param id
     * @return
     */
    public List<Step> getStepByStageId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
