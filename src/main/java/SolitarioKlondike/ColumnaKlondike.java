package SolitarioKlondike;

import SolitarioBase.Carta;
import SolitarioBase.Columna;
import SolitarioBase.Pile;

public class ColumnaKlondike extends Columna {

    public ColumnaKlondike(Pile cartasNoVisibles) {
        super(cartasNoVisibles);
    }

    private boolean paloContrario(Carta a, Carta b) {
        if (a.getPalo() == Carta.Palo.PICA || a.getPalo() == Carta.Palo.TREBOL){
            return b.getPalo() == Carta.Palo.DIAMANTE || b.getPalo() == Carta.Palo.CORAZON;
        }
        return b.getPalo() == Carta.Palo.TREBOL || b.getPalo() == Carta.Palo.PICA;
    }

    @Override
    protected boolean cartaValida(Carta cartaRecibida) {
        if (cartasVisibles.isEmpty()) {
            return cartaRecibida.getNumero() == 13;
        }
        var cartaApoyada = cartasVisibles.get(cartasVisibles.size()-1);
        int unNumeroMenos = -1;
        if (!paloContrario(cartaApoyada, cartaRecibida)){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroMenos == cartaRecibida.getNumero();
    }

    @Override
    protected boolean cantidadValida(int cant) {
        return cant <= cartasVisibles.size();
    }
}