package actividad3;

public class Torniquete {
    private int personasDentro;

    public Torniquete() {
        this.personasDentro = 0; // Inicializa el contador de personas a 0
    }

    // Registra la entrada de una cantidad específica de personas
    public void registrarEntrada(int cantidad) {
        this.personasDentro += cantidad;
        System.out.println("Entraron " + cantidad + " personas. Total actual: " + personasDentro);
    }

    // Registra la salida de una cantidad específica de personas
    public void registrarSalida(int cantidad) {
        if (cantidad <= personasDentro) {
            this.personasDentro -= cantidad;
            System.out.println("Salieron " + cantidad + " personas. Total actual: " + personasDentro);
        } else {
            System.out.println("Error: Más personas intentando salir de las que están en la atracción.");
        }
    }

    // Resetea el contador de personas a 0
    public void resetear() {
        this.personasDentro = 0;
        System.out.println("El contador de personas se ha reseteado.");
    }
}
