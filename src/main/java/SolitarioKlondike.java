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