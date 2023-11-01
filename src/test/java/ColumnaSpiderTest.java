import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnaSpiderTest {

    @Test
    public void testAgregarValido() {
        var carta1 = new Carta(11, Carta.Palo.TREBOL);
        var carta2 = new Carta(7, Carta.Palo.CORAZON);
        var carta3 = new Carta(6, Carta.Palo.DIAMANTE);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);
        var col = new ColumnaSpider(pile);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 2);
        assertEquals(col.getCartasVisibles().get(1), carta3);
    }

    @Test
    public void testSacarCartasValidas() {
        var carta1 = new Carta(11, Carta.Palo.TREBOL);
        var carta2 = new Carta(7, Carta.Palo.CORAZON);
        var carta3 = new Carta(6, Carta.Palo.DIAMANTE);
        var carta4 = new Carta(5, Carta.Palo.DIAMANTE);
        var pile = new Pile();
        var cartas = new ArrayList<Carta>();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        var col = new ColumnaSpider(pile);
        col.agregarCartas(cartas);

        col.sacarCartas(2);
        var cartaVisible = col.obtenerCartas(1).get(0);

        assertEquals(cartaVisible, carta2);

    }

    @Test
    public void testColumnaCompleta() {
        var cartas = new ArrayList<Carta>();
        for (int i = 12; i >= 1; i--) {
            cartas.add(new Carta(i, Carta.Palo.TREBOL));
        }
        var pile = new Pile();
        var carta1 = new Carta(13, Carta.Palo.TREBOL);
        pile.push(carta1);
        var col = new ColumnaSpider(pile);

        col.agregarCartas(cartas);

        assertTrue(col.columnaCompleta());
        assertEquals(col.sacarColumnaCompletada().size(), 13);
    }
}