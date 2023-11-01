import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;


public class SerializarTest {

    @Test
    public void testSerializar() {

        int seed = 2;
        var thrown = true;
        var Juego = new SolitarioSpider(seed);
        try{
            var serializar = Serializar.getSerializable("doc/archivo.dat");
            serializar.GuardarPartida(Juego);

        }catch (IOException  e) {
            thrown = false;
        }

        assertTrue(thrown);
    }

    @Test
    public void testDeserializar() {

        int seed = 2;
        var thrown = true;
        var Juego = new SolitarioSpider(seed);
        try{
            var serializar = Serializar.getSerializable("doc/archivo.dat");
            serializar.GuardarPartida(Juego);
            var JuegoCargado = serializar.CargarPartida();

        }catch (IOException | ClassNotFoundException e) {
            thrown = false;
        }

        assertTrue(thrown);
    }
}