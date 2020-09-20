package Supply;

import Modify.ModifySupply;
import Production.NecessarySupply;
import Supply.Measure;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-06-22T17:05:59")
@StaticMetamodel(Supply.class)
public class Supply_ { 

    public static volatile SingularAttribute<Supply, Integer> code;
    public static volatile SingularAttribute<Supply, Double> cost;
    public static volatile SingularAttribute<Supply, Double> quantity;
    public static volatile SingularAttribute<Supply, Measure> measure;
    public static volatile ListAttribute<Supply, NecessarySupply> necessarySupplyList;
    public static volatile SingularAttribute<Supply, String> name;
    public static volatile SingularAttribute<Supply, String> description;
    public static volatile SingularAttribute<Supply, LocalDate> dateOfAdmission;
    public static volatile SingularAttribute<Supply, Boolean> availability;
    public static volatile ListAttribute<Supply, ModifySupply> modifySupplyList;
    public static volatile SingularAttribute<Supply, String> internalCode;
    public static volatile SingularAttribute<Supply, LocalDate> expirationDate;

}