/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.ExtraCost;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class ExtraCostDTO {
    private Integer idExtraCost;
    private String description;
    private double cost;
    private Integer idProduction;

    public ExtraCostDTO() {
    }


    public ExtraCostDTO(ExtraCost extraCost) {
        this.idExtraCost = extraCost.getIdExtraCost();
        this.description = extraCost.getDescription();
        this.cost = extraCost.getCost();
        this.idProduction = extraCost.getIdProduction().getIdProduction();
    }

    public Integer getIdExtraCost() {
        return idExtraCost;
    }

    public void setIdExtraCost(Integer idExtraCost) {
        this.idExtraCost = idExtraCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(Integer idProduction) {
        this.idProduction = idProduction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idExtraCost);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.cost) ^ (Double.doubleToLongBits(this.cost) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.idProduction);
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
        final ExtraCostDTO other = (ExtraCostDTO) obj;
        if (Double.doubleToLongBits(this.cost) != Double.doubleToLongBits(other.cost)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idExtraCost, other.idExtraCost)) {
            return false;
        }
        if (!Objects.equals(this.idProduction, other.idProduction)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
