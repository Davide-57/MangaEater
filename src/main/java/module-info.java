module it.ispw.mangaeater {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens it.ispw.mangaeater to javafx.fxml;
    exports it.ispw.mangaeater;
}