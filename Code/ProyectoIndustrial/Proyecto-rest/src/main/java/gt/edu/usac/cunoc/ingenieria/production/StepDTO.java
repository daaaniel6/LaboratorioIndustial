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

/**
 *
 * @author daniel
 */
public class StepDTO implements Serializable {

    private Integer idStep;

    private String name;

    private String description;

    private Stage stageId;
    private List<Commentary> commentaryList;

    public StepDTO(Step step) {
        this.idStep = step.getIdStep();
        this.name = step.getName();
        this.description = step.getDescription();
        this.stageId = step.getStageId();
        this.commentaryList = step.getCommentaryList();
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

    public Stage getStageId() {
        return stageId;
    }

    public void setStageId(Stage stageId) {
        this.stageId = stageId;
    }

    public List<Commentary> getCommentaryList() {
        return commentaryList;
    }

    public void setCommentaryList(List<Commentary> commentaryList) {
        this.commentaryList = commentaryList;
    }
    
    

}
