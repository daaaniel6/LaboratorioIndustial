package Group;

import Group.GroupIndustrial;
import User.User;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-09-19T11:34:27")
@StaticMetamodel(GroupUser.class)
public class GroupUser_ { 

    public static volatile SingularAttribute<GroupUser, LocalDate> admissionDate;
    public static volatile SingularAttribute<GroupUser, GroupIndustrial> groupId;
    public static volatile SingularAttribute<GroupUser, Integer> idGruopUser;
    public static volatile SingularAttribute<GroupUser, User> userCarnet;

}