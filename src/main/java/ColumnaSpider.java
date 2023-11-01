import java.util.ArrayList;

public class ColumnaSpider extends Columna{

    public ColumnaSpider(Pile cartasNoVisibles) {
        super(cartasNoVisibles);
    }

    @Override
    protected boolean cartaValida(Carta cartaApoyada, Carta cartaRecibida) {
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
            Carta carta = cartasVisibles.get(cantidadCartasCompleto - i);
            if (carta.getPalo() != paloSeleccionado) return false;
            if (carta.getNumero() != i) return false;
        }
        return true;
    }
    public ArrayList<Carta> sacarColumnaCompletada() {
        if (!columnaCompleta()) {return new ArrayList<>();}
        return sacarCartas(13);
    }
}