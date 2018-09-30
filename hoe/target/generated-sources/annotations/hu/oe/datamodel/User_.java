package hu.oe.datamodel;

import hu.oe.datamodel.Levels;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-30T21:56:49")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Levels> accessories;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Long> id;

}