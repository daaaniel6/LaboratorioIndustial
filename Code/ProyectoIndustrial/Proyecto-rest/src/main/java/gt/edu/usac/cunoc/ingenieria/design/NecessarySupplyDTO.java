/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.design;

import Supply.Supply;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class NecessarySupplyDTO {

    private Integer idNecessarySupply;
    private double quantity;
    private Integer designId;
    private Integer supplyCode;
    
    
    public NecessarySupplyDTO() {
    }

    public NecessarySupplyDTO(Integer idNecessarySupply, double quantity, Integer designId, Integer supplyCode) {
        this.idNecessarySupply = idNecessarySupply;
        this.quantity = quantity;
        this.designId = designId;
        this.supplyCode = supplyCode;
    }

    public Integer getIdNecessarySupply() {
        return idNecessarySupply;
    }

    public void setIdNecessarySupply(Integer idNecessarySupply) {
        this.idNecessarySupply = idNecessarySupply;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Integer getDesignId() {
        return designId;
    }

    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    public Integer getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(Integer supplyCode) {
        this.supplyCode = supplyCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idNecessarySupply);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.quantity) ^ (Double.doubleToLongBits(this.quantity) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.designId);
        hash = 83 * hash + Objects.hashCode(this.supplyCode);
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
        final NecessarySupplyDTO other = (NecessarySupplyDTO) obj;
        if (Double.doubleToLongBits(this.quantity) != Double.doubleToLongBits(other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.idNecessarySupply, other.idNecessarySupply)) {
            return false;
        }
        if (!Objects.equals(this.designId, other.designId)) {
            return false;
        }
        if (!Objects.equals(this.supplyCode, other.supplyCode)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
