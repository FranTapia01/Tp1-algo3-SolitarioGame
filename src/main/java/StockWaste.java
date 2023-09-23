import java.util.Collections;

public class StockWaste {
    private Monton stock;
    private Monton wastePile;//pile = monton

    public StockWaste(int cantidadDeBarajas) { //baraja = juego de naipes de 52 cartas (en el spider se usan 2)
        this.stock = new Monton();
        this.wastePile = new Monton();
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
        return wastePile.peek();
    }

    public boolean estaVacio() {
        return wastePile.isEmpty() && stock.isEmpty();
    }
}
