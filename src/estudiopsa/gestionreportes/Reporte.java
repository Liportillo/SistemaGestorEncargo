package estudiopsa.gestionreportes;

import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de reportes

public class Reporte { // Definición de la clase Reporte
    private Integer idReporte; // Identificador único del reporte
    private String formato; // Formato del reporte (ej. PDF, Excel)
    private String fechaGeneracion; // Fecha en que se generó el reporte
    private String datos; // Datos contenidos en el reporte
    private Integer idEncargo; // Identificador del encargo asociado al reporte

    // Constructor que inicializa los atributos del reporte
    public Reporte(Integer idReporte, String formato, String fechaGeneracion, String datos) {
        this.idReporte = idReporte;
        this.formato = formato;
        this.fechaGeneracion = fechaGeneracion;
        this.datos = datos;
    }

    // Método para establecer el ID del encargo asociado al reporte
    public void setIdEncargo(Integer idEncargo) {
        this.idEncargo = idEncargo;
    }

    // Método para guardar el reporte en la base de datos
    public void guardar() {
        // SQL para insertar un nuevo reporte en la tabla 'reporte'
        String sql = "INSERT INTO reporte (formato, fechaReporte, rutaReporte, idEncargo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, formato); 
            pstmt.setDate(2, Date.valueOf(fechaGeneracion)); // Convertir la fecha a formato SQL
            pstmt.setString(3, "ruta/del/reporte"); // Ruta del archivo del reporte (valor por defecto)
            pstmt.setInt(4, idEncargo); // ID del encargo asociado
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Reporte guardado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al guardar reporte: " + e.getMessage()); // Manejo de excepciones
        }
    }

    // Método estático para obtener todos los reportes de la base de datos
    public static ArrayList<Reporte> obtenerReportes() {
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

    // Método para mostrar el formulario del reporte en la consola
    public void mostrarFormulario() {
        System.out.println("Formulario de Reporte:"); // Encabezado del formulario
        System.out.println("ID Reporte: " + idReporte); // Mostrar ID del reporte
        System.out.println("Formato: " + formato); // Mostrar formato del reporte
        System.out.println("Fecha Generacion: " + fechaGeneracion); // Mostrar fecha de generación
        System.out.println("Datos: " + datos); // Mostrar datos del reporte
    }

    // Métodos getter y setter para acceder y modificar los atributos privados de la clase
    public Integer getIdReporte() {
        return idReporte; // Retorna el idReporte
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte; // Asigna un nuevo valor al idReporte
    }

    public String getFechaGeneracion() {
        return fechaGeneracion; // Retorna la fechaGeneracion
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion; // Asigna un nuevo valor a la fecha Generacion
    }

    public String getDatos() {
        return datos; // Retorna los datos del reporte
    }

    public void setDatos(String datos) {
        this.datos = datos; // Asigna un nuevo valor a los datos del reporte
    }

    public String getFormato() {
        return formato; // Retorna el formato del reporte
    }

    public void setFormato(String formato) {
        this.formato = formato; // Asigna un nuevo valor al formato del reporte
    }
}