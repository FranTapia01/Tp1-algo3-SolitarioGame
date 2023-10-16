import java.util.ArrayList;

public class Columna {
    private final Pile cartasNoVisibles;
    private final ArrayList<Carta> cartasVisibles;

    public Columna(Pile cartasNoVisibles) {
        this.cartasNoVisibles = cartasNoVisibles;
        this.cartasVisibles = new ArrayList<>();
        reponerCartasVisibles();
    }

    private boolean paloContrario(Carta a, Carta b) {
        if (a.getPalo() == Carta.Palo.PICA || a.getPalo() == Carta.Palo.TREBOL){
            return b.getPalo() == Carta.Palo.DIAMANTE || b.getPalo() == Carta.Palo.CORAZON;
        }
        return b.getPalo() == Carta.Palo.TREBOL || b.getPalo() == Carta.Palo.PICA;
    }

    private boolean cartaValida(Carta cartaApoyada,Carta cartaRecibida){
        int unNumeroMenos = -1;
        if (!paloContrario(cartaApoyada, cartaRecibida)){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroMenos == cartaRecibida.getNumero();
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

    public ArrayList<Carta> obtenerCartas(int cantidad) {
        if(cantidad > cartasVisibles.size()) {
            throw new RuntimeException("No se puede sacar esa cantidad de cartas");
        }
        var aux = new ArrayList<Carta>();
        for (int i = 0; i < cantidad; i++) {
            aux.add(0, cartasVisibles.get(cartasVisibles.size()-1));
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
}
