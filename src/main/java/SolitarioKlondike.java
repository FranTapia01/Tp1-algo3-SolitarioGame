import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SolitarioKlondike implements Solitario{
    private final TableuKlondike tableu;
    private final Foundation foundation;
    private final Pile stock;
    private final Pile waste;

    public SolitarioKlondike(int seed){
        this.stock = crearStock(seed);
        this.waste = new Pile();
        int tamanioFoundation = 4;
        this.foundation = new Foundation(tamanioFoundation);
        this.tableu = new TableuKlondike(crearColumnas());
    }

    public SolitarioKlondike(Pile stock, Pile waste, Foundation foundation, TableuKlondike tableu) {
        this.stock = stock;
        this.waste = waste;
        this.foundation = foundation;
        this.tableu = tableu;
    }

    @Override
    public boolean moverCarta(Movimiento movimiento, int posOrigen, int posDestino, int cantCartas) {
        return switch (movimiento) {
            case TABLEUTOTABLEU -> tableuToTableu(posOrigen, posDestino, cantCartas);
            case TABLEUTOFOUNDATION -> tableuToFoundation(posOrigen, posDestino);
            case FOUNDATIONTOFOUNDATION -> foundationToFoundation(posOrigen, posDestino);
            case FOUNDATIONTOTABLEU -> foundationToTableu(posOrigen, posDestino);
            case WASTETOFOUNDATION -> wasteToFoundation(posDestino);
            case WASTETOTABLEU -> wasteToTableu(posDestino);
        };
    }

    @Override
    public boolean pedirCarta() {
        if (this.stock.isEmpty()) {
            if (this.waste.isEmpty()) {
                return false;
            }
            for (int i = 0; i < waste.getCantidadCartas(); i++) {
                this.stock.push(this.waste.pop());
            }
        }
        this.waste.push(this.stock.pop());
        return true;
    }

    @Override
    public boolean juegoGanado() {
        return this.foundation.estaCompleta();
    }


    private boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumnaOrigen, cantCartas);
        if (!(this.tableu.agregarCartas(numColumnaDestino, cartas))) {return false;}
        this.tableu.sacarCartas(numColumnaOrigen, cantCartas);
        return true;
    }

    private boolean tableuToFoundation(int numColumna, int numFoundation) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumna, 1);
        if (!(this.foundation.agregarCarta(cartas.get(0), numFoundation))) {return false;}
        this.tableu.sacarCartas(numColumna, 1);
        return true;
    }

    private boolean foundationToFoundation(int numFoundationOrigen, int numFoundationDestino) {
        Carta carta = this.foundation.sacarCarta(numFoundationOrigen);
        if (!(this.foundation.agregarCarta(carta, numFoundationDestino))) {
            this.foundation.agregarCarta(carta, numFoundationOrigen);
            return false;
        }
        return true;
    }

    private boolean foundationToTableu(int numFoundation, int numColumna) {
        ArrayList<Carta> carta = new ArrayList<>();
        carta.add(this.foundation.sacarCarta(numFoundation));
        if(!(this.tableu.agregarCartas(numColumna, carta))) {
            this.foundation.agregarCarta(carta.get(0), numFoundation);
            return false;
        }
        return true;
    }

    private boolean wasteToFoundation(int numFoundation) {
        if (!waste.isEmpty()) {
            Carta carta = waste.peek();
            if (!(this.foundation.agregarCarta(carta, numFoundation))) { return false;}
            waste.pop();
            return true;
        }
        return false;
    }

    private boolean wasteToTableu(int numColumna) {
        if (!waste.isEmpty()) {
            ArrayList<Carta> cartas = new ArrayList<>();
            cartas.add(waste.peek());
            if (!(this.tableu.agregarCartas(numColumna, cartas))) {return false;}
            waste.pop();
            return true;
        }
        return false;
    }

    private Pile crearStock(int seed){
        int cantCartasDeUnPalo = 13;
        Pile stock = new Pile();
        for(Carta.Palo palo: Carta.Palo.values()) {
            for (int j = 1; j <= cantCartasDeUnPalo; j++) {
                Carta carta = new Carta(j, palo);
                stock.push(carta);
            }
        }
        var rnd = new Random(seed);
        Collections.shuffle(stock, rnd);
        return stock;
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
