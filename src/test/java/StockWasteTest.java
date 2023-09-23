import org.junit.Test;

import static org.junit.Assert.*;

public class StockWasteTest {

    @Test
    public void Test1() {
        StockWaste stockWaste = new StockWaste(1);
        while(!stockWaste.estaVacio()) {
            stockWaste.pasarCarta();
            stockWaste.sacarCarta();
        }
        assertTrue(stockWaste.estaVacio());
    }

    @Test
    public void Test2() {
        StockWaste stockWaste = new StockWaste(1);
        stockWaste.pasarCarta();

        assertEquals(stockWaste.verCartaExpuesta(), stockWaste.sacarCarta());
    }

    @Test
    public void Test3() {//chequea que al pasar todas las cartas y rellenar el mazo queden en el mismo orden
        StockWaste stockWaste = new StockWaste(1);
        stockWaste.pasarCarta();
        Carta primerCarta = stockWaste.verCartaExpuesta();

        for (int i = 0; i < 51 ; i++) {//pasa todas las cartas del mazo y luego lo rellena
            stockWaste.pasarCarta();
        }
        stockWaste.pasarCarta();

        assertEquals(primerCarta, stockWaste.verCartaExpuesta());
    }
}