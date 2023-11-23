package SolitarioBase;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Columna implements Serializable {
    protected final Pile cartasNoVisibles;
    protected final ArrayList<Carta> cartasVisibles;

    public Columna(Pile cartasNoVisibles) {
        this.cartasNoVisibles = cartasNoVisibles;
        this.cartasVisibles = new ArrayList<>();
        reponerCartasVisibles();
    }

    public boolean agregarCartas(ArrayList<Carta> cartas) {
        if (cartas.isEmpty()) {
            return true;
        }
        if (cartaValida(cartas.get(0))) {
            cartasVisibles.addAll(cartas);
            return true;
        }
        return false;
    }

    public ArrayList<Carta> sacarCartas(int cantidad) {
        if(cantidadValida(cantidad)) {
            var aux = new ArrayList<Carta>();
            for (int i = 0; i < cantidad; i++) {
                aux.add(0, cartasVisibles.get(cartasVisibles.size()-1));
                cartasVisibles.remove(cartasVisibles.size()-1);
            }
            reponerCartasVisibles();
            return aux;
        }
        return new ArrayList<>();

    }

    public ArrayList<Carta> obtenerCartas(int cantidad) {
        if(cantidadValida(cantidad)) {
            var aux = new ArrayList<Carta>();
            for (int i = 1; i <= cantidad; i++) {
                aux.add(0, cartasVisibles.get(cartasVisibles.size()-i));
            }
            return aux;
        }
        return new ArrayList<>();
    }

    public ArrayList<Carta> getCartasVisibles() {
        return cartasVisibles;
    }

    private void reponerCartasVisibles() {
        if(cartasVisibles.isEmpty() && !cartasNoVisibles.isEmpty()) {
            cartasVisibles.add(cartasNoVisibles.pop());
        }
    }

    public int getCantidadCartasNoVisibles() {
        return cartasNoVisibles.size();
    }

    protected abstract boolean cartaValida(Carta cartaRecibida);

    protected abstract boolean cantidadValida(int cant);
}