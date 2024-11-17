package estudiopsa.gestionreportes;

import estudiopsa.gestionreportes.Reporte; // Importación de la clase Reporte
import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de reportes

public class GestorReporte implements IReporte { // Definición de la clase GestorReporte que implementa la interfaz IReporte

    @Override
    public void generarReporte(Reporte reporte) {
        // SQL para insertar un nuevo reporte en la tabla 'reporte'
        String sql = "INSERT INTO reporte (formato, fechaReporte, rutaReporte, idEncargo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, reporte.getFormato()); // Formato del reporte
            pstmt.setDate(2, Date.valueOf(reporte.getFechaGeneracion())); // Fecha del reporte
            pstmt.setString(3, "ruta/del/reporte"); // Ruta del archivo del reporte (valor por defecto)
            pstmt.setInt(4, 1); // ID del encargo asociado (valor fijo en este caso)
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Reporte generado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al generar reporte: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public Reporte obtenerReporte(Integer id) {
        // SQL para obtener un reporte específico por su ID
        String sql = "SELECT * FROM reporte WHERE idReporte=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Establecer el ID del reporte a buscar
            ResultSet rs = pstmt.executeQuery(); // Ejecutar la consulta
            if (rs.next()) { // Si se encuentra un reporte
                return new Reporte(
                        rs.getInt("idReporte"),
                        rs.getString("formato"),
                        rs.getString("fechaReporte"),
                        rs.getString("rutaReporte")
                ); // Retornar el objeto Reporte creado
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte: " + e.getMessage()); // Manejo de excepciones
        }
        return null; // Retornar null si no se encuentra el reporte
    }

    @Override
    public void eliminarReporte(Integer id) {
        // SQL para eliminar un reporte de la tabla 'reporte'
        String sql = "DELETE FROM reporte WHERE idReporte=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Establecer el ID del reporte a eliminar
            pstmt.executeUpdate(); // Ejecutar la eliminación
            System.out.println("Reporte eliminado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al eliminar reporte: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public ArrayList<Reporte> obtenerReportes() {
        ArrayList<Reporte> reportes = new ArrayList<>(); // Lista para almacenar los reportes
        String sql = "SELECT * FROM reporte"; // SQL para obtener todos los reportes
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { // Iterar sobre los resultados
                // Crear un nuevo objeto Reporte a partir de los datos obtenidos
                Reporte reporte = new Reporte(
                        rs.getInt("idReporte"),
                        rs.getString("formato"),
                        rs.getString("fechaReporte"),
                        rs.getString("rutaReporte")
                );
                reportes.add(reporte); // Agregar el reporte a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes: " + e.getMessage()); // Manejo de excepciones
        }
        return reportes; // Retornar la lista de reportes
    }
}
