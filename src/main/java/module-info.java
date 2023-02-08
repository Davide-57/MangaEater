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
    opens it.ispw.mangaeater.controller_grafici to javafx.fxml;
    exports it.ispw.mangaeater;
    exports it.ispw.mangaeater.boundary.jikan;
    opens it.ispw.mangaeater.boundary.jikan to javafx.fxml;
    exports it.ispw.mangaeater.controller_grafici;
    exports it.ispw.mangaeater.controller;
    exports it.ispw.mangaeater.controller.pagamento;
    exports it.ispw.mangaeater.exception;
    exports it.ispw.mangaeater.bean;
    exports it.ispw.mangaeater.myenum;
    exports it.ispw.mangaeater.decorator_pattern;
}