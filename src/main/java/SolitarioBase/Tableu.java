package SolitarioBase;

import java.io.Serializable;
import java.util.ArrayList;

public class Tableu<T extends Columna> implements AreaJugable ,Serializable {
    protected final ArrayList<T> tableuColumnas;

    public Tableu(ArrayList<T> columnas) {
        this.tableuColumnas = columnas;
    }

    private boolean columnaInvalida(int numColumna) {
        return !(numColumna <= tableuColumnas.size() && numColumna >= 1);
    }

    @Override
    public boolean agregarCartas(int posDestino ,ArrayList<Carta> cartas) {
        if (columnaInvalida(posDestino)) {
            return false;
        }
        int posColumna = posDestino - 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.agregarCartas(cartas);
    }

    @Override
    public ArrayList<Carta> sacarCartas(int posOrigen, int cantidad) {
        if (columnaInvalida(posOrigen) || cantidad <= 0) {
            return new ArrayList<>();
        }
        int posColumna = posOrigen - 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.sacarCartas(cantidad);
    }

    @Override
    public ArrayList<Carta> getCartas(int posOrigen, int cantidad) {
        if (columnaInvalida(posOrigen) || cantidad <= 0) {
            return new ArrayList<>();
        }
        int posColumna = posOrigen - 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.obtenerCartas(cantidad);
    }

    public ArrayList<T> getTableuColumnas() {
        return tableuColumnas;
    }
}