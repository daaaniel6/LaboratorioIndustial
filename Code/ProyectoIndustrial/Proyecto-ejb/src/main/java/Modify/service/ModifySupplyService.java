package Modify.service;

import Modify.ModifySupply;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;

/**
 *
 * @author angelrg
 */
@Stateless
@LocalBean
public class ModifySupplyService{

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    
//    @Inject
//    private SecurityContext securityContext;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Create a new modification history
     *
     * @param modifySupply
     * @return 
     */
    public ModifySupply createModifySupply(ModifySupply modifySupply) {

//        securityContext.getCallerPrincipal().getName();
        entityManager.persist(modifySupply);
        
        return modifySupply;
    }
}
