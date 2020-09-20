/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production;

import Production.Commentary;
import Production.Stage;
import Production.Step;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class StepDTO implements Serializable {

    private Integer idStep;

    private String name;

    private String description;

    private Integer stageId;
    //private List<Commentary> commentaryList;

    public StepDTO(Step step) {
        this.idStep = step.getIdStep();
        this.name = step.getName();
        this.description = step.getDescription();
        this.stageId = step.getStageId().getIdStage();
        //this.commentaryList = step.getCommentaryList();
    }

    public StepDTO() {
    }

    
    
    
    public Integer getIdStep() {
        return idStep;
    }

    public void setIdStep(Integer idStep) {
        this.idStep = idStep;
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

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idStep);
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.stageId);
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
        final StepDTO other = (StepDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idStep, other.idStep)) {
            return false;
        }
        if (!Objects.equals(this.stageId, other.stageId)) {
            return false;
        }
        return true;
    }

    


}
