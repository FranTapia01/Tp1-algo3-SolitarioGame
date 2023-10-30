

public class ColumnaKlondike extends Columna{

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
    boolean cartaValida(Carta cartaApoyada, Carta cartaRecibida) {
        int unNumeroMenos = -1;
        if (!paloContrario(cartaApoyada, cartaRecibida)){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroMenos == cartaRecibida.getNumero();
    }

    @Override
    boolean cantidadValida(int cant) {
        return cant <= cartasVisibles.size();
    }
}