package estudiopsa.gestiondocumentos;

public class Documento { // Definición de la clase Documento
    private Integer idDocumento; // Identificador único del documento
    private String tipo; // Tipo de documento (por ejemplo, informe, contrato, etc.)
    private String etiqueta; // Etiqueta que describe o clasifica el documento
    private String cliente; // Nombre del cliente asociado al documento
    private Integer idEncargo; // Identificador del encargo relacionado con el documento
    private String estado; // Estado del documento (por ejemplo, activo, archivado, etc.)

    // Constructor que inicializa los atributos del documento
    public Documento(Integer idDocumento, String tipo, String etiqueta, String cliente, Integer idEncargo, String estado) {
        this.idDocumento = idDocumento;
        this.tipo = tipo;
        this.etiqueta = etiqueta;
        this.cliente = cliente;
        this.idEncargo = idEncargo;
        this.estado = estado; 
    }

    // Métodos getter y setter para acceder y modificar los atributos de la clase

    public Integer getIdDocumento() {
        return idDocumento; // Retornar el ID del documento
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento; // Establecer el ID del documento
    }

    public String getTipo() {
        return tipo; // Retornar el tipo del documento
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // Establecer el tipo del documento
    }

    public String getEtiqueta() {
        return etiqueta; // Retornar la etiqueta del documento
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta; // Establecer la etiqueta del documento
    }

    public String getCliente() {
        return cliente; // Retornar el nombre del cliente asociado al documento
    }

    public void setCliente(String cliente) {
        this.cliente = cliente; // Establecer el nombre del cliente asociado al documento
    }

    public Integer getIdEncargo() {
        return idEncargo; // Retornar el ID del encargo relacionado con el documento
    }

    public void setIdEncargo(Integer idEncargo) {
        this.idEncargo = idEncargo; // Establecer el ID del encargo relacionado con el documento
    }

    public String getEstado() {
        return estado; // Retornar el estado del documento
    }

    public void setEstado(String estado) {
        this.estado = estado; // Establecer el estado del documento
    }
}