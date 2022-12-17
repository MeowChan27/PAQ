module com.example.paq {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.paq to javafx.fxml;
    exports com.example.paq;
}