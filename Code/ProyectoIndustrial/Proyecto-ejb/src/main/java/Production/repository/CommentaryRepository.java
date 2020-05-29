package Production.repository;

import Production.Commentary;
import java.util.List;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class CommentaryRepository {
    
    public static final String FIND_BY_ID = "SELECT g FROM COMMENTARY g WHERE g.id_commentary = :id";
    public static final String FIND_BY_STAGE_ID = "SELECT g FROM COMMENTARY g WHERE g.stage_id = :stageId";
    public static final String GET_ALL = "SELECT g FROM product g";
    
    
    private EntityManager entityManager;
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Commentary> getCommentaryById(Integer id){
        TypedQuery<Commentary> typedQuery = entityManager.createQuery(FIND_BY_ID,Commentary.class).setParameter("id", id);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    public Optional<Commentary> getCommentaryByStage(Integer stageId){
        TypedQuery<Commentary> typedQuery = entityManager.createQuery(FIND_BY_STAGE_ID,Commentary.class).setParameter("stageId", stageId);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    public Optional<List<Commentary>> getAll(){
        TypedQuery<Commentary> typedQuery = entityManager.createQuery(GET_ALL,Commentary.class);
        try {
            return Optional.ofNullable(typedQuery.getResultList());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    
    
    
    
    
}
