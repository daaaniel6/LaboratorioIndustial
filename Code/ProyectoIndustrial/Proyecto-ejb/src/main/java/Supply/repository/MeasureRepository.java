package Supply.repository;

import Supply.Measure;
import Supply.exception.MandatoryAttributeSupplyException;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.criteria.Predicate;

@Stateless
@LocalBean
public class MeasureRepository {

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MeasureRepository() {
    }

    public List<Measure> getMeasure(Measure measure) throws MandatoryAttributeSupplyException {
        if (measure == null) {
            throw new MandatoryAttributeSupplyException("Measure is null");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Measure> criteriaQuery = criteriaBuilder.createQuery(Measure.class);
        Root<Measure> Measure = criteriaQuery.from(Measure.class);
        List<Predicate> predicates = new ArrayList<>();
        if (measure.getIdMeasure() != null) {
            predicates.add(criteriaBuilder.equal(Measure.get("idMeasure"), measure.getIdMeasure()));
        }
        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Measure> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Optional<Measure> getMeasureById(Integer id) {
        return Optional.ofNullable(entityManager.find(Measure.class, id));
    }

    public List<Measure> getAllMeasures() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Measure> criteriaQuery = criteriaBuilder.createQuery(Measure.class);
        Root<Measure> measure = criteriaQuery.from(Measure.class);
        criteriaQuery.select(measure);
        TypedQuery<Measure> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
