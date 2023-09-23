import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnaTest {

    @Test
    public void Test1() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);

        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.add(carta1);
        pile.add(carta2);
        pile.add(carta3);
        cartas.add(carta1);
        cartas.add(carta2);

        Columna col = new Columna(pile);
        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 3);
        assertEquals(col.getCartasVisibles().get(0), carta3);

    }

    @Test
    public void Test2() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(3, Carta.Palo.TREBOL);

        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);

        Columna col = new Columna(pile);
        col.agregarCartas(cartas);
        var sobra = new ArrayList<>(col.sacarCartas(2));

        assertEquals(col.getCartasVisibles().size(), 1);
        assertEquals(col.getCartasVisibles().get(0), carta1);
        assertEquals(sobra.get(0), carta2);
    }

    @Test
    public void Test3() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);

        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);

        Columna col = new Columna(pile);

        assertEquals(col.getCantidadCartasNoVisibles(), 1);

    }
}