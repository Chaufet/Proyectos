using System;

public class Tarea
{
    // Propiedades automáticas (simplificadas)
    public string Titulo { get; set; }
    public string Descripcion { get; set; }
    public bool Completada { get; private set; } // Solo modificable dentro de la clase

    // Constructor
    public Tarea(string titulo, string descripcion, bool completada = false)
    {
        Titulo = titulo;
        Descripcion = descripcion;
        Completada = completada;
    }

    // Método para cambiar el estado de la tarea a completada
    public void MarcarComoCompletada()
    {
        Completada = true;
    }

    // Método para obtener una representación de la tarea en forma de texto
    public override string ToString()
    {
        // Usa una cadena más clara para representar el estado
        string estado = Completada ? "✓ Completada" : "⏳ Pendiente";
        return $"{Titulo} - {Descripcion} [{estado}]";
    }
}
