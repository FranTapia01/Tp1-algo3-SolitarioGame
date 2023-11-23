package ui;
import SolitarioBase.Pile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PileView {
    private Pile pileADibujar;
    private ImageView imagenCarta;

    public PileView(Pile pile) {
        this.pileADibujar = pile;
    }

    public void dibujarPile(Pane pane){
        cargarImagen();
        if (!pileADibujar.isEmpty()) {
            pane.getChildren().add(imagenCarta);
        } else {
            pane.getChildren().clear();
        }
    }

    private void cargarImagen() {
        if (!pileADibujar.isEmpty()) {
            var imagen = new Image("file:doc/img/"+pileADibujar.peek().getId()+".gif");
            this.imagenCarta = new ImageView(imagen);
        }
    }
}
