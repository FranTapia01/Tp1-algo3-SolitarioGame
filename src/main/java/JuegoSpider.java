import java.util.ArrayList;

public class JuegoSpider extends Juego{

    public JuegoSpider(int seed) {
        super(seed);
    }

    public JuegoSpider(Foundation foundation, Tableu tableu, StockWaste stockWaste) {
        super(foundation, tableu, stockWaste);
    }

    @Override
    protected ArrayList<Columna> crearColumnas(Pile mazo) {
        ArrayList<Columna> columnas = new ArrayList<>();
        int cantDeColumnas = 10;
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

    @Override
    public boolean stockWasteToTableu(int numColumna) {
        int cantDeColumnas = 10;
        boolean bool = true;

        for(int i = 1; i <= cantDeColumnas; i++){
            super.pasarCarta();
            bool = super.stockWasteToTableu(i);
        }
        return bool;
    }

}