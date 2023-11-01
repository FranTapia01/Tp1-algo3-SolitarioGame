import java.io.Serializable;
import java.util.ArrayList;

public class Tableu<T extends Columna> implements Serializable {
    protected final ArrayList<T> tableuColumnas;

    public Tableu(ArrayList<T> columnas) {
        this.tableuColumnas = columnas;
    }

    private boolean columnaInvalida(int numColumna) {
        return !(numColumna <= tableuColumnas.size() && numColumna >= 1);
    }

    public boolean agregarCartas(int numColumna, ArrayList<Carta> cartas){
        if (columnaInvalida(numColumna)) {
            return false;
        }
        int posColumna = numColumna - 1;
        Columna columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.agregarCartas(cartas);
    }

    public ArrayList<Carta> sacarCartas(int numColumna, int cantCartas) {
        if (columnaInvalida(numColumna) || cantCartas <= 0) {
            return new ArrayList<>();
        }
        int posColumna = numColumna - 1;
        Columna columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.sacarCartas(cantCartas);
    }

    public ArrayList<Carta> obtenerCartasExpuestas(int numColumna, int cantCartas) {
        if (columnaInvalida(numColumna) || cantCartas <= 0) {
            return new ArrayList<>();
        }
        int posColumna = numColumna - 1;
        Columna columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.obtenerCartas(cantCartas);
    }
}