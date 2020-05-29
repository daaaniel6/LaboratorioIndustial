package Production.service;

import Production.Commentary;
import Production.Stage;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CommentaryService {
    
    @PersistenceContext(name=PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Commentary createCommentary(Commentary commentary) throws MandatoryAttributeProductionException{
        if(commentary==null){
            throw new MandatoryAttributeProductionException("Commentary is null");
        }
        entityManager.persist(commentary);
        return commentary;
    }
    public Commentary updateCommentary(Commentary commentary, String text, Step stageId) throws MandatoryAttributeProductionException{
        
        if ((text != null) && (!text.isEmpty())) {
            commentary.setCommentary(text);
        }
        if ((stageId != null)) {
            commentary.setIdStep(stageId);
        }

        return entityManager.merge(commentary);
        
    }
    
    
}
