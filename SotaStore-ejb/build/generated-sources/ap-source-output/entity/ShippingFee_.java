package entity;

import entity.Orders;
import entity.Users;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(ShippingFee.class)
public class ShippingFee_ { 

    public static volatile SingularAttribute<ShippingFee, Integer> id;
    public static volatile SingularAttribute<ShippingFee, BigDecimal> fee;
    public static volatile CollectionAttribute<ShippingFee, Users> usersCollection;
    public static volatile SingularAttribute<ShippingFee, String> district;
    public static volatile CollectionAttribute<ShippingFee, Orders> ordersCollection;

}