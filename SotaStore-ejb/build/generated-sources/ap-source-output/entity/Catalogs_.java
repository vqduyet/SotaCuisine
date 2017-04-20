package entity;

import entity.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Catalogs.class)
public class Catalogs_ { 

    public static volatile SingularAttribute<Catalogs, Integer> id;
    public static volatile SingularAttribute<Catalogs, Integer> parentId;
    public static volatile SingularAttribute<Catalogs, String> status;
    public static volatile SingularAttribute<Catalogs, Date> created;
    public static volatile CollectionAttribute<Catalogs, Products> productsCollection;
    public static volatile SingularAttribute<Catalogs, Integer> sortOrder;
    public static volatile SingularAttribute<Catalogs, String> name;
    public static volatile SingularAttribute<Catalogs, String> icons;

}