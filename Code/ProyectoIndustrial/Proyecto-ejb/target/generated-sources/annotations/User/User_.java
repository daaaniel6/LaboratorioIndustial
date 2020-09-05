package User;

import User.Career;
import User.RolUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-21T15:48:56")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, RolUser> rolUser;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Career> career;
    public static volatile SingularAttribute<User, Integer> carnet;
    public static volatile SingularAttribute<User, Integer> phone;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Boolean> state;
    public static volatile SingularAttribute<User, String> email;

}