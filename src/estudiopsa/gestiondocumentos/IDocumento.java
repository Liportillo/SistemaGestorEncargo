package estudiopsa.gestiondocumentos;

import java.util.ArrayList; // Importación de la clase ArrayList para manejar listas de documentos

public interface IDocumento { // Definición de la interfaz IDocumento

    // Método para cargar un nuevo documento en el sistema
    void cargarDocumento(Documento documento);

    // Método para actualizar la información de un documento existente
    void actualizarDocumento(Documento documento);

    // Método para obtener un documento específico utilizando su identificador
    Documento obtenerDocumento(Integer id);

    // Método para obtener la lista de todos los documentos en el sistema
    ArrayList<Documento> obtenerDocumentos();
}