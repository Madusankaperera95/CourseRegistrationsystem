module lk.cmjd.coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jbcrypt;
    requires java.mail;


    opens lk.cmjd.coursework.controller to javafx.fxml;
    opens lk.cmjd.coursework.dto to javafx.base;
    opens lk.cmjd.coursework.entity to org.hibernate.orm.core;
    exports lk.cmjd.coursework;
}