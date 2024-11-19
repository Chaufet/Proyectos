package modelos;

public class Tarea {
    String titulo;
    String descripcion;
    Boolean estado;
    
    public Tarea(String titulo, String descripcion, boolean estado){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public Boolean getEstado() {
        return estado;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método para obtener la representación del estado de la tarea
    public String getEstadoString() {
        return estado ? "Completada" : "Pendiente";
    }

    @Override
    public String toString() {
        return estado ? "[✅] " + titulo + " " + descripcion :
                        "[ ] " + titulo + " " + descripcion;
    }
}
