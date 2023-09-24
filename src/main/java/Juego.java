import java.util.ArrayList;
import java.util.Collections;

public class Juego {

    //private Tableu tableu;
    private final Foundation foundation;
    private final StockWaste stockWaste;

    private final Tableu tableu;
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
        this.tableu = new Tableu(columnas);
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
    /*public boolean stockWasteToFoundation(int numColumna){
        Carta carta = this.stockWaste.sacarCarta();
        return this.foundation.agregarCarta(carta,numColumna);
    }*/
    //No contempla el devolver la carta a stockwaste si foundation.agregarCarta es inválido (rehecha abajo)

    public boolean stockWasteToFoundation(int numPile) {
        Carta carta = this.stockWaste.verCartaExpuesta();
        if (!(this.foundation.agregarCarta(carta, numPile))) { return false;}
        this.stockWaste.sacarCarta();
        return true;
    }

    //si stockwaste está vacío se lanza excepción
    public boolean stockWasteToTableu(int numColumna) {
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(this.stockWaste.verCartaExpuesta());
        if (!(this.tableu.agregarCartas(numColumna, cartas))) {return false;}
        this.stockWaste.sacarCarta();
        return true;
    }

    //En caso de Tableu.sacarCarta inválido no tira excepción, devuelve array vacío
    public boolean tableuToTableu(int numColumna1, int numColumna2, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.sacarCartas(numColumna1, cantCartas);
        if (!(this.tableu.agregarCartas(numColumna2, cartas))) {
            this.tableu.agregarCartas(numColumna1, cartas);
            return false;
        }
        return true;
    }

    //Para Foundation hay excepciones que trabajan sobre casos inválidos
    public boolean tableuToFoundation(int numColumna, int numPile) {
        ArrayList<Carta> cartas = this.tableu.sacarCartas(numColumna, 1);
        //if (cartas.isEmpty()){return false;} en este caso se tira una excepción de columna
        if (!(this.foundation.agregarCarta(cartas.get(0), numPile))) {
            this.tableu.agregarCartas(numColumna, cartas);
            return false;
        }
        return true;
    }
    public boolean foundationToFoundatiom(int numPile1, int numPile2) {
        Carta carta = this.foundation.sacarCarta(numPile1);
        if (!(this.foundation.agregarCarta(carta, numPile2))) {
            this.foundation.agregarCarta(carta, numPile1);
            return false;
        }
        return true;
    }
    public boolean foundationToTableu(int numPile, int numColumna) {
        ArrayList<Carta> carta = new ArrayList<>();
        carta.add(this.foundation.sacarCarta(numPile));
        if(!(this.tableu.agregarCartas(numColumna, carta))) {
            this.foundation.agregarCarta(carta.get(0), numPile);
            return false;
        }
        return true;
    }

}