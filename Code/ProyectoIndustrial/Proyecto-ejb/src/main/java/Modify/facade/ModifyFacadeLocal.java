/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modify.facade;

import java.util.Optional;
import Modify.ModifySupply;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author angelrg
 */
@Local
public interface ModifyFacadeLocal {

    /**
     * Find a specific ModifySuply by its id, returning the object inside of
     * optional, to avoid NULL result
     *
     * @param id
     * @return
     */
    public Optional<ModifySupply> getById(Integer id);

    /**
     * Return a list of ModifySupply where the foraying key idUser are similar
     *
     * @param idUser
     * @return
     */
    public List<ModifySupply> getModificationByUser(Integer idUser);

    /**
     * Return a list of ModifySupply where the foraying key idUser are similar
     *
     * @param idSupply
     * @return
     */
    public List<ModifySupply> getModificationBySupply(Integer idSupply);

    /**
     * Return all elements in database
     *
     * @return
     */
    public List<ModifySupply> getAll();

}
