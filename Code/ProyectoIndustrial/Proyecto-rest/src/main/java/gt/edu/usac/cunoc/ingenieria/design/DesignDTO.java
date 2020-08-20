/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.design;

import Design.Design;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class DesignDTO implements Serializable {

    private Integer idDesign;
    private Integer designData;
    private Integer productIdProduct;

    public DesignDTO(Design design) {
        this.idDesign = design.getIdDesign();
        this.designData = design.getDesignData().getIddesignData();
        this.productIdProduct = design.getProductIdProduct().getIdProduct();
        
         
    }

    public Integer getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Integer idDesign) {
        this.idDesign = idDesign;
    }

    public Integer getDesignData() {
        return designData;
    }

    public void setDesignData(Integer designData) {
        this.designData = designData;
    }

    public Integer getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(Integer productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.idDesign);
        hash = 17 * hash + Objects.hashCode(this.designData);
        hash = 17 * hash + Objects.hashCode(this.productIdProduct);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DesignDTO other = (DesignDTO) obj;
        if (!Objects.equals(this.idDesign, other.idDesign)) {
            return false;
        }
        if (!Objects.equals(this.designData, other.designData)) {
            return false;
        }
        if (!Objects.equals(this.productIdProduct, other.productIdProduct)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    

}
