import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FoundationTest {
    @Test
    public void testAgregarCartaConNumeroIncorrecto() {

        var carta1 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        assertFalse(foundation.agregarCarta(carta1,1));
    }

    @Test
    public void testAgregarCartaAFoundationIncorrecta() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        assertFalse(foundation.agregarCarta(carta1,5));
    }

    @Test
    public void testAgregarCartaValida() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        assertTrue(foundation.agregarCarta(carta1,1));
    }

    @Test
    public void testAgregarCartaIncorrectaSobreOtra() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(3, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        assertFalse(foundation.agregarCarta(carta2,1));
    }

    @Test
    public void testAgregarCartaCorrectaSobreOtra() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        assertTrue(foundation.agregarCarta(carta2,1));
    }
    @Test
    public void testAgregarCartaDeDistintoPalo() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.PICA);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        assertFalse(foundation.agregarCarta(carta2,1));
    }

    @Test
    public void testSacarCartaAgregada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        assertEquals(foundation.sacarCarta(1), carta1);
    }

    @Test
    public void testAgregarCartaSiguienteACartaSacada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var carta2 = new Carta(2, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        foundation.sacarCarta(1);
        assertFalse(foundation.agregarCarta(carta2,1));
    }

    @Test
    public void testAgregarMismaCartaLuegoDeSerSacada() {

        var carta1 = new Carta(1, Carta.Palo.DIAMANTE);
        var foundation = new Foundation();

        foundation.agregarCarta(carta1,1);
        foundation.sacarCarta(1);
        assertTrue(foundation.agregarCarta(carta1,1));
    }

    @Test
    public void testEstaCompletaFoundationVacia() {

        var foundation = new Foundation();
        assertFalse(foundation.estaCompleta());
    }

    @Test
    public void testEstaCompletaFoundationLlena() {

        var foundation = new Foundation();
        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.DIAMANTE);
            foundation.agregarCarta(carta,1);
        }
        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.PICA);
            foundation.agregarCarta(carta,2);
        }
        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.TREBOL);
            foundation.agregarCarta(carta,3);
        }
        for(int i = 1; i < 14 ;i++){
            var carta = new Carta(i, Carta.Palo.CORAZON);
            foundation.agregarCarta(carta,4);
        }

        assertTrue(foundation.estaCompleta());
    }
}