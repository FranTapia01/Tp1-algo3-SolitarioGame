package SolitarioBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Solitario<T extends Columna> implements Serializable{
    protected Tableu<T> tableu;
    protected Foundation foundation;
    protected Pile stock;

    public Solitario(int cantBarajas, int tamanioFoundation, int seed) {
        this.stock = crearStock(cantBarajas, seed);
        this.foundation = new Foundation(tamanioFoundation);
    }

    public Solitario(Pile stock, Foundation foundation, Tableu<T> tableu) {
        this.stock = stock;
        this.foundation = foundation;
        this.tableu = tableu;
    }

    public abstract boolean pedirCarta();

    public boolean juegoGanado() {
        return this.foundation.estaCompleta();
    }



    public boolean moverCarta(AreaJugable origen, AreaJugable destino, int posOrigen, int posDestino, int cantCartas) {
        ArrayList<Carta> cartas = origen.getCartas(posOrigen, cantCartas);
        if (!(destino.agregarCartas(posDestino, cartas))) {return false;}
        origen.sacarCartas(posOrigen, cantCartas);
        return true;
    }


    public boolean tableuToTableu(int numColumnaOrigen, int numColumnaDestino, int cantCartas) {
        ArrayList<Carta> cartas = this.tableu.obtenerCartasExpuestas(numColumnaOrigen, cantCartas);
        if (!(this.tableu.agregarCartas(numColumnaDestino, cartas))) {return false;}
        this.tableu.sacarCartas(numColumnaOrigen, cantCartas);
        return true;
    }

    protected Pile crearStock(int cantBarajas ,int seed){
        int cantCartasDeUnPalo = 13;
        Pile stock = new Pile();
        for (int i = 0; i < cantBarajas; i++) {
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

    public void serializar(OutputStream outputStream) throws IOException {
        try (ObjectOutputStream objetoSerializable = new ObjectOutputStream(outputStream)) {
            objetoSerializable.writeObject(this);
        }
    }

    public static Object deserializar(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objetoDeserializable = new ObjectInputStream(inputStream)) {
            return (Solitario) objetoDeserializable.readObject();
        }
    }

    public Pile getStock() {
        return stock;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public Tableu<T> getTableu() {
        return tableu;
    }
}