import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnaTest {

    @Test // Agregar carta válida a columna vacia
    public void Test1() {
        var carta1 = new Carta(13, Carta.Palo.DIAMANTE);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        Columna col = new Columna(pile);
        cartas.add(carta1);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 1);
        assertEquals(col.getCartasVisibles().get(0), carta1);

    }

    @Test // Agregar carta inválida a columna vacia
    public void Test2() {
        var carta1 = new Carta(12, Carta.Palo.DIAMANTE);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        Columna col = new Columna(pile);
        cartas.add(carta1);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 0);

    }
    @Test // Agregar carta válida
    public void Test3() {
        var carta1 = new Carta(11, Carta.Palo.TREBOL);
        var carta2 = new Carta(7, Carta.Palo.CORAZON);
        var carta3 = new Carta(6, Carta.Palo.PICA);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);
        Columna col = new Columna(pile);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 2);
        assertEquals(col.getCartasVisibles().get(1), carta3);
    }

    @Test // Agregar carta inválida
    public void Test4() {
        var carta1 = new Carta(13, Carta.Palo.PICA);
        var carta2 = new Carta(11, Carta.Palo.TREBOL);
        var carta3 = new Carta(7, Carta.Palo.CORAZON);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);
        Columna col = new Columna(pile);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 1);
    }

    @Test // Agregar cartas válidas
    public void Test5() {
        var carta1 = new Carta(11, Carta.Palo.TREBOL);
        var carta2 = new Carta(7, Carta.Palo.CORAZON);
        var carta3 = new Carta(6, Carta.Palo.PICA);
        var carta4 = new Carta(9, Carta.Palo.TREBOL);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        Columna col = new Columna(pile);

        col.agregarCartas(cartas);

        assertEquals(col.getCartasVisibles().size(), 3);
        assertEquals(col.getCartasVisibles().get(2), carta4);
    }

    @Test // Sacar cartas de columna con cartas
    public void Test6() {
        var carta1 = new Carta(3, Carta.Palo.CORAZON);
        var carta2 = new Carta(7, Carta.Palo.TREBOL);
        var carta3 = new Carta(13, Carta.Palo.CORAZON);
        var carta4 = new Carta(12, Carta.Palo.PICA);
        var carta5 = new Carta(8, Carta.Palo.DIAMANTE);
        var cartas = new ArrayList<Carta>();
        var pile = new Pile();
        pile.push(carta1);
        pile.push(carta2);
        pile.push(carta3);
        cartas.add(carta4);
        cartas.add(carta5);
        Columna col = new Columna(pile);
        col.agregarCartas(cartas);

        var cartasSacadas = col.sacarCartas(3);

        assertEquals(col.getCartasVisibles().size(), 1);
        assertEquals(col.getCantidadCartasNoVisibles(), 1);
        assertEquals(cartasSacadas.size(), 3);
        assertEquals(cartasSacadas.get(0), carta3);
        assertEquals(cartasSacadas.get(1), carta4);
        assertEquals(cartasSacadas.get(2), carta5);
    }


}