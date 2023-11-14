import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TableuKlondikeTest {
    
    //tests de agregarCarta
    @Test
    public void testAgregarColumnaInvalida() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        assertFalse(tableu.agregarCartas(8, new ArrayList<>()));
    }
    @Test
    public void testAgregarUna() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(4, Carta.Palo.PICA));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);

        assertTrue(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 2);
    }
    @Test
    public void testAgregarNinguna() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        ColumnaKlondike columnaSeleccionada = columnas.get(4);

        assertTrue(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
    }
    @Test
    public void testAgregarValidoColumnaVacia() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(13, Carta.Palo.PICA));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);
        columnaSeleccionada.sacarCartas(1);

        assertTrue(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
    }
    @Test
    public void testAgregarInvalidoColumnaVacia() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        TableuKlondike tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(12, Carta.Palo.PICA));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);
        columnaSeleccionada.sacarCartas(1);

        assertFalse(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 0);
    }
    @Test
    public void testAgregarPaloInvalido() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(4, Carta.Palo.CORAZON));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);

        assertFalse(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
    }
    @Test
    public void testAgregarNumeroInvalido() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(6, Carta.Palo.PICA));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);

        assertFalse(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
    }
    @Test
    public void testAgregarVarias() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(4, Carta.Palo.PICA));
        cartas.add(new Carta(3, Carta.Palo.CORAZON));
        ColumnaKlondike columnaSeleccionada = columnas.get(4);

        assertTrue(tableu.agregarCartas(5, cartas));
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 3);
    }
    //tests sacarCarta
    @Test
    public void testSacarColumnaInvalida() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);

        ArrayList<Carta> cartasSacadas = tableu.sacarCartas(8, 1);

        assertEquals(cartasSacadas.size(), 0);
    }
    @Test
    public void testSacarNinguna() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ColumnaKlondike columnaSeleccionada = columnas.get(3);

        ArrayList<Carta> cartasSacadas = tableu.sacarCartas(4, 0);

        assertEquals(cartasSacadas.size(), 0);
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
    }
    @Test
    public void testSacarParte() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ColumnaKlondike columnaSeleccionada = columnas.get(6);
        ArrayList<Carta> cartasAgregarColumna = new ArrayList<>();
        cartasAgregarColumna.add(new Carta(6, Carta.Palo.TREBOL));
        cartasAgregarColumna.add(new Carta(5, Carta.Palo.CORAZON));
        columnaSeleccionada.agregarCartas(cartasAgregarColumna);

        ArrayList<Carta> cartasSacadas = tableu.sacarCartas(7, 2);

        assertEquals(cartasSacadas.size(), 2);
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 1);
        assertEquals((cartasSacadas.get(0).getPalo()), Carta.Palo.TREBOL);
        assertEquals((cartasSacadas.get(1).getPalo()), Carta.Palo.CORAZON);
    }
    @Test
    public void testSacarTodo() {
        ArrayList<ColumnaKlondike> columnas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Pile pila = new Pile();
            pila.add(new Carta(i+1, Carta.Palo.DIAMANTE));
            columnas.add(new ColumnaKlondike(pila));
        }
        var tableu = new TableuKlondike(columnas);
        ColumnaKlondike columnaSeleccionada = columnas.get(6);
        ArrayList<Carta> cartasAgregarColumna = new ArrayList<>();
        cartasAgregarColumna.add(new Carta(6, Carta.Palo.TREBOL));
        cartasAgregarColumna.add(new Carta(5, Carta.Palo.CORAZON));
        columnaSeleccionada.agregarCartas(cartasAgregarColumna);

        ArrayList<Carta> cartasSacadas = tableu.sacarCartas(7, 3);

        assertEquals(cartasSacadas.size(), 3);
        assertEquals((columnaSeleccionada.getCartasVisibles()).size(), 0);
        assertEquals((cartasSacadas.get(0).getPalo()), Carta.Palo.DIAMANTE);
        assertEquals((cartasSacadas.get(1).getPalo()), Carta.Palo.TREBOL);
        assertEquals((cartasSacadas.get(2).getPalo()), Carta.Palo.CORAZON);
    }
    

}

