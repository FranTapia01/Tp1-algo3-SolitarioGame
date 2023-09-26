import org.junit.Test;

import static org.junit.Assert.*;

public class StockWasteTest {

    @Test
    public void Test1() {
        Pile monton = new Pile();
        StockWaste stockWaste = new StockWaste(monton);

        assertFalse(stockWaste.pasarCarta());
        assertFalse(stockWaste.hayCartaExpuesta());
    }

    @Test
    public void Test2() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);

        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        pile.push(carta3);

        StockWaste stockWaste = new StockWaste(pile);
        stockWaste.pasarCarta();
        assertEquals(stockWaste.verCartaExpuesta(), stockWaste.sacarCarta());
    }

    @Test
    public void Test3() {//chequea que al pasar todas las cartas y rellenar el mazo queden en el mismo orden
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);

        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        pile.push(carta3);

        StockWaste stockWaste = new StockWaste(pile);

        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        stockWaste.pasarCarta();
        stockWaste.pasarCarta();

        assertEquals(carta3, stockWaste.verCartaExpuesta());
    }
}