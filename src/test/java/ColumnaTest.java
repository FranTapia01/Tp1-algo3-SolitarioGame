import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnaTest {

    @Test
    public void Test1() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);
        var carta4 = new Carta(3, Carta.Palo.TREBOL);

        var cartas = new ArrayList<Carta>();
        var monton = new Monton();
        monton.add(carta1);
        monton.add(carta2);
        monton.add(carta3);
        cartas.add(carta1);
        cartas.add(carta2);

        Columna col = new Columna(monton, carta4);
        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 3);
        assertEquals(col.getCartasVisibles().get(0), carta4);

    }

    @Test
    public void Test2() {
        var carta1 = new Carta(9, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(13, Carta.Palo.PICA);
        var carta3 = new Carta(1, Carta.Palo.TREBOL);
        var carta4 = new Carta(3, Carta.Palo.TREBOL);

        var cartas = new ArrayList<Carta>();
        var monton = new Monton();
        monton.push(carta1);
        monton.push(carta2);
        cartas.add(carta4);

        Columna col = new Columna(monton, carta3);
        col.agregarCartas(cartas);
        var sobra = new ArrayList<>(col.sacarCartas(2));

        assertEquals(col.getCartasVisibles().size(), 1);
        assertEquals(col.getCartasVisibles().get(0), carta2);
        assertEquals(sobra.get(0), carta3);
    }
}