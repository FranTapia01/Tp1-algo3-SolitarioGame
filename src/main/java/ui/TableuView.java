package ui;
import SolitarioBase.Carta;
import SolitarioBase.Columna;
import SolitarioBase.Tableu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TableuView<T extends Columna> {
    private Tableu<T> tableu;

    private Pane paneMain;
    private Image cartaRevez;




    public TableuView(Tableu<T> tableu, Pane ventana) {
        this.tableu = tableu;
        this.paneMain = ventana;
        cartaRevez = new Image("file:doc/img/blue_back.gif");
    }

    public void dibujarTableu() {
        limpiarTableu();
        dibujarCartasOcultas();
        dibujarCartasVisibles();

    }

    private void dibujarCartasOcultas() {

        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            int yAgregado = 0;
            for (int j = 1; j <= tableu.getTableuColumnas().get(i-1).getCantidadCartasNoVisibles(); j++) {
                var imagenCarta = new ImageView(cartaRevez);
                imagenCarta.setTranslateY(yAgregado);
                ((Pane)paneMain.lookup("#cajaTableu"+i)).getChildren().add(imagenCarta);
                yAgregado += 25;
            }
        }
    }
    private void dibujarCartasVisibles() {

        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            int yAgregado = 0;
            var columna = tableu.getTableuColumnas().get(i-1);
            for (int j = 0; j < columna.getCantidadCartasNoVisibles(); j++) {
                yAgregado+=25;
            }
                for (Carta carta : columna.getCartasVisibles()) {


                    var imagen = new Image("file:doc/img/"+carta.getId()+".gif");
                    var imagenCarta = new ImageView(imagen);
                    imagenCarta.setTranslateY(yAgregado);
                    ((Pane)paneMain.lookup("#cajaTableu"+i)).getChildren().add(imagenCarta);
                    yAgregado += 25;
                }
        }
    }

    private void limpiarTableu() {
        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            ((Pane)paneMain.lookup("#cajaTableu"+i)).getChildren().clear();
        }
    }




}
