package entity;

import entity.Admin;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(AdminGroup.class)
public class AdminGroup_ { 

    public static volatile SingularAttribute<AdminGroup, Integer> id;
    public static volatile SingularAttribute<AdminGroup, String> status;
    public static volatile SingularAttribute<AdminGroup, Date> created;
    public static volatile SingularAttribute<AdminGroup, Integer> sortOrder;
    public static volatile SingularAttribute<AdminGroup, String> name;
    public static volatile SingularAttribute<AdminGroup, String> permissions;
    public static volatile CollectionAttribute<AdminGroup, Admin> adminCollection;

}