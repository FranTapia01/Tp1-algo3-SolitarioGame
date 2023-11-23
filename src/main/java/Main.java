import SolitarioBase.*;
import SolitarioKlondike.SolitarioKlondike;
import SolitarioSpider.SolitarioSpider;
import javafx.scene.layout.Pane;
import ui.SolitarioKlondikeView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.SolitarioSpiderView;
import ui.SolitarioView;
import java.io.IOException;


public class Main extends Application {

    @FXML
    Pane cajaStock;
    @FXML
    Pane cajaWaste;
    @FXML
    Pane cajaFoundation;
    @FXML
    Pane cajaTableu;
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
            var loaderKlondike = new FXMLLoader(getClass().getResource("tableroKlondikeNuevo.fxml"));
            loaderKlondike.setController(this);
            AnchorPane ventanaKlondike;
            try {
                ventanaKlondike = loaderKlondike.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            solitarioView = new SolitarioKlondikeView((SolitarioKlondike) solitario, ventanaKlondike);
            solitarioView.dibujarSolitario();
            mostrarJuego(ventanaKlondike);

        });

        opcionSpider.setOnMouseClicked(e -> {
            solitario = new SolitarioSpider(2);
            var loaderSpider = new FXMLLoader(getClass().getResource("tableroSpiderNuevo.fxml"));
            loaderSpider.setController(this);
            AnchorPane ventanaSpider;

            try {
                ventanaSpider = loaderSpider.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            solitarioView = new SolitarioSpiderView((SolitarioSpider) solitario, ventanaSpider);
            solitarioView.dibujarSolitario();
            mostrarJuego(ventanaSpider);

        });
        var scene = new Scene(ventana, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarJuego(AnchorPane ventana) {
        if (ventana.getId().equals("solitarioKlondike")) {
            //WASTE
            cajaWaste.setOnMouseClicked(ActionEvent -> {
                wasteSeleccionado = true;
            });
            //FOUNDATION
            int numChildren = cajaFoundation.getChildren().size();
            for (int i = 0; i < numChildren; i++) {
                int pos = i+1;
                javafx.scene.Node node = cajaFoundation.getChildren().get(i);
                if (node instanceof Pane pane) {
                    pane.setOnMouseClicked(ActionEvent -> {
                        manejarEvento(solitario.getFoundation(), pos, solitario, 1);
                    });
                }
            }
        }

        //STOCK
        ventana.lookup("#cajaStock").setOnMouseClicked(ActionEvent -> {
            solitario.pedirCarta();
            solitarioView.dibujarSolitario();
        });

        //TABLEU
        int num = cajaTableu.getChildren().size();
        for (int i = 1; i <= num; i++) {
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
        int yBase = columna.getCantidadCartasNoVisibles()*25;
        int cantCartas = columna.getCartasVisibles().size();
        for (int i = 0; i < cantCartas; i++) {
            int yTope = yBase+25;
            if (i == cantCartas-1) {
                yTope = yBase+95;
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