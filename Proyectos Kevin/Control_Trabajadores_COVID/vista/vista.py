import tkinter as tk
from tkinter import messagebox, ttk
from datetime import datetime

class Vista:
    def __init__(self, root, controlador):
        self.root = root
        self.controlador = controlador
        self.root.title("Control de Empleados COVID-19")

        # Crear un cuaderno (notebook) para manejar las pestañas
        self.notebook = ttk.Notebook(root)
        self.notebook.pack(fill=tk.BOTH, expand=True)

        # Pestaña de "Registrar Empleado"
        self.registrar_empleado_tab = tk.Frame(self.notebook)
        self.notebook.add(self.registrar_empleado_tab, text="Registrar Empleado")
        self.crear_formulario_empleado()

        # Pestaña de "Registrar Estado Diario"
        self.registrar_diario_tab = tk.Frame(self.notebook)
        self.notebook.add(self.registrar_diario_tab, text="Registrar Estado Diario")
        self.crear_formulario_diario()

        # Nueva pestaña: Verificar empleados aptos
        self.verificar_tab = tk.Frame(self.notebook)
        self.notebook.add(self.verificar_tab, text="Verificar Empleados Aptos")
        self.crear_tab_verificar()
        
        # Pestaña de "Gestionar Empleados"
        self.gestionar_empleados_tab = tk.Frame(self.notebook)
        self.notebook.add(self.gestionar_empleados_tab, text="Gestionar Empleados")
        self.crear_gestionar_empleados()

        # Llamar a actualizar empleados combo después de crear las pestañas
        self.actualizar_empleados_combo()

    def crear_formulario_empleado(self):
        """Formulario para registrar un nuevo empleado"""
        self.nombre_label = tk.Label(self.registrar_empleado_tab, text="Nombre del Empleado:")
        self.nombre_label.grid(row=0, column=0)
        self.nombre_entry = tk.Entry(self.registrar_empleado_tab)
        self.nombre_entry.grid(row=0, column=1)

        self.direccion_label = tk.Label(self.registrar_empleado_tab, text="Dirección:")
        self.direccion_label.grid(row=1, column=0)
        self.direccion_entry = tk.Entry(self.registrar_empleado_tab)
        self.direccion_entry.grid(row=1, column=1)

        self.localidad_label = tk.Label(self.registrar_empleado_tab, text="Localidad:")
        self.localidad_label.grid(row=2, column=0)
        self.localidad_entry = tk.Entry(self.registrar_empleado_tab)
        self.localidad_entry.grid(row=2, column=1)

        self.personas_convive_label = tk.Label(self.registrar_empleado_tab, text="Personas con las que convive:")
        self.personas_convive_label.grid(row=3, column=0)
        self.personas_convive_entry = tk.Entry(self.registrar_empleado_tab)
        self.personas_convive_entry.grid(row=3, column=1)

        self.estado_salud_label = tk.Label(self.registrar_empleado_tab, text="Estado de Salud Actual:")
        self.estado_salud_label.grid(row=4, column=0)
        self.estado_salud_entry = tk.Entry(self.registrar_empleado_tab)
        self.estado_salud_entry.grid(row=4, column=1)

        self.covid_positivo_label = tk.Label(self.registrar_empleado_tab, text="¿Ha tenido COVID?")
        self.covid_positivo_label.grid(row=5, column=0)
        self.covid_positivo_entry = tk.Entry(self.registrar_empleado_tab)
        self.covid_positivo_entry.grid(row=5, column=1)

        self.nivel_afectacion_label = tk.Label(self.registrar_empleado_tab, text="Nivel de Afectación (leve/medio/grave):")
        self.nivel_afectacion_label.grid(row=6, column=0)
        self.nivel_afectacion_entry = tk.Entry(self.registrar_empleado_tab)
        self.nivel_afectacion_entry.grid(row=6, column=1)

        self.comorbilidades_label = tk.Label(self.registrar_empleado_tab, text="Comorbilidades:")
        self.comorbilidades_label.grid(row=7, column=0)
        self.comorbilidades_entry = tk.Entry(self.registrar_empleado_tab)
        self.comorbilidades_entry.grid(row=7, column=1)

        self.registrar_empleado_button = tk.Button(self.registrar_empleado_tab, text="Registrar Empleado", command=self.registrar_empleado)
        self.registrar_empleado_button.grid(row=8, column=0, columnspan=2)

    def registrar_empleado(self):
        """Registrar un nuevo empleado en la base de datos"""
        nombre = self.nombre_entry.get()
        direccion = self.direccion_entry.get()
        localidad = self.localidad_entry.get()
        personas_con_que_convive = self.personas_convive_entry.get()
        estado_salud = self.estado_salud_entry.get()
        covid_positivo = self.covid_positivo_entry.get()
        nivel_afectacion = self.nivel_afectacion_entry.get()
        comorbilidades = self.comorbilidades_entry.get()

        if not all([nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades]):
            messagebox.showerror("Error", "Por favor complete todos los campos.")
            return

        self.controlador.agregar_empleado(nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades)
        messagebox.showinfo("Éxito", "Empleado registrado exitosamente.")
        
        # Actualizar la lista de empleados en la tabla y el ComboBox

        self.cargar_empleados()
        self.actualizar_empleados_combo()
    
    def actualizar_empleados_combo(self):
        """Actualizar el ComboBox con los empleados actuales"""
        self.empleado_combo['values'] = [empleado.nombre for empleado in self.controlador.empleados]
        self.empleado_combo.set('')  # Para asegurarnos de que no haya una selección anterior


    def crear_formulario_diario(self):
        """Formulario para registrar el estado diario de un empleado"""
        self.empleado_label = tk.Label(self.registrar_diario_tab, text="Empleado:")
        self.empleado_label.grid(row=0, column=0)
        self.empleado_combo = ttk.Combobox(self.registrar_diario_tab)
        self.empleado_combo['values'] = [empleado.nombre for empleado in self.controlador.empleados]  # Cargar nombres de empleados
        self.empleado_combo.grid(row=0, column=1)

        self.como_se_siente_label = tk.Label(self.registrar_diario_tab, text="¿Cómo se siente hoy?")
        self.como_se_siente_label.grid(row=1, column=0)
        self.como_se_siente_entry = tk.Entry(self.registrar_diario_tab)
        self.como_se_siente_entry.grid(row=1, column=1)

        self.contacto_virus_label = tk.Label(self.registrar_diario_tab, text="¿Ha tenido contacto con alguien con COVID?")
        self.contacto_virus_label.grid(row=2, column=0)
        self.contacto_virus_entry = tk.Entry(self.registrar_diario_tab)
        self.contacto_virus_entry.grid(row=2, column=1)

        self.temperatura_label = tk.Label(self.registrar_diario_tab, text="Temperatura (°C):")
        self.temperatura_label.grid(row=3, column=0)
        self.temperatura_entry = tk.Entry(self.registrar_diario_tab)
        self.temperatura_entry.grid(row=3, column=1)
        
        self.fecha_label_static = tk.Label(self.registrar_diario_tab, text="Fecha y Hora:")
        self.fecha_label_static.grid(row=4, column=0)
        self.fecha_label_dynamic = tk.Label(self.registrar_diario_tab, text="")
        self.fecha_label_dynamic.grid(row=4, column=1)
    
        def actualizar_fecha_hora():
            fecha_actual = datetime.now().strftime("%Y-%m-%d %H:%M")  # Formato sin segundos
            self.fecha_label_dynamic.config(text=fecha_actual)  # Actualiza el Label
            self.fecha_label_dynamic.after(1000, actualizar_fecha_hora)
        
        actualizar_fecha_hora()

        self.registrar_estado_button = tk.Button(self.registrar_diario_tab, text="Registrar Estado Diario", command=self.registrar_estado_diario)
        self.registrar_estado_button.grid(row=5, column=0, columnspan=2)

    def registrar_estado_diario(self):
        """Registrar el estado diario de un empleado"""
        empleado_nombre = self.empleado_combo.get()
        empleado = self.controlador.obtener_empleado_por_nombre(empleado_nombre)
        
        if not empleado:
            messagebox.showerror("Error", "Empleado no encontrado.")
            return

        como_se_siente = self.como_se_siente_entry.get()
        contacto_con_virus = self.contacto_virus_entry.get()
        temperatura = self.temperatura_entry.get()
        fecha_actual = datetime.now().strftime("%Y-%m-%d %H:%M")
        
        if not all([como_se_siente, contacto_con_virus, temperatura]):
            messagebox.showerror("Error", "Por favor complete todos los campos.")
            return

        self.controlador.registrar_estado_diario(
        empleado.id, 
        como_se_siente, 
        contacto_con_virus, 
        temperatura, 
        fecha_actual
    )
        messagebox.showinfo("Éxito", "Estado diario registrado exitosamente.")
         
    def crear_tab_verificar(self):
        """Interfaz para mostrar empleados aptos e inaptos"""
        self.verificar_button = tk.Button(self.verificar_tab, text="Verificar Empleados", command=self.verificar_empleados)
        self.verificar_button.pack(pady=10)

        self.resultados_label = tk.Label(self.verificar_tab, text="Resultados:", font=("Arial", 12))
        self.resultados_label.pack()

        self.resultados_text = tk.Text(self.verificar_tab, wrap=tk.WORD, height=20, width=70)
        self.resultados_text.pack()
        

    def verificar_empleados(self):
        """Consulta empleados aptos/inaptos y muestra los resultados"""
        aptos, inaptos = self.controlador.verificar_empleados_aptos()

        resultados = "Empleados Aptos para Trabajar:\n"
        resultados += "\n".join([f"- {empleado[0]} ({empleado[1]})" for empleado in aptos])
        resultados += "\n\nEmpleados NO aptos para Trabajar:\n"
        resultados += "\n".join([f"- {empleado[0]} ({empleado[1]})" for empleado in inaptos])

        self.resultados_text.delete(1.0, tk.END)
        self.resultados_text.insert(tk.END, resultados)
    
    def crear_gestionar_empleados(self):
     self.tabla_empleados = ttk.Treeview(self.gestionar_empleados_tab, columns=("id", "nombre", "direccion", "localidad"), show="headings")
     self.tabla_empleados.heading("id", text="ID")
     self.tabla_empleados.heading("nombre", text="Nombre")
     self.tabla_empleados.heading("direccion", text="Dirección")
     self.tabla_empleados.heading("localidad", text="Localidad")
     self.tabla_empleados.pack(fill=tk.BOTH, expand=True)

     self.cargar_empleados()

     self.eliminar_button = tk.Button(self.gestionar_empleados_tab, text="Eliminar", command=self.eliminar_empleado)
     self.eliminar_button.pack(side=tk.LEFT, padx=5, pady=5)

     self.editar_button = tk.Button(self.gestionar_empleados_tab, text="Editar", command=self.editar_empleado)
     self.editar_button.pack(side=tk.LEFT, padx=5, pady=5)

    def cargar_empleados(self):
     for row in self.tabla_empleados.get_children():
          self.tabla_empleados.delete(row)
     for empleado in self.controlador.empleados:
        self.tabla_empleados.insert("", "end", values=(empleado.id, empleado.nombre, empleado.direccion, empleado.localidad))

    def eliminar_empleado(self):
     selected = self.tabla_empleados.selection()
     if not selected:
        messagebox.showerror("Error", "Seleccione un empleado para eliminar.")
        return

     empleado_id = self.tabla_empleados.item(selected[0])["values"][0]
     self.controlador.eliminar_empleado(empleado_id)
     self.cargar_empleados()
     messagebox.showinfo("Éxito", "Empleado eliminado exitosamente.")

    def editar_empleado(self):
     selected = self.tabla_empleados.selection()
     if not selected:
        messagebox.showerror("Error", "Seleccione un empleado para editar.")
        return

     empleado_id = self.tabla_empleados.item(selected[0])["values"][0]
     empleado = self.controlador.obtener_empleado_por_id(empleado_id)

     if not empleado:
        messagebox.showerror("Error", "Empleado no encontrado.")
        return
 
    # Crear ventana de edición
     edit_window = tk.Toplevel(self.root)
     edit_window.title("Editar Empleado")

    # Campos del formulario
     tk.Label(edit_window, text="Nombre").grid(row=0, column=0, padx=5, pady=5)
     nombre_entry = tk.Entry(edit_window)
     nombre_entry.insert(0, empleado.nombre)
     nombre_entry.grid(row=0, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Dirección").grid(row=1, column=0, padx=5, pady=5)
     direccion_entry = tk.Entry(edit_window)
     direccion_entry.insert(0, empleado.direccion)
     direccion_entry.grid(row=1, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Localidad").grid(row=2, column=0, padx=5, pady=5)
     localidad_entry = tk.Entry(edit_window)
     localidad_entry.insert(0, empleado.localidad)
     localidad_entry.grid(row=2, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Personas con las que convive").grid(row=3, column=0, padx=5, pady=5)
     personas_convive_entry = tk.Entry(edit_window)
     personas_convive_entry.insert(0, empleado.personas_con_que_convive)
     personas_convive_entry.grid(row=3, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Estado de Salud Actual").grid(row=4, column=0, padx=5, pady=5)
     estado_salud_entry = tk.Entry(edit_window)
     estado_salud_entry.insert(0, empleado.estado_salud)
     estado_salud_entry.grid(row=4, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="¿Ha tenido COVID?").grid(row=5, column=0, padx=5, pady=5)
     covid_positivo_entry = tk.Entry(edit_window)
     covid_positivo_entry.insert(0, empleado.covid_positivo)
     covid_positivo_entry.grid(row=5, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Nivel de Afectación (leve/medio/grave)").grid(row=6, column=0, padx=5, pady=5)
     nivel_afectacion_entry = tk.Entry(edit_window)
     nivel_afectacion_entry.insert(0, empleado.nivel_afectacion)
     nivel_afectacion_entry.grid(row=6, column=1, padx=5, pady=5)

     tk.Label(edit_window, text="Comorbilidades").grid(row=7, column=0, padx=5, pady=5)
     comorbilidades_entry = tk.Entry(edit_window)
     comorbilidades_entry.insert(0, empleado.comorbilidades)
     comorbilidades_entry.grid(row=7, column=1, padx=5, pady=5)

    # Botón para guardar los cambios
     def guardar_cambios():
        nombre = nombre_entry.get()
        direccion = direccion_entry.get()
        localidad = localidad_entry.get()
        personas_con_que_convive = personas_convive_entry.get()
        estado_salud = estado_salud_entry.get()
        covid_positivo = covid_positivo_entry.get()
        nivel_afectacion = nivel_afectacion_entry.get()
        comorbilidades = comorbilidades_entry.get()

        if not all([nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades]):
            messagebox.showerror("Error", "Por favor complete todos los campos.")
            return

        self.controlador.actualizar_empleado(
            empleado_id, nombre, direccion, localidad, personas_con_que_convive,
            estado_salud, covid_positivo, nivel_afectacion, comorbilidades
        )
        self.cargar_empleados()
        edit_window.destroy()
        messagebox.showinfo("Éxito", "Empleado actualizado exitosamente.")

    # Botón para guardar
     tk.Button(edit_window, text="Guardar", command=guardar_cambios).grid(row=8, column=0, columnspan=2, pady=10)

