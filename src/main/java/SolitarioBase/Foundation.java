package SolitarioBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Foundation implements AreaJugable ,Serializable {
    private final ArrayList<Pile> foundationsPiles; // 4 piles

    public Foundation(int cantPiles){
        this.foundationsPiles =  new ArrayList<>();
        IntStream.range(0, cantPiles).forEach(i -> this.foundationsPiles.add(new Pile()));
    }

    private boolean cartaValida(Carta cartaApoyada,Carta cartaRecibida){
        int unNumeroExtra = 1;
        if (cartaApoyada.getPalo() != cartaRecibida.getPalo()){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroExtra == cartaRecibida.getNumero();
    }






    public boolean estaCompleta(){
    int pileCompleta = 13;
        for (Pile pile : foundationsPiles) {
            if (pile.getCantidadCartas() < pileCompleta ) return false;
        }
        return true;
    }

    public Pile getPileFoundation(int numPile) {
        numPile -= 1;
        return foundationsPiles.get(numPile);

    }

    @Override
    public boolean agregarCartas(int posDestino ,ArrayList<Carta> cartas) {
        if (posDestino > foundationsPiles.size() || cartas.size() != 1){
            return false;
        }
        Carta cartaRecibida = cartas.get(0);
        int vacio = 0;
        int primerNumero = 1;
        posDestino -= 1; // para corregir el indice en la lista
        Pile pile = foundationsPiles.get(posDestino);
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

    @Override
    public ArrayList<Carta> sacarCartas(int posFoundation, int cantidad) {
        var cartaDevuelta = new ArrayList<Carta>();
        if (posFoundation > foundationsPiles.size()){
            throw new RuntimeException("Numero de FoundationPile no existente");
        }
        posFoundation -= 1; // para corregir el indice en la lista
        Pile pile = foundationsPiles.get(posFoundation);
        if (!pile.isEmpty()) cartaDevuelta.add(pile.pop());
        return cartaDevuelta;
    }


    @Override
    public ArrayList<Carta> getCartas(int posFoundation, int cantidad) {
        var cartaDevuelta = new ArrayList<Carta>();
        if (posFoundation > foundationsPiles.size()){
            throw new RuntimeException("Numero de FoundationPile no existente");
        }
        posFoundation -= 1; // para corregir el indice en la lista
        Pile pile = foundationsPiles.get(posFoundation);
        if (!pile.isEmpty()) cartaDevuelta.add(pile.peek());
        return cartaDevuelta;
    }
}
