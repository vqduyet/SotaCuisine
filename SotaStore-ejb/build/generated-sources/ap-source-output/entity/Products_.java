package entity;

import entity.Catalogs;
import entity.OrderDetail;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, Integer> viewNo;
    public static volatile SingularAttribute<Products, String> status;
    public static volatile SingularAttribute<Products, Integer> rateCount;
    public static volatile SingularAttribute<Products, String> imageLinkDetail;
    public static volatile SingularAttribute<Products, Integer> rateTotal;
    public static volatile SingularAttribute<Products, String> decription;
    public static volatile SingularAttribute<Products, BigDecimal> discount;
    public static volatile SingularAttribute<Products, Integer> id;
    public static volatile SingularAttribute<Products, BigDecimal> price;
    public static volatile SingularAttribute<Products, Date> created;
    public static volatile SingularAttribute<Products, String> imageLink;
    public static volatile CollectionAttribute<Products, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Products, String> name;
    public static volatile SingularAttribute<Products, Catalogs> catalogId;
    public static volatile SingularAttribute<Products, String> shortDescriptions;
    public static volatile SingularAttribute<Products, Integer> bought;

}