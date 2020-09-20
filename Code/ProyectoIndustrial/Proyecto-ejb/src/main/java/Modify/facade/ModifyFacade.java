/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modify.facade;

import Modify.ModifySupply;
import Modify.repository.ModifySupplyRepository;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author angelrg
 */
@Stateless
public class ModifyFacade implements ModifyFacadeLocal {

    private ModifySupplyRepository modifySupplyRepository;

    @EJB
    public void setModifySupplyRepository(ModifySupplyRepository modifySupplyRepository) {
        this.modifySupplyRepository = modifySupplyRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ModifySupply> getById(Integer id) {
       return modifySupplyRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModifySupply> getModificationByUser(Integer idUser) {
        return modifySupplyRepository.getModificationByUser(idUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModifySupply> getModificationBySupply(Integer idSupply) {
        return modifySupplyRepository.getModificationBySupply(idSupply);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModifySupply> getAll() {
        return modifySupplyRepository.getAll();
    }

}
