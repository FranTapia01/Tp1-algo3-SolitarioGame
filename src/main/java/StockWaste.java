import java.util.Collections;

public class StockWaste {
    private Pile stock;
    private Pile wastePile;//

    public StockWaste(int cantidadDeBarajas) { //baraja = juego de naipes de 52 cartas (en el spider se usan 2)
        this.stock = new Pile();
        this.wastePile = new Pile();
        for (int i = 0; i < cantidadDeBarajas ; i++) {
            for(Carta.Palo palo: Carta.Palo.values()) {
                for (int j = 1; j < 14; j++) {
                    Carta carta = new Carta(j, palo);
                    stock.push(carta);
                }
            }
        }
        Collections.shuffle(stock);//mezcla el mazo
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
