using System;
using System.Collections.Generic;

class Vista
{
    // Colores ANSI para dar formato a la terminal
    private const string COLOR_RESET = "\u001b[0m";
    private const string COLOR_TITULO = "\u001b[34;1m"; // Azul fuerte
    private const string COLOR_OPCION = "\u001b[32m"; // Verde
    private const string COLOR_ERROR = "\u001b[31m"; // Rojo
    private const string COLOR_EXITO = "\u001b[32;1m"; // Verde brillante
    private const string COLOR_TAREA_COMPLETADA = "\u001b[33m"; // Amarillo
    private const string COLOR_TAREA_INCOMPLETA = "\u001b[37m"; // Blanco

    public int Inicio() 
    {
        Console.WriteLine($"{COLOR_TITULO}¿Qué acción deseas realizar?{COLOR_RESET}");
        Console.WriteLine($"{COLOR_OPCION}1. Crear nueva tarea{COLOR_RESET}");
        Console.WriteLine($"{COLOR_OPCION}2. Mostrar tareas{COLOR_RESET}");
        Console.WriteLine($"{COLOR_OPCION}3. Completar tarea{COLOR_RESET}");
        Console.WriteLine($"{COLOR_OPCION}4. Eliminar tarea{COLOR_RESET}");
        Console.WriteLine($"{COLOR_OPCION}5. Salir{COLOR_RESET}");

        return ElegirOpcion(5);
    }

    public void Mensaje(string mensaje, bool exito = true)
    {
        string color = exito ? COLOR_EXITO : COLOR_ERROR;
        Console.WriteLine($"{color}{mensaje}{COLOR_RESET}");
    }

    public void MostrarTareas(List<Tarea> tareas)
    {
        if (tareas.Count == 0)
        {
            Mensaje("No hay tareas para mostrar.\n", false);
            return;
        }

        Console.WriteLine($"{COLOR_TITULO}\nEstas son tus tareas:{COLOR_RESET}");
        int i = 1;
        foreach (var tarea in tareas)
        {
            string estado = tarea.Completada ? "✓" : " ";
            string colorTarea = tarea.Completada ? COLOR_TAREA_COMPLETADA : COLOR_TAREA_INCOMPLETA;
            Console.WriteLine($"{colorTarea}{i}. {tarea.Titulo} - {tarea.Descripcion} [{estado}]{COLOR_RESET}");
            i++;
        }
    }

    public int ElegirOpcion(int max)
    {
        while (true)
        {
            Console.Write($"{COLOR_OPCION}˯ {COLOR_RESET}");
            var opcion = Console.ReadLine();

            if (int.TryParse(opcion, out int x) && x >= 1 && x <= max)
            {
                return x;
            }
            Console.WriteLine($"{COLOR_ERROR}La opción que elegiste es inválida, intenta de nuevo.{COLOR_RESET}\n");
        }
    }

    public Tarea AgregarTarea()
    {
        Console.Write($"{COLOR_TITULO}Dame el título de la tarea:{COLOR_RESET} ");
        var titulo = Console.ReadLine();

        Console.Write($"{COLOR_TITULO}Dame la descripción de la tarea:{COLOR_RESET} ");
        var descripcion = Console.ReadLine();

        return new Tarea(titulo ?? "Sin título", descripcion ?? "Sin descripción");
    }
}
