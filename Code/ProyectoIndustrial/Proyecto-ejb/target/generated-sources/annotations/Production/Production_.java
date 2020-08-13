package Production;

import Design.Design;
import Group.GroupIndustrial;
import Production.ExtraCost;
import Production.Product;
import Production.Stage;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-13T10:52:16")
@StaticMetamodel(Production.class)
public class Production_ { 

    public static volatile SingularAttribute<Production, Integer> quantity;
    public static volatile SingularAttribute<Production, Product> productId;
    public static volatile SingularAttribute<Production, LocalDate> endDate;
    public static volatile SingularAttribute<Production, Double> initCost;
    public static volatile ListAttribute<Production, Stage> stageList;
    public static volatile SingularAttribute<Production, Double> finalCost;
    public static volatile SingularAttribute<Production, GroupIndustrial> groupId;
    public static volatile SingularAttribute<Production, Design> postDesign;
    public static volatile SingularAttribute<Production, Double> qualification;
    public static volatile SingularAttribute<Production, Integer> idProduction;
    public static volatile SingularAttribute<Production, String> name;
    public static volatile SingularAttribute<Production, Design> designId;
    public static volatile ListAttribute<Production, ExtraCost> extraCostList;
    public static volatile SingularAttribute<Production, Boolean> state;
    public static volatile SingularAttribute<Production, LocalDate> startDate;

}