/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.udu.usac.cunoc.ingenieria.inventory.view;

import Modify.ModifySupply;
import Modify.facade.ModifyFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class modifySupplyView implements Serializable {

    @EJB
    private ModifyFacadeLocal modifyFacadeLocal;

    private List<ModifySupply> listModifySupply;
    
    @PostConstruct
    public void init() {

        listModifySupply = new ArrayList<>();
        listModifySupply = modifyFacadeLocal.getAll();
        
    }

    public List<ModifySupply> getListModifySupply() {
        return listModifySupply;
    }

    public void setListModifySupply(List<ModifySupply> listModifySupply) {
        this.listModifySupply = listModifySupply;
    }

    
    
    
}
