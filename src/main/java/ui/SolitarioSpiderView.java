package ui;

import SolitarioBase.Foundation;
import SolitarioBase.Pile;
import SolitarioKlondike.ColumnaKlondike;
import SolitarioSpider.SolitarioSpider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class SolitarioSpiderView implements SolitarioView{
    @FXML
    Pane cajaStock;
    @FXML
    Pane cajaFoundation;
    @FXML
    Pane cajaTableu;

    Pile stockView;
    TableuView<ColumnaKlondike> tableuView;
    Foundation foundationView;
    AnchorPane ventana;
    Image cartaRevez;

    public SolitarioSpiderView(SolitarioSpider solitario) {
        this.stockView = solitario.getStock();
        this.foundationView = solitario.getFoundation();
        cargarVentana();
        this.tableuView = new TableuView(solitario.getTableu(), ventana);
        cartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));

    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarFoundation();
        tableuView.dibujarTableu();
    }

    private void dibujarStock(){
        if (!stockView.isEmpty()) {
            var imagen = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
            ((Pane) ventana.lookup("#cajaStock")).getChildren().add(new ImageView(imagen));
        } else {
            ((Pane) ventana.lookup("#cajaStock")).getChildren().clear();
        }
    }

    private void dibujarFoundation() {
        for (int i = 1; i <= 8; i++) {
            if (!foundationView.getPileFoundation(i).isEmpty()) {
                var imagenCarta = new ImageView(cartaRevez);
                ((Pane) ventana.lookup("#cajaFoundation"+i)).getChildren().add(imagenCarta);
            }
        }
    }


    private void cargarVentana() {
        var loaderKlondike = new FXMLLoader(getClass().getResource("/tableroSpiderNuevo.fxml"));
        loaderKlondike.setController(this);
        try {
            this.ventana = loaderKlondike.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public AnchorPane getVentana() {
        return this.ventana;
    }

    public Pane getCajaStock() {
        return cajaStock;
    }
    public int getTamanioFoundation() {
        return cajaFoundation.getChildren().size();
    }
    public int getTamanioTableu() {
        return cajaTableu.getChildren().size();
    }



}
