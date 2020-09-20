package Modify.repository;

import Modify.ModifySupply;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author angelrg
 */

@Stateless
@LocalBean
public class ModifySupplyRepository{
    
    public static final String FIND_MODIFICATION_BY_USER = "SELECT ms FROM ModifySupply ms WHERE ms.user.carnet = :idUser";
    public static final String FIND_MODIFICATION_BY_SUPPLY = "SELECT ms FROM ModifySupply ms WHERE ms.supply.code = :idSupply";
    public static final String GET_ALL_SUPPLY = "SELECT ms FROM ModifySupply ms";
    public static final String NAME_SUPPLY_ID = "idUser";
    public static final String NAME_USER_ID = "idSupply";
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<ModifySupply> getById(Integer id){
        return Optional.ofNullable(entityManager.find(ModifySupply.class, id));
    }
    
    public List<ModifySupply> getModificationByUser(Integer idUser){
        Query query = entityManager.createQuery(FIND_MODIFICATION_BY_USER);
        query.setParameter(NAME_USER_ID, idUser);
        return query.getResultList();
    }
    
    public List<ModifySupply> getModificationBySupply(Integer idSupply){
        Query query = entityManager.createQuery(FIND_MODIFICATION_BY_SUPPLY);
        query.setParameter(NAME_SUPPLY_ID, idSupply);
        return query.getResultList();
    }
    
    public List<ModifySupply> getAll(){        
        Query query = entityManager.createQuery(GET_ALL_SUPPLY);
        return query.getResultList();
    }
    
}
