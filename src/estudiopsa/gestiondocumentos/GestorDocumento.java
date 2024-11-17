package estudiopsa.gestiondocumentos;

import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de documentos

public class GestorDocumento implements IDocumento { // Definición de la clase GestorDocumento que implementa la interfaz IDocumento

    @Override
    public void cargarDocumento(Documento documento) {
        // SQL para insertar un nuevo documento en la tabla 'documento'
        String sql = "INSERT INTO documento (nombreDocumento, fechaSubida, rutaArchivo, estado, idEncargo, idTipoDoc) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, documento.getEtiqueta()); // Nombre del documento
            pstmt.setDate(2, Date.valueOf(java.time.LocalDate.now())); // Fecha de subida (hoy)
            pstmt.setString(3, "ruta/del/archivo"); // Ruta del archivo (valor fijo en este caso)
            pstmt.setString(4, "pendiente"); // Estado inicial del documento
            pstmt.setInt(5, documento.getIdEncargo()); // ID del encargo asociado
            pstmt.setInt(6, 1); // ID del tipo de documento (valor fijo en este caso)
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Documento guardado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al guardar documento: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public void actualizarDocumento(Documento documento) {
        // SQL para actualizar un documento existente en la tabla 'documento'
        String sql = "UPDATE documento SET nombreDocumento=?, estado=? WHERE idDocumento=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, documento.getEtiqueta()); // Nombre del documento
            pstmt.setString(2, documento.getEstado()); // Estado del documento
            pstmt.setInt(3, documento.getIdDocumento()); // ID del documento a actualizar
            pstmt.executeUpdate(); // Ejecutar la actualización
            System.out.println("Documento actualizado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al actualizar documento: " + e.getMessage()); // Manejo de excepciones
        }
    }

    @Override
    public Documento obtenerDocumento(Integer id) {
        // SQL para obtener un documento específico por su ID
        String sql = "SELECT * FROM documento WHERE idDocumento=?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Establecer el ID del documento a buscar
            ResultSet rs = pstmt.executeQuery(); // Ejecutar la consulta
            if (rs.next()) { // Si hay resultados
                // Crear y retornar un nuevo objeto Documento a partir de los datos obtenidos
                return new Documento(
                        rs.getInt("idDocumento"),
                        rs.getString("tipo"),
                        rs.getString("nombreDocumento"),
                        rs.getString("rutaArchivo"),
                        rs.getInt("idEncargo"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener documento: " + e.getMessage()); // Manejo de excepciones
        }
        return null; // Retornar null si no se encuentra el documento
    }

    @Override
    public ArrayList<Documento> obtenerDocumentos() {
        ArrayList<Documento> documentos = new ArrayList<>(); // Lista para almacenar los documentos
        // SQL para obtener todos los documentos de la tabla 'documento'
        String sql = "SELECT * FROM documento";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { // Iterar sobre los resultados
                // Crear un nuevo objeto Documento a partir de los datos obtenidos
                Documento documento = new Documento(
                        rs.getInt("idDocumento"),
                        rs.getString("tipo"),
                        rs.getString("nombreDocumento"),
                        rs.getString("rutaArchivo"),
                        rs.getInt("idEncargo"),
                        rs.getString("estado")
                );
                documentos .add(documento); // Agregar el documento a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener documentos: " + e.getMessage()); // Manejo de excepciones
        }
        return documentos; // Retornar la lista de documentos
    }
}