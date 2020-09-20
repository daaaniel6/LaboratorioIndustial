/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.group;

import User.User;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author daniel
 */
public class GroupDTO implements Serializable{
    private GroupIndustrialDTO groupIndustrialDTO;
    private List<User> users;

    public GroupDTO() {
    }

//    public GroupDTO(GroupDTO groupDTO) {
//        this.groupIndustrialDTO = groupDTO.getGroupIndustrialDTO();
//        this.users = groupDTO.getUsers();
//    }

    public GroupDTO(GroupIndustrialDTO groupIndustrialDTO, List<User> users) {
        this.groupIndustrialDTO = groupIndustrialDTO;
        this.users = users;
    }
    
    

    public GroupIndustrialDTO getGroupIndustrialDTO() {
        return groupIndustrialDTO;
    }

    public void setGroupIndustrialDTO(GroupIndustrialDTO groupIndustrialDTO) {
        this.groupIndustrialDTO = groupIndustrialDTO;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    
}
