package Group;

import Group.GroupUser;
import Production.Production;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-16T21:31:47")
@StaticMetamodel(GroupIndustrial.class)
public class GroupIndustrial_ { 

    public static volatile SingularAttribute<GroupIndustrial, Integer> idGroup;
    public static volatile SingularAttribute<GroupIndustrial, String> name;
    public static volatile SingularAttribute<GroupIndustrial, String> information;
    public static volatile SingularAttribute<GroupIndustrial, String> section;
    public static volatile ListAttribute<GroupIndustrial, GroupUser> groupUserList;
    public static volatile ListAttribute<GroupIndustrial, Production> productionList;

}