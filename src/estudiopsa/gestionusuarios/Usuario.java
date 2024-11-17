package estudiopsa.gestionusuarios;

public class Usuario {
    // Atributos privados de la clase, que representan las propiedades de un usuario
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String correo; 
    private String contraseña;
    private String rol;

    // Constructor de la clase Usuario que inicializa los atributos
    public Usuario(Integer idUsuario, String nombre, String apellido, String correo, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Método para validar el inicio de sesión del usuario
    public boolean validarInicio(String correo, String contraseña) {
        // Compara el correo y la contraseña proporcionados con los del usuario
        return (this.correo.equals(correo) && this.contraseña.equals(contraseña));
    }

    // Método para mostrar un mensaje de error si el usuario no es encontrado o los datos son incorrectos
    public void mostrarMensajeError() {
        System.out.println("Error: Usuario no encontrado o datos incorrectos.");
    }

    // Método para mostrar un mensaje de confirmación cuando el usuario se registra correctamente
    public void mostrarConfirmacion() {
        System.out.println("Usuario registrado correctamente.");
    }

    // Métodos getter y setter para acceder y modificar los atributos privados de la clase

    public Integer getIdUsuario() {
        return idUsuario; // Retorna el idUsuario
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario; // Asigna un nuevo valor al idUsuario
    }

    public String getNombre() {
        return nombre; // Retorna el nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Asigna un nuevo valor al nombre
    }

    public String getApellido() {
        return apellido; // Retorna el apellido
    }

    public void setApellido(String apellido) {
        this.apellido = apellido; // Asigna un nuevo valor al apellido
    }

    public String getCorreo() {
        return correo; // Retorna el correo
    }

    public void setCorreo(String correo) {
        this.correo = correo; // Asigna un nuevo valor al correo
    }

    public String getContraseña() {
        return contraseña; // Retorna la contraseña
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña; // Asigna un nuevo valor a la contraseña
    }

    public String getRol() {
        return rol; // Retorna el rol
    }

    public void setRol(String rol) {
        this.rol = rol; // Asigna un nuevo valor al rol
    }
}