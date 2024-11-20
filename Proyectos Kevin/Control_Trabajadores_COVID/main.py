# main.py
from modelo.db import crear_base_de_datos
from controlador.controlador import Controlador
from vista.vista import Vista
import tkinter as tk

def main():
    try:
        # Crear la base de datos y las tablas
        crear_base_de_datos()
        print("Base de datos y tablas creadas con éxito.")
    except Exception as e:
        print(f"Error al crear la base de datos: {e}")
        return  # Detener la ejecución si ocurre un error

    # Inicializar controlador y vista
    controlador = Controlador()
    root = tk.Tk()
    vista = Vista(root, controlador)
    root.mainloop()

if __name__ == "__main__":
    main()
