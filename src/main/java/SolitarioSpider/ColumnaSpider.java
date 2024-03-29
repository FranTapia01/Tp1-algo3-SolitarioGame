package SolitarioSpider;

import SolitarioBase.Carta;
import SolitarioBase.Columna;
import SolitarioBase.Pile;

import java.util.ArrayList;

public class ColumnaSpider extends Columna {

    public ColumnaSpider(Pile cartasNoVisibles) {
        super(cartasNoVisibles);
    }

    @Override
    protected boolean cartaValida(Carta cartaRecibida) {
        if (cartasVisibles.isEmpty()) return true;
        var cartaApoyada = cartasVisibles.get(cartasVisibles.size()-1);
        int unNumeroMenos = -1;
        return cartaApoyada.getNumero() + unNumeroMenos == cartaRecibida.getNumero();
    }

    @Override
    protected boolean cantidadValida(int cant) {
        int tamanio = cartasVisibles.size();
        if (cant > tamanio) return false;
        var cartasSolicitadas = new ArrayList<Carta>();
        for (int i = 1; i <= cant; i++) {
            cartasSolicitadas.add(cartasVisibles.get(tamanio-i));
        }
        var paloSeleccionado = cartasSolicitadas.get(0).getPalo();
        for (Carta carta: cartasSolicitadas) {
            if (carta.getPalo() != paloSeleccionado) return false;
        }
        return true;
    }

    public boolean columnaCompleta() {
        int tamanio = cartasVisibles.size();
        int cantidadCartasCompleto = 13;
        if (tamanio < cantidadCartasCompleto) return false;

        var paloSeleccionado = cartasVisibles.get(cantidadCartasCompleto-1).getPalo();

        for (int i = 1; i <= cantidadCartasCompleto; i++) {
            Carta carta = cartasVisibles.get(tamanio - i);
            if (carta.getPalo() != paloSeleccionado) return false;
            if (carta.getNumero() != i) return false;
        }
        return true;
    }
    public ArrayList<Carta> sacarColumnaCompletada() {
        if (!columnaCompleta()) {return new ArrayList<>();}
        return sacarCartas(13);
    }

    public void agregarCartaSinValidar(Carta carta) {
        cartasVisibles.add(carta);
    }
}