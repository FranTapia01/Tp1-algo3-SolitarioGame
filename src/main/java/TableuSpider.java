import java.util.ArrayList;

public class TableuSpider extends Tableu<ColumnaSpider>{
    public TableuSpider(ArrayList<ColumnaSpider> columnas) {super(columnas);}

    public ArrayList<Carta> hayColumnaCompleta(int numColumna) {
        var posColumna = numColumna - 1;
        var columnaSeleccionada = tableuColumnas.get(posColumna);
        return columnaSeleccionada.sacarColumnaCompletada();
    }

}
