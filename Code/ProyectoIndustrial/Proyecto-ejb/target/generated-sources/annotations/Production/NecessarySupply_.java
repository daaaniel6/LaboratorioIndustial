package Production;

import Design.Design;
import Supply.Supply;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-20T18:29:46")
=======
@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-21T15:48:56")
>>>>>>> dev
@StaticMetamodel(NecessarySupply.class)
public class NecessarySupply_ { 

    public static volatile SingularAttribute<NecessarySupply, Double> quantity;
    public static volatile SingularAttribute<NecessarySupply, Integer> idNecessarySupply;
    public static volatile SingularAttribute<NecessarySupply, Design> designId;
    public static volatile SingularAttribute<NecessarySupply, Supply> supplyCode;

}