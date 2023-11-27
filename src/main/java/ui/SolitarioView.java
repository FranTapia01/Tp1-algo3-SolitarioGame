package ui;

import SolitarioBase.Pile;
import SolitarioBase.Solitario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
public abstract class SolitarioView {
    @FXML
    Pane cajaStock;
    @FXML
    Pane cajaFoundation;
    @FXML
    Pane cajaTableu;
    @FXML
    Pane cajaWaste;
    @FXML
    Button btnJuegoNuevo;

    protected AnchorPane ventana;
    protected Solitario solitario;

    protected Image imagenCartaRevez;

    public SolitarioView(Solitario solitario, String ruta) {
        cargarVentana(ruta);
        this.solitario = solitario;

        this.imagenCartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
    }

    public abstract void dibujarSolitario();



    protected void dibujarStock(){
        cajaStock.getChildren().clear();
        if (!solitario.getStock().isEmpty()) {
            cajaStock.getChildren().add(new ImageView(imagenCartaRevez));
        } else {
            cajaStock.getChildren().clear();
        }
    }

    protected void dibujarPile(Pile pile, Pane pane) {
        pane.getChildren().clear();
        if (!pile.isEmpty()) {
            var imagenCarta = new Image(String.valueOf(getClass().getResource("/img/"+pile.peek().getId()+".gif")));
            pane.getChildren().add(new ImageView(imagenCarta));
        }else {
            pane.getChildren().clear();
        }
    }

    protected void dibujarJuegoGanado() {
        if (solitario.juegoGanado()) {
            Text texto = new Text("¡Felicidades, Ganaste el Juego!");
            texto.setFont(new Font(40));
            texto.setTranslateY(400);
            texto.setTranslateX(150);
            ventana.getChildren().add(texto);
        }
    }



    protected void cargarVentana(String ruta) {
        var loaderKlondike = new FXMLLoader(getClass().getResource(ruta));
        loaderKlondike.setController(this);
        try {
            this.ventana = loaderKlondike.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public AnchorPane getVentana() {
        return this.ventana;
    }

    public Pane getCajaStock() {
        return cajaStock;
    }

    public Button getBtnJuegoNuevo() {
        return btnJuegoNuevo;
    }

    public int getTamanioFoundation() {
        return cajaFoundation.getChildren().size();
    }

    public int getTamanioTableu() {
        return cajaTableu.getChildren().size();
    }

}
