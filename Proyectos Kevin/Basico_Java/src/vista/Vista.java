package vista;

import java.util.Scanner;
import java.util.ArrayList;
import modelos.Tarea;

public class Vista {
    private Scanner entrada;

    public Vista() {
        entrada = new Scanner(System.in);
    }

    public int inicio() {
        System.out.println("\nBienvenido a su checklist");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Editar tarea");
        System.out.println("3. Eliminar tarea");
        System.out.println("4. Marcar tarea como completada");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
        
        while (!entrada.hasNextInt()) {
            System.out.print("Opción no válida. Intente de nuevo: ");
            entrada.next(); // Limpiar entrada no válida
        }

        return entrada.nextInt();
    }

    public void mostrarTareas(ArrayList<Tarea> tareas) {
        System.out.println("\nLista de tareas:");
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas en la lista.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i) + " - " + tareas.get(i).getEstadoString());
            }
        }
    }

    public Tarea agregarTarea() {
        entrada.nextLine();  // Consumir el salto de línea
        System.out.print("Título de la tarea: ");
        String titulo = entrada.nextLine();
        System.out.print("Descripción de la tarea: ");
        String descripcion = entrada.nextLine();
        return new Tarea(titulo, descripcion, false);
    }

    public int editarTarea() {
        System.out.print("Ingrese el número de la tarea a editar: ");
        return validarOpcion();
    }

    public void editar(Tarea tarea) {
        entrada.nextLine();  // Consumir el salto de línea
        System.out.print("Nuevo título: ");
        String titulo = entrada.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = entrada.nextLine();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
    }

    public int eliminarTarea() {
        System.out.print("Ingrese el número de la tarea a eliminar: ");
        return validarOpcion();
    }

    public int marcarTareasCompleta() {
        System.out.print("Ingrese el número de la tarea completada: ");
        return validarOpcion();
    }

    public void salir() {
        System.out.println("Hasta pronto :)");
    }

    private int validarOpcion() {
        while (!entrada.hasNextInt()) {
            System.out.print("Opción no válida. Intente de nuevo: ");
            entrada.next();  // Limpiar entrada no válida
        }
        return entrada.nextInt() - 1;  // Convertir a índice 0-based
    }
}
