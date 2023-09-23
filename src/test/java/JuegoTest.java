import org.junit.Test;
import static org.junit.Assert.*;

public class JuegoTest {
    @Test
    public void Test1() {
        Juego juego = new Juego();

        assertFalse(juego.juegoGanado());
    }
}