import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SolitarioSpider implements Solitario{
    private final TableuSpider tableu;
    private final Foundation foundation;
    private final Pile stock;


    public SolitarioSpider(int seed){
        this.stock = crearStock(seed);
        this.foundation = new Foundation(8);
        this.tableu = new TableuSpider(crearColumnas());
    }

    public SolitarioSpider(Pile stock, Foundation foundation, TableuSpider tableu) {
        this.stock = stock;
        this.foundation = foundation;
        this.tableu = tableu;
    }



    @Override
    public boolean moverCarta(Movimiento movimiento, int posOrigen, int posDestino, int cantCartas) {
        return tableuToTableu(posOrigen, posDestino, cantCartas);
    }

    @Override
    public boolean pedirCarta() {
        return false;
    }

    @Override
    public boolean juegoGanado() {
        return this.foundation.estaCompleta();
    }

    @Override
    public void guardarJuego() {

    }

    @Override
    public void cargarJuego() {

    }


    public boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumnaOrigen, cantCartas);
        if (!(this.tableu.agregarCartas(numColumnaDestino, cartas))) {return false;}
        this.tableu.sacarCartas(numColumnaOrigen, cantCartas);
        columnaCompletaToFoundation(numColumnaDestino);
        return true;
    }


    private Pile crearStock(int seed){
        int cantCartasDeUnPalo = 13;
        Pile stock = new Pile();
        for (int i = 0; i < 2; i++) {
            for(Carta.Palo palo: Carta.Palo.values()) {
                for (int j = 1; j <= cantCartasDeUnPalo; j++) {
                    Carta carta = new Carta(j, palo);
                    stock.push(carta);
                }
            }
        }
        var rnd = new Random(seed);
        Collections.shuffle(stock, rnd);
        return stock;
    }

    private ArrayList<ColumnaSpider> crearColumnas() {
        var columnas = new ArrayList<ColumnaSpider>();
        int cantDeColumnas = 10;
        for (int i = 1; i <= cantDeColumnas; i++) {
            Pile monton = new Pile();
            for (int j = 0; j < i; j++) {
                monton.add(stock.pop());
            }
            var columna = new ColumnaSpider(monton);
            columnas.add(columna);
        }
        return columnas;
    }

    private void columnaCompletaToFoundation(int posColumna) {
        var cartas = tableu.DevolverColumnaCompleta(posColumna);
        var numFoundation = 0;
        boolean numFoundationValido = false;

        while (!numFoundationValido) {
            for (int i = 0; i < 13; i++) {
                numFoundationValido = foundation.agregarCarta(cartas.get(i), numFoundation);
            }
            numFoundation++;
        }
    }
}