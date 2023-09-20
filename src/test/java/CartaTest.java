import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {

    @Test
    public void Test1() {

        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);

        assertEquals(carta1.getNumero(), 9);
        assertEquals(carta2.getPalo(), Carta.Palo.PICA);
    }
}