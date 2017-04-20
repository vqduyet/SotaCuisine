package entity;

import entity.Contact;
import entity.Orders;
import entity.ShippingFee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile CollectionAttribute<Users, Contact> contactCollection;
    public static volatile SingularAttribute<Users, Date> created;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> status;
    public static volatile SingularAttribute<Users, Integer> score;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, String> fullName;
    public static volatile SingularAttribute<Users, ShippingFee> district;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, String> userPass;

}