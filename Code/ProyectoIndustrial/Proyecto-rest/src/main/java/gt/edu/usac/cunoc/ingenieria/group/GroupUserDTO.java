/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.group;

import Group.GroupUser;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author daniel
 */
public class GroupUserDTO {
  
    private Integer idGruopUser;
    private LocalDate admissionDate;
    private Integer groupId;
    private Integer userCarnet;

    public GroupUserDTO(GroupUser groupUser) {
        this.idGruopUser = groupUser.getIdGruopUser();
        this.admissionDate = groupUser.getAdmissionDate();
        this.groupId = groupUser.getGroupId().getIdGroup();
        this.userCarnet = groupUser.getUserCarnet().getCarnet();
    }

    public Integer getIdGruopUser() {
        return idGruopUser;
    }

    public void setIdGruopUser(Integer idGruopUser) {
        this.idGruopUser = idGruopUser;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserCarnet() {
        return userCarnet;
    }

    public void setUserCarnet(Integer userCarnet) {
        this.userCarnet = userCarnet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idGruopUser);
        hash = 83 * hash + Objects.hashCode(this.admissionDate);
        hash = 83 * hash + Objects.hashCode(this.groupId);
        hash = 83 * hash + Objects.hashCode(this.userCarnet);
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
        final GroupUserDTO other = (GroupUserDTO) obj;
        if (!Objects.equals(this.idGruopUser, other.idGruopUser)) {
            return false;
        }
        if (!Objects.equals(this.admissionDate, other.admissionDate)) {
            return false;
        }
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        if (!Objects.equals(this.userCarnet, other.userCarnet)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
