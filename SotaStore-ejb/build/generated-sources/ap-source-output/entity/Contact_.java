package entity;

import entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Contact.class)
public class Contact_ { 

    public static volatile SingularAttribute<Contact, Integer> id;
    public static volatile SingularAttribute<Contact, String> content;
    public static volatile SingularAttribute<Contact, String> phone;
    public static volatile SingularAttribute<Contact, String> email;
    public static volatile SingularAttribute<Contact, String> address;
    public static volatile SingularAttribute<Contact, String> status;
    public static volatile SingularAttribute<Contact, Date> created;
    public static volatile SingularAttribute<Contact, String> name;
    public static volatile SingularAttribute<Contact, Users> usersId;

}