package SolitarioKlondike;

import SolitarioBase.*;

import java.io.Serializable;
import java.util.ArrayList;


public class SolitarioKlondike extends Solitario implements Serializable {
    private final Pile waste;
    private final TableuKlondike tableu;

    public SolitarioKlondike(int seed){
        super(1, 4, seed);
        this.tableu = new TableuKlondike(crearColumnas());
        this.waste = new Pile();
    }

    public SolitarioKlondike(Pile stock, Pile waste, Foundation foundation, TableuKlondike tableu) {
        super(stock, foundation);
        this.waste = waste;
        this.tableu = tableu;
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

    public boolean wasteToAreaJugable(AreaJugable destino, int posDestino) {
        if (!waste.isEmpty()) {
            var carta = new ArrayList<Carta>();
            carta.add(waste.peek());
            if (!(destino.agregarCartas(posDestino, carta))) { return false;}
            waste.pop();
            return true;
        }
        return false;
    }

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
    public TableuKlondike getTableu() {
        return tableu;
    }
}