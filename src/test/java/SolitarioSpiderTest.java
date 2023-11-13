import org.junit.Test;

import java.util.ArrayList;

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
    public void testRepartirCartas() {
        var solitario = new SolitarioSpider(seed);

        assertTrue(solitario.pedirCarta());
    }

    @Test
    public void testMoverCartaValida() {
        var solitario = new SolitarioSpider(seed);

        boolean movimiento1 = solitario.tableuToTableu(2, 6, 1);
        boolean movimiento2 = solitario.tableuToTableu(3, 6, 1);

        assertTrue(movimiento1);
        assertTrue(movimiento2);
    }

    @Test
    public void testMoverMasDeUnaCarta() {
        var solitario = new SolitarioSpider(seed);
        solitario.tableuToTableu(2, 6, 1);
        solitario.pedirCarta();
        solitario.tableuToTableu(6, 7, 1);

        boolean movimiento1 = solitario.tableuToTableu(6, 2, 2);
        boolean movimiento2 = solitario.tableuToTableu(6, 1, 2);

        assertFalse(movimiento1);
        assertTrue(movimiento2);
    }

    @Test
    public void testSePidenTodasLasCartas() {
        var solitario = new SolitarioSpider(seed);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();

        assertFalse(solitario.pedirCarta());
    }

    @Test
    public void testIniciarJuegoEstadoParticular() {
        var stock = new Pile();
        var foundation = new Foundation(4);
        var tableu = new TableuSpider(new ArrayList<>());

        var solitario = new SolitarioSpider(stock, foundation, tableu);
    }
}