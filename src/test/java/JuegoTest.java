import org.junit.Test;

import static org.junit.Assert.*;

public class JuegoTest {
    @Test
    public void Test1() {
        Juego juego = new Juego(Juego.EstadoDeInicializacion.CASITERMINADO);

        juego.tableuToFoundation(1, 4);

        assertTrue(juego.juegoGanado());
    }

    @Test
    public void Test2() {
        Juego juego = new Juego(Juego.EstadoDeInicializacion.PREDECIBLE);
        for (int i = 0; i < 5; i++) juego.pasarCarta();

        assertTrue(juego.stockWasteToTableu(7));
        assertFalse(juego.juegoGanado());
    }

    @Test
    public void Test3() {
        Juego juego = new Juego(Juego.EstadoDeInicializacion.PREDECIBLE);
        for (int i = 0; i < 22; i++) juego.pasarCarta();

        assertTrue(juego.stockWasteToFoundation(3));
        assertTrue(juego.foundationToFoundatiom(3, 1));
        assertFalse(juego.foundationToTableu(1, 5));
        assertFalse(juego.tableuToFoundation(4, 4));
    }

    @Test
    public void Test4() {
        Juego juego = new Juego(Juego.EstadoDeInicializacion.PREDECIBLE);
        for (int i = 0; i < 5; i++) juego.pasarCarta();
        juego.stockWasteToTableu(7);
        for (int i = 0; i < 3; i++) juego.pasarCarta();
        juego.stockWasteToTableu(1);
        for (int i = 0; i < 9; i++) juego.pasarCarta();
        juego.stockWasteToTableu(3);
        for (int i = 0; i < 5; i++) juego.pasarCarta();
        juego.stockWasteToTableu(3);

        assertTrue(juego.tableuToTableu(7, 6, 2));
        assertFalse(juego.tableuToTableu(1, 2, 2));
    }

    @Test
    public void Test5() {
        Juego juego = new Juego(Juego.EstadoDeInicializacion.RANDOM);
        assertFalse(juego.juegoGanado());
    }
}