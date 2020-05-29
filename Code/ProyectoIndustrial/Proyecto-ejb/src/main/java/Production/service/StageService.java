
package Production.service;


import Production.Stage;
import Production.exceptions.MandatoryAttributeProductionException;
import static config.Constants.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author daniel
 */
@Stateless
@LocalBean
public class StageService {
     private EntityManager entityManager;
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    } 
    
     public Stage edit(Stage oldStage) throws MandatoryAttributeProductionException {
         
         //se setea antes o despues?
        if (oldStage.getName() == null) {
            throw new MandatoryAttributeProductionException("Nombre nulo");
        }
        if (oldStage.getDescription()== null) {
            throw new MandatoryAttributeProductionException("Descripcion nula");
        }
        

        entityManager.merge(oldStage);
        
        return oldStage;

    }
    
}
