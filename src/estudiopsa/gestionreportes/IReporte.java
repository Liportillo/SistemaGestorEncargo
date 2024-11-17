package estudiopsa.gestionreportes;

import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de reportes

public interface IReporte { // Definición de la interfaz IReporte

    // Método para generar un nuevo reporte en el sistema
    void generarReporte(Reporte reporte);

    // Método para obtener un reporte específico utilizando su identificador
    Reporte obtenerReporte(Integer id);

    // Método para eliminar un reporte del sistema utilizando su identificador
    void eliminarReporte(Integer id);

    // Método para obtener la lista de todos los reportes en el sistema
    ArrayList<Reporte> obtenerReportes();
}