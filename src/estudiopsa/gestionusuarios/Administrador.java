package estudiopsa.gestionusuarios;

public class Administrador extends Usuario {

    public Administrador(Integer idUsuario, String nombre, String apellido, String correo, String contraseña, String rol) {
        super(idUsuario, nombre, apellido, correo, contraseña, rol);
    }

    public void registrarUsuario(GestorUsuario gestor, Usuario usuario) {
        gestor.registrarUsuario(usuario);
    }

    public void modificarUsuario(GestorUsuario gestor, Usuario usuario) {
        gestor.modificarUsuario(usuario);
    }

    public void eliminarUsuario(GestorUsuario gestor, Integer id) {
        gestor.eliminarUsuario(id);
    }
}