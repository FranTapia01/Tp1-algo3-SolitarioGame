import org.junit.Test;

import static org.junit.Assert.*;

public class SolitarioSpiderTest {
    public static final int seed = 2;

    @Test
    public void testJuegoIniciadoNoEstaGanado() {
        var solitario = new SolitarioSpider(seed);

        boolean juegoEstaGanado = solitario.juegoGanado();

        assertFalse(juegoEstaGanado);
    }


    @Test
    public void testRepartirCartasCorrectamente() {
        var solitario = new SolitarioSpider(seed);

        assertTrue(solitario.pedirCarta());

    }

    @Test
    public void test() {
        var solitario = new SolitarioSpider(seed);


        for (int i = 1; i < 11; i++) {
            var carta = solitario.getTableu().obtenerCartasExpuestas(i, 1);
            System.out.println(carta.get(0).getNumero());
            System.out.println(carta.get(0).getPalo());

        }



    }
}