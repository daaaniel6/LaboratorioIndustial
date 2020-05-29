package Production.service;

import Production.Step;
import User.exception.UserException;
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
public class StepService {

    private final String ERROR_MGS = "Debe indicarse un paso";
    private final String ERROR_MGS_NAME = "Debe indicarse un nombre";
    private final String ERROR_MGS_DESC = "Debe indicarse una descripcion";
    private final String ERROR_MGS_STAGE = "Debe indicarse la etapa ";

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Step createStep(Step step) throws UserException {
        if (step == null) {
            throw new UserException(ERROR_MGS);
        }

        if (step.getName() == null) {
            throw new UserException(ERROR_MGS_NAME);
        }
        if (step.getDescription() == null) {
            throw new UserException(ERROR_MGS_DESC);
        }
        if (step.getStageId() == null) {
            throw new UserException(ERROR_MGS_STAGE);
        }

        entityManager.persist(step);
        return step;
    }

    public Step edit(Step oldStep) throws UserException {

        if (oldStep == null) {
            throw new UserException(ERROR_MGS);
        }
        Step step = entityManager.find(Step.class, oldStep.getIdStep());

        if (oldStep.getName() != null) {
            step.setName(oldStep.getName());
        }

        if (oldStep.getDescription() != null) {
            step.setDescription(oldStep.getDescription());
        }

        return step;
    }

    public void remove(Step step) throws UserException {
        if (step == null) {
            throw new UserException(ERROR_MGS);
        }

        Step toRemove = entityManager.find(Step.class, step.getIdStep());

        entityManager.remove(toRemove);
    }

}
