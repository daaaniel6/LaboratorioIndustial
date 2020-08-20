/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.group;

import Group.GroupIndustrial;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class GroupIndustrialDTO implements Serializable{
    
    
    private Integer idGroup;
    private String information;
    private String name;
    private String section;

    public GroupIndustrialDTO(GroupIndustrial groupIndustrial) {
        this.idGroup = groupIndustrial.getIdGroup();
        this.information = groupIndustrial.getInformation();
        this.name = groupIndustrial.getName();
        this.section = groupIndustrial.getSection();
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idGroup);
        hash = 37 * hash + Objects.hashCode(this.information);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.section);
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
        final GroupIndustrialDTO other = (GroupIndustrialDTO) obj;
        if (!Objects.equals(this.information, other.information)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.section, other.section)) {
            return false;
        }
        if (!Objects.equals(this.idGroup, other.idGroup)) {
            return false;
        }
        return true;
    }
    
    
    
}
