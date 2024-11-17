package estudiopsa.gestionusuarios;

import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de usuarios

public class GestorUsuario implements IUsuario { // Definición de la clase GestorUsuario que implementa la interfaz IUsuario

    @Override
    public void registrarUsuario(Usuario usuario) {
        // SQL para insertar un nuevo usuario en la tabla 'usuario'
        String sql = "INSERT INTO usuario (nombre, apellido, nombreUsuario, contraseña, correo, rol, fechaCreacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getCorreo());
            pstmt.setString(4, usuario.getContraseña());
            pstmt.setString(5, usuario.getCorreo());
            pstmt.setString(6, usuario.getRol());
            pstmt.setDate(7, Date.valueOf(java.time.LocalDate.now()));
            pstmt.setString(8, "activo"); // Estado inicial del usuario
            pstmt.executeUpdate(); // Ejecutar la actualización
            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        // SQL para actualizar la información de un usuario existente
        String sql = "UPDATE usuario SET nombre=?, apellido=?, contraseña=?, rol=? WHERE idUsuario=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getContraseña());
            pstmt.setString(4, usuario.getRol());
            pstmt.setInt(5, usuario.getIdUsuario()); // ID del usuario a modificar
            pstmt.executeUpdate(); // Ejecutar la actualización
            System.out.println("Usuario modificado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void eliminarUsuario(Integer id) {
        // SQL para eliminar un usuario de la tabla 'usuario'
        String sql = "DELETE FROM usuario WHERE idUsuario=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // ID del usuario a eliminar
            pstmt.executeUpdate(); // Ejecutar la eliminación
            System.out.println("Usuario eliminado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public Usuario iniciarSesion(String correo, String contraseña) {
        // SQL para buscar un usuario que coincida con el correo y la contraseña
        String sql = "SELECT * FROM usuario WHERE correo=? AND contraseña=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, correo);
            pstmt.setString(2, contraseña);
            ResultSet rs = pstmt.executeQuery(); // Ejecutar la consulta
            if (rs.next()) { // Si se encuentra un usuario
                String rol = rs.getString("rol");
                String apellido = rs.getString("apellido");
                // Crear un objeto Usuario según el rol del usuario
                if (rol.equals("administrador")) {
                    return new Administrador(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            apellido,
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rol
                    );
                } else {
                    return new Profesional(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            apellido,
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rol,
                            "profesión" // Valor por defecto para la profesión
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en inicio de sesión: " + e.getMessage()); // Manejo de excepciones
        }
        System.out.println("Error: Usuario no encontrado o datos incorrectos."); // Mensaje de error si no se encuentra el usuario
        return null; // Retorna null si no se encuentra el usuario
    }

    @Override
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista para almacenar los usuarios
        String sql = "SELECT * FROM usuario"; // SQL para obtener todos los usuarios
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { // Iterar sobre los resultados
                String rol = rs.getString("rol");
                Usuario usuario;
                // Crear un objeto Usuario según el rol del usuario
                if (rol.equals("administrador")) {
                    usuario = new Administrador(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rol
                    );
                } else {
                    usuario = new Profesional(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rol,
                            "profesión" // Valor por defecto para la profesión
                    );
                }
                usuarios.add(usuario); // Agregar el usuario a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage()); // Manejo de excepciones
        }
        return usuarios; // Retornar la lista de usuarios
    }

    @Override
    public void verProfesionales() {
        // SQL para obtener usuarios con rol de 'contador' o 'abogado'
        String sql = "SELECT * FROM usuario WHERE rol IN ('contador', 'abogado')";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Lista de profesionales:"); // Mensaje de encabezado
            while (rs.next()) { // Iterar sobre los resultados
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                Integer idUsuario = rs.getInt("idUsuario");
                String rol = rs.getString("rol");

                // Imprimir la información de cada profesional
                System.out.println("ID: " + idUsuario + ", Nombre: " + nombre + " " + apellido + ", Correo: " + correo + ", Rol: " + rol);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener profesionales: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void verAdministradores() {
        // SQL para obtener usuarios con rol de 'administrador'
        String sql = "SELECT * FROM usuario WHERE rol = 'administrador'";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Lista de administradores:"); // Mensaje de encabezado
            while (rs.next()) { // Iterar sobre los resultados
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                Integer idUsuario = rs.getInt("idUsuario");

                // Imprimir la información de cada administrador
                System.out.println("ID: " + idUsuario + ", Nombre: " + nombre + " " + apellido + ", Correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener administradores: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        // SQL para insertar un nuevo cliente en la tabla 'cliente'
        String sql = "INSERT INTO cliente (nombre, apellido, razonSocial, correo, telefono, direccion, fechaRegistro, tipoCliente, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getRazonSocial());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setString(5, cliente.getTelefono());
            pstmt.setString(6, cliente.getDireccion());
            pstmt.setDate(7, cliente.getFechaRegistro()); // Fecha de registro del cliente
            pstmt.setString(8, cliente.getTipoCliente()); // Tipo de cliente
            pstmt.setString(9, cliente.getEstado()); // Estado del cliente
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Cliente registrado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void verClientes() {
        // SQL para obtener todos los clientes
        String sql = "SELECT * FROM cliente";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Lista de clientes:"); // Mensaje de encabezado
            while (rs.next()) { // Iterar sobre los resultados
                Integer idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String razonSocial = rs.getString("razonSocial");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");

                // Imprimir la información de cada cliente
                System.out.println("ID: " + idCliente + ", Nombre: " + nombre + " " + apellido + ", Razón Social: " + razonSocial + ", Correo: " + correo + ", Teléfono: " + telefono + ", Dirección: " + direccion + ", Estado: " + estado);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage()); // Manejo de excepciones
        }
    }
}
