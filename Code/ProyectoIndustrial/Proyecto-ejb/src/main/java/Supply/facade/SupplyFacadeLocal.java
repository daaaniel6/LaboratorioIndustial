package Supply.facade;

import Supply.Measure;
import Supply.Supply;
import Supply.exception.MandatoryAttributeSupplyException;
import Supply.repository.AvailabilityFilter;
import Supply.repository.ExpirationDateFilter;
import java.util.List;
import User.User;
import User.exception.UserException;
import java.time.LocalDate;
import java.util.Optional;
import javax.ejb.Local;

@Local
public interface SupplyFacadeLocal {

    /**
     * Obtiene todas las medidas
     *
     * @return Lista de medidas
     */
    public List<Measure> getAllMeasures();

    public List<Supply> getSupplyAvailable();

    /**
     * *
     * Obtiene una medida por medio de su id
     *
     * @return La medida con el id proporcionado
     */
    public Optional<Measure> getMeasureById(Integer id);

    /**
     * *
     * Crea un nuevo Insumo en BD
     *
     * @param newSupply Objeto Supply a crear en BD
     * @return El Objeto creado en la BD
     * @throws MandatoryAttributeSupplyException Si hace falta algun atributo
     * obligatorio
     */
    public Supply createSupply(Supply newSupply) throws MandatoryAttributeSupplyException;

    /**
     * *
     * Busca los Insumos que cumplan con los criterios de busqueda
     * proporcionados
     *
     * @param codeSupply codigo del Insumo a buscar
     * @param internalCode
     * @param nameSupply Nombre del Insumo a buscar
     * @param availabilitySupply Disponibilidad del Insumo a Buscar
     * @param expirationDateSupply Fecha de Expiracion del Insumo a Buscar
     * @return
     */
    public List<Supply> searchSupplies(Integer codeSupply, String internalCode, String nameSupply, AvailabilityFilter availabilitySupply, ExpirationDateFilter expirationDateSupply);

    /**
     * This option allow to modify the quantity available, and save the identity
     * of the modifier user
     *
     * @param supplyToChange
     * @param newQuantity
     * @param user
     * @param noteModify
     * @return
     * @throws Supply.exception.MandatoryAttributeSupplyException
     */
    public Supply modifyQuantity(Supply supplyToChange, Double newQuantity, User user, String noteModify) throws MandatoryAttributeSupplyException;

    /**
     * This option allow to modify simple attributes
     *
     * @param supply
     * @return
     */
    public Supply modifySupply(Supply supply) throws UserException;
}
