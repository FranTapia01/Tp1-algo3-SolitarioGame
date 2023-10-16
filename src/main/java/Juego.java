import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Juego {
    private final Tableu tableu;
    private final Foundation foundation;
    private final StockWaste stockWaste;

    public enum EstadoDeInicializacion {
        RANDOM, PREDECIBLE, CASITERMINADO
    }

    public Juego(EstadoDeInicializacion estado){
        Pile stock = crearStock();
        if(estado == EstadoDeInicializacion.CASITERMINADO) {
            Pile stockInvertido = new Pile();
            for (int i = 0; i < 52; i++) {
                stockInvertido.push(stock.pop());
            }
            this.foundation = new Foundation();
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 13; j++) {
                    foundation.agregarCarta(stockInvertido.pop(), i);
                }
            }
            ArrayList<Columna> columnas = new ArrayList<>();
            for (int i = 1; i < 8 ; i++){
                Pile pileInicial = new Pile();
                Columna columna = new Columna(pileInicial);
                columnas.add(columna);
            }
            this.tableu = new Tableu(columnas);
            foundationToTableu(4, 1);
            this.stockWaste = new StockWaste(stockInvertido);

        }else {
            mezclarMazo(stock, estado);
            ArrayList<Columna> columnas = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                Pile monton = new Pile();
                for (int j = 0; j < i; j++) {
                    monton.add(stock.pop());
                }
                Columna columna = new Columna(monton);
                columnas.add(columna);
            }
            this.foundation = new Foundation();
            this.tableu = new Tableu(columnas);
            this.stockWaste = new StockWaste(stock);
        }
    }

    private Pile crearStock(){
        Pile stock = new Pile();
        for(Carta.Palo palo: Carta.Palo.values()) {
            for (int j = 1; j < 14; j++) {
                Carta carta = new Carta(j, palo);
                stock.push(carta);
            }
        }
        return stock;
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

    public boolean foundationToFoundatiom(int numFoundationOrigen, int numFoundationDestino) {
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

    private void mezclarMazo(Pile mazo, EstadoDeInicializacion estado) {
        if(estado == EstadoDeInicializacion.PREDECIBLE) {
            var rnd = new Random(2);
            Collections.shuffle(mazo, rnd);
        }else if(estado == EstadoDeInicializacion.RANDOM){
            Collections.shuffle(mazo);
        }
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