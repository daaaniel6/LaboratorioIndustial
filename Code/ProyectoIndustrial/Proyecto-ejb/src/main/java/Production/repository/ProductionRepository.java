package Production.repository;

import Production.Product;
import Production.Production;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import static config.Constants.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author daniel
 */
@Stateless
@LocalBean
public class ProductionRepository {

    private EntityManager entityManager;
    public static final String QUERY_FIND_BY_ID = "SELECT p FROM Production p WHERE p.idProduction  = ?";
    public static final String QUERY_ALL_PRODUCTIONS = "SELECT p FROM Production p";
    public static final String QUERY_LIKE_PRODUCTIONS = "SELECT p FROM Production p WHERE p.name  LIKE ?";

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * fecha de creacion de la produccion reacion entre comentario y paso
     */
    /**
     * Busca una produccion por el id si no encruenta nada devulve un Optional
     * vacio
     *
     * @param idProduction
     * @return Production
     */
    public Optional<Production> findByIdProduction(int idProduction) {
        return Optional.of(entityManager.find(Production.class, idProduction));
    }
//    public Optional<Production> findByIdProduction(int idProduction) {
//
//        TypedQuery<Production> typedQuery = entityManager.createQuery(QUERY_FIND_BY_ID, Production.class)
//                .setParameter(1, idProduction);
//
//        try {
//            return Optional.of(typedQuery.getSingleResult());
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//
//    }

    public List<Production> AllProductions() {

        TypedQuery<Production> typedQuery = entityManager.createQuery(QUERY_ALL_PRODUCTIONS, Production.class);
        return typedQuery.getResultList();

    }

    public List<Production> findProductionLikeName(String nameProduction) {

        TypedQuery<Production> typedQuery = entityManager.createQuery(QUERY_LIKE_PRODUCTIONS, Production.class)
                .setParameter(1, nameProduction);

        return typedQuery.getResultList();

    }

    /**
     * To get all results just set all with null
     *
     * startDate and endDate use to filter the startDate attribute of Production
     *
     * @param idProduction
     * @param name
     * @param startDate
     * @param endDate
     * @param editable
     * @return
     */
    public List<Production> findProduction(Integer idProduction, String name, LocalDate startDate, LocalDate endDate, boolean editable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Production> criteriaQuery = criteriaBuilder.createQuery(Production.class);
        Root<Production> production = criteriaQuery.from(Production.class);
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (idProduction != null) {
            predicates.add(criteriaBuilder.equal(production.get("idProduction"), idProduction));
        }

        if (name != null) {
            predicates.add(criteriaBuilder.like(production.get("name"), "%" + name + "%"));
        }

        if (startDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(production.get("startDate"), startDate));
        }

        if (endDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(production.get("startDate"), endDate));
        }

        if (editable) {
            predicates.add(criteriaBuilder.isNull(production.get("endDate")));
            predicates.add(criteriaBuilder.isFalse(production.get("state")));
        }

        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Production> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Return the best one production base on the products list, selected by the
     * best score.
     *
     * @param products
     * @return
     */
    public List<Production> getBestProductions(List<Product> products) {
        List<Production> productions = new LinkedList<>();
        for (Product product : products) {
            List<Production> result = getBestProductionByProductDesc(product);
            if (!result.isEmpty()) {
                productions.add(result.get(0));
            }
        }
        return productions;
    }

    private List<Production> getBestProductionByProductDesc(Product product) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Production> criteriaQuery = criteriaBuilder.createQuery(Production.class);
        Root<Production> production = criteriaQuery.from(Production.class);

        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(production.get("designId").get("productIdProduct")
                .get("idProduct"), product.getIdProduct()));

        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new))
                .orderBy(criteriaBuilder.desc(production.get("qualification")));
        TypedQuery<Production> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public double initCost(Production production) {
        double total = 0;
        if (production != null) {
            for (int i = 0; i < production.getDesignId().getNecessarySupplyList().size(); i++) {
                total = total
                        + (production.getQuantity()
                        * production.getDesignId().getNecessarySupplyList().get(i).getQuantity()
                        * production.getDesignId().getNecessarySupplyList().get(i).getSupplyCode().getCost());

            }
        }
        return total;
    }

    public double finalCost(Production production) {
        double total = 0;
        if (production != null) {

            for (int i = 0; i < production.getPostDesign().getNecessarySupplyList().size(); i++) {
                total = total
                        + (production.getPostDesign().getNecessarySupplyList().get(i).getQuantity()
                        * production.getPostDesign().getNecessarySupplyList().get(i).getSupplyCode().getCost());

            }
        }
        return total;
    }

    public double totalExtraCost(Production production) {

        double total = 0;
        if (production != null) {
            for (int i = 0; i < production.getExtraCostList().size(); i++) {
                total = total + production.getExtraCostList().get(i).getCost();

            }
        }
        return total;
    }

}
