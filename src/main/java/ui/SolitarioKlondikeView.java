package ui;

import SolitarioBase.Pile;
import SolitarioKlondike.ColumnaKlondike;
import SolitarioKlondike.SolitarioKlondike;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class SolitarioKlondikeView implements SolitarioView{
    Pile stockView;
    PileView wasteView;
    TableuView<ColumnaKlondike> tableuView;
    ArrayList<PileView> foundationView;
    AnchorPane ventana;



    public SolitarioKlondikeView(SolitarioKlondike solitario, AnchorPane ventana) {
        this.stockView = solitario.getStock();
        this.wasteView = new PileView(solitario.getWaste());
        this.foundationView = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            foundationView.add(new PileView(solitario.getFoundation().getPileFoundation(i)));
        }
        this.ventana = ventana;
        this.tableuView = new TableuView(solitario.getTableu(), ventana);
    }

    public void dibujarSolitario() {
        dibujarStock();
        wasteView.dibujarPile((Pane) ventana.lookup("#cajaWaste"));
        for (int i = 1; i <= 4; i++) {
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
