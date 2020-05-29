package Production.repository;

import Production.Product;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author charly
 * @refactor angelrg
 */
@Stateless
@LocalBean
public class ProductRepository {

    public static final String GET_ALL = "SELECT g FROM Product g";

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public List<Product> findProduct(Integer id, String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            predicates.add(criteriaBuilder.equal(product.get("idProduct"), id));
        }
        if (name != null) {
            predicates.add(criteriaBuilder.like(product.get("name"), "%" + name + "%"));
        }

        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Optional<List<Product>> getAll() {
        TypedQuery<Product> typedQuery = entityManager.createQuery(GET_ALL, Product.class);
        try {
            return Optional.ofNullable(typedQuery.getResultList());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
