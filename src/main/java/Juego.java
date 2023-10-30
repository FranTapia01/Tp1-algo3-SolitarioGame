import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Juego {
    protected final Tableu tableu;
    protected final Foundation foundation;
    protected final StockWaste stockWaste;

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

    abstract ArrayList<Columna> crearColumnas(Pile mazo);

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