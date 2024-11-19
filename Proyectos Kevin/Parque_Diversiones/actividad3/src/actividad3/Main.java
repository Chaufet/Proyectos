package actividad3;

public class Main {

    public static void main(String[] args) {
        
        // Inicialización de atracciones
        Noria noria = new Noria(20); // Crea una instancia de la Noria con 20 vehículos
        Montaña_rusa rusa = new Montaña_rusa(10); // Crea una instancia de la Montaña Rusa con 10 vagones

        // Creación de operarios
        Operarios operario1 = new Operarios("Jose"); // Crea un operario llamado Jose
        Operarios operario2 = new Operarios("Marta"); // Crea un operario llamado Marta
        Operarios operario3 = new Operarios("Ana"); // Crea un operario llamado Ana
        Operarios operario4 = new Operarios("Luis"); // Crea un operario llamado Luis
        
        // Inicialización del CRA y asignación de operarios
        CRA.iniciar(noria, rusa); // Inicializa el sistema CRA con las atracciones Noria y Montaña Rusa
        CRA.añadirOperario(operario1); // Añade el operario Jose al sistema CRA
        CRA.añadirOperario(operario2); // Añade el operario Marta al sistema CRA
        CRA.añadirOperario(operario3); // Añade el operario Ana al sistema CRA
        CRA.añadirOperario(operario4); // Añade el operario Luis al sistema CRA

        // Mensaje de bienvenida
        System.out.println("============================================");
        System.out.println("    Bienvenido al sistema de atracciones");
        System.out.println("============================================");
        
        // Mostrar operarios disponibles
        System.out.println("\nOperarios disponibles:");
        int i = 1;
        for (Operarios operario : CRA.getOperarios()) { // Recorre la lista de operarios disponibles
            System.out.println(i++ + ". " + operario.getNombre()); // Muestra el nombre de cada operario
        }
        
        try {
            // Pausa para visualizar el mensaje de bienvenida
            Thread.sleep(2000); // Espera 2 segundos para que el usuario pueda leer el mensaje de bienvenida
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja cualquier excepción de interrupción
        }

        // Simulación de alertas y arranque
        System.out.println("\n============================");
        System.out.println("  Verificación de la Noria");
        System.out.println("============================");
        noria.verificarVehiculos(); // Verifica los vehículos de la Noria
        noria.arranque(Math.floor(Math.random()*90) + 50); // Inicia la Noria con un número aleatorio de personas (entre 50 y 139)

        try {
            // Pausa para visualizar la verificación de la Noria
            Thread.sleep(3000); // Espera 3 segundos para que el usuario pueda ver la verificación de la Noria
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja cualquier excepción de interrupción
        }

        System.out.println("\n============================");
        System.out.println("  Verificación de la Montaña Rusa");
        System.out.println("============================");
        rusa.verificarVagones(); // Verifica los vagones de la Montaña Rusa
        rusa.arranque(Math.floor(Math.random()*45) + 30); // Inicia la Montaña Rusa con un número aleatorio de personas (entre 30 y 74)

        try {
            // Pausa para visualizar la verificación de la Montaña Rusa
            Thread.sleep(3000); // Espera 3 segundos para que el usuario pueda ver la verificación de la Montaña Rusa
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja cualquier excepción de interrupción
        }

        // Mostrar reportes de alertas
        System.out.println("\n============================");
        System.out.println("    Reportes Totales a la CRA");
        System.out.println("============================");
        for (String alerta : CRA.getAlertas()) { // Recorre la lista de alertas
            System.out.println(alerta); // Muestra cada alerta
        }

        try {
            // Pausa para visualizar los reportes de alertas
            Thread.sleep(3000); // Espera 3 segundos para que el usuario pueda ver los reportes de alertas
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja cualquier excepción de interrupción
        }

        // Mostrar averías atendidas y extra
        System.out.println("\n============================");
        System.out.println("    Resumen de Averías Atendidas");
        System.out.println("============================");
        for (Operarios operario : CRA.getOperarios()) { // Recorre la lista de operarios
            System.out.println("Operario: " + operario.getNombre()); // Muestra el nombre del operario
            System.out.println("Averías Atendidas: " + operario.getAveriasAtendidas()); // Muestra el número de averías atendidas por el operario
            System.out.println("Extra: $" + operario.calcularExtra()); // Muestra el extra calculado para el operario
            System.out.println("----------------------------");
        }

        try {
            // Pausa final para revisar el resumen de averías atendidas
            Thread.sleep(5000); // Espera 5 segundos para que el usuario pueda revisar el resumen de averías atendidas
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja cualquier excepción de interrupción
        }

        System.out.println("El programa ha terminado. Presione Enter para salir...");
        try {
            System.in.read(); // Espera la entrada del usuario para cerrar el programa
        } catch (Exception e) {
            e.printStackTrace(); // Maneja cualquier excepción durante la espera de entrada
        }
    }
}
