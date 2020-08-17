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
    private Design designId;
    private Design postDesign;
    private GroupIndustrial groupId;
    private Product productId;
    private List<Stage> stageList;
    private List<ExtraCost> extraCostList;

    public ProductionDTO(Production production) {
        this.idProduction = production.getIdProduction();
        this.name = production.getName();
        this.startDate = production.getStartDate();
        this.endDate = production.getEndDate();
        this.state = production.getState();
        this.qualification = production.getQualification();
        this.quantity = production.getQuantity();
        this.initCost = production.getInitCost();
        this.finalCost = production.getFinalCost();
        this.designId = production.getDesignId();
        this.postDesign = production.getPostDesign();
        this.groupId = production.getGroupId();
        this.productId = production.getProductId();
        this.stageList = production.getStageList();
        this.extraCostList = production.getExtraCostList();
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

    public boolean isState() {
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

    public Design getDesignId() {
        return designId;
    }

    public void setDesignId(Design designId) {
        this.designId = designId;
    }

    public Design getPostDesign() {
        return postDesign;
    }

    public void setPostDesign(Design postDesign) {
        this.postDesign = postDesign;
    }

    public GroupIndustrial getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupIndustrial groupId) {
        this.groupId = groupId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public List<ExtraCost> getExtraCostList() {
        return extraCostList;
    }

    public void setExtraCostList(List<ExtraCost> extraCostList) {
        this.extraCostList = extraCostList;
    }
    
        
           
            
}
