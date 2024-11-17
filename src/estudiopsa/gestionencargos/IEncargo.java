package estudiopsa.gestionencargos;

import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de encargos

public interface IEncargo { // Definición de la interfaz IEncargo

    // Método para registrar un nuevo encargo en el sistema
    void registrarEncargo(Encargo encargo);

    // Método para modificar la información de un encargo existente
    void modificarEncargo(Encargo encargo);

    // Método para cerrar un encargo utilizando su identificador
    void cerrarEncargo(Integer id);

    // Método para obtener la lista de todos los encargos en el sistema
    ArrayList<Encargo> obtenerEncargos();
}