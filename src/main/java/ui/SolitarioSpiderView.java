package ui;

import SolitarioSpider.ColumnaSpider;
import SolitarioBase.SolitarioObserver;
import SolitarioSpider.SolitarioSpider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;


public class SolitarioSpiderView implements SolitarioView, SolitarioObserver {
    @FXML
    Pane cajaStock;
    @FXML
    Pane cajaFoundation;
    @FXML
    Pane cajaTableu;

    AnchorPane ventana;
    SolitarioSpider solitario;
    TableuView<ColumnaSpider> tableuView;
    Image imagenCartaRevez;

    public SolitarioSpiderView(SolitarioSpider solitario) {
        cargarVentana();
        this.solitario = solitario;
        solitario.agregarObservador(this);
        this.tableuView = new TableuView(solitario.getTableu(), ventana);
        this.imagenCartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarFoundation();
        tableuView.dibujarTableu();
    }

    private void dibujarStock(){
        if (!solitario.getStock().isEmpty()) {
            cajaStock.getChildren().add(new ImageView(imagenCartaRevez));
        } else {
            cajaStock.getChildren().clear();
        }
    }

    private void dibujarFoundation() {
        for (int i = 1; i <= 8; i++) {
            if (!solitario.getFoundation().getPileFoundation(i).isEmpty()) {
                var imagenCarta = new ImageView(imagenCartaRevez);
                ((Pane) ventana.lookup("#cajaFoundation"+i)).getChildren().add(imagenCarta);
            }
        }
    }

    @Override
    public void actualizar() {
        dibujarSolitario();
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