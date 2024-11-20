from modelo.modelo import Empleado

class Controlador:
    def __init__(self):
        self.empleados = Empleado.obtener_empleados()

    def agregar_empleado(self, nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades):
        Empleado.agregar_empleado(nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades)
        self.empleados = Empleado.obtener_empleados()

    def obtener_empleado_por_nombre(self, nombre):
        """Buscar un empleado por su nombre desde la base de datos"""
        for empleado in self.empleados:
            if empleado.nombre == nombre:
                return empleado
        return None

    def registrar_estado_diario(self, empleado_id, como_se_siente, contacto_con_virus, temperatura, fecha):
        """Registrar el estado diario de un empleado"""
        Empleado.agregar_estado_diario(empleado_id, como_se_siente, contacto_con_virus, temperatura, fecha)

    def obtener_ultimo_estado_diario(self, empleado_id):
        return Empleado.obtener_ultimo_estado_diario(empleado_id)
    
    def verificar_empleados_aptos(self):
        """Llama al modelo para obtener empleados aptos e inaptos"""
        return Empleado.verificar_empleados_aptos()
    
    def eliminar_empleado(self, empleado_id):
     Empleado.eliminar_empleado(empleado_id)
     self.empleados = Empleado.obtener_empleados()  # Actualizar la lista de empleados

    def actualizar_empleado(self, empleado_id, nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades):
     Empleado.actualizar_empleado(empleado_id, nombre, direccion, localidad, personas_con_que_convive, estado_salud, covid_positivo, nivel_afectacion, comorbilidades)
     self.empleados = Empleado.obtener_empleados()  # Actualizar la lista de empleados

    def obtener_empleado_por_id(self, empleado_id):
     for empleado in self.empleados:
        if empleado.id == empleado_id:
            return empleado
     return None

