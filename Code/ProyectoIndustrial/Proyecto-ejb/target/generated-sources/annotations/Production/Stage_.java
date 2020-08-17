package Production;

import Production.Production;
import Production.Step;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-16T21:31:47")
@StaticMetamodel(Stage.class)
public class Stage_ { 

    public static volatile SingularAttribute<Stage, Production> productionId;
    public static volatile SingularAttribute<Stage, String> name;
    public static volatile SingularAttribute<Stage, Integer> idStage;
    public static volatile SingularAttribute<Stage, String> description;
    public static volatile ListAttribute<Stage, Step> stepList;

}