from flask import Flask, render_template, url_for, redirect
from forms import AñadirEstudianteForm, EliminarEstudianteForm, AñadirNotaForm, EliminarNotaForm, LoginForm
from backend.Users import login_user, users_dict, eliminar_usuario, agregar_usuario, agregar_nota, eliminar_notas, mostrar_notas

app = Flask(__name__)
app.config['SECRET_KEY'] = 'keyultrasecreta'

# este users es un ejemplo, hay que reemplazarlo

@app.route("/", methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.is_submitted():
        name = str(form.username.data)
        pwd = str(form.password.data)

        if(login_user(name, pwd)):
            if name == 'admin':
                return redirect(url_for('admin'))
            else:
                return estudiante(name)
        else:
            pass
    return render_template('login.html', form=form)

@app.route("/estudiante")
def estudiante(name):
    notas = mostrar_notas(name)
    total = 0
    for nota in notas:
        total += nota["nota"] * nota["creditos"]
    return render_template('estudiante.html', total=total, notas=notas)

@app.route("/admin")
def admin():
    return render_template('admin/index.html')

@app.route("/añadir_estudiante", methods=['GET', 'POST'])
def añadir_estudiante():
    form = AñadirEstudianteForm()
    if form.is_submitted():
        pwd = str(form.password.data)
        name = str(form.username.data)

        usuario = {
            "name": name,
            "password": pwd,
            "notas": []
        }

        agregar_usuario(usuario)
        return redirect(url_for('añadir_estudiante'))
    return render_template('admin/añadir_estudiante.html', form=form)

@app.route("/añadir_nota", methods=['GET', 'POST'])
def añadir_nota():
    form = AñadirNotaForm()
    if form.is_submitted():
        creditos = int(form.creditos.data)
        name = str(form.name.data)
        nota = float(form.nota.data)

        nueva_nota = {
            "name": name,
            "creditos": creditos,
            "nota": nota
        }

        agregar_nota(nueva_nota)

        return redirect(url_for('añadir_nota'))
    return render_template('admin/añadir_nota.html', form=form)

@app.route("/eliminar_estudiante", methods=['GET', 'POST'])
def eliminar_estudiante():
    form = EliminarEstudianteForm()
    if form.is_submitted():
        name = str(form.username.data)
        eliminar_usuario(name)

        return redirect(url_for('eliminar_estudiante'))
    return render_template('admin/eliminar_estudiante.html', form=form)

@app.route("/eliminar_nota", methods=['GET', 'POST'])
def eliminar_nota():
    form = EliminarNotaForm()
    if form.is_submitted():
        name = str(form.name.data)
        username = str(form.username.data)
        
        eliminar_notas(username, name)
        return redirect(url_for('eliminar_nota'))
    return render_template('admin/eliminar_nota.html', form=form)

@app.route("/lista_estudiantes")
def lista_estudiantes():
    return render_template('admin/lista_estudiantes.html', users=users_dict)



app.run(host='0.0.0.0', port=3030)