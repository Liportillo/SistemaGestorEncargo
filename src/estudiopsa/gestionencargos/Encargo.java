package estudiopsa.gestionencargos;

import ConexionBD.ConexionBD; // Importación de la clase ConexionBD para manejar la conexión a la base de datos
import java.sql.*; // Importación de las clases de SQL para trabajar con bases de datos
import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de encargos

public class Encargo { // Definición de la clase Encargo
    private Integer idEncargo;  // Identificador único del encargo
    private String fecha;        // Fecha del encargo
    private String descripcion;  // Descripción del encargo
    private String prioridad;     // Prioridad del encargo
    private String estado;        // Estado del encargo
    private Integer idCliente;    // Identificador del cliente asociado al encargo

    // Constructor que inicializa los atributos del encargo
    public Encargo(Integer idEncargo, String fecha, String descripcion, String prioridad, String estado, Integer idCliente) {
        this.idEncargo = idEncargo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.idCliente = idCliente; 
    }

    // Método para guardar el encargo en la base de datos
    public void guardar() {
        // SQL para insertar un nuevo encargo en la tabla 'encargo'
        String sql = "INSERT INTO encargo (descripcion, fechaInicio, fechaFin, estado, idCliente, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, descripcion); // Descripción del encargo
            pstmt.setDate(2, Date.valueOf(fecha)); // Fecha de inicio del encargo
            pstmt.setDate(3, Date.valueOf(java.time.LocalDate.now().plusDays(7))); // Fecha de fin (7 días después de hoy)
            pstmt.setString(4, estado); // Estado del encargo
            pstmt.setInt(5, idCliente); // ID del cliente asociado
            pstmt.setInt(6, 1); // ID del usuario (valor fijo en este caso)
            pstmt.executeUpdate(); // Ejecutar la inserción
            System.out.println("Encargo guardado correctamente."); // Mensaje de éxito
        } catch (SQLException e) {
            System.out.println("Error al guardar encargo: " + e.getMessage()); // Manejo de excepciones
        }
    }

    // Método estático para obtener todos los encargos de la base de datos
    public static ArrayList<Encargo> obtenerEncargos() {
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

    // Método para mostrar el formulario del encargo en la consola
    public void mostrarFormulario() {
        System.out.println("Formulario de Encargo:"); // Encabezado del formulario
        System.out.println("ID Encargo: " + idEncargo); // Mostrar ID del encargo
        System.out.println("Descripcion: " + descripcion); // Mostrar descripción del encargo
        System.out.println("Prioridad: " + prioridad); // Mostrar prioridad del encargo
        System.out.println("Estado: " + estado); // Mostrar estado del encargo
        System.out.println("ID Cliente: " + idCliente); // Mostrar ID del cliente asociado al encargo
    }

    // Métodos getter y setter para acceder y modificar los atributos de la clase
    public Integer getIdEncargo() {
        return idEncargo; // Retornar el ID del encargo
    }

    public void setIdEncargo(Integer idEncargo) {
        this.idEncargo = idEncargo; // Establecer el ID del encargo
    }

    public String getFecha() {
        return fecha; // Retornar la fecha del encargo
    }

    public void setFecha(String fecha) {
        this.fecha = fecha; // Establecer la fecha del encargo
    }

    public String getDescripcion() {
        return descripcion; // Retornar la descripción del encargo
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Establecer la descripción del encargo
    }

    public String getPrioridad() {
        return prioridad; // Retornar la prioridad del encargo
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad; // Establecer la prioridad del encargo
    }

    public String getEstado() {
        return estado; // Retornar el estado del encargo
    }

    public void setEstado(String estado) {
        this.estado = estado; // Establecer el estado del encargo
    }

    public Integer getIdCliente() {
        return idCliente; // Retornar el ID del cliente asociado al encargo
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente; // Establecer el ID del cliente asociado al encargo
    }
}