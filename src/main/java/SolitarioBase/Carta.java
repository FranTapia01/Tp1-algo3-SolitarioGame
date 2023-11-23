package SolitarioBase;

import java.io.Serializable;

public class Carta implements Serializable {
    private final int numero;
    private final Palo palo;

    public enum Palo {
        PICA, TREBOL, DIAMANTE, CORAZON
    }

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public String getId() {
        char primeraLetra = this.palo.name().charAt(0);
        return String.valueOf(this.numero) + primeraLetra;
    }
}