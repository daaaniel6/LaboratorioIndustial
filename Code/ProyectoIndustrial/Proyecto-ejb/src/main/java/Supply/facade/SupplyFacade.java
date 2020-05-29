package Supply.facade;

import Supply.Measure;
import Supply.repository.ExpirationDateFilter;
import Supply.repository.MeasureRepository;
import Supply.repository.SupplyRepository;
import java.util.List;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.service.SupplyServices;
import User.User;
import User.exception.UserException;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import Supply.repository.AvailabilityFilter;
import java.util.Optional;

@Stateless
@LocalBean
public class SupplyFacade implements SupplyFacadeLocal {

    @EJB
    private MeasureRepository measureRepository;

    @EJB
    private SupplyServices supplyService;

    @EJB
    private SupplyRepository supplyRepository;

    @EJB
    public void setMeasureRepository(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    @EJB
    public void setSupplyService(SupplyServices supplyService) {
        this.supplyService = supplyService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Measure> getAllMeasures() {
        return measureRepository.getAllMeasures();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Measure> getMeasureById(Integer id) {
        return measureRepository.getMeasureById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supply createSupply(Supply newSupply) throws MandatoryAttributeSupplyException {
        return supplyService.create(newSupply);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Supply> searchSupplies(Integer codeSupply, String internalCode, String nameSupply, AvailabilityFilter availabilitySupply, ExpirationDateFilter expirationDateSupply) {
        return supplyRepository.getSupply(codeSupply, nameSupply, internalCode, availabilitySupply, expirationDateSupply);
    }

    @Override
    public List<Supply> getSupplyAvailable() {
        return supplyRepository.getSupply(null, null, null, AvailabilityFilter.AVAILABLE, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supply modifyQuantity(Supply supplyToChange, Double newQuantity, User user, String noteModify) throws MandatoryAttributeSupplyException {
        return supplyService.modifyQuantity(supplyToChange, newQuantity, user, noteModify);
    }

    @Override
    public Supply modifySupply(Supply supply) throws UserException {
        return supplyService.modifySupply(supply);
    }

}
