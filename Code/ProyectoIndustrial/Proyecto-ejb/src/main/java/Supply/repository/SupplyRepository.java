
package Supply.repository;

import Supply.Supply;
import java.time.LocalDate;
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
public class SupplyRepository {

    private EntityManager entityManager;

    public SupplyRepository() {
    }
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Supply> getSupply(Integer codeSupply, String nameSupply, String internalCode, AvailabilityFilter availabilitySupply, ExpirationDateFilter expirationDateSupply){       
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Supply> criteriaQuery = criteriaBuilder.createQuery(Supply.class);
        Root<Supply> supply = criteriaQuery.from(Supply.class);
        ArrayList<Predicate> predicates = new ArrayList<>();
        
        if (codeSupply != null){
            predicates.add(criteriaBuilder.like(supply.get("code"), "%" + codeSupply + "%"));
        }
        
        if (nameSupply != null){
            predicates.add(criteriaBuilder.like(supply.get("name"), "%" + nameSupply + "%"));
        }
        
        if (internalCode != null){
            predicates.add(criteriaBuilder.like(supply.get("internalCode"), "%" + internalCode + "%"));
        }
        
        if (availabilitySupply != null){
            if (availabilitySupply.equals(AvailabilityFilter.AVAILABLE)){
                predicates.add(criteriaBuilder.equal(supply.get("availability"), "1"));
            } else if (availabilitySupply.equals(AvailabilityFilter.NOT_AVAILABLE)){
                predicates.add(criteriaBuilder.equal(supply.get("availability"), "0"));
            }
        }
        
        if (expirationDateSupply != null){
            if (expirationDateSupply.equals(expirationDateSupply.EXPIRED)){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(supply.get("expiration_date"), LocalDate.now()));
            } else if (expirationDateSupply.equals(expirationDateSupply.NOT_EXPIRED)){
                predicates.add(criteriaBuilder.greaterThan(supply.get("expiration_date"), LocalDate.now()));
            }    
        }
        
        criteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<Supply> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}   