package entity;

import entity.OrderDetail;
import entity.ShippingFee;
import entity.Users;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, BigDecimal> amount;
    public static volatile SingularAttribute<Orders, BigDecimal> shippingFee;
    public static volatile SingularAttribute<Orders, Integer> id;
    public static volatile SingularAttribute<Orders, String> usersName;
    public static volatile SingularAttribute<Orders, Date> created;
    public static volatile SingularAttribute<Orders, String> status;
    public static volatile SingularAttribute<Orders, String> usersPhone;
    public static volatile CollectionAttribute<Orders, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Orders, Integer> score;
    public static volatile SingularAttribute<Orders, Users> usersId;
    public static volatile SingularAttribute<Orders, Integer> quantity;
    public static volatile SingularAttribute<Orders, ShippingFee> district;
    public static volatile SingularAttribute<Orders, String> shippingAddress;
    public static volatile SingularAttribute<Orders, String> note;

}