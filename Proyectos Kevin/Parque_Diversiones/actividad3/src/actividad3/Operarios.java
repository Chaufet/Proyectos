package actividad3;

public class Operarios {
    private String name; // Nombre del operario
    private Dispositivo_operario dispositivo; // Dispositivo asociado al operario
    private int averiasAtendidas; // Contador de averías atendidas
    private static final double EXTRA_POR_AVERIA = 50.0; // Extra por avería atendida

    // Constructor que inicializa el nombre y el dispositivo del operario
    public Operarios(String name) {
        this.name = name;
        this.dispositivo = new Dispositivo_operario(); // Inicializa el dispositivo del operario
        dispositivo.setCodigo(name); // Configura el código del dispositivo con el nombre del operario
        this.averiasAtendidas = 0; // Inicializa el contador de averías atendidas
    }

    // Método para obtener el nombre del operario
    public String getName() {
        return name;
    }

    // Método para obtener el nombre del operario (redundante si ya tienes getName)
    public String getNombre() {
        return name;
    }

    // Método para obtener el dispositivo del operario
    public Dispositivo_operario getDispositivo() {
        return dispositivo;
    }

    // Método para obtener el número de averías atendidas
    public int getAveriasAtendidas() {
        return averiasAtendidas;
    }

    // Método para calcular el extra ganado por el operario
    public double calcularExtra() {
        return averiasAtendidas * EXTRA_POR_AVERIA;
    }

    // Método para que el operario reciba una avería
    public void recibirMantenimiento(String calle, String componente) {
        if (dispositivo.isDisponible()) { // Verifica si el dispositivo está disponible
            dispositivo.marcarOcupado(); // Marca el dispositivo como ocupado
            System.out.println("\n============================================");
            System.out.println("Operario: " + name + " ha recibido una avería.");
            System.out.println("Ubicación: " + calle);
            System.out.println("Componente: " + componente);
            System.out.println("============================================");
            // Simula un tiempo de reparación en un nuevo hilo
            new Thread(() -> {
                try {
                    Thread.sleep(5000); // Simula el tiempo de reparación
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Maneja cualquier excepción de interrupción
                }
                terminarReparacion(calle, componente); // Llama al método para finalizar la reparación
            }).start();
        } else {
            System.out.println(name + " está ocupado."); // Mensaje si el operario está ocupado
        }
    }

    // Método para finalizar la reparación
    private void terminarReparacion(String calle, String componente) {
        dispositivo.marcarLibre(); // Marca el dispositivo como libre
        CRA.repararFallo(calle, componente); // Notifica a CRA que se ha reparado el fallo
        averiasAtendidas++; // Incrementa el contador de averías atendidas
        System.out.println("\n============================================");
        System.out.println("Operario: " + name + " ha terminado la reparación.");
        System.out.println("Ubicación: " + calle);
        System.out.println("Componente: " + componente);
        System.out.println("============================================");
    }
}
