package Production;

import Design.Design;
import Production.Production;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2020-10-29T21:14:49")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, Design> designList;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Integer> idProduct;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile ListAttribute<Product, Production> productionList;

}