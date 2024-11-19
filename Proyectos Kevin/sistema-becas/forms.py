from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField, IntegerField, FloatField

class AñadirEstudianteForm(FlaskForm):
    username = StringField('Usuario')
    password = PasswordField('Contraseña')
    submit = SubmitField('Añadir')

class EliminarEstudianteForm(FlaskForm):
    username = StringField('Usuario')
    submit = SubmitField('Eliminar')

class AñadirNotaForm(FlaskForm):
    name = StringField('Nombre')
    creditos = IntegerField('Creditos')
    nota = FloatField('Nota')
    submit = SubmitField('Añadir')

class EliminarNotaForm(FlaskForm):
    username = StringField('Usuario')
    name = StringField('Nombre')
    submit = SubmitField('Eliminar')

class LoginForm(FlaskForm):
    username = StringField('Usuario')
    password = PasswordField('Contraseña')
    submit = SubmitField('Iniciar Sesión')