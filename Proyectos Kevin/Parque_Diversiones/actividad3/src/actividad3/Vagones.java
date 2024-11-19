package actividad3;

public class Vagones {
    private int id; // Identificador único del vagón
    private int asientos; // Número de asientos en el vagón
    private Vagones vagon; // Referencia al vagón que está detrás de este
    private boolean anclaje; // Estado del anclaje del vagón
    private boolean falla; // Estado de falla del vagón

    // Constructor que inicializa el vagón con un ID único
    public Vagones(int id) {
        this.id = id;
        this.asientos = 4; // Número inicial de asientos
        this.anclaje = true; // Inicialmente el anclaje está en buen estado
        this.falla = false; // Inicialmente no hay falla
    }

    // Devuelve el identificador del vagón
    public int getId() {
        return id;
    }

    // Devuelve el estado del anclaje
    public boolean getAnclaje() {
        return anclaje;
    }

    // Devuelve el número de asientos en el vagón
    public int getAsientos() {
        return asientos;
    }

    // Establece el vagón que está detrás de este vagón
    public void setVagon_atras(Vagones vagon_atras) {
        this.vagon = vagon_atras;
    }

    // Verifica el estado del anclaje y actualiza el estado de falla si es necesario
    public void verificarAnclaje() {
        if (vagon != null) {
            // Simula la verificación del anclaje con una probabilidad de fallo del 10%
            this.anclaje = Math.random() > 0.1;
            if (!this.anclaje) {
                falla = true; // Marca el vagón como fallido
                Falla(); // Llama al método para manejar la falla
            }
        }
    }

    // Devuelve el estado de falla del vagón
    public boolean isFalla() {
        return falla;
    }

    // Maneja la falla del vagón, notificando al sistema CRA
    private void Falla() {
        System.out.println("Se solicita revisión en el vagón " + id);
        CRA.recibirAlerta("Montaña Rusa", "Vagón " + id); // Notifica a CRA sobre la falla
    }
}
