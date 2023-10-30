import java.util.ArrayList;

public class JuegoKlondike extends Juego{

    public JuegoKlondike(int seed) {
        super(seed);
    }

    public JuegoKlondike(Foundation foundation, Tableu tableu, StockWaste stockWaste) {
        super(foundation, tableu, stockWaste);
    }

    @Override
    protected ArrayList<Columna> crearColumnas(Pile mazo) {
        ArrayList<Columna> columnas = new ArrayList<>();
        int cantDeColumnas = 7;
        for (int i = 1; i <= cantDeColumnas; i++) {
            Pile monton = new Pile();
            for (int j = 0; j < i; j++) {
                monton.add(mazo.pop());
            }
            Columna columna = new ColumnaSpider(monton);
            columnas.add(columna);
        }
        return columnas;
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

    public boolean foundationToFoundation(int numFoundationOrigen, int numFoundationDestino) {
        Carta carta = this.foundation.sacarCarta(numFoundationOrigen);
        if (!(this.foundation.agregarCarta(carta, numFoundationDestino))) {
            this.foundation.agregarCarta(carta, numFoundationOrigen);
            return false;
        }
        return true;
    }

    public boolean tableuToFoundation(int numColumna, int numFoundation) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumna, 1);
        if (!(this.foundation.agregarCarta(cartas.get(0), numFoundation))) {return false;}
        this.tableu.sacarCartas(numColumna, 1);
        return true;
    }

    public boolean stockWasteToFoundation(int numFoundation) {
        Carta carta = this.stockWaste.verCartaExpuesta();
        if (!(this.foundation.agregarCarta(carta, numFoundation))) { return false;}
        this.stockWaste.sacarCarta();
        return true;
    }


}
