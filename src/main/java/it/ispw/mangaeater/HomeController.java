package it.ispw.mangaeater;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.cell.ColorGridCell;

import java.util.ArrayList;
import java.util.Random;

public class HomeController{

    @FXML
    private GridView<Color> gv;

    @FXML
    private TextField searchBar;

    @FXML
    void temp(MouseEvent event) {
        Color c = new Color(1,2,3,1);
        Color c1 = Color.web("#1a53ff");
        ObservableList<Color> list = new FilteredList<>();
        list.add(c);
        gv = new GridView<>(list);
        gv.setCellFactory(new Callback<GridView<Color>, GridCell<Color>>() {
            public GridCell<Color> call(GridView<Color> gridView) {
                return new ColorGridCell();
            }
        });
        Random r = new Random(System.currentTimeMillis());
        for(int i = 0; i < 500; i++) {
            list.add(new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1.0));
        }
    }

}


    /*Color c = new Color(1,2,3,1);
    Color c1 = Color.web("#1a53ff");
    ObservableList<Color> list = new ObservableArray<>();
            list.add(c);
                    gv = new GridView<>(list);
        gv.setCellFactory(new Callback<GridView<Color>, GridCell<Color>>() {
public GridCell<Color> call(GridView<Color> gridView) {
        return new ColorGridCell();
        }
        });
        Random r = new Random(System.currentTimeMillis());
        for(int i = 0; i < 500; i++) {
        list.add(new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1.0));
        }*/