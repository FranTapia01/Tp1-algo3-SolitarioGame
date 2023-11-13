import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Solitario<T extends Columna> implements Serializable{
    protected Tableu<T> tableu;
    protected Foundation foundation;
    protected Pile stock;

    public Solitario() {

    }
    public Solitario(Pile stock, Foundation foundation, Tableu<T> tableu) {
        this.stock = stock;
        this.foundation = foundation;
        this.tableu = tableu;
    }

    abstract boolean pedirCarta();

    public boolean juegoGanado() {
        return this.foundation.estaCompleta();
    }

    public boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumnaOrigen, cantCartas);
        if (!(this.tableu.agregarCartas(numColumnaDestino, cartas))) {return false;}
        this.tableu.sacarCartas(numColumnaOrigen, cantCartas);
        return true;
    }

    protected Pile crearStock(int cantBarajas ,int seed){
        int cantCartasDeUnPalo = 13;
        Pile stock = new Pile();
        for (int i = 0; i < cantBarajas; i++) {
            for(Carta.Palo palo: Carta.Palo.values()) {
                for (int j = 1; j <= cantCartasDeUnPalo; j++) {
                    Carta carta = new Carta(j, palo);
                    stock.push(carta);
                }
            }
        }
        var rnd = new Random(seed);
        Collections.shuffle(stock, rnd);
        return stock;
    }

    public void serializar(ObjectOutputStream output) throws IOException {
        output.writeObject(this);
        output.close();
    }

    public static Object deserializar(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Solitario object = (Solitario) input.readObject();
        input.close();
        return object;
    }
}