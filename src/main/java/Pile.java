import java.util.Stack;

public class Pile extends Stack<Carta> {
    private int cantidadCartas;

    public Pile() {
        this.cantidadCartas = 0;
    }

    @Override
    public Carta push(Carta carta) {
        cantidadCartas++;
        return super.push(carta);
    }

    @Override
    public synchronized Carta pop() {
        cantidadCartas--;
        return super.pop();
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }
}