import SolitarioBase.*;
import SolitarioKlondike.SolitarioKlondike;
import SolitarioSpider.SolitarioSpider;
import javafx.scene.layout.Pane;
import ui.SolitarioKlondikeView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.SolitarioSpiderView;
import ui.SolitarioView;
import java.io.IOException;


public class Main extends Application {


    @FXML
    Pane opcionSpider;
    @FXML
    Pane opcionKlondike;


    private AreaJugable areaOrigen = null;
    private int posOrigen;
    private boolean wasteSeleccionado = false;
    private int cantCartas;
    private SolitarioView solitarioView;
    private Solitario solitario;

    private Stage stage;


    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        mostrarMenu();

    }




    private void mostrarMenu() throws IOException {
        stage.setTitle("Solitario");
        var loader = new FXMLLoader(getClass().getResource("menuInicio.fxml"));
        loader.setController(this);
        AnchorPane ventana = loader.load();

        opcionKlondike.setOnMouseClicked(e -> {
            solitario = new SolitarioKlondike(2);
            solitarioView = new SolitarioKlondikeView((SolitarioKlondike) solitario);
            solitarioView.dibujarSolitario();
            mostrarJuego(solitarioView.getVentana());

        });

        opcionSpider.setOnMouseClicked(e -> {
            solitario = new SolitarioSpider(2);
            solitarioView = new SolitarioSpiderView((SolitarioSpider) solitario);
            solitarioView.dibujarSolitario();
            mostrarJuego(solitarioView.getVentana());
        });

        var scene = new Scene(ventana, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarJuego(AnchorPane ventana) {
        if (ventana.getId().equals("solitarioKlondike")) {
            //WASTE
            ((SolitarioKlondikeView)solitarioView).getCajaWaste().setOnMouseClicked(ActionEvent -> {
                wasteSeleccionado = true;
            });
            //FOUNDATION
            for (int i = 1; i <= solitarioView.getTamanioFoundation(); i++) {
                int pos = i;
                (ventana.lookup("#cajaFoundation"+i)).setOnMouseClicked(ActionEvent -> {
                    manejarEvento(solitario.getFoundation(), pos, solitario, 1);
                });
            }
        }

        //STOCK
        solitarioView.getCajaStock().setOnMouseClicked(ActionEvent -> {
            solitario.pedirCarta();
            solitarioView.dibujarSolitario();
        });

        //TABLEU
        for (int i = 1; i <= solitarioView.getTamanioTableu(); i++) {
            int pos = i;
            (ventana.lookup("#cajaTableu"+i)).setOnMouseClicked(ActionEvent -> {
                int cantidad = cantCartasSeleccionadas((Columna)solitario.getTableu().getTableuColumnas().get(pos-1), ActionEvent.getY());
                manejarEvento(solitario.getTableu(), pos, solitario, cantidad);
            });
        }
        Scene juegoScene = new Scene(ventana, 800, 700);
        stage.setScene(juegoScene);
    }


    private void manejarEvento(AreaJugable area, int pos, Solitario solitario, int cantCartas) {
        if (solitario instanceof SolitarioKlondike && wasteSeleccionado) {
            ((SolitarioKlondike)solitario).moverCarta(area, pos);
            wasteSeleccionado = false;
            solitarioView.dibujarSolitario();
            return;
        }
        if (areaOrigen == null) {
            this.areaOrigen = area;
            this.posOrigen = pos;
            this.cantCartas = cantCartas;
        } else {
            solitario.moverCarta(areaOrigen, area, posOrigen, pos, this.cantCartas);
            this.areaOrigen = null;
            this.cantCartas = 0;
        }
        solitarioView.dibujarSolitario();
    }



    private int cantCartasSeleccionadas(Columna columna, double y) {
        int margenVisible = 25;
        int altoCarta = 95;
        int yBase = columna.getCantidadCartasNoVisibles()*margenVisible;
        int cantCartas = columna.getCartasVisibles().size();
        for (int i = 0; i < cantCartas; i++) {
            int yTope = yBase+margenVisible;
            if (i == cantCartas-1) {
                yTope = yBase+altoCarta;
            }
            if (y > yBase && y < yTope) {
                return cantCartas-i;
            }
            yBase = yTope;
        }
        return 0;


    }
}

//PARA DIBUJAR EL RECTANGULO DE MARCAR CARTA SELECCIONADA
//Rectangle rect = new Rectangle(65, 95);
//rect.setStroke(Color.BLACK);
//rect.setOpacity(0.3);