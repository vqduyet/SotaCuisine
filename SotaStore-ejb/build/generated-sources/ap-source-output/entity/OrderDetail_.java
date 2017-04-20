package entity;

import entity.OrderDetailPK;
import entity.Orders;
import entity.Products;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, BigDecimal> amount;
    public static volatile SingularAttribute<OrderDetail, BigDecimal> price;
    public static volatile SingularAttribute<OrderDetail, Integer> quantity;
    public static volatile SingularAttribute<OrderDetail, Orders> orders;
    public static volatile SingularAttribute<OrderDetail, Products> products;
    public static volatile SingularAttribute<OrderDetail, OrderDetailPK> orderDetailPK;

}