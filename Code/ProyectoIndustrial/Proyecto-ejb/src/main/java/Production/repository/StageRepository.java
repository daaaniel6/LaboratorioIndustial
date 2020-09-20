package Production.repository;

import Production.Stage;
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
public class StageRepository {

    private EntityManager entityManager;
    public static final String QUERY_FIND_BY_ID = "SELECT s FROM Stage s WHERE s.idStage = ?";
    public static final String QUERY_ALL_STAGES = "SELECT s FROM Stage s";
    public static final String QUERY_LIKE_STAGES = "SELECT s FROM Stage s WHERE s.name  LIKE  ?";

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Busca una Stage por el id si no encuentra nada devulve un Optional vacio
     *
     * @param idStage
     * @return Production
     */
    public Optional<Stage> findByIdStage(int idStage) {

        TypedQuery<Stage> typedQuery = entityManager.createQuery(QUERY_FIND_BY_ID, Stage.class)
                .setParameter(1, idStage);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public List<Stage> AllStages() {
        TypedQuery<Stage> typedQuery = entityManager.createQuery(QUERY_ALL_STAGES, Stage.class);
        return typedQuery.getResultList();

    }

    public List<Stage> findStageLikeName(String nameStage) {

        TypedQuery<Stage> typedQuery = entityManager.createQuery(QUERY_LIKE_STAGES, Stage.class)
                .setParameter(1, nameStage);

        return typedQuery.getResultList();

    }

    /**
     * Find Stages associated to a Production
     *
     * 
     *
     * @param id
     * @return
     */
    public List<Stage> getStageByProductionId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
