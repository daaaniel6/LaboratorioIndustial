/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.repository;

import Group.GroupIndustrial;
import static Group.repository.GroupRepository.GET_ALL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author angelrg
 */
public class GroupRepositoryTest {

    @Test
    public void findByIdWithResult() {
        // Arrange
        int groupId = 1;
        GroupIndustrial group = new GroupIndustrial();
        
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.find(GroupIndustrial.class, groupId)
        ).thenReturn(group);

        GroupRepository groupRepository = new GroupRepository();
        groupRepository.setEntityManager(entityManager);

        // Act
        Optional<GroupIndustrial> result = groupRepository.findById(groupId);

        // Assert
        Assert.assertEquals(result.get(), group);
    }

    @Test
    public void findByIdWithNoResult() {
        // Arrange
        int groupId = 1;
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(entityManager.find(GroupIndustrial.class, groupId)
        ).thenReturn(null);

        GroupRepository groupRepository = new GroupRepository();
        groupRepository.setEntityManager(entityManager);

        // Act
        Optional<GroupIndustrial> result = groupRepository.findById(groupId);

        // Assert
        Assert.assertFalse(result.isPresent(), "Expected optional empty");
    }
    
    @Test
    public void getAllWithResult(){
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery<GroupIndustrial> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(GET_ALL, GroupIndustrial.class)
        ).thenReturn(typedQuery);
        List<GroupIndustrial> list = new ArrayList<GroupIndustrial>();
        GroupIndustrial group = new GroupIndustrial();
        list.add(group);
        Mockito.when(typedQuery.getResultList()).thenReturn(list);
        
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.setEntityManager(entityManager);
        
        // Act
        List<GroupIndustrial> result = groupRepository.getAll();
        
        // Assert
        Assert.assertEquals(result, list);
    }
    
    @Test
    public void getAllEmptyResult(){
        // Arrange
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        TypedQuery<GroupIndustrial> typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(GET_ALL, GroupIndustrial.class)
        ).thenReturn(typedQuery);
        List<GroupIndustrial> list = new ArrayList<GroupIndustrial>();
        Mockito.when(typedQuery.getResultList()).thenReturn(list);
        
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.setEntityManager(entityManager);
        
        // Act
        List<GroupIndustrial> result = groupRepository.getAll();
        
        // Assert
        Assert.assertEquals(result, list);
    }
}
