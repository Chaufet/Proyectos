package actividad3;

import java.util.ArrayList;
import java.util.List;

public class CRA {
    // Lista de alertas generadas
    private static List<String> alertas = new ArrayList<>();
    
    // Lista de operarios disponibles en el sistema
    private static List<Operarios> operarios = new ArrayList<>();
    
    // Lista de fallos pendientes de reparación
    private static List<String> fallosPendientes = new ArrayList<>();

    // Inicializa el sistema de CRA para las atracciones Noria y Montaña Rusa
    public static void iniciar(Noria noria, Montaña_rusa montañaRusa) {
        System.out.println("\n============================================");
        System.out.println("Sistema iniciado para Noria y Montaña Rusa.");
        System.out.println("============================================");
    }

    // Añade un operario a la lista de operarios disponibles
    public static void añadirOperario(Operarios operario) {
        operarios.add(operario);
        System.out.println("Operario " + operario.getNombre() + " añadido.");
    }

    // Devuelve la lista de alertas recibidas
    public static List<String> getAlertas() {
        return alertas;
    }

    // Devuelve la lista de operarios disponibles
    public static List<Operarios> getOperarios() {
        return operarios;
    }

    // Recibe una alerta de fallo y asigna un operario para la reparación
    public static void recibirAlerta(String calle, String componente) {
        // Crea un mensaje de alerta con la ubicación y el componente afectado
        String alerta = "Componente: " + componente + " en " + calle;
        
        // Añade la alerta a la lista de alertas
        alertas.add(alerta);
        
        // Asigna un operario disponible para la reparación del componente
        asignarOperario(calle, componente);
        
        // Añade la alerta a la lista de fallos pendientes
        fallosPendientes.add(alerta);
    }

    // Asigna un operario disponible para la reparación del componente
    private static void asignarOperario(String calle, String componente) {
        for (Operarios operario : operarios) {
            // Verifica si el operario tiene un dispositivo disponible para reparaciones
            if (operario.getDispositivo().isDisponible()) {
                // Asigna la tarea de reparación al operario y muestra un mensaje
                operario.recibirMantenimiento(calle, componente);
                System.out.println("Operario: " + operario.getNombre() + " ha recibido una avería.");
                return;
            }
        }
        // Si no hay operarios disponibles, muestra un mensaje de espera
        System.out.println("\n============================================");
        System.out.println("Alerta Recibida en CRA");
        System.out.println("============================================");
        System.out.println("No hay operarios disponibles en el sistema. Esperando disponibilidad...");
        // Aquí se podría añadir una lógica de espera o reintento para asignar el fallo
    }

    // Marca un fallo como reparado y actualiza el estado del sistema
    public static void repararFallo(String calle, String componente) {
        // Crea un mensaje de alerta que representa el fallo reparado
        String alerta = "Componente: " + componente + " en " + calle;
        
        // Verifica si el fallo está en la lista de fallos pendientes
        if (fallosPendientes.contains(alerta)) {
            // Elimina el fallo de la lista de fallos pendientes
            fallosPendientes.remove(alerta);
            System.out.println("Fallo en " + componente + " en " + calle + " reparado.");
            // Aquí se podría implementar la lógica para reactivar la atracción asociada
            // Por ejemplo:
            // if (calle.equalsIgnoreCase("Noria")) {
            //     // Rehabilitar la Noria aquí
            // } else if (calle.equalsIgnoreCase("Montaña Rusa")) {
            //     // Rehabilitar la Montaña Rusa aquí
            // }
        } else {
            // Muestra un mensaje si no se encuentra el fallo en la lista de fallos pendientes
            System.out.println("No se encontró fallo pendiente en " + componente + " en " + calle + ".");
        }
    }
}
