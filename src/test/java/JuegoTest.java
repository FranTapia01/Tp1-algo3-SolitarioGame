import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JuegoTest {
    public static final int seed = 2;

    @Test
    public void testJuegoIniciadoNoEstaGanado() {
        Juego juego = new Juego(seed);

        boolean juegoEstaGanado = juego.juegoGanado();

        assertFalse(juegoEstaGanado);
    }

    @Test
    public void testDealValido() {//deal = mover del stock al tableu
        Juego juego = new Juego(seed);
        for (int i = 0; i < 5; i++) juego.pasarCarta();

        boolean movimientoValido = juego.stockWasteToTableu(7);

        assertTrue(movimientoValido);
    }

    @Test
    public void testMovimientosFoundationValidos() {
        Juego juego = new Juego(seed);
        for (int i = 0; i < 22; i++) juego.pasarCarta();

        boolean movimiento1 = juego.stockWasteToFoundation(3);
        boolean movimiento2 = juego.foundationToFoundation(3, 1);

        assertTrue(movimiento1);
        assertTrue(movimiento2);
    }

    @Test
    public void testMovimientosFoundationInvalidos() {
        Juego juego = new Juego(seed);
        for (int i = 0; i < 22; i++) juego.pasarCarta();
        juego.stockWasteToFoundation(3);
        juego.foundationToFoundation(3, 1);

        boolean movimiento1 = juego.foundationToTableu(1, 5);
        boolean movimiento2 = juego.tableuToFoundation(4, 4);
        //boolean movimiento3 = juego.foundationToFoundation()

        assertFalse(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testMoverMasDeUnaCarta() {
        Juego juego = new Juego(seed);
        for (int i = 0; i < 5; i++) juego.pasarCarta();
        juego.stockWasteToTableu(7);
        for (int i = 0; i < 3; i++) juego.pasarCarta();
        juego.stockWasteToTableu(1);
        for (int i = 0; i < 9; i++) juego.pasarCarta();
        juego.stockWasteToTableu(3);
        for (int i = 0; i < 5; i++) juego.pasarCarta();
        juego.stockWasteToTableu(3);

        boolean movimiento1 = juego.tableuToTableu(7, 6, 2);
        boolean movimiento2 = juego.tableuToTableu(1, 2, 2);

        assertTrue(movimiento1);
        assertFalse(movimiento2);
    }

    @Test
    public void testIniciarJuegoEstadoParticular() {
        ArrayList<Columna> columnas = new ArrayList<>();
        Pile stock = new Pile();
        Foundation foundation = new Foundation();
        Tableu tableu = new Tableu(columnas);
        StockWaste stockWaste = new StockWaste(stock);
        Juego juego = new Juego(foundation, tableu, stockWaste);
    }


}