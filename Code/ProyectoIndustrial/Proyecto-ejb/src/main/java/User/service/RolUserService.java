package User.service;

import User.RolUser;
import User.exception.UserException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import javax.ejb.EJB;

@Stateless
@LocalBean
public class RolUserService {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    @EJB
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public RolUser createRolUser(RolUser rolUser) throws UserException {
        if (rolUser == null) {
            throw new UserException("rolUser is null");
        }
        entityManager.persist(rolUser);
        return rolUser;
    }

    public RolUser updateRolUser(RolUser rolUser) throws UserException {
        if (rolUser == null) {
            throw new UserException("rolUser is null");
        }
        RolUser updateRolUser = entityManager.find(RolUser.class, rolUser.getIdRolUser());
        updateRolUser.setName(rolUser.getName());
        return rolUser;
    }

}
