import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolitarioKlondikeTest {
    public static final int seed = 2;

    @Test
    public void testJuegoIniciadoNoEstaGanado() {
        var solitario = new SolitarioKlondike(seed);

        boolean juegoEstaGanado = solitario.juegoGanado();

        assertFalse(juegoEstaGanado);
    }

    @Test
    public void testMoverDelWasteAlTableu() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();

        boolean movimientoValido = solitario.wasteToTableu(7);

        assertTrue(movimientoValido);
    }

    @Test
    public void testMovimientosFoundationValidos() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 22; i++) solitario.pedirCarta();


        boolean movimiento1 = solitario.wasteToFoundation(3);
        boolean movimiento2 = solitario.foundationToFoundation(3, 1);

        assertTrue(movimiento1);
        assertTrue(movimiento2);
    }

    @Test
    public void testMovimientosFoundationInvalidos() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 22; i++) solitario.pedirCarta();
        solitario.wasteToFoundation(3);
        solitario.foundationToFoundation(3, 1);

        boolean movimiento1 = solitario.foundationToTableu(1, 5);
        boolean movimiento2 = solitario.tableuToFoundation(4, 4);

        assertFalse(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testMoverMasDeUnaCarta() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();
        solitario.wasteToTableu(7);
        for (int i = 0; i < 3; i++) solitario.pedirCarta();
        solitario.wasteToTableu(1);
        for (int i = 0; i < 9; i++) solitario.pedirCarta();
        solitario.wasteToTableu(3);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();
        solitario.wasteToTableu(3);

        boolean movimiento1 = solitario.tableuToTableu(7, 6, 2);
        boolean movimiento2 = solitario.tableuToTableu(1, 2, 2);

        assertTrue(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testIniciarJuegoEstadoParticular() {
        var stock = new Pile();
        var waste = new Pile();
        var foundation = new Foundation(4);
        var tableu = new TableuKlondike(new ArrayList<>());

        var solitario = new SolitarioKlondike(stock, waste, foundation, tableu);
    }

    @Test
    public void testStockVacioSeReestockea() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 24; i++) solitario.pedirCarta();

        assertTrue(solitario.pedirCarta());
    }
}