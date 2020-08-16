package Supply.service;

import Modify.ModificationType;
import Modify.ModifySupply;
import Modify.service.ModifySupplyService;
import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.repository.SupplyRepository;
import User.User;
import User.exception.UserException;
import User.repository.UserRepository;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.time.LocalDate;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class SupplyServices {

    private EntityManager entityManager;
    private ModifySupplyService modifySupplyService;
    private UserRepository userRepository;
    private SupplyRepository supplyRepository;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @EJB
    public void setModifySupplyService(ModifySupplyService modifySupplyService) {
        this.modifySupplyService = modifySupplyService;
    }

    @EJB
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EJB
    public void setSupplyRepository(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public SupplyServices() {
    }

    public Supply create(Supply newSupply) throws MandatoryAttributeSupplyException {
        if (newSupply.getName() == null) {
            throw new MandatoryAttributeSupplyException("Atributo Nombre Obligatorio");
        }
        if (newSupply.getExpirationDate() == null) {
            throw new MandatoryAttributeSupplyException("Atributo Fecha de Expiración Obligatorio");
        }
        if (newSupply.getCost() == 0.0) {
            throw new MandatoryAttributeSupplyException("Atributo Costo Obligatorio");
        }
        if (newSupply.getQuantity() == 0.0) {
            throw new MandatoryAttributeSupplyException("Atributo Cantidad Obligatorio");
        }
        if (newSupply.getMeasure() == null) {
            throw new MandatoryAttributeSupplyException("Atributo Medida Obligatorio");
        }
        newSupply.setDateOfAdmission(LocalDate.now());
        entityManager.persist(newSupply);
        return newSupply;
    }

    public Supply modifyQuantity(Supply supplyToChange, Double newQuantity, User user, String noteModify) throws MandatoryAttributeSupplyException {
        if (supplyToChange == null || user == null) {
            throw new MandatoryAttributeSupplyException("Debe indicar el insumo y/o usuario");
        }

        Optional<Supply> selectedSupply = supplyRepository.findSupplyByID(supplyToChange.getCode());
        Optional<User> selectedUser = userRepository.getUserByID(user.getCarnet());

        if (newQuantity == null) {
            throw new MandatoryAttributeSupplyException("Atributo Cantidad Obligatorio");
        } else if (!selectedSupply.isPresent() && !selectedUser.isPresent()) {
            throw new MandatoryAttributeSupplyException("Insumo o usuario incorrecto");
        } else {

            selectedSupply.get().setQuantity(supplyToChange.getQuantity() - newQuantity);
            saveModificationHistory(selectedSupply.get(), selectedUser.get(), ModificationType.CANTIDAD, newQuantity, noteModify);
        }
        entityManager.merge(selectedSupply.get());
        return selectedSupply.get();
    }

    public Supply modifySupply(Supply supply) throws UserException {
        if (supply == null) {
            throw new UserException("Supply is null");
        }

        Supply updateSupply = entityManager.find(Supply.class, supply.getCode());

        if (supply.getName() != null) {
            updateSupply.setName(supply.getName());
        }
        if (supply.getExpirationDate() != null) {
            updateSupply.setExpirationDate(supply.getExpirationDate());
        }
        if (supply.getCost() >= 0) {
            updateSupply.setCost(supply.getCost());
        }
        if (supply.getDescription() != null) {
            updateSupply.setDescription(supply.getDescription());
        }
        return updateSupply;
    }

    private void saveModificationHistory(Supply supply, User user, ModificationType typeModification, Double newQuantity, String note) {
        ModifySupply newModifySupply = new ModifySupply(user, supply, typeModification, newQuantity, LocalDate.now(), note);
        modifySupplyService.createModifySupply(newModifySupply);
    }

    /**
     * to use this function needs the supply to modify and quantity to reduce
     * that must be sent in toReduce
     *
     * toReduce is not the new quantity to set
     *
     * @param supply
     * @param toReduce
     * @return
     * @throws UserException
     */
    public Supply reduceSupplyQuantity(Supply supply, double toReduce) throws UserException {
        if (supply.getQuantity() >= toReduce) {
            supply.setQuantity(supply.getQuantity() - toReduce);
            return modifySupply(supply);
        }
        throw new UserException("Cantidad insuficiente en el insumo " + supply.getInternalCode());
    }
}
