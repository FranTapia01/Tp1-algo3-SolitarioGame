import java.util.ArrayList;
import java.util.Collections;

public class Juego {

    //private Tableu tableu;
    private final Foundation foundation;
    private final StockWaste stockWaste;

    public boolean juegoGanado(){return this.foundation.estaCompleta();}

    public Juego(){
        int cantidadBarajas = 2;
        this.foundation = new Foundation();


        Pile stock = shuffle(cantidadBarajas);
        ArrayList<Columna> columnas =  new ArrayList<>();
        for (int i = 1;i < 8 ; i++){
            Pile monton = new Pile();
            for (int j = 0;j < i ; j++){
                monton.add(stock.pop());
            }
            Columna columna = new Columna(monton);
            columnas.add(columna);
        }
        //this.tableu = new Tableu(columnas);
        this.stockWaste = new StockWaste(stock);

    }

    private Pile shuffle(int cantidadDeBarajas){
        Pile stock = new Pile();

        for (int i = 0; i < cantidadDeBarajas ; i++) {
            for(Carta.Palo palo: Carta.Palo.values()) {
                for (int j = 1; j < 14; j++) {
                    Carta carta = new Carta(j, palo);
                    stock.push(carta);
                }
            }
        }
        Collections.shuffle(stock);//mezcla el mazo
        return stock;
    }
    public boolean stockWasteToFoundation(int numColumna){
        Carta carta = this.stockWaste.sacarCarta();
        return this.foundation.agregarCarta(carta,numColumna);
    }

}