import java.util.ArrayList;
import java.util.stream.IntStream;

public class Foundation {
    private final ArrayList<Monton> montones ; // 4 montones

    private boolean cartaValida(Carta cartaApoyada,Carta cartaRecibida){

        int unNumeroExtra = 1;
        if (cartaApoyada.getPalo() != cartaRecibida.getPalo()){
            return false;
        }else return cartaApoyada.getNumero() + unNumeroExtra == cartaRecibida.getNumero();
    }

    public Foundation(){
        this.montones =  new ArrayList<>();
        int cantidadMontones = 4;
        IntStream.range(0, cantidadMontones).forEach(i -> this.montones.add(new Monton()));
    }

    public boolean agregarCarta(Carta cartaRecibida ,int numMonton ){

        int vacio = 0;
        int primerNumero = 1;
        if (numMonton > montones.size()){
            return false;
        }

        numMonton -= 1; // para corregir el indice en la lista
        Monton monton = montones.get(numMonton);
        if (monton.getCantidadCartas() == vacio){
            if(cartaRecibida.getNumero() != primerNumero) {
                return false;
            }
        }else if (!cartaValida(monton.peek(),cartaRecibida)){
            return false;
        }

        monton.push(cartaRecibida);
        return true;
    }

    public Carta sacarCarta(int numMonton){

        int vacio = 0;
        if (numMonton > montones.size()){
            throw new RuntimeException("Numero de monton no existente");
        }

        numMonton -= 1; // para corregir el indice en la lista
        Monton monton = montones.get(numMonton);
        if (monton.getCantidadCartas() == vacio){
            throw new RuntimeException("Monton vacio");
        }
        return monton.pop();
    }

    public boolean estaCompleta(){
    int montonCompleto = 13;
        for (Monton monton : montones) {
            if (monton.getCantidadCartas() < montonCompleto ) return false;
        }
        return true;
    }
}
