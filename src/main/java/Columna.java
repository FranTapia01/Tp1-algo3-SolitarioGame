import java.util.ArrayList;

public class Columna extends Monton{
    private Monton cartasNoVisibles;
    private ArrayList<Carta> cartasVisibles = new ArrayList<>();

    public Columna(Monton cartasNoVisibles, Carta cartaVisible) {
        this.cartasNoVisibles = cartasNoVisibles;
        this.cartasVisibles.add(cartaVisible);
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

    public void reponerCartasVisibles() {
        if(cartasVisibles.isEmpty() && !cartasNoVisibles.isEmpty()) {
            cartasVisibles.add(cartasNoVisibles.pop());
        }
    }
}
