package Production.repository;

import Production.NecessarySupply;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;

@Stateless
@LocalBean
public class NecessarySupplyRepository {

    public static final String FIND_BY_ID = "SELECT g FROM NECESSARY_SUPPLY g WHERE g.id_necessary_supply = :id";
    public static final String FIND_BY_STEP = "SELECT g FROM NECESSARY_SUPPLY g WHERE g.step_id = :step";
    public static final String FIND_BY_SUPPLY = "SELECT g FROM NECESSARY_SUPPLY g WHERE g.supply_code = :supply";
    public static final String GET_ALL = "SELECT g FROM NECESSARY_SUPPLY g";

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<NecessarySupply> getNecessarySupplyById(Integer id) {
        TypedQuery<NecessarySupply> typedQuery = entityManager.createQuery(FIND_BY_ID, NecessarySupply.class).setParameter("id", id);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<NecessarySupply> getNecessarySupplyByStep(Integer stepId) {
        TypedQuery<NecessarySupply> typedQuery = entityManager.createQuery(FIND_BY_STEP, NecessarySupply.class).setParameter("step", stepId);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<NecessarySupply> getNecessarySupplyBySupply(Integer supplyCode) {
        TypedQuery<NecessarySupply> typedQuery = entityManager.createQuery(FIND_BY_SUPPLY, NecessarySupply.class).setParameter("supply", supplyCode);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<List<NecessarySupply>> getAll() {
        TypedQuery<NecessarySupply> typedQuery = entityManager.createQuery(GET_ALL, NecessarySupply.class);
        try {
            return Optional.ofNullable(typedQuery.getResultList());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Find NecessarySupplyies associated to a Step
     *
     * @param stepId
     * @return
     */
    public List<NecessarySupply> getNecessarySuppliesByStep(Integer stepId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
