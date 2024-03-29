import SolitarioBase.*;
import SolitarioKlondike.SolitarioKlondike;
import SolitarioSpider.SolitarioSpider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.SolitarioKlondikeView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.SolitarioSpiderView;
import ui.SolitarioView;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Random;

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
    private Pane seleccionActual = null;
    private Rectangle cuadroSeleccion;
    private String rutaCompletaArchivo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        String directorioDocumentos = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        String nombreCarpetaJuego = "SolitarioGame";
        String rutaCarpetaJuego = directorioDocumentos + File.separator + nombreCarpetaJuego;
        File carpetaJuego = new File(rutaCarpetaJuego);
        if (!carpetaJuego.exists()) {
            carpetaJuego.mkdir();
        }
        String nombreArchivo = "datos.dat";
        rutaCompletaArchivo = rutaCarpetaJuego + File.separator + nombreArchivo;

        this.stage = stage;
        stage.setTitle("Solitario");
        if (!cargarJuego()) {
            mostrarMenu();
        }

        cuadroSeleccion = new Rectangle(64, 95);
        cuadroSeleccion.setStroke(Color.BLACK);
        cuadroSeleccion.setOpacity(0.3);
        stage.show();
    }

    private void mostrarMenu() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("menuInicio.fxml"));
        loader.setController(this);
        AnchorPane ventana = loader.load();
        areaOrigen = null;
        wasteSeleccionado = false;
        seleccionActual = null;
        var random = new Random();
        var semillaRandom = random.nextInt();

        opcionKlondike.setOnMouseClicked(e -> {
            solitario = new SolitarioKlondike(semillaRandom);
            solitarioView = new SolitarioKlondikeView((SolitarioKlondike) solitario);
            solitarioView.dibujarSolitario();
            mostrarJuego(solitarioView.getVentana());
        });

        opcionSpider.setOnMouseClicked(e -> {
            solitario = new SolitarioSpider(semillaRandom);
            solitarioView = new SolitarioSpiderView((SolitarioSpider) solitario);
            solitarioView.dibujarSolitario();
            mostrarJuego(solitarioView.getVentana());
        });

        var scene = new Scene(ventana, 800, 700);
        stage.setScene(scene);
    }

    private void mostrarJuego(AnchorPane ventana) {
        if (ventana.getId().equals("solitarioKlondike")) {
            //WASTE
            ((SolitarioKlondikeView)solitarioView).getCajaWaste().setOnMouseClicked(ActionEvent -> {
                wasteSeleccionado = true;
                cuadroSeleccion.setHeight(95);
                cuadroSeleccion.setTranslateY(0);
                marcarCarta(((SolitarioKlondikeView)solitarioView).getCajaWaste());
            });
            //FOUNDATION
            for (int i = 1; i <= solitarioView.getTamanioFoundation(); i++) {
                int pos = i;
                (ventana.lookup("#cajaFoundation"+i)).setOnMouseClicked(ActionEvent ->
                        manejarEvento(solitario.getFoundation(), pos, solitario, 1,(Pane)ventana.lookup("#cajaFoundation"+pos)));
            }
        }

        //STOCK
        solitarioView.getCajaStock().setOnMouseClicked(ActionEvent -> {
            solitario.pedirCarta();
            solitarioView.dibujarSolitario();
            seleccionActual = null;

        });

        //TABLEU
        for (int i = 1; i <= solitarioView.getTamanioTableu(); i++) {
            int pos = i;
            (ventana.lookup("#cajaTableu"+i)).setOnMouseClicked(ActionEvent -> {
                int cantidad = cantCartasSeleccionadas((Columna)solitario.getTableu().getTableuColumnas().get(pos-1), ActionEvent.getY());

                manejarEvento(solitario.getTableu(), pos, solitario, cantidad, (Pane)ventana.lookup("#cajaTableu"+pos));

            });
        }

        solitarioView.getBtnJuegoNuevo().setOnAction(e -> {
            try {
                mostrarMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        stage.setOnCloseRequest(event -> {
            try {
                solitario.serializar(new FileOutputStream(rutaCompletaArchivo));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Scene juegoScene = new Scene(ventana, 800, 700);
        stage.setScene(juegoScene);
    }


    private void manejarEvento(AreaJugable area, int pos, Solitario solitario, int cantCartas, Pane pane) {
        if (solitario instanceof SolitarioKlondike && wasteSeleccionado) {
            ((SolitarioKlondike)solitario).wasteToAreaJugable(area, pos);
            solitarioView.dibujarSolitario();
            wasteSeleccionado = false;
            seleccionActual = null;
            return;
        }
        if (areaOrigen == null) {
            if (cantCartas == 0) return;
            redimensionarMarca(area, pos, cantCartas);
            marcarCarta(pane);
            this.areaOrigen = area;
            this.posOrigen = pos;
            this.cantCartas = cantCartas;
        } else {
            solitario.moverCarta(areaOrigen, area, posOrigen, pos, this.cantCartas);
            seleccionActual = null;
            solitarioView.dibujarSolitario();
            this.areaOrigen = null;
            this.cantCartas = 0;
        }

    }


    private int cantCartasSeleccionadas(Columna columna, double y) {
        int margenVisible = 25;
        int margenVisibleCartasOcultas = 20;
        int altoCarta = 95;
        int yBase = columna.getCantidadCartasNoVisibles()*margenVisibleCartasOcultas;
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

    public boolean cargarJuego() throws IOException, ClassNotFoundException {
        File file = new File(rutaCompletaArchivo);
        if (!file.exists()) {
            return false;
        }
        solitario = (Solitario) Solitario.deserializar(new FileInputStream(file));
        if (solitario.juegoGanado()) {
            return false;
        }
        if (solitario instanceof SolitarioKlondike) {
            solitarioView = new SolitarioKlondikeView((SolitarioKlondike) solitario);
        }else {
            solitarioView = new SolitarioSpiderView((SolitarioSpider) solitario);
        }
        solitarioView.dibujarSolitario();
        mostrarJuego(solitarioView.getVentana());
        return true;

    }

    public void marcarCarta(Pane pane) {
        if (pane.getChildren().isEmpty()) {
            return;
        }
        if(seleccionActual == null) {
            seleccionActual = pane;
            seleccionActual.getChildren().add(cuadroSeleccion);
            return;
        }
        if (seleccionActual != pane) {
            seleccionActual.getChildren().remove(cuadroSeleccion);
            seleccionActual = pane;
            seleccionActual.getChildren().add(cuadroSeleccion);
        }
    }

    public void redimensionarMarca(AreaJugable area, int pos, int cantCartas) {
        if (cantCartas == 0) return;
        int margenVisible = 25;
        int margenVisibleCartasOcultas = 20;
        int altoCarta = 95;
        if (area == solitario.getTableu()) {
            Columna columna = (Columna) solitario.getTableu().getTableuColumnas().get(pos-1);
            int yBase = columna.getCantidadCartasNoVisibles()*margenVisibleCartasOcultas;
            int yAgregado = (columna.getCartasVisibles().size() - cantCartas)*margenVisible;
            cuadroSeleccion.setHeight((cantCartas*margenVisible)+70);
            cuadroSeleccion.setTranslateY(yBase+yAgregado);
        }else {
            cuadroSeleccion.setHeight(altoCarta);
            cuadroSeleccion.setTranslateY(0);
        }
    }
}

