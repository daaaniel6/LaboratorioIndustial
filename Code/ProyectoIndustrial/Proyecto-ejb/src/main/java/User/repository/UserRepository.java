package User.repository;

import User.User;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class UserRepository {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> getUserByCarnet(Integer carnet) {
        TypedQuery<User> typeQuerry = entityManager.createQuery("SELECT u FROM user u WHERE u.carnet = :carnet", User.class);
        typeQuerry.setParameter("carnet", carnet);
        try {
            return Optional.ofNullable(typeQuerry.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<User> getUserByID(Integer carnet) {
        return Optional.ofNullable(entityManager.find(User.class, carnet));
    }

    public List<User> getUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("User is null");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userR = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        if (user.getCarnet() != null) {
            predicates.add(criteriaBuilder.equal(userR.get("carnet"), user.getCarnet()));
        }
        if (user.getName() != null) {
            predicates.add(criteriaBuilder.like(userR.get("name"), "%" + user.getName() + "%"));
        }
        if (user.getState() != null) {
            predicates.add(criteriaBuilder.equal(userR.get("state"), user.getState()));
        }
        if (user.getRolUser() != null) {
            predicates.add(criteriaBuilder.equal(userR.get("rolUser"), user.getRolUser()));
        }
        if (user.getCareer() != null) {
            predicates.add(criteriaBuilder.equal(userR.get("career"), user.getCareer().getIdCareer()));
        }
        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
