/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.facade;

import Group.GroupIndustrial;
import Group.GroupUser;
import Group.service.GroupService;
import Group.service.GroupUserService;
import Group.repository.GroupUserRepository;
import Group.repository.GroupRepository;
import User.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author angelrg
 */
@Stateless
public class GroupFacade implements GroupFacadelocal {

    private GroupUserRepository groupUserRepository;
    private GroupRepository groupRepository;
    private GroupUserService groupUserService;
    private GroupService groupService;

    @EJB
    public void setGroupUserRepository(GroupUserRepository groupUserRepository) {
        this.groupUserRepository = groupUserRepository;
    }

    @EJB
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @EJB
    public void setGroupUserService(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    @EJB
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GroupIndustrial> createGroup(GroupIndustrial group, List<User> users) {
        GroupIndustrial newGroup = groupService.createGroup(group);
        if (!users.isEmpty()) {
            for (User user : users) {
                groupUserService.createGroupUser(new GroupUser(user, group, LocalDate.now()));
            }
        }
        return Optional.ofNullable(newGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GroupUser> assignUserToGroup(GroupIndustrial group, User users) {
        if (groupUserRepository.getGroupUserByUserAndGroup(group.getIdGroup(), users.getCarnet()).isEmpty()) {
            return Optional.ofNullable(groupUserService.createGroupUser(new GroupUser(users, group, LocalDate.now())));
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupIndustrial updateGroup(GroupIndustrial group, String information, String section) {
        return groupService.updateGroup(group, information, section);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupUser updateUserGroup(GroupUser groupUser, GroupIndustrial group) {
        return groupUserService.updateUserGroup(groupUser, group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GroupUser> removeUserFromGroup(GroupUser groupUser) {
        return groupUserService.removeUserFromGroup(groupUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GroupIndustrial> findById(Integer id) {
        return groupRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupIndustrial> getAll() {
        return groupRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GroupUser> findGroupUserById(Integer id) {
        return groupUserRepository.findGroupUserById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findUsersByGroup(Integer groupId) {
        return groupUserRepository.findUsersByGroup(groupId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupIndustrial> findGroupsOfUser(Integer carnet) {
        return groupUserRepository.findGroupsOfUser(carnet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupUser> getGroupUserByUserAndGroup(Integer groupId, Integer carnet) {
        return groupUserRepository.getGroupUserByUserAndGroup(groupId, carnet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupUser> getAllGroupUser() {
        return groupUserRepository.getAllGroupUser();
    }

}
