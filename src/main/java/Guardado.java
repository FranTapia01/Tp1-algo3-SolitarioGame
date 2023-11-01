import java.io.*;

public class Guardado {
    private static Guardado guardado;
    private Guardado( String name) throws IOException {
        write = new FileOutputStream( name );
        read = new FileInputStream( name );
    };

    private final FileOutputStream write;
    private final FileInputStream read;

    public static Guardado getGuardado(String name) throws IOException {
        if (guardado == null){
            guardado = new Guardado(name);
        }
        return guardado;
    }
    public void GuardarPartida(Serializable object) throws IOException {
        var output = new ObjectOutputStream(write);
        output.writeObject(object);
        output.close();
    }

    public Object CargarPartida() throws IOException, ClassNotFoundException {
        var input = new ObjectInputStream(read);
        Object object = input.readObject();
        input.close();
        return object;
    }
}
