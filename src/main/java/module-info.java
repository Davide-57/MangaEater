module it.ispw.mangaeater {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires reactive.jikan;
    requires reactor.core;
    requires com.opencsv;

    opens it.ispw.mangaeater to javafx.fxml;
    exports it.ispw.mangaeater;
    exports it.ispw.mangaeater.jikan;
    opens it.ispw.mangaeater.jikan to javafx.fxml;
}