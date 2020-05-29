/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.production.view;

import Supply.Supply;
import Supply.facade.SupplyFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author daniel
 */
@Named("manageSupplies")
@ViewScoped
public class ManageSupplies implements Serializable {

    @EJB
    private SupplyService service;

    private List<Supply> supplies;

    private List<Supply> droppedSupplies;

    private Supply selectedSupply;

    @EJB
    private SupplyFacadeLocal supplyFacade;

    @PostConstruct
    public void init() {
        //supplies = service.createSupplies(5);
        supplies = supplyFacade.getSupplyAvailable();
        droppedSupplies = new ArrayList<>();
    }

    public void onCarDrop(DragDropEvent ddEvent) {
        Supply supply = (Supply) ddEvent.getData();

        droppedSupplies.add(supply);
        supplies.remove(supply);
    }

    public void setService(SupplyService service) {
        this.service = service;
    }

    public void setSupplyFacade(SupplyFacadeLocal supplyFacade) {
        this.supplyFacade = supplyFacade;
    }
    

    public List<Supply> getSupplies() {
        return supplies;
    }

    public List<Supply> getDroppedSupplies() {
        return droppedSupplies;
    }

    public Supply getSelectedSupply() {
        return selectedSupply;
    }

    public void setSelectedSupply(Supply selectedSupply) {
        this.selectedSupply = selectedSupply;
    }

}
