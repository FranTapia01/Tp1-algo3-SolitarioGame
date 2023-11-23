package SolitarioBase;

import java.util.ArrayList;

public interface AreaJugable {
    boolean agregarCartas(int posDestino, ArrayList<Carta> cartas);

    ArrayList<Carta> sacarCartas(int posOrigen, int cantCartas);

    ArrayList<Carta> getCartas(int posOrigen, int cantCartas);
}
