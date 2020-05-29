package Group.service;

import Group.*;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.Optional;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author angelrg
 */
@Stateless
@LocalBean

public class GroupUserService{

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GroupUser createGroupUser(GroupUser groupUser) {
        entityManager.persist(groupUser);
        return groupUser;
    }

    /**
     * Change the group of an user
     *
     * @param groupUser
     * @param group
     * @return
     */
    public GroupUser updateUserGroup(GroupUser groupUser, GroupIndustrial group) {
        groupUser.setGroupId(group);
        entityManager.merge(groupUser);
        return groupUser;
    }

    /**
     * This method remove a user from a group
     *
     * @param groupUser
     * @return
     */
    public Optional<GroupUser> removeUserFromGroup(GroupUser groupUser) {
        try {
           
            entityManager.remove(entityManager.merge(groupUser));
            return Optional.ofNullable(groupUser);
        } catch (TransactionRequiredException e) {
            return Optional.empty();
        }
    }
}
