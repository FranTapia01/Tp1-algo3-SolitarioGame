import java.util.Collections;

public class StockWaste {
    private Pile stock;
    private Pile wastePile;//

    public StockWaste(Pile pile) {
        this.stock = pile;
        this.wastePile = new Pile();
    }

    public Carta sacarCarta() {
        if(wastePile.isEmpty()) {
            throw new RuntimeException("wastePile vacia");
        }
        return wastePile.pop();
    }

    public boolean pasarCarta() {
        if (estaVacio()) {
            return false;
        } else if (stock.isEmpty()) {
            while(!wastePile.isEmpty()) {
                stock.push(wastePile.pop());
            }
        }
        wastePile.push(stock.pop());
        return true;

    }

    public Carta verCartaExpuesta() {
        if(wastePile.isEmpty()) {
            throw  new RuntimeException("No hay ninguna carta expuesta");
        }
        return wastePile.peek();
    }

    public boolean hayCartaExpuesta() {
        return !wastePile.isEmpty();
    }

    public boolean estaVacio() {
        return (wastePile.isEmpty() && stock.isEmpty());
    }
}
