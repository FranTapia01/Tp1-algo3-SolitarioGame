import org.junit.Test;

import static org.junit.Assert.*;

public class PileTest {

    @Test
    public void test1() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);

        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        pile.push(carta3);

        assertEquals(pile.getCantidadCartas(), 3);
        assertEquals(pile.pop(), carta3);
        assertEquals(pile.peek(), carta2);
    }
}