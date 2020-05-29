/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Production.service;

import Design.Design;
import Design.DesignData;
import Production.NecessarySupply;
import Production.exceptions.MandatoryAttributeProductionException;
import static config.Constants.PERSISTENCE_UNIT_NAME;
import java.util.ArrayList;
import java.util.List;
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
public class DesignService {

    private EntityManager entityManager;

    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Design createDesign(Design design, DesignData designData, List<NecessarySupply> necessarySupplys) {

        design.setDesignData(designData);
        design.setNecessarySupplyList(necessarySupplys);
        List<Design> list = new ArrayList<>();
        list.add(design);
        designData.setDesignList(list);
        entityManager.persist(design);

        return design;
    }
    
    
    public Design editDesign(Design design) throws MandatoryAttributeProductionException{
        if(design.getNecessarySupplyList().isEmpty()){
            throw new MandatoryAttributeProductionException("La sista de insumos esta vacia");
        }
        if(design.getDesignData().getPicture() == null){
            throw new MandatoryAttributeProductionException("No seleccion una imagen");
        }
        if(design.getDesignData().getName().isEmpty()){
            throw new MandatoryAttributeProductionException("El diseño no tiene un nombre");
        }
        entityManager.merge(design);
        
        return design;
    }

}
