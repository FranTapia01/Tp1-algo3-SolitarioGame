import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolitarioKlondikeTest {
    public static final int seed = 2;

    @Test
    public void testJuegoIniciadoNoEstaGanado() {
        Solitario solitario = new SolitarioKlondike(seed);

        boolean juegoEstaGanado = solitario.juegoGanado();

        assertFalse(juegoEstaGanado);
    }

    @Test
    public void testMoverDelWasteAlTableu() {
        Solitario solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();

        boolean movimientoValido =
                solitario.moverCarta(Solitario.Movimiento.WASTETOTABLEU, 1, 7, 1);

        assertTrue(movimientoValido);
    }

    @Test
    public void testMovimientosFoundationValidos() {
        Solitario solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 22; i++) solitario.pedirCarta();

        boolean movimiento1 = solitario.moverCarta(Solitario.Movimiento.WASTETOFOUNDATION, 1, 3, 1);
        boolean movimiento2 = solitario.moverCarta(Solitario.Movimiento.FOUNDATIONTOFOUNDATION, 3, 1, 1);

        assertTrue(movimiento1);
        assertTrue(movimiento2);
    }

    @Test
    public void testMovimientosFoundationInvalidos() {
        Solitario solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 22; i++) solitario.pedirCarta();
        solitario.moverCarta(Solitario.Movimiento.WASTETOFOUNDATION, 1, 3, 1);
        solitario.moverCarta(Solitario.Movimiento.FOUNDATIONTOFOUNDATION, 3, 1, 1);

        boolean movimiento1 = solitario.moverCarta(Solitario.Movimiento.FOUNDATIONTOTABLEU, 1, 5, 1);
        boolean movimiento2 = solitario.moverCarta(Solitario.Movimiento.TABLEUTOFOUNDATION, 4, 4, 1);

        assertFalse(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testMoverMasDeUnaCarta() {
        Solitario solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();
        solitario.moverCarta(Solitario.Movimiento.WASTETOTABLEU, 1, 7, 1);
        for (int i = 0; i < 3; i++) solitario.pedirCarta();
        solitario.moverCarta(Solitario.Movimiento.WASTETOTABLEU, 1, 1, 1);
        for (int i = 0; i < 9; i++) solitario.pedirCarta();
        solitario.moverCarta(Solitario.Movimiento.WASTETOTABLEU, 1, 3, 1);
        for (int i = 0; i < 5; i++) solitario.pedirCarta();
        solitario.moverCarta(Solitario.Movimiento.WASTETOTABLEU, 1, 3, 1);

        boolean movimiento1 = solitario.moverCarta(Solitario.Movimiento.TABLEUTOTABLEU, 7, 6, 2);
        boolean movimiento2 = solitario.moverCarta(Solitario.Movimiento.TABLEUTOTABLEU, 1, 2, 2);

        assertTrue(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testIniciarJuegoEstadoParticular() {
        var carta = new Carta(2, Carta.Palo.TREBOL);
        var columnas = new ArrayList<ColumnaKlondike>();
        var stock = new Pile();
        stock.push(carta);
        var waste = new Pile();
        var foundation = new Foundation(4);
        var tableu = new TableuKlondike(columnas);

        var solitario = new SolitarioKlondike(stock, waste, foundation, tableu);

    }

    @Test
    public void testStockVacioSeReestockea() {
        var solitario = new SolitarioKlondike(seed);
        for (int i = 0; i < 24; i++) solitario.pedirCarta();

        assertTrue(solitario.pedirCarta());
    }
}