package Production;

import Production.Commentary;
import Production.Stage;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-05-31T19:29:29")
@StaticMetamodel(Step.class)
public class Step_ { 

    public static volatile SingularAttribute<Step, Integer> idStep;
    public static volatile ListAttribute<Step, Commentary> commentaryList;
    public static volatile SingularAttribute<Step, String> name;
    public static volatile SingularAttribute<Step, String> description;
    public static volatile SingularAttribute<Step, Stage> stageId;

}