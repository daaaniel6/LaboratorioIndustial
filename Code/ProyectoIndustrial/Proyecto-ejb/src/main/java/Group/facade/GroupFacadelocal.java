/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.facade;

import User.User;
import Group.GroupIndustrial;
import Group.GroupUser;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;

/**
 *
 * @author angelrg
 */

@Local
public interface GroupFacadelocal {

    /**
     * Create a new group, need a list of students to create the group
     *
     * @param group
     * @param users
     * @return
     */
    public Optional<GroupIndustrial> createGroup(GroupIndustrial group, List<User> users);
    //so so
    
    /**
     * Add a User in a new GroupIndustrial, this method verify if this relationship
 already exist
     *
     * @param group
     * @param users
     * @return
     */
    public Optional<GroupUser> assignUserToGroup(GroupIndustrial group, User users);
    //Not Found
    
    /**
     * To update just information or section, send the same text, if the text is
     * empty can't update the fill
     *
     * @param group
     * @param information
     * @param section
     * @return
     */
    public GroupIndustrial updateGroup(GroupIndustrial group, String information, String section);
    //ok.
    
    /**
     * Change the group of an user, in the GroupUser element
     *
     * @param groupUser
     * @param group
     * @return
     */
    public GroupUser updateUserGroup(GroupUser groupUser, GroupIndustrial group);
    //Not Found
    
    /**
     * This method remove a user from a group
     *
     * @param groupUser
     * @return
     */
    public Optional<GroupUser> removeUserFromGroup(GroupUser groupUser);
    //ok

    /**
     * Find a GroupIndustrial base on its ID
     *
     * @param id
     * @return
     */
    public Optional<GroupIndustrial> findById(Integer id);
    //ok
    /**
     * Return all groups stored in the system
     *
     * @return
     */
    public List<GroupIndustrial> getAll();
    //ok
    /**
     * Find a GroupUser base on ID, this entity contain the relationship between
 User and a GroupIndustrial
     *
     * @param id
     * @return
     */
    public Optional<GroupUser> findGroupUserById(Integer id);
    //Not found 
    
    /**
     * Return the users that are in a GroupIndustrial, base on the ID
     *
     * @param groupId
     * @return
     */
    public List<User> findUsersByGroup(Integer groupId);
    //Not Found 
    
    
    /**
     * Return the Groups where the user were assigned
     *
     * @param carnet
     * @return
     */
    public List<GroupIndustrial> findGroupsOfUser(Integer carnet);
    //Not Found 
    
    /**
     * Return al relation between a group and user
     *
     * @param groupId
     * @param carnet
     * @return
     */
    public List<GroupUser> getGroupUserByUserAndGroup(Integer groupId, Integer carnet);
    //Not Found
    
    /**
     * return all elements
     *
     * @return
     */
    public List<GroupUser> getAllGroupUser();
    //Not Found

}
