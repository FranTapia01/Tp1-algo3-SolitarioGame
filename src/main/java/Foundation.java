import java.util.ArrayList;
import java.util.stream.IntStream;

public class Foundation {
    private final ArrayList<Pile> foundationsPiles; // 4 piles

    private boolean cartaValida(Carta cartaApoyada,Carta cartaRecibida){
        int unNumeroExtra = 1;
        if (cartaApoyada.getPalo() != cartaRecibida.getPalo()){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroExtra == cartaRecibida.getNumero();
    }

    public Foundation(){
        this.foundationsPiles =  new ArrayList<>();
        int cantidadPiles = 4;
        IntStream.range(0, cantidadPiles).forEach(i -> this.foundationsPiles.add(new Pile()));
    }

    public boolean agregarCarta(Carta cartaRecibida ,int numFoundation ){
        int vacio = 0;
        int primerNumero = 1;
        if (numFoundation > foundationsPiles.size()){
            return false;
        }
        numFoundation -= 1; // para corregir el indice en la lista
        Pile pile = foundationsPiles.get(numFoundation);
        if (pile.getCantidadCartas() == vacio){
            if(cartaRecibida.getNumero() != primerNumero) {
                return false;
            }
        }else if (!cartaValida(pile.peek(),cartaRecibida)){
            return false;
        }
        pile.push(cartaRecibida);
        return true;
    }

    public Carta sacarCarta(int numPile){
        int vacio = 0;
        if (numPile > foundationsPiles.size()){
            throw new RuntimeException("Numero de FoundationPile no existente");
        }
        numPile -= 1; // para corregir el indice en la lista
        Pile pile = foundationsPiles.get(numPile);
        if (pile.getCantidadCartas() == vacio){
            throw new RuntimeException("FoundationPile vacio");
        }
        return pile.pop();
    }

    public boolean estaCompleta(){
    int pileCompleta = 13;
        for (Pile pile : foundationsPiles) {
            if (pile.getCantidadCartas() < pileCompleta ) return false;
        }
        return true;
    }
}
