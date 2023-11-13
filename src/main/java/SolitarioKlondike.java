import java.util.ArrayList;


public class SolitarioKlondike extends Solitario<ColumnaKlondike> {
    private final Pile waste;

    public SolitarioKlondike(int seed){
        int cantBarajas = 1;
        this.stock = crearStock(cantBarajas, seed);
        int tamanioFoundation = 4;
        this.foundation = new Foundation(tamanioFoundation);
        this.tableu = new TableuKlondike(crearColumnas());
        this.waste = new Pile();
    }

    public SolitarioKlondike(Pile stock, Pile waste, Foundation foundation, TableuKlondike tableu) {
        this.stock = stock;
        this.waste = waste;
        this.foundation = foundation;
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

    public boolean wasteToFoundation(int numFoundation) {
        if (!waste.isEmpty()) {
            Carta carta = waste.peek();
            if (!(this.foundation.agregarCarta(carta, numFoundation))) { return false;}
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
}