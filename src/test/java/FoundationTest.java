import SolitarioBase.Carta;
import SolitarioBase.Foundation;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FoundationTest {

    @Test
    public void testAgregarCartaConNumeroIncorrecto() {

        var carta1 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas = new ArrayList<Carta>();
        cartas.add(carta1);

        assertFalse(foundation.agregarCartas(1,cartas));
    }

    @Test
    public void testAgregarCartaAFoundationIncorrecta() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas = new ArrayList<Carta>();
        cartas.add(carta1);

        assertFalse(foundation.agregarCartas(5,cartas));
    }

    @Test
    public void testAgregarCartaValida() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas = new ArrayList<Carta>();
        cartas.add(carta1);

        assertTrue(foundation.agregarCartas(1,cartas));
    }

    @Test
    public void testAgregarCartaIncorrectaSobreOtra() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(3, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas1 = new ArrayList<Carta>();
        cartas1.add(carta1);
        var cartas2 = new ArrayList<Carta>();
        cartas2.add(carta2);

        foundation.agregarCartas(1,cartas1);
        assertFalse(foundation.agregarCartas(1,cartas2));
    }

    @Test
    public void testAgregarCartaCorrectaSobreOtra() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas1 = new ArrayList<Carta>();
        cartas1.add(carta1);
        var cartas2 = new ArrayList<Carta>();
        cartas2.add(carta2);

        foundation.agregarCartas(1,cartas1);
        assertTrue(foundation.agregarCartas(1,cartas2));
    }

    @Test
    public void testAgregarCartaDeDistintoPalo() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.PICA);
        var foundation = new Foundation(4);
        var cartas1 = new ArrayList<Carta>();
        cartas1.add(carta1);
        var cartas2 = new ArrayList<Carta>();
        cartas2.add(carta2);

        foundation.agregarCartas(1,cartas1);
        assertFalse(foundation.agregarCartas(2,cartas2));
    }

    @Test
    public void testSacarCartaAgregada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas = new ArrayList<Carta>();
        cartas.add(carta1);

        foundation.agregarCartas(1,cartas);
        var cartaSacada = foundation.sacarCartas(1, 1);
        assertEquals(cartaSacada.get(0), carta1);
    }

    @Test
    public void testAgregarCartaSiguienteACartaSacada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas1 = new ArrayList<Carta>();
        cartas1.add(carta1);
        var cartas2 = new ArrayList<Carta>();
        cartas2.add(carta2);

        foundation.agregarCartas(1,cartas1);
        foundation.sacarCartas(1, 1);
        assertFalse(foundation.agregarCartas(1,cartas2));
    }

    @Test
    public void testAgregarMismaCartaLuegoDeSerSacada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation(4);
        var cartas = new ArrayList<Carta>();
        cartas.add(carta1);

        foundation.agregarCartas(1,cartas);
        foundation.sacarCartas(1, 1);
        assertTrue(foundation.agregarCartas(1,cartas));
    }

    @Test
    public void testEstaCompletaFoundationVacia() {
        var foundation = new Foundation(4);
        assertFalse(foundation.estaCompleta());
    }

    @Test
    public void testEstaCompletaFoundationLlena() {

        var foundation = new Foundation(4);
        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.DIAMANTE);
            var cartas = new ArrayList<Carta>();
            cartas.add(carta);
            foundation.agregarCartas(1, cartas);
        }

        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.PICA);
            var cartas = new ArrayList<Carta>();
            cartas.add(carta);
            foundation.agregarCartas(2, cartas);
        }

        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.TREBOL);
            var cartas = new ArrayList<Carta>();
            cartas.add(carta);
            foundation.agregarCartas(3, cartas);
        }

        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.CORAZON);
            var cartas = new ArrayList<Carta>();
            cartas.add(carta);
            foundation.agregarCartas(4, cartas);
        }

        assertTrue(foundation.estaCompleta());
    }

}