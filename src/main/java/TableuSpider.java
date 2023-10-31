import java.util.ArrayList;

public class TableuSpider extends Tableu{
    public TableuSpider(ArrayList<ColumnaSpider> columnas) {super(columnas);}

    public ArrayList<Carta> ColumnaCompleta(int numColumna) {
        var posColumna = numColumna + 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);

    }
}
