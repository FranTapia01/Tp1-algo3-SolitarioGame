package ui;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public interface SolitarioView {

    void dibujarSolitario();

    AnchorPane getVentana();
    int getTamanioTableu();
    Pane getCajaStock();

    int getTamanioFoundation();
}
