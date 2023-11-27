package ui;

import SolitarioSpider.SolitarioSpider;

import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import SolitarioSpider.ColumnaSpider;



public class SolitarioSpiderView extends SolitarioView  {
    private final TableuView<ColumnaSpider> tableuView;

    public SolitarioSpiderView(SolitarioSpider solitario) {
        super(solitario, "/tableroSpiderNuevo.fxml");
        this.tableuView = new TableuView<>(solitario.getTableu(), ventana);
    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarFoundation();
        tableuView.dibujarTableu();
        dibujarJuegoGanado();
    }


    private void dibujarFoundation() {
        for (int i = 1; i <= 8; i++) {
            if (!solitario.getFoundation().getPileFoundation(i).isEmpty()) {
                var imagenCarta = new ImageView(imagenCartaRevez);
                ((Pane) ventana.lookup("#cajaFoundation"+i)).getChildren().add(imagenCarta);
            }
        }
    }

}