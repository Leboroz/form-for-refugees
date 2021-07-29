/**
 *
 */
module com.leboroz {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires org.postgresql.jdbc;
    requires java.sql;
    opens com.leboroz.data;
    opens com.leboroz;
}