package actividad3;

import java.util.ArrayList;
import java.util.List;

public class Montaña_rusa {
    private static List<Vagones> vagones; // Lista estática de vagones en la Montaña Rusa
    private int capacidadMaxima; // Capacidad máxima de la Montaña Rusa
    private String indicador; // Indicador de estado de la Montaña Rusa (Verde, Amarillo, etc.)
    private int personasDentro; // Número de personas actualmente en la Montaña Rusa
    private Torniquete torniqueteEntrada; // Torniquete para registrar la entrada de personas
    private Torniquete torniqueteSalida; // Torniquete para registrar la salida de personas

    // Constructor de la clase Montaña_rusa
    public Montaña_rusa(int numVagones) {
        vagones = new ArrayList<>(); // Inicializa la lista de vagones
        for (int i = 1; i <= numVagones; i++) {
            vagones.add(new Vagones(i)); // Añade un nuevo vagón a la lista
        }
        this.capacidadMaxima = numVagones * vagones.get(0).getAsientos(); // Calcula la capacidad máxima
        this.indicador = "Verde"; // Inicializa el indicador en Verde
        this.personasDentro = 0; // Inicializa el número de personas dentro en 0
        this.torniqueteEntrada = new Torniquete(); // Crea una instancia del torniquete de entrada
        this.torniqueteSalida = new Torniquete(); // Crea una instancia del torniquete de salida
    }

    // Método para verificar el estado de los vagones
    public void verificarVagones() {
        for (Vagones vagon : vagones) {
            vagon.verificarAnclaje(); // Verifica el anclaje de cada vagón
        }
    }

    // Método para iniciar el recorrido de la Montaña Rusa
    public void arranque(double personasActuales) {
        if (this.indicador.equalsIgnoreCase("Verde")) {
            System.out.println("La Montaña Rusa puede iniciar su recorrido");
            torniqueteEntrada.registrarEntrada((int) personasActuales); // Registra la entrada de personas
            torniqueteEntrada(personasActuales); // Llama al método para gestionar la entrada de personas
        } else {
            System.out.println("El indicador está en amarillo, no se puede iniciar el recorrido");
        }
    }

    // Método para gestionar la entrada de personas a la Montaña Rusa
    public void torniqueteEntrada(double personasAEntrar) {
        if (hayAveriasPendientes()) { // Verifica si hay averías pendientes
            setIndicador("Amarillo"); // Cambia el indicador a Amarillo
            System.out.println("La Montaña Rusa está esperando reparación. No se puede permitir la entrada.");
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

    // Método para gestionar la salida de personas de la Montaña Rusa
    public void torniqueteSalida(double personasAAbandonar) {
        if (personasAAbandonar <= personasDentro) { // Verifica si el número de personas que intentan salir es válido
            personasDentro -= (int) personasAAbandonar; // Actualiza el número de personas dentro
            System.out.println("Personas afuera: " + personasAAbandonar);
            if (personasDentro == 0) { // Verifica si ya no hay personas dentro
                System.out.println("Todos los pasajeros han salido. La atracción está lista para nuevos usuarios.");
                setIndicador("Verde"); // Cambia el indicador a Verde
            }
        } else {
            System.out.println("Error: Más personas intentando salir de las que están en la atracción.");
        }
    }

    // Método para verificar si hay averías pendientes en los vagones
    private boolean hayAveriasPendientes() {
        for (Vagones vagon : vagones) {
            if (vagon.isFalla()) { // Verifica si algún vagón tiene una falla
                return true; // Retorna true si hay al menos una falla
            }
        }
        return false; // Retorna false si no hay fallas
    }
    
    // Método para cambiar el indicador de estado de la Montaña Rusa
    public void setIndicador(String luz) {
        this.indicador = luz;
        System.out.println("Indicador de la Montaña Rusa cambiado a " + luz);
    }
}
