package User.repository;

import User.Career;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import static config.Constants.PERSISTENCE_UNIT_NAME;

@Stateless
@LocalBean
public class CareerRepository {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Career> getCareer(Career career) throws UserException {
        if (career == null) {
            throw new UserException("career is null");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Career> criteriaQuery = criteriaBuilder.createQuery(Career.class);
        Root<Career> Career = criteriaQuery.from(Career.class);
        List<Predicate> predicates = new ArrayList<>();
        if (career.getIdCareer() != null) {
            predicates.add(criteriaBuilder.equal(Career.get("idCareer"), career.getIdCareer()));
        }
        if (career.getName()!= null) {
            predicates.add(criteriaBuilder.like(Career.get("name"), "%" + career.getName()+ "%"));
        }
        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Career> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Career> getAllCareer() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Career> criteriaQuery = criteriaBuilder.createQuery(Career.class);
        Root<Career> rootEntry = criteriaQuery.from(Career.class);
        CriteriaQuery<Career> all = criteriaQuery.select(rootEntry); 
        TypedQuery<Career> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
