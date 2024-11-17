package estudiopsa.gestionencargos;

import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de encargos

public class GestorEncargo implements IEncargo { // Definición de la clase GestorEncargo que implementa la interfaz IEncargo

    @Override
    public void registrarEncargo(Encargo encargo) {
        // SQL para insertar un nuevo encargo en la tabla 'encargo'
        String sql = "INSERT INTO encargo (descripcion, fechaInicio, fechaFin, estado, idCliente, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, encargo.getDescripcion()); // Descripción del encargo
            pstmt.setDate(2, Date.valueOf(encargo.getFecha())); // Fecha de inicio del encargo
            pstmt.setDate(3, Date.valueOf(java.time.LocalDate.now().plusDays(7))); // Fecha de fin (7 días después de hoy)
            pstmt.setString(4, encargo.getEstado()); // Estado del encargo
            pstmt.setInt(5, encargo.getIdCliente()); // ID del cliente asociado
            pstmt.setInt(6, 1); // ID del usuario (valor fijo en este caso)
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Encargo registrado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al registrar encargo: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void modificarEncargo(Encargo encargo) {
        // SQL para actualizar un encargo existente en la tabla 'encargo'
        String sql = "UPDATE encargo SET descripcion=?, fechaInicio=?, fechaFin=?, estado=? WHERE idEncargo=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, encargo.getDescripcion()); // Descripción del encargo
            pstmt.setDate(2, Date.valueOf(encargo.getFecha())); // Fecha de inicio del encargo
            pstmt.setDate(3, Date.valueOf(java.time.LocalDate.now().plusDays(7))); // Fecha de fin (7 días después de hoy)
            pstmt.setString(4, encargo.getEstado()); // Estado del encargo
            pstmt.setInt(5, encargo.getIdEncargo()); // ID del encargo a modificar
            pstmt.executeUpdate(); // Ejecutar la actualización
            System.out.println("Encargo modificado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al modificar encargo: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void cerrarEncargo(Integer id) {
        // SQL para actualizar el estado de un encargo a 'finalizado'
        String sql = "UPDATE encargo SET estado='finalizado' WHERE idEncargo=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Establecer el ID del encargo a cerrar
            pstmt.executeUpdate(); // Ejecutar la actualización
            System.out.println("Encargo finalizado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al cerrar encargo: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public ArrayList<Encargo> obtenerEncargos() {
        ArrayList<Encargo> encargos = new ArrayList<>(); // Lista para almacenar los encargos
        // SQL para obtener todos los encargos junto con el nombre del cliente
        String sql = "SELECT e.idEncargo, e.descripcion, e.prioridad, e.estado, e.idCliente, c.nombre AS clienteNombre "
                + "FROM encargo e "
                + "JOIN cliente c ON e.idCliente = c.idCliente";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { // Iterar sobre los resultados
                // Crear un nuevo objeto Encargo a partir de los datos obtenidos
                Encargo encargo = new Encargo(
                        rs.getInt("idEncargo"),
                        null, // Fecha no se obtiene en esta consulta
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getString("estado"),
                        rs.getInt("idCliente")
                );
                encargos.add(encargo); // Agregar el encargo a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener encargos: " + e.getMessage()); // Manejo de excepciones
        }
        return encargos; // Retornar la lista de encargos
    }
}
