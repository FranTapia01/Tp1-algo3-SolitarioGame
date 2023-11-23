package ui;

import SolitarioBase.Pile;
import SolitarioKlondike.ColumnaKlondike;
import SolitarioSpider.SolitarioSpider;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class SolitarioSpiderView implements SolitarioView{
    Pile stockView;
    TableuView<ColumnaKlondike> tableuView;
    ArrayList<PileView> foundationView;
    AnchorPane ventana;




    public SolitarioSpiderView(SolitarioSpider solitario, AnchorPane ventana) {
        this.stockView = solitario.getStock();
        this.foundationView = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            foundationView.add(new PileView(solitario.getFoundation().getPileFoundation(i)));
        }
        this.ventana = ventana;
        this.tableuView = new TableuView(solitario.getTableu(), ventana);
    }

    public void dibujarSolitario() {
        dibujarStock();
        for (int i = 1; i <= foundationView.size(); i++) {
            foundationView.get(i-1).dibujarPile((Pane) ventana.lookup("#cajaFoundation"+i));
        }
        tableuView.dibujarTableu();
    }


    private void dibujarStock(){
        if (!stockView.isEmpty()) {
            var imagen = new Image("file:doc/img/blue_back.gif");
            ((Pane) ventana.lookup("#cajaStock")).getChildren().add(new ImageView(imagen));
        } else {
            ((Pane) ventana.lookup("#cajaStock")).getChildren().clear();
        }
    }

}
