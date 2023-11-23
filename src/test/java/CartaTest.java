import SolitarioBase.Carta;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    @Test
    public void testNumeroCorrecto() {
        var carta = new Carta(9, Carta.Palo.DIAMANTE);
        int numeroEsperado = 9;

        int numeroCarta = carta.getNumero();

        assertEquals(numeroCarta, numeroEsperado);
    }

    @Test
    public void testPaloCorrecto() {
        var carta = new Carta(7, Carta.Palo.CORAZON);
        Carta.Palo paloEsperado = Carta.Palo.CORAZON;

        Carta.Palo paloCarta = carta.getPalo();

        assertEquals(paloCarta, paloEsperado);
    }
}