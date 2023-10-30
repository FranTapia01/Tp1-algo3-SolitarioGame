import java.util.ArrayList;

public class ColumnaSpider extends Columna{

    public ColumnaSpider(Pile cartasNoVisibles) {
        super(cartasNoVisibles);
    }

    @Override
    boolean cartaValida(Carta cartaApoyada, Carta cartaRecibida) {
        int unNumeroMenos = -1;
        return cartaApoyada.getNumero() + unNumeroMenos == cartaRecibida.getNumero();
    }

    @Override
    boolean cantidadInvalida(int cant) {
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
}