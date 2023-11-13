import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SolitarioSpider extends Solitario<ColumnaSpider> {
    protected TableuSpider tableu;

    public SolitarioSpider(int seed){
        int cantBarajas = 2;
        this.stock = crearStock(seed, cantBarajas);
        int tamanioFoundation = 8;
        this.foundation = new Foundation(tamanioFoundation);
        this.tableu = new TableuSpider(crearColumnas());
    }

    public SolitarioSpider(Pile stock, Foundation foundation, TableuSpider tableu) {
        this.stock = stock;
        this.foundation = foundation;
        this.tableu = tableu;
    }

    @Override
    public boolean pedirCarta() {
        if (stock.isEmpty()) {
            return false;
        }
        var cartas = new ArrayList<Carta>();
        for (int i = 0; i < 10; i++) cartas.add(stock.pop());
        return tableu.repartirCartas(cartas);
    }

    private ArrayList<ColumnaSpider> crearColumnas() {
        var columnas = new ArrayList<ColumnaSpider>();
        int cantDeColumnas = 10;
        int cantAAgregar = 6;
        for (int i = 1; i <= cantDeColumnas; i++) {
            Pile monton = new Pile();
            if (i > 4) cantAAgregar = 5;
            for (int j = 0; j < cantAAgregar; j++) {
                monton.add(stock.pop());
            }
            var columna = new ColumnaSpider(monton);
            columnas.add(columna);
        }
        return columnas;
    }

    private void columnaCompletaToFoundation(int posColumna) {
        var cartas = tableu.hayColumnaCompleta(posColumna);
        if (!cartas.isEmpty()) {
            var numFoundation = 1;
            boolean numFoundationValido = false;

            while (!numFoundationValido) {
                for (int i = 0; i < 13; i++) {
                    numFoundationValido = foundation.agregarCarta(cartas.get(i), numFoundation);
                }
                numFoundation++;
            }
        }
    }
}