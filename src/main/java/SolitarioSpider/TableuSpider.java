package SolitarioSpider;

import SolitarioBase.Carta;
import SolitarioBase.Tableu;

import java.util.ArrayList;

public class TableuSpider extends Tableu<ColumnaSpider> {
    public TableuSpider(ArrayList<ColumnaSpider> columnas) {super(columnas);}

    public ArrayList<Carta> hayColumnaCompleta(int numColumna) {
        var posColumna = numColumna - 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.sacarColumnaCompletada();
    }

    public boolean repartirCartas(ArrayList<Carta> cartas) {
        var tamanioTableu = tableuColumnas.size();
        if (cartas.size() != tamanioTableu) {
            return false;
        }
        for (int i = 0; i < tamanioTableu; i++) {
            ColumnaSpider columnaSeleccionada = tableuColumnas.get(i);
            columnaSeleccionada.agregarCartaSinValidar(cartas.get(i));
        }
        return true;
    }
}
