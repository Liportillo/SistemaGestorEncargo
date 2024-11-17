package estudiopsa.gestionusuarios;

import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de usuarios

public interface IUsuario { // Definición de la interfaz IUsuario

    // Método para registrar un nuevo usuario en el sistema
    void registrarUsuario(Usuario usuario);

    // Método para modificar la información de un usuario existente
    void modificarUsuario(Usuario usuario);

    // Método para eliminar un usuario del sistema utilizando su identificador
    void eliminarUsuario(Integer id);

    // Método para iniciar sesión, validando el correo y la contraseña del usuario
    Usuario iniciarSesion(String correo, String contraseña);

    // Método para obtener la lista de todos los usuarios registrados en el sistema
    ArrayList<Usuario> getUsuarios();

    // Método para visualizar la lista de profesionales en el sistema
    void verProfesionales();

    // Método para visualizar la lista de administradores en el sistema
    void verAdministradores();

    // Método para registrar un nuevo cliente en el sistema
    void registrarCliente(Cliente cliente);

    // Método para obtener la lista de todos los clientes registrados en el sistema
    void verClientes();
}
