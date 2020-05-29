/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.usac.cunoc.ingenieria.production.group.view;

/**
 * TODO: Ver y Scrol
 */
/**
 *
 * @author daniel
 */
import Group.GroupIndustrial;
import Group.GroupUser;
import Group.facade.GroupFacadelocal;
import Production.facade.ProductionFacadeLocal;
import User.User;
import User.exception.UserException;
import User.facade.UserFacadeLocal;
import static config.Constants.MAIN_PAGE;
import gt.edu.usac.cunoc.ingenieria.utils.MessageUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class groupView implements Serializable {

    private static final String GROUP_CREATED = "Grupo Creado Correctamente";
    private static final String GROUP_EDIT = "Grupo Editado Correctamente";
    private List<User> allStudents;
    private List<User> groupList;
    private List<User> groupListEdit;
    private List<GroupIndustrial> groupsList;

    private GroupIndustrial groupIndustrial;

    //TODO file up bloob, clase de journal
    @EJB
    private GroupFacadelocal groupFacadelocal;

    @EJB
    private ProductionFacadeLocal productionFacadeLocal;

    @EJB
    private UserFacadeLocal userFacadeLocal;
    
    @Inject
    private ExternalContext externalContext;

    @PostConstruct
    public void init() {
        try {

            allStudents = userFacadeLocal.getUserEstudent();
            groupList = new ArrayList<>();

            groupsList = groupFacadelocal.getAll();

        } catch (UserException ex) {
            Logger.getLogger(groupView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddStudent(User user) {
        groupList.add(user);
        allStudents.remove(user);
    }

    public void AddStudentEdit(User user) {
        groupListEdit.add(user);
        allStudents.remove(user);
    }

    public void RemoveStudentEdit(User user) {
        groupListEdit.remove(user);
        allStudents.add(user);
    }

    public void createGroup() {
        try {
            //groupIndustrial.setIdGroup(null);
            groupFacadelocal.createGroup(groupIndustrial, groupList);
            MessageUtils.addSuccessMessage(GROUP_CREATED);
            externalContext.getFlash().setKeepMessages(true);
            externalContext.redirect(externalContext.getRequestContextPath() + MAIN_PAGE);
        } catch (Exception e) {
            MessageUtils.addErrorMessage(e.getMessage());
        }

    }

    public void editGroup() {

        try {

            List<GroupUser> list = new ArrayList<>();
            for (int i = 0; i < groupListEdit.size(); i++) {

                GroupUser groupUser = new GroupUser(groupListEdit.get(i), groupIndustrial, LocalDate.now());
                list.add(groupUser);
            }

            List<GroupUser> listDelete = new ArrayList<>();
            listDelete = groupIndustrial.getGroupUserList();

            for (int i = 0; i < listDelete.size(); i++) {
                groupFacadelocal.removeUserFromGroup(listDelete.get(i));
            }

            groupIndustrial.setGroupUserList(list);
            groupFacadelocal.updateGroup(groupIndustrial, groupIndustrial.getInformation(), groupIndustrial.getSection());

            MessageUtils.addSuccessMessage(GROUP_EDIT);
        } catch (Exception e) {
            MessageUtils.addErrorMessage(e.getMessage());
        }

    }

    public void selection(GroupIndustrial groupSelected) {
        try {
            allStudents = userFacadeLocal.getUserEstudent();

            groupListEdit = new ArrayList<>();

            groupIndustrial = groupSelected;
            List<GroupUser> list = groupSelected.getGroupUserList();
            for (int i = 0; i < list.size(); i++) {
                groupListEdit.add(list.get(i).getUserCarnet());
            }

            for (int i = 0; i < groupListEdit.size(); i++) {
                for (int j = 0; j < allStudents.size(); j++) {
                    if (Objects.equals(allStudents.get(j).getCarnet(), groupListEdit.get(i).getCarnet())) {
                        allStudents.remove(j);
                    }
                }
            }

            this.setAllStudents(allStudents);
        } catch (UserException ex) {
            Logger.getLogger(groupView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<User> allStudents) {
        this.allStudents = allStudents;
    }

    public List<User> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<User> groupList) {
        this.groupList = groupList;
    }

    public GroupIndustrial getGroupIndustrial() {
        if (groupIndustrial == null) {
            groupIndustrial = new GroupIndustrial(null);
        }
        return groupIndustrial;
    }

    public void setGroupIndustrial(GroupIndustrial groupIndustrial) {
        this.groupIndustrial = groupIndustrial;
    }

    public List<GroupIndustrial> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GroupIndustrial> groupsList) {
        this.groupsList = groupsList;
    }

    public List<User> getGroupListEdit() {

        return groupListEdit;
    }

    public void setGroupListEdit(List<User> groupListEdit) {
        this.groupListEdit = groupListEdit;
    }

}
