import java.util.ArrayList;

public class Columna {
    private final Pile cartasNoVisibles;
    private final ArrayList<Carta> cartasVisibles;

    public Columna(Pile cartasNoVisibles) {
        this.cartasNoVisibles = cartasNoVisibles;
        this.cartasVisibles = new ArrayList<>();
        reponerCartasVisibles();
    }

    public void agregarCartas(ArrayList<Carta> cartas) {
        cartasVisibles.addAll(cartas);
    }

    public ArrayList<Carta> sacarCartas(int cantidad) {
        if(cantidad > cartasVisibles.size()) {
            throw new RuntimeException("No se puede sacar esa cantidad de cartas");
        }
        var aux = new ArrayList<Carta>();
        for (int i = 0; i < cantidad; i++) {
            aux.add(0, cartasVisibles.get(cartasVisibles.size()-1));
            cartasVisibles.remove(cartasVisibles.size()-1);
        }
        reponerCartasVisibles();
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
}
