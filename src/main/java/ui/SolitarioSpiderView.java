package ui;

import SolitarioSpider.ColumnaSpider;
import SolitarioSpider.SolitarioSpider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;


public class SolitarioSpiderView implements SolitarioView {
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

        this.tableuView = new TableuView(solitario.getTableu(), ventana);
        this.imagenCartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarFoundation();
        tableuView.dibujarTableu();
        dibujarJuegoGanado();
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

    private void dibujarJuegoGanado() {
        if (solitario.juegoGanado()) {
            Text texto = new Text("¡Felicidades, Ganaste el Juego!");
            texto.setFont(new Font(30));
            texto.setTranslateY(400);
            texto.setTranslateX(150);
            ventana.getChildren().add(texto);
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