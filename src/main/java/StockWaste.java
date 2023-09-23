import java.util.Collections;

public class StockWaste {
    private Pile stock;
    private Pile wastePile;//

    public StockWaste(Pile monton) { //baraja = juego de naipes de 52 cartas (en el spider se usan 2)
        this.stock = monton;
        this.wastePile = new Pile();
    }

    public Carta sacarCarta() {
        if(wastePile.isEmpty()) {
            throw new RuntimeException("wastePile vacia");
        }
        return wastePile.pop();
    }

    public void pasarCarta() {
        if(stock.isEmpty()) {
            while(!wastePile.isEmpty()) {
                stock.push(wastePile.pop());
            }
        }
        wastePile.push(stock.pop());
    }

    public Carta verCartaExpuesta() {
        if(wastePile.isEmpty()) {
            throw  new RuntimeException("no hay ninguna carta expuesta");
        }
        return wastePile.peek();
    }

    public boolean estaVacio() {
        return wastePile.isEmpty() && stock.isEmpty();
    }
}
