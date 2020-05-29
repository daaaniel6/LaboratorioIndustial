package User.repository;

import User.Career;
import User.RolUser;
import User.exception.UserException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import static config.Constants.PERSISTENCE_UNIT_NAME;

@Stateless
@LocalBean
public class RolUserRepository {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public static final String QUERY_FIND_BY_ID = "SELECT r FROM RolUser r WHERE r.idRol = :idRolUserParameter";

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<RolUser> getRolUser(RolUser rolUser) throws UserException {
        if (rolUser == null) {
            throw new UserException("rolUser is null");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RolUser> criteriaQuery = criteriaBuilder.createQuery(RolUser.class);
        Root<RolUser> RolUser = criteriaQuery.from(RolUser.class);
        List<Predicate> predicates = new ArrayList<>();
        if (rolUser.getIdRolUser() != null) {
            predicates.add(criteriaBuilder.equal(RolUser.get("id_rol"), rolUser.getIdRolUser()));
        }
        if (rolUser.getName() != null) {
            predicates.add(criteriaBuilder.like(RolUser.get("name"), "%" + rolUser.getName() + "%"));
        }
        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<RolUser> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<RolUser> getAllRolUser() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RolUser> criteriaQuery = criteriaBuilder.createQuery(RolUser.class);
        Root<RolUser> rootEntry = criteriaQuery.from(RolUser.class);
        CriteriaQuery<RolUser> all = criteriaQuery.select(rootEntry);
        TypedQuery<RolUser> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Optional<RolUser> findRolUserById(int idRolUser) throws UserException {
        if (idRolUser < 0) {
            throw new UserException("rolUser is null");
        }
        return Optional.ofNullable(entityManager.find(RolUser.class, idRolUser));
    }

    public Optional<RolUser> findByIdRolUser(int idRolUser) {

        TypedQuery<RolUser> typedQuery = entityManager.createQuery(QUERY_FIND_BY_ID, RolUser.class)
                .setParameter("idRolUserParameter", idRolUser);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
