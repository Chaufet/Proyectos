package actividad3;

public class Vehiculo {
    private int id; // Identificador único del vehículo
    private int asientos; // Número de asientos en el vehículo
    private boolean anclaje; // Estado del anclaje del vehículo
    private boolean falla; // Estado de falla del vehículo

    // Constructor que inicializa el vehículo con un ID único
    public Vehiculo(int id) {
        this.id = id;
        this.asientos = 4; // Número inicial de asientos
        this.anclaje = true; // Inicialmente el anclaje está en buen estado
        this.falla = false; // Inicialmente no hay falla
    }

    // Devuelve el identificador del vehículo
    public int getId() {
        return id;
    }

    // Devuelve el estado del anclaje
    public boolean getAnclaje() {
        return anclaje;
    }

    // Devuelve el número de asientos en el vehículo
    public int getAsientos() {
        return asientos;
    }

    // Verifica el estado del anclaje y actualiza el estado de falla si es necesario
    public void verificarAnclaje() {
        // Simula la verificación del anclaje con una probabilidad de fallo del 10%
        this.anclaje = Math.random() > 0.1;
        if (!this.anclaje) {
            falla = true; // Marca el vehículo como fallido
            Falla(); // Llama al método para manejar la falla
        }
    }

    // Devuelve el estado de falla del vehículo
    public boolean isFalla() {
        return falla;
    }

    // Maneja la falla del vehículo, notificando al sistema CRA
    private void Falla() {
        System.out.println("Se solicita revisión en vehículo " + id);
        CRA.recibirAlerta("Noria", "Vehículo " + id); // Notifica a CRA sobre la falla
    }
}
