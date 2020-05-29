package User.service;

import User.Career;
import User.exception.UserException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static config.Constants.PERSISTENCE_UNIT_NAME;

@Stateless
@LocalBean
public class CareerService {

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Career createCareer(Career career) throws UserException {
        if (career == null) {
            throw new UserException("career is null");
        }
        entityManager.persist(career);
        return career;
    }

    public Career updateCareer(Career career) throws UserException {
        if (career == null) {
            throw new UserException("career is null");
        }
        Career updateCareer = entityManager.find(Career.class, career.getIdCareer());
        updateCareer.setName(career.getName());
        return updateCareer;
    }
}
