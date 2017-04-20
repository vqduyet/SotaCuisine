package entity;

import entity.AdminGroup;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, Integer> id;
    public static volatile SingularAttribute<Admin, AdminGroup> admingroupid;
    public static volatile SingularAttribute<Admin, String> phone;
    public static volatile SingularAttribute<Admin, String> address;
    public static volatile SingularAttribute<Admin, String> status;
    public static volatile SingularAttribute<Admin, Date> created;
    public static volatile SingularAttribute<Admin, String> userName;
    public static volatile SingularAttribute<Admin, String> fullName;
    public static volatile SingularAttribute<Admin, String> userPass;

}