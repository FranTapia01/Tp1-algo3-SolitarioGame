import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TableuSpiderTest {
    @Test
    public void testEsperoListaVaciaAlPedirColumnaCompleta() {
        var columnas = new ArrayList<ColumnaSpider>();
        columnas.add(new ColumnaSpider(new Pile()));
        var tableu = new TableuSpider(columnas);
        var columnaCompleta = tableu.hayColumnaCompleta(1);
        int listaVacia = 0;

        assertEquals(listaVacia,columnaCompleta.size());
    }

    @Test
    public void testEsperoListaCompletaAlPedirColumnaCompleta() {
        var columnas = new ArrayList<ColumnaSpider>();
        columnas.add(new ColumnaSpider(new Pile()));
        var tableu = new TableuSpider(columnas);
        var cartas = new ArrayList<Carta>();

        for (int i = 13; i >= 1; i--) {
            cartas.add(new Carta(i, Carta.Palo.TREBOL));
        }

        tableu.agregarCartas(1,cartas);
        var columnaCompleta = tableu.hayColumnaCompleta(1);
        int listaLlena = 13;
        assertEquals(listaLlena,columnaCompleta.size());
    }
}