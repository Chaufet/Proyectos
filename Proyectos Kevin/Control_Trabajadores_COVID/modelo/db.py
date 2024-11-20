import sqlite3

def crear_base_de_datos():
    # Conectamos a la base de datos
    conn = sqlite3.connect("empleados.db")
    cursor = conn.cursor()

    # Crear la tabla de empleados
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS empleados (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT,
        direccion TEXT,
        localidad TEXT,
        personas_con_que_convive TEXT,
        estado_salud TEXT,
        covid_positivo INTEGER,  -- Cambié a INTEGER
        nivel_afectacion TEXT,
        comorbilidades TEXT
    )
    """)

    # Crear la tabla de estados diarios
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS estados_diarios (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        empleado_id INTEGER,
        como_se_siente TEXT,
        contacto_con_virus INTEGER,  -- Cambié a INTEGER
        temperatura REAL,
        fecha TEXT,  -- Puedes usar TEXT con formato 'YYYY-MM-DD'
        FOREIGN KEY(empleado_id) REFERENCES empleados(id)
    )
    """)

    # Crear índice en empleado_id para optimizar consultas
    cursor.execute("""
    CREATE INDEX IF NOT EXISTS idx_empleado_id ON estados_diarios(empleado_id);
    """)

    # Guardar y cerrar la conexión
    conn.commit()
    conn.close()
