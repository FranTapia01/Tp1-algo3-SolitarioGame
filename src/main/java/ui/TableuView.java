package ui;
import SolitarioBase.Carta;
import SolitarioBase.Columna;
import SolitarioBase.Tableu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TableuView<T extends Columna> {
    private Tableu<T> tableu;

    private Pane ventana;
    private Image cartaRevez;
    
    public TableuView(Tableu<T> tableu, Pane ventana) {
        this.tableu = tableu;
        this.ventana = ventana;
        cartaRevez = new Image(String.valueOf(getClass().getResource("/img/blue_back.gif")));
    }

    public void dibujarTableu() {
        limpiarTableu();
        dibujarCartasOcultas();
        dibujarCartasVisibles();

    }

    private void dibujarCartasOcultas() {
        int margenVisible = 25;
        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            int yAgregado = 0;
            for (int j = 1; j <= tableu.getTableuColumnas().get(i-1).getCantidadCartasNoVisibles(); j++) {
                var imagenCarta = new ImageView(cartaRevez);
                imagenCarta.setTranslateY(yAgregado);
                ((Pane)ventana.lookup("#cajaTableu"+i)).getChildren().add(imagenCarta);
                yAgregado += margenVisible;
            }
        }
    }
    private void dibujarCartasVisibles() {
        int margenVisible = 25;
        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            int yAgregado = 0;
            var columna = tableu.getTableuColumnas().get(i-1);
            for (int j = 0; j < columna.getCantidadCartasNoVisibles(); j++) {
                yAgregado+=margenVisible;
            }
                for (Carta carta : columna.getCartasVisibles()) {


                    var imagen = new Image(String.valueOf(getClass().getResource("/img/"+carta.getId()+".gif")));
                    var imagenCarta = new ImageView(imagen);
                    imagenCarta.setTranslateY(yAgregado);
                    ((Pane)ventana.lookup("#cajaTableu"+i)).getChildren().add(imagenCarta);
                    yAgregado += margenVisible;
                }
        }
    }

    private void limpiarTableu() {
        for (int i = 1; i <= tableu.getTableuColumnas().size(); i++) {
            ((Pane)ventana.lookup("#cajaTableu"+i)).getChildren().clear();
        }
    }




}
