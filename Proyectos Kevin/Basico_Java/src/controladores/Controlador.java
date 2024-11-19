package controladores;
import java.util.ArrayList;
import modelos.Tarea;
import vista.Vista;

public class Controlador{
	ArrayList<Tarea> Tareas;
	Vista vista;
	public Controlador(){
		Tareas = new ArrayList<Tarea>();
		vista = new Vista();
		Tareas.add(new Tarea("Tarea 1","Sacar al perro",false));
		Tareas.add(new Tarea("Tarea 2","Hacer compras",true));
		Tareas.add(new Tarea("Tarea 3","Preparar el almuerzo",false));
	}
	public void inicio() {
		boolean bandera = true;
		while(bandera) {
			mostrarTareas();
			switch(vista.inicio()) {
			case 1:
				agregarTarea();
				break;
			case 2:
				editarTarea();
				break;
			case 3:
				eliminarTarea();
				break;
			case 4:
				marcarTareasCompleta();
				break;
			case 5:
				vista.salir();
				bandera=false;
				break;
			}
		}
		
	}
	public void agregarTarea(){
		Tarea tarea;
		tarea = vista.agregarTarea();
		Tareas.add(tarea);
	}
	public void editarTarea(){
		mostrarTareas();
		int index = vista.editarTarea();
		Tarea tarea = Tareas.get(index);
		vista.editar(tarea);
	}
	public void eliminarTarea(){
		int index;
		mostrarTareas();
		index= vista.eliminarTarea();
		Tareas.remove(index);
	}
	public void marcarTareasCompleta(){
		int index;
		Tarea tarea;
		mostrarTareas();
		index = vista.marcarTareasCompleta();
		tarea = Tareas.get(index);
		tarea.setEstado(true);
	}
	public void mostrarTareas() {
		vista.mostrarTareas(Tareas);
	}
}
