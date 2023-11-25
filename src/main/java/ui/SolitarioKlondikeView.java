package ui;

import SolitarioBase.Pile;
import SolitarioBase.SolitarioObserver;
import SolitarioKlondike.ColumnaKlondike;
import SolitarioKlondike.SolitarioKlondike;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class SolitarioKlondikeView implements SolitarioView, SolitarioObserver {
    @FXML
    Pane cajaStock;
    @FXML
    Pane cajaFoundation;
    @FXML
    Pane cajaTableu;
    @FXML
    Pane cajaWaste;

    AnchorPane ventana;
    SolitarioKlondike solitario;
    TableuView<ColumnaKlondike> tableuView;
    Image imagenCartaRevez;

    public SolitarioKlondikeView(SolitarioKlondike solitario) {
        cargarVentana();
        this.solitario = solitario;
        solitario.agregarObservador(this);
        this.tableuView = new TableuView(solitario.getTableu(), ventana);
        this.imagenCartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
    }

    public void dibujarSolitario() {
        dibujarStock();
        dibujarPile(solitario.getWaste(), cajaWaste);
        for (int i = 1; i <= 4; i++) {
            dibujarPile(solitario.getFoundation().getPileFoundation(i), (Pane) ventana.lookup("#cajaFoundation"+i));
        }
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

    private void dibujarPile(Pile pile, Pane pane) {
        if (!pile.isEmpty()) {
            var imagenCarta = new Image(String.valueOf(getClass().getResource("/img/"+pile.peek().getId()+".gif")));
            pane.getChildren().add(new ImageView(imagenCarta));
        }else {
            pane.getChildren().clear();
        }
    }

    private void dibujarJuegoGanado() {
        /*
        if (solitario.juegoGanado()) {
            Text texto = new Text("¡Felicidades, Ganaste el Juego!");
            texto.setFont(new Font(40));
            texto.setTranslateY(400);
            texto.setTranslateX(400);
            ventana.getChildren().add(texto);
        }*/
    }

    @Override
    public void actualizar() {
        dibujarSolitario();
    }

    private void cargarVentana() {
        var loaderKlondike = new FXMLLoader(getClass().getResource("/tableroKlondikeNuevo.fxml"));
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

    public Pane getCajaWaste() {
        return cajaWaste;
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