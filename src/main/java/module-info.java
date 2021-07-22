module com.leboroz {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.controlsfx.controls;

    opens com.leboroz.data;
    opens com.leboroz;
}