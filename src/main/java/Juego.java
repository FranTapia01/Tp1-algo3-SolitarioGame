import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Juego {
    private final Tableu tableu;
    private final Foundation foundation;
    private final StockWaste stockWaste;

    public Juego(int seed){
        Pile stock = crearStock();
        mezclarStock(stock, seed);
        ArrayList<Columna> columnas = crearColumnas(stock);
        this.foundation = new Foundation();
        this.tableu = new Tableu(columnas);
        this.stockWaste = new StockWaste(stock);
    }

    public Juego(Foundation foundation, Tableu tableu, StockWaste stockWaste){
        this.foundation = foundation;
        this.tableu = tableu;
        this.stockWaste = stockWaste;
    }

    public boolean pasarCarta() {
        return stockWaste.pasarCarta();
    }

    public boolean stockWasteToFoundation(int numFoundation) {
        Carta carta = this.stockWaste.verCartaExpuesta();
        if (!(this.foundation.agregarCarta(carta, numFoundation))) { return false;}
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
    public boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumnaOrigen, cantCartas);
        if (!(this.tableu.agregarCartas(numColumnaDestino, cartas))) {return false;}
        this.tableu.sacarCartas(numColumnaOrigen, cantCartas);
        return true;
    }

    //Para Foundation hay excepciones que trabajan sobre casos inválidos
    public boolean tableuToFoundation(int numColumna, int numFoundation) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumna, 1);
        if (!(this.foundation.agregarCarta(cartas.get(0), numFoundation))) {return false;}
        this.tableu.sacarCartas(numColumna, 1);
        return true;
    }

    public boolean foundationToFoundation(int numFoundationOrigen, int numFoundationDestino) {
        Carta carta = this.foundation.sacarCarta(numFoundationOrigen);
        if (!(this.foundation.agregarCarta(carta, numFoundationDestino))) {
            this.foundation.agregarCarta(carta, numFoundationOrigen);
            return false;
        }
        return true;
    }

    public boolean foundationToTableu(int numFoundation, int numColumna) {
        ArrayList<Carta> carta = new ArrayList<>();
        carta.add(this.foundation.sacarCarta(numFoundation));
        if(!(this.tableu.agregarCartas(numColumna, carta))) {
            this.foundation.agregarCarta(carta.get(0), numFoundation);
            return false;
        }
        return true;
    }

    public boolean juegoGanado() {return this.foundation.estaCompleta();}

    private Pile crearStock(){
        int cantCartasDeUnPalo = 13;
        Pile stock = new Pile();
        for(Carta.Palo palo: Carta.Palo.values()) {
            for (int j = 1; j <= cantCartasDeUnPalo; j++) {
                Carta carta = new Carta(j, palo);
                stock.push(carta);
            }
        }
        return stock;
    }

    private ArrayList<Columna> crearColumnas(Pile mazo) {
        ArrayList<Columna> columnas = new ArrayList<>();
        int cantDeColumnas = 7;
        for (int i = 1; i <= cantDeColumnas; i++) {
            Pile monton = new Pile();
            for (int j = 0; j < i; j++) {
                monton.add(mazo.pop());
            }
            Columna columna = new Columna(monton);
            columnas.add(columna);
        }
        return columnas;
    }

    private void mezclarStock(Pile stock, int seed) {
        var rnd = new Random(seed);
        Collections.shuffle(stock, rnd);
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public StockWaste getStockWaste() {
        return stockWaste;
    }

    public Tableu getTableu() {
        return tableu;
    }
}