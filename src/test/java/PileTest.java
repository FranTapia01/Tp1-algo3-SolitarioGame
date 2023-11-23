import SolitarioBase.Carta;
import SolitarioBase.Pile;
import org.junit.Test;

import static org.junit.Assert.*;

public class PileTest {

    @Test
    public void testCantidadCartasAgregadas() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var pile = new Pile();
        int cantidadEsperada = 2;

        pile.push(carta1);
        pile.push(carta2);
        int cantidadAgregadas = pile.getCantidadCartas();

        assertEquals(cantidadAgregadas, cantidadEsperada);
    }

    @Test
    public void testCantidadCartasSacadas() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        int cantidadEsperada = 1;

        pile.pop();
        int cantidadRestante = pile.getCantidadCartas();
        
        assertEquals(cantidadRestante, cantidadEsperada);
    }
}