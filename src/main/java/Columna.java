import java.util.ArrayList;

public abstract class Columna {
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
        if (cartasVisibles.isEmpty()) {
            if (cartas.get(0).getNumero() == 13) {
                cartasVisibles.addAll(cartas);
                return true;
            }
            return false;
        }
        Carta cartaApoyada = (cartasVisibles.get(cartasVisibles.size()-1));
        if (cartaValida(cartaApoyada, cartas.get(0))) {
            cartasVisibles.addAll(cartas);
            return true;
        }
        return false;
    }

    public ArrayList<Carta> sacarCartas(int cantidad) {
        if(!cantidadValida(cantidad)) {
            return new ArrayList<>();
        }
        var aux = new ArrayList<Carta>();
        for (int i = 0; i < cantidad; i++) {
            aux.add(0, cartasVisibles.get(cartasVisibles.size()-1));
            cartasVisibles.remove(cartasVisibles.size()-1);
        }
        reponerCartasVisibles();
        return aux;
    }

    public ArrayList<Carta> obtenerCartas(int cantidad) {
        if(!cantidadValida(cantidad)) {
            throw new RuntimeException("No se puede sacar esa cantidad de cartas");
        }
        var aux = new ArrayList<Carta>();
        for (int i = 1; i <= cantidad; i++) {
            aux.add(0, cartasVisibles.get(cartasVisibles.size()-i));
        }
        return aux;
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

    abstract boolean cartaValida(Carta cartaApoyada,Carta cartaRecibida);

    abstract boolean cantidadValida(int cant);
}