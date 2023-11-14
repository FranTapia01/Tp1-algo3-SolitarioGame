import java.util.ArrayList;

public class SolitarioSpider extends Solitario<ColumnaSpider> {
    public SolitarioSpider(int seed){
        int cantBarajas = 2;
        this.stock = crearStock(seed, cantBarajas);
        int tamanioFoundation = 8;
        this.foundation = new Foundation(tamanioFoundation);
        this.tableu = new TableuSpider(crearColumnas());
    }

    public SolitarioSpider(Pile stock, Foundation foundation, TableuSpider tableu) {
        super(stock, foundation, tableu);
    }

    @Override
    public boolean pedirCarta() {
        if (stock.isEmpty()) {
            return false;
        }
        var cartas = new ArrayList<Carta>();
        var cantColumnas = 10;
        for (int i = 0; i < cantColumnas; i++) cartas.add(stock.pop());
        return ((TableuSpider) this.tableu).repartirCartas(cartas);
    }

    public boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        boolean movimientoValido = super.tableuToTableu(numColumnaOrigen, numColumnaDestino, cantCartas);
        if (movimientoValido) {
            columnaCompletaToFoundation(numColumnaDestino);
        }
        return movimientoValido;
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
        var cartas = ((TableuSpider) this.tableu).hayColumnaCompleta(posColumna);
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