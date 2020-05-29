package Production.service;

import Production.Commentary;
import Production.Stage;
import Production.Step;
import Production.exceptions.MandatoryAttributeProductionException;
import javax.persistence.EntityManager;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CommentaryServiceTest {
    
    
    @Test
    public void createCommentaryTest() throws MandatoryAttributeProductionException{
        // Arrange
        Commentary commentary = new Commentary();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        
        Mockito.doNothing().when(entityManager).persist(commentary);
                
        CommentaryService commentaryService = new CommentaryService();
        commentaryService.setEntityManager(entityManager);
        
        // Act
        Commentary result = commentaryService.createCommentary(commentary);
        
        // Assert
        Assert.assertEquals(result, commentary);
    }
    
    
    @Test
    public void updateCommentaryTextTest() throws MandatoryAttributeProductionException{
        // Arrange
        String text = "Text";
        Step step = null;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Commentary commentary = Mockito.mock(Commentary.class);
        
        Mockito.when(
                entityManager.merge(commentary)
        ).thenReturn(commentary);
        
        CommentaryService commentaryService = new CommentaryService();
        commentaryService.setEntityManager(entityManager);
        
        // Act
        Commentary result = commentaryService.updateCommentary(commentary, text, step);
        
        //Assert
        Assert.assertEquals(result, commentary);
        
    }
    
    
    
    
    @Test
    public void updateCommentaryStageIdTest() throws MandatoryAttributeProductionException{
        // Arrange
        String text = null;
        Step step = new Step();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Commentary commentary = Mockito.mock(Commentary.class);
        
        Mockito.when(
                entityManager.merge(commentary)
        ).thenReturn(commentary);
        
        CommentaryService commentaryService = new CommentaryService();
        commentaryService.setEntityManager(entityManager);
        
        // Act
        Commentary result = commentaryService.updateCommentary(commentary, text, step);
        
        //Assert
        Assert.assertEquals(result, commentary);
        
    }
    
    
    
    public void updateCommentaryStageIdAndTextTest() throws MandatoryAttributeProductionException{
        // Arrange
        String text = "Text";
        Step step = new Step();
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Commentary commentary = Mockito.mock(Commentary.class);
        
        Mockito.when(
                entityManager.merge(commentary)
        ).thenReturn(commentary);
        
        CommentaryService commentaryService = new CommentaryService();
        commentaryService.setEntityManager(entityManager);
        
        // Act
        Commentary result = commentaryService.updateCommentary(commentary, text, step);
        
        //Assert
        Assert.assertEquals(result, commentary);
        
    }
    
    
    
}
