/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.Production;
import Production.Stage;
import Production.Step;
import java.util.List;

/**
 *
 * @author daniel
 */
public class StageDTO {
    
    private Integer idStage;
    private String name;
    private String description;
    private Production productionId;
    private List<Step> stepList;

    public StageDTO(Stage stage) {
        this.idStage = stage.getIdStage();
        this.name = stage.getName();
        this.description = stage.getDescription();
        this.productionId = stage.getProductionId();
        this.stepList = stage.getStepList();
    }

    public Integer getIdStage() {
        return idStage;
    }

    public void setIdStage(Integer idStage) {
        this.idStage = idStage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Production getProductionId() {
        return productionId;
    }

    public void setProductionId(Production productionId) {
        this.productionId = productionId;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }
    
    
    
    
    
}
