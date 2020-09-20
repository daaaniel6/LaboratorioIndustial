/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.Production;
import Production.Stage;
import Production.Step;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class StageDTO implements Serializable{
    
    private Integer idStage;
    private String name;
    private String description;
    private Integer productionId;
    //private List<Step> stepList;

    public StageDTO(Stage stage) {
        this.idStage = stage.getIdStage();
        this.name = stage.getName();
        this.description = stage.getDescription();
        this.productionId = stage.getProductionId().getIdProduction();
        //this.stepList = stage.getStepList();
    }

    public StageDTO() {
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

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

  
//    public List<Step> getStepList() {
//        return stepList;
//    }
//
//    public void setStepList(List<Step> stepList) {
//        this.stepList = stepList;
//    }
//    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idStage);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.productionId);
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
        final StageDTO other = (StageDTO) obj;
        return true;
    }
    
    
    
    
}
