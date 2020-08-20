/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Design.Design;
import Group.GroupIndustrial;
import Production.ExtraCost;
import Production.Product;
import Production.Production;
import Production.Stage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class ProductionDTO implements Serializable{
    private Integer idProduction;  
    private String name;   
    private LocalDate startDate;   
    private LocalDate endDate;
    private boolean state;
    private Double qualification;
    private Integer quantity;
    private Double initCost;
    private Double finalCost;
    private Integer designId;
    private Integer postDesign;
    private Integer groupId;
    //private Product productId;
    //private List<Stage> stageList;
    //private List<ExtraCost> extraCostList;

    public ProductionDTO(Production production) {
        this.idProduction = production.getIdProduction();
        this.name = production.getName();
        this.startDate = production.getStartDate();
        this.endDate = production.getEndDate();  //
        this.state = production.getState();
        this.qualification = production.getQualification();
        this.quantity = production.getQuantity();
        this.initCost = production.getInitCost();
        this.finalCost = production.getFinalCost();
        this.designId = production.getDesignId().getIdDesign();
        this.postDesign = production.getPostDesign().getIdDesign();
        this.groupId = production.getGroupId().getIdGroup();
        
    }
    
    

    public ProductionDTO() {
    }
    
    

    public Integer getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(Integer idProduction) {
        this.idProduction = idProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getInitCost() {
        return initCost;
    }

    public void setInitCost(Double initCost) {
        this.initCost = initCost;
    }

    public Double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(Double finalCost) {
        this.finalCost = finalCost;
    }

    public Integer getDesignId() {
        return designId;
    }

    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    public Integer getPostDesign() {
        return postDesign;
    }

    public void setPostDesign(Integer postDesign) {
        this.postDesign = postDesign;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idProduction);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.startDate);
        hash = 89 * hash + Objects.hashCode(this.endDate);
        hash = 89 * hash + (this.state ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.qualification);
        hash = 89 * hash + Objects.hashCode(this.quantity);
        hash = 89 * hash + Objects.hashCode(this.initCost);
        hash = 89 * hash + Objects.hashCode(this.finalCost);
        hash = 89 * hash + Objects.hashCode(this.designId);
        hash = 89 * hash + Objects.hashCode(this.postDesign);
        hash = 89 * hash + Objects.hashCode(this.groupId);
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
        final ProductionDTO other = (ProductionDTO) obj;
        if (this.state != other.state) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.idProduction, other.idProduction)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.qualification, other.qualification)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.initCost, other.initCost)) {
            return false;
        }
        if (!Objects.equals(this.finalCost, other.finalCost)) {
            return false;
        }
        if (!Objects.equals(this.designId, other.designId)) {
            return false;
        }
        if (!Objects.equals(this.postDesign, other.postDesign)) {
            return false;
        }
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        return true;
    }

    
    
       
}
