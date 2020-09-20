/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.design;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class DesignDataDTO implements Serializable {
    
    private Integer iddesignData;
    private String name;
    private String description;
    

    public DesignDataDTO() {
    }

    public DesignDataDTO(Integer iddesignData, String name, String description) {
        this.iddesignData = iddesignData;
        this.name = name;
        this.description = description;
    }

    public Integer getIddesignData() {
        return iddesignData;
    }

    public void setIddesignData(Integer iddesignData) {
        this.iddesignData = iddesignData;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.iddesignData);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.description);
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
        final DesignDataDTO other = (DesignDataDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.iddesignData, other.iddesignData)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
