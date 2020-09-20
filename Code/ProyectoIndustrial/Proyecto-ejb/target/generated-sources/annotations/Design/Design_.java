package Design;

import Design.DesignData;
import Production.NecessarySupply;
import Production.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-20T18:29:46")
=======
@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-08-21T15:48:56")
>>>>>>> dev
@StaticMetamodel(Design.class)
public class Design_ { 

    public static volatile SingularAttribute<Design, Integer> idDesign;
    public static volatile SingularAttribute<Design, Product> productIdProduct;
    public static volatile ListAttribute<Design, NecessarySupply> necessarySupplyList;
    public static volatile SingularAttribute<Design, DesignData> designData;

}