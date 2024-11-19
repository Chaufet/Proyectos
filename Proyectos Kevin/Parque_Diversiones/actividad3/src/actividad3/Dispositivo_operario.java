package actividad3;

public class Dispositivo_operario {
    // Variable que indica si el dispositivo está disponible para recibir tareas
    private boolean disponible;
    
    // Código único del dispositivo
    private String codigo;

    // Constructor que inicializa el dispositivo como disponible
    public Dispositivo_operario() {
        this.disponible = true; // El dispositivo está disponible por defecto al ser creado
    }

    // Método que devuelve si el dispositivo está disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Método para marcar el dispositivo como ocupado
    public void marcarOcupado() {
        this.disponible = false; // Cambia el estado del dispositivo a no disponible
    }

    // Método para marcar el dispositivo como libre
    public void marcarLibre() {
        this.disponible = true; // Cambia el estado del dispositivo a disponible
    }

    // Método para asignar un código al dispositivo
    public void setCodigo(String codigo) {
        this.codigo = codigo; // Establece el código del dispositivo
    }

    // Método para obtener el código del dispositivo
    public String getCodigo() {
        return codigo; // Devuelve el código del dispositivo
    }
}
