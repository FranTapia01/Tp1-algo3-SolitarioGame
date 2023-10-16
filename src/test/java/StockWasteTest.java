import org.junit.Test;

import static org.junit.Assert.*;

public class StockWasteTest {

    @Test
    public void testStockWasteEstaVacio() {
        Pile pile = new Pile();
        StockWaste stockWaste = new StockWaste(pile);

        assertFalse(stockWaste.pasarCarta());
        assertFalse(stockWaste.hayCartaExpuesta());
    }

    @Test
    public void testPasarCartasCorrectamente() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        StockWaste stockWaste = new StockWaste(pile);
        Carta cartaEsperada = carta1;

        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        Carta cartaExpuesta = stockWaste.verCartaExpuesta();

        assertEquals(cartaExpuesta, cartaEsperada);
    }

    @Test
    public void testSePasanTodasLasCartas() {//chequea que al pasar todas las cartas y rellenar el mazo queden en el mismo orden
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        pile.push(carta3);
        StockWaste stockWaste = new StockWaste(pile);
        Carta cartaEsperada = carta3;

        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        Carta cartaExpuesta = stockWaste.verCartaExpuesta();

        assertEquals(cartaExpuesta, cartaEsperada);
    }

    @Test
    public void testSeSacanTodasLasCartas() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        StockWaste stockWaste = new StockWaste(pile);

        for (int i = 0; i < 2; i++) {
            stockWaste.pasarCarta();
            stockWaste.sacarCarta();
        }

        assertFalse(stockWaste.pasarCarta());
        assertTrue(stockWaste.estaVacio());
    }
}