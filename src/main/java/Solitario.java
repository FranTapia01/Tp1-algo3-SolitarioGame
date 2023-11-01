import java.io.Serializable;

public interface Solitario extends Serializable {
    enum Movimiento{
        TABLEUTOTABLEU,
        TABLEUTOFOUNDATION,
        FOUNDATIONTOFOUNDATION,
        FOUNDATIONTOTABLEU,
        WASTETOFOUNDATION,
        WASTETOTABLEU
    }
    boolean moverCarta(Movimiento movimiento, int posOrigen, int posDestino, int cantCartas);

    boolean pedirCarta();

    boolean juegoGanado();

}