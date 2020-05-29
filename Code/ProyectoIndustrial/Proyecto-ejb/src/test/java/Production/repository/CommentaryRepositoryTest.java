package Production.repository;

import Production.Commentary;
//import Production.repository.*;
import static Production.repository.CommentaryRepository.FIND_BY_ID;
import static Production.repository.CommentaryRepository.FIND_BY_STAGE_ID;
import static Production.repository.CommentaryRepository.GET_ALL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.persistence.TypedQuery;

public class CommentaryRepositoryTest {
    
    
    @Test
    public void getCommentaryByIdResultWithResult() throws Exception {
        // Arrange
        int id = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        Commentary commentary = new Commentary();
        Mockito.when(
                entityManager.createQuery(FIND_BY_ID,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("id", id)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenReturn(commentary);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<Commentary> result = commentaryRepository.getCommentaryById(id);

        // Assert
        Assert.assertEquals(result.get(), commentary);
    }
    
    @Test
    public void getCommentaryByIdResultWithNoResult() throws Exception {
        // Arrange
        int id = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        Commentary commentary = new Commentary();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(FIND_BY_ID,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("id", id)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenThrow(exception);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<Commentary> result = commentaryRepository.getCommentaryById(id);

        // Assert
        
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
        
        
    }
    
    @Test
    public void getCommentaryByStageWithResult() throws Exception {
        // Arrange
        int id_stage = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        Commentary commentary = new Commentary();
        Mockito.when(
                entityManager.createQuery(FIND_BY_STAGE_ID,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("stageId", id_stage)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenReturn(commentary);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<Commentary> result = commentaryRepository.getCommentaryByStage(id_stage);

        // Assert
        Assert.assertEquals(result.get(), commentary);
    }
    
    @Test
    public void getCommentaryByStageWithNoResult() throws Exception {
        // Arrange
        int id_stage = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        Commentary commentary = new Commentary();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(FIND_BY_STAGE_ID,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.setParameter("stageId", id_stage)).thenReturn(type);
        Mockito.when(type.getSingleResult()).thenThrow(exception);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<Commentary> result = commentaryRepository.getCommentaryByStage(id_stage);

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
    
    @Test
    public void getAllWithResult() throws Exception {
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        List<Commentary> commentary = new LinkedList<Commentary>();
        Mockito.when(
                entityManager.createQuery(GET_ALL,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.getResultList()).thenReturn(commentary);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<List<Commentary>> result = commentaryRepository.getAll();

        // Assert
        Assert.assertEquals(result.get(), commentary);
    }
    
    
    @Test
    public void getAllWithNoResult() throws Exception {
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery type = Mockito.mock(TypedQuery.class);
        List<Commentary> commentary = new LinkedList<Commentary>();
        NoResultException exception = new NoResultException();
        Mockito.when(
                entityManager.createQuery(GET_ALL,Commentary.class)
        ).thenReturn(type);
        Mockito.when(type.getResultList()).thenThrow(exception);
        CommentaryRepository commentaryRepository = new CommentaryRepository();
        commentaryRepository.setEntityManager(entityManager);

        // Act        
        Optional<List<Commentary>> result = commentaryRepository.getAll();

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected an optional empty");
    }
    
    
}
