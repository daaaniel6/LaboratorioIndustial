package Group.service;

import Group.GroupIndustrial;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author angelrg
 */
@Stateless
@LocalBean
public class GroupService{

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GroupIndustrial createGroup(GroupIndustrial group) {
        entityManager.persist(group);
        return group;
    }

    /**
     * To update just information or section, send the same text, if the text is
     * empty can't update the fill
     *
     * @param group
     * @param information
     * @param section
     * @return
     */
    public GroupIndustrial updateGroup(GroupIndustrial group, String information, String section) {

        if ((information != null) && (!information.isEmpty())) {
            group.setInformation(information);
        }
        if ((section != null) && (!section.isEmpty())) {
            group.setSection(section);
        }

        return entityManager.merge(group);
    }
}
