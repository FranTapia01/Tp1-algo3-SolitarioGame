package SolitarioKlondike;

import SolitarioBase.*;

import java.util.ArrayList;


public class SolitarioKlondike extends Solitario<ColumnaKlondike> {
    private final Pile waste;

    public SolitarioKlondike(int seed){
        super(1, 4, seed);
        this.tableu = new TableuKlondike(crearColumnas());
        this.waste = new Pile();
    }

    public SolitarioKlondike(Pile stock, Pile waste, Foundation foundation, TableuKlondike tableu) {
        super(stock, foundation, tableu);
        this.waste = waste;
    }

    @Override
    public boolean pedirCarta() {
        if (this.stock.isEmpty()) {
            if (this.waste.isEmpty()) {
                return false;
            }
            while (!waste.isEmpty()){
                this.stock.push(this.waste.pop());
            }
        }
        this.waste.push(this.stock.pop());
        return true;
    }


    public boolean moverCarta(AreaJugable destino, int posDestino) {
        if (!waste.isEmpty()) {
            var carta = new ArrayList<Carta>();
            carta.add(waste.peek());
            if (!(destino.agregarCartas(posDestino, carta))) { return false;}
            waste.pop();
            return true;
        }
        return false;
    }

    /*
    public boolean wasteToFoundation(int numFoundation) {
        if (!waste.isEmpty()) {
            Carta carta = waste.peek();
            if (!(this.foundation.agregarCartas(numFoundation, carta))) { return false;}
            waste.pop();
            return true;
        }
        return false;
    }

    public boolean wasteToTableu(int numColumna) {
        if (!waste.isEmpty()) {
            ArrayList<Carta> cartas = new ArrayList<>();
            cartas.add(waste.peek());
            if (!(this.tableu.agregarCartas(numColumna, cartas))) {return false;}
            waste.pop();
            return true;
        }
        return false;
    }
    */
    private ArrayList<ColumnaKlondike>crearColumnas() {
        var columnas = new ArrayList<ColumnaKlondike>();
        int cantDeColumnas = 7;
        for (int i = 1; i <= cantDeColumnas; i++) {
            Pile monton = new Pile();
            for (int j = 0; j < i; j++) {
                monton.add(stock.pop());
            }
            var columna = new ColumnaKlondike(monton);
            columnas.add(columna);
        }
        return columnas;
    }

    public Pile getWaste() {
        return waste;
    }
}