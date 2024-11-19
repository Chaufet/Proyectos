package actividad3;

import java.util.ArrayList;
import java.util.List;

public class Noria {
    private static List<Vehiculo> vehiculos; // Lista estática de vehículos en la Noria
    private int capacidadMaxima; // Capacidad máxima de la Noria
    private String indicador; // Indicador de estado de la Noria (Verde, Amarillo, etc.)
    private int personasDentro; // Número de personas actualmente en la Noria
    private Torniquete torniqueteEntrada; // Torniquete para registrar la entrada de personas
    private Torniquete torniqueteSalida; // Torniquete para registrar la salida de personas

    // Constructor de la clase Noria
    public Noria(int numVehiculos) {
        vehiculos = new ArrayList<>(); // Inicializa la lista de vehículos
        for (int i = 1; i <= numVehiculos; i++) {
            vehiculos.add(new Vehiculo(i)); // Añade un nuevo vehículo a la lista
        }
        this.capacidadMaxima = numVehiculos * vehiculos.get(0).getAsientos(); // Calcula la capacidad máxima
        this.indicador = "Verde"; // Inicializa el indicador en Verde
        this.personasDentro = 0; // Inicializa el número de personas dentro en 0
        this.torniqueteEntrada = new Torniquete(); // Crea una instancia del torniquete de entrada
        this.torniqueteSalida = new Torniquete(); // Crea una instancia del torniquete de salida
    }

    // Método para verificar el estado de los vehículos
    public void verificarVehiculos() {
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.verificarAnclaje(); // Verifica el anclaje de cada vehículo
        }
    }

    // Método para iniciar el recorrido de la Noria
    public void arranque(double personasActuales) {
        if (this.indicador.equalsIgnoreCase("Verde")) {
            System.out.println("La Rueda de Chicago puede iniciar su recorrido");
            torniqueteEntrada.registrarEntrada((int) personasActuales); // Registra la entrada de personas
            torniqueteEntrada(personasActuales); // Llama al método para gestionar la entrada de personas
        } else {
            System.out.println("El indicador está en amarillo, no se puede iniciar el recorrido");
        }
    }

    // Método para gestionar la entrada de personas a la Noria
    public void torniqueteEntrada(double personasAEntrar) {
        // Primero, verifica si hay averías pendientes
        if (hayAveriasPendientes()) { // Verifica si hay averías pendientes
            setIndicador("Amarillo"); // Cambia el indicador a Amarillo
            System.out.println("La Noria está esperando reparación. No se puede permitir la entrada.");
        } else if (personasAEntrar + personasDentro <= capacidadMaxima) { // Verifica si el aforo no excede la capacidad
            personasDentro += (int) personasAEntrar; // Actualiza el número de personas dentro
            System.out.println("Aforo actual: " + personasDentro + "/" + capacidadMaxima);
            System.out.println("Personas abordo: " + personasAEntrar);
            new Thread(() -> { // Crea un nuevo hilo para gestionar la salida de personas
                try {
                    Thread.sleep(5000); // Espera 5 segundos antes de registrar la salida
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Maneja cualquier excepción de interrupción
                }
                torniqueteSalida.registrarSalida((int) personasAEntrar); // Registra la salida de personas
            }).start();
        } else {
            System.out.println("Aforo excedido. No se puede permitir la entrada.");
        }
    }

    // Método para gestionar la salida de personas de la Noria
    public void torniqueteSalida(double personasAAbandonar) {
        if (personasAAbandonar <= personasDentro) { // Verifica si el número de personas que intentan salir es válido
            personasDentro -= (int) personasAAbandonar; // Actualiza el número de personas dentro
            System.out.println("Personas afuera: " + personasAAbandonar);
            if (personasDentro == 0) { // Verifica si ya no hay personas dentro
                System.out.println("Todos los pasajeros han salido. La atracción está lista para nuevos usuarios.");
                // Si no hay averías pendientes, cambia el indicador a verde
                if (!hayAveriasPendientes()) {
                    setIndicador("Verde"); // Cambia el indicador a Verde
                }
            }
        } else {
            System.out.println("Error: Más personas intentando salir de las que están en la atracción.");
        }
    }

    // Método para verificar si hay averías pendientes en los vehículos
    private boolean hayAveriasPendientes() {
        for (Vehiculo vehiculo : vehiculos) {
            if (!vehiculo.getAnclaje()) { // Verifica si hay una falla en el anclaje del vehículo
                return true; // Retorna true si hay al menos una falla
            }
        }
        return false; // Retorna false si no hay fallas
    }

    // Método para cambiar el indicador de estado de la Noria
    public void setIndicador(String luz) {
        this.indicador = luz;
        System.out.println("Indicador de la Noria cambiado a " + luz);
    }

    // Método para reparar la Noria
    public void reparar() {
        // Cambia el indicador a verde después de la reparación
        setIndicador("Verde");
        System.out.println("La Noria ha sido reparada y está lista para funcionar nuevamente.");
    }
}
