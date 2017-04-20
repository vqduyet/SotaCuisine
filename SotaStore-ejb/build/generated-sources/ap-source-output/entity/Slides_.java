package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-04-17T08:52:53")
@StaticMetamodel(Slides.class)
public class Slides_ { 

    public static volatile SingularAttribute<Slides, Integer> id;
    public static volatile SingularAttribute<Slides, String> imageName;
    public static volatile SingularAttribute<Slides, Date> created;
    public static volatile SingularAttribute<Slides, Integer> sortOrder;
    public static volatile SingularAttribute<Slides, String> name;
    public static volatile SingularAttribute<Slides, String> imageLink;
    public static volatile SingularAttribute<Slides, String> link;
    public static volatile SingularAttribute<Slides, String> info;

}