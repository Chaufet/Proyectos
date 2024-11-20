import sqlite3

class Empleado:
    def __init__(self, id, nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades):
        self.id = id
        self.nombre = nombre
        self.direccion = direccion
        self.localidad = localidad
        self.personas_con_que_convive = personas_con_que_convive
        self.estado_salud = estado_salud
        self.covid_positivo = covid_positivo
        self.nivel_afectacion = nivel_afectacion
        self.comorbilidades = comorbilidades
        self.estados_diarios = []  # Lista para almacenar los estados diarios de cada empleado

    @staticmethod
    def agregar_empleado(nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades):
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        cursor.execute("""
        INSERT INTO empleados (nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, (nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades))

        conn.commit()
        conn.close()

    @staticmethod
    def obtener_empleados():
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        cursor.execute("SELECT * FROM empleados")
        empleados = cursor.fetchall()
        conn.close()

        # Para cada empleado, obtenemos sus estados diarios
        empleados_con_estados = []
        for empleado in empleados:
            emp = Empleado(*empleado)
            emp.estados_diarios = Empleado.obtener_estados_diarios(emp.id)
            empleados_con_estados.append(emp)
        
        return empleados_con_estados

    @staticmethod
    def agregar_estado_diario(empleado_id, como_se_siente, contacto_con_virus, temperatura, fecha):
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        cursor.execute("""
        INSERT INTO estados_diarios (empleado_id, como_se_siente, contacto_con_virus, temperatura, fecha)
        VALUES (?, ?, ?, ?, ?)
        """, (empleado_id, como_se_siente, contacto_con_virus, temperatura, fecha))

        conn.commit()
        conn.close()

    @staticmethod
    def obtener_estados_diarios(empleado_id):
        """Obtener todos los estados diarios de un empleado específico"""
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        cursor.execute("""
        SELECT como_se_siente, contacto_con_virus, temperatura, fecha
        FROM estados_diarios
        WHERE empleado_id = ?
        ORDER BY fecha DESC
        """, (empleado_id,))
        
        estados = cursor.fetchall()
        conn.close()

        # Convertir los resultados a objetos de estado
        return [EstadoDiario(*estado) for estado in estados]

    @staticmethod
    def obtener_ultimo_estado_diario(empleado_id):
        """Obtener el último estado registrado de un empleado"""
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        cursor.execute("""
        SELECT como_se_siente, contacto_con_virus, temperatura, fecha
        FROM estados_diarios
        WHERE empleado_id = ?
        ORDER BY fecha DESC
        LIMIT 1
        """, (empleado_id,))
        
        estado = cursor.fetchone()
        conn.close()

        if estado:
            return EstadoDiario(*estado)
        return None
    
    @staticmethod
    def verificar_empleados_aptos():
        """Devuelve dos listas: empleados aptos e inaptos para trabajar."""
        conn = sqlite3.connect("empleados.db")
        cursor = conn.cursor()

        # Consulta para obtener el último estado de cada empleado
        cursor.execute("""
        SELECT e.id, e.nombre, e.estado_salud, ed.como_se_siente, ed.temperatura
        FROM empleados e
        LEFT JOIN (
            SELECT empleado_id, MAX(fecha) as ultima_fecha
            FROM estados_diarios
            GROUP BY empleado_id
        ) ultimos
        ON e.id = ultimos.empleado_id
        LEFT JOIN estados_diarios ed
        ON e.id = ed.empleado_id AND ed.fecha = ultimos.ultima_fecha
        """)

        empleados = cursor.fetchall()
        conn.close()

        aptos = []
        inaptos = []

        # Determinar aptitud basado en criterios
        for empleado in empleados:
            empleado_id, nombre, estado_salud, como_se_siente, temperatura = empleado

            # Validar datos
            try:
                temperatura = float(temperatura) if temperatura else None
            except ValueError:
                temperatura = None

            if not como_se_siente or temperatura is None:  # Si faltan datos o temperatura no válida
                inaptos.append((nombre, "Datos incompletos o incorrectos"))
            elif como_se_siente.lower() == "enfermo" or temperatura >= 37.5:
                inaptos.append((nombre, f"Estado: {como_se_siente}, Temp: {temperatura or 'N/A'}"))
            else:
                aptos.append((nombre, f"Estado: {como_se_siente}, Temp: {temperatura or 'N/A'}"))

        return aptos, inaptos
    
    @staticmethod
    def eliminar_empleado(empleado_id):
     conn = sqlite3.connect("empleados.db")
     cursor = conn.cursor()

    # Eliminar estados diarios del empleado
     cursor.execute("DELETE FROM estados_diarios WHERE empleado_id = ?", (empleado_id,))
    # Eliminar al empleado
     cursor.execute("DELETE FROM empleados WHERE id = ?", (empleado_id,))

     conn.commit()
     conn.close()
     
    @staticmethod
    def actualizar_empleado(empleado_id, nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades):
      conn = sqlite3.connect("empleados.db")
      cursor = conn.cursor()

      cursor.execute("""
      UPDATE empleados
      SET nombre = ?, direccion = ?, localidad = ?, personas_con_que_convive = ?, estado_salud = ?, covid_positivo = ?, nivel_afectacion = ?, comorbilidades = ?
      WHERE id = ?
      """, (nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades, empleado_id))

      conn.commit()
      conn.close()




class EstadoDiario:
    def __init__(self, como_se_siente, contacto_con_virus, temperatura, fecha):
        self.como_se_siente = como_se_siente
        self.contacto_con_virus = contacto_con_virus
        self.temperatura = temperatura
        self.fecha = fecha
