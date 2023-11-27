package ui;

import SolitarioKlondike.SolitarioKlondike;

import SolitarioKlondike.ColumnaKlondike;
import javafx.scene.layout.Pane;


public class SolitarioKlondikeView extends SolitarioView {
    private final TableuView<ColumnaKlondike> tableuView;


    public SolitarioKlondikeView(SolitarioKlondike solitario) {
        super(solitario, "/tableroKlondikeNuevo.fxml");
        this.tableuView = new TableuView<>(solitario.getTableu(), ventana);
    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarPile(((SolitarioKlondike)solitario).getWaste(), cajaWaste);
        for (int i = 1; i <= 4; i++) {
            dibujarPile(solitario.getFoundation().getPileFoundation(i), (Pane) ventana.lookup("#cajaFoundation"+i));
        }
        tableuView.dibujarTableu();
        dibujarJuegoGanado();
    }

    public Pane getCajaWaste() {
        return cajaWaste;
    }

}