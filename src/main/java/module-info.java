module org.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires org.xerial.sqlitejdbc;

    opens org.app to javafx.fxml;
    exports org.app;
    exports org.app.Controller;
    exports org.app.Object;
    opens org.app.Controller to javafx.fxml;
}