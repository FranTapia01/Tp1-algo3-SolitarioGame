import org.junit.Test;

import static org.junit.Assert.*;

public class MontonTest {

    @Test
    public void test1() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);

        var monton = new Monton();
        monton.push(carta1);
        monton.push(carta2);
        monton.push(carta3);

        assertEquals(monton.getCantidadCartas(), 3);
        assertEquals(monton.pop(), carta3);
        assertEquals(monton.peek(), carta2);
    }
}