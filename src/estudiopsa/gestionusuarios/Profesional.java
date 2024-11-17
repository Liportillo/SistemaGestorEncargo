package estudiopsa.gestionusuarios;

import estudiopsa.gestionusuarios.Usuario;

public class Profesional extends Usuario { // La clase Profesional hereda de la clase Usuario

    private String profesion; // Atributo que almacena la profesión del profesional

    // Constructor de la clase Profesional que recibe varios parámetros
    public Profesional(Integer idUsuario, String nombre, String apellido, String correo, String contraseña, String rol, String profesion) {
        super(idUsuario, nombre, apellido, correo, contraseña, rol); // Llama al constructor de la clase base Usuario
        this.profesion = profesion; // Inicializa el atributo profesion con el valor proporcionado
    }

    // Método para asignar una tarea al profesional
    public void asignarTarea(String tarea) {
        System.out.println("Tarea asignada a " + getNombre() + ": " + tarea); // Imprime un mensaje de asignación de tarea
    }
}