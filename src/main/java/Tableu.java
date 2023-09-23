import java.util.ArrayList;

public class Tableu {
    private final ArrayList<Columna> tableuColumnas;

    public Tableu(ArrayList<Columna> columnas) {
        this.tableuColumnas = columnas;
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
    private boolean columnaInvalida(int numColumna) {
        return !(numColumna <= tableuColumnas.size() && numColumna >= 1);
    }
    public boolean agregarCartas(int numColumna, ArrayList<Carta> cartas){
        if (columnaInvalida(numColumna)) {
            return false;
        }
        int posColumna = numColumna - 1;
        Columna columnaSeleccionada = tableuColumnas.get(posColumna);
        ArrayList<Carta> visibles = columnaSeleccionada.getCartasVisibles();
        if (visibles.isEmpty() || cartas.isEmpty()){
            columnaSeleccionada.agregarCartas(cartas);
            return true;
        }
        Carta cartaApoyada = (visibles.get(visibles.size()-1));
        if (cartaValida(cartaApoyada, cartas.get(0))) {
            columnaSeleccionada.agregarCartas(cartas);
            return true;
        }
        return false;
    }
    public ArrayList<Carta> sacarCartas(int numColumna, int cantCartas) {
        if (columnaInvalida(numColumna) || cantCartas <= 0) {
            return new ArrayList<>();
        }
        int posColumna = numColumna - 1;
        Columna columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.sacarCartas(cantCartas);
    }
}
