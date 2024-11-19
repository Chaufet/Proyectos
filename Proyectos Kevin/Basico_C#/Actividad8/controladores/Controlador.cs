using System;
using System.Collections.Generic;

class Controlador
{
    private List<Tarea> tareas;
    private Vista vista;

    public Controlador()
    {
        tareas = new List<Tarea>();
        vista = new Vista();
        
        // Ejemplo de tareas iniciales
        tareas.Add(new Tarea("Tarea 1", "Lavar los platos", false));
        tareas.Add(new Tarea("Tarea 2", "Comprar víveres", false));
        tareas.Add(new Tarea("Tarea 3", "Programar", true));
    }

    public void Inicio()
    {
        // Mensajes de bienvenida
        vista.Mensaje("==============================================");
        vista.Mensaje(" Bienvenido al programa de TO-DO de UniSalle ");
        vista.Mensaje("==============================================\n");

        bool continuar = true;
        while (continuar)
        {
            int opcion = vista.Inicio();
            switch (opcion)
            {
                case 1:
                    // Crear nueva tarea
                    tareas.Add(vista.AgregarTarea());
                    vista.Mensaje("\n✅ Tarea agregada con éxito!\n");
                    break;
                case 2:
                    // Mostrar tareas
                    vista.Mensaje("\n📋 Lista de tareas:\n");
                    vista.MostrarTareas(tareas);
                    break;
                case 3:
                    // Completar tarea
                    vista.Mensaje("\n📋 Tareas disponibles para completar:\n");
                    vista.MostrarTareas(tareas);

                    if (tareas.Count > 0)
                    {
                        int opcionCompletar = vista.ElegirOpcion(tareas.Count) - 1;
                        if (opcionCompletar >= 0 && opcionCompletar < tareas.Count)
                        {
                            tareas[opcionCompletar].MarcarComoCompletada();
                            vista.Mensaje("\n✅ Tarea marcada como completada!\n");
                        }
                        else
                        {
                            vista.Mensaje("\n⚠️ Opción inválida para completar tarea.\n", false);
                        }
                    }
                    break;
                case 4:
                    // Eliminar tarea
                    vista.Mensaje("\n🗑️ Tareas disponibles para eliminar:\n");
                    vista.MostrarTareas(tareas);

                    if (tareas.Count > 0)
                    {
                        int opcionEliminar = vista.ElegirOpcion(tareas.Count) - 1;
                        if (opcionEliminar >= 0 && opcionEliminar < tareas.Count)
                        {
                            tareas.RemoveAt(opcionEliminar);
                            vista.Mensaje("\n✅ Tarea eliminada con éxito!\n");
                        }
                        else
                        {
                            vista.Mensaje("\n⚠️ Opción inválida para eliminar tarea.\n", false);
                        }
                    }
                    break;
                case 5:
                    // Salir
                    vista.Mensaje("\n👋 Hasta pronto, gracias por usar el programa!");
                    continuar = false;
                    break;
                default:
                    // Manejo de opción no válida
                    vista.Mensaje("⚠️ Opción no válida, intenta de nuevo.", false);
                    break;
            }
        }
    }
}
