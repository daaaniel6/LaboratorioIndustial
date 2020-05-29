package Group.repository;

import Group.GroupIndustrial;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author angelrg
 */
@Stateless
@LocalBean
public class GroupRepository {
    
    public static final String FIND_BY_ID = "SELECT g FROM Group g WHERE g.idGroup = :id";
    public static final String GET_ALL = "SELECT g FROM GroupIndustrial g";
    
    private EntityManager entityManager;
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<GroupIndustrial> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(GroupIndustrial.class, id));
        
    }
    
    public List<GroupIndustrial> getAll() {
        TypedQuery<GroupIndustrial> typedQuery = entityManager.createQuery(GET_ALL, GroupIndustrial.class);
        return typedQuery.getResultList();
    }
    
}
