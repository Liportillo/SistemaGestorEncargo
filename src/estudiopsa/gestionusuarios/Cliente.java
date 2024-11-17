package estudiopsa.gestionusuarios;

import java.sql.Date; // Importación de la clase Date para manejar fechas en formato SQL

public class Cliente { // Definición de la clase Cliente
    // Atributos privados de la clase que representan las propiedades de un cliente
    private Integer idCliente;      // Identificador único del cliente
    private String nombre;          // Nombre del cliente
    private String apellido;        // Apellido del cliente
    private String razonSocial;     // Razón social del cliente (para clientes empresariales)
    private String correo;          // Correo electrónico del cliente
    private String telefono;        // Número de teléfono del cliente
    private String direccion;       // Dirección del cliente
    private Date fechaRegistro;     // Fecha en que se registró el cliente
    private String tipoCliente;     // Tipo de cliente (ej. individual, empresa, etc.)
    private String estado;          // Estado del cliente (ej. activo, inactivo, etc.)

    // Constructor de la clase Cliente que inicializa los atributos
    public Cliente(Integer idCliente, String nombre, String apellido, String razonSocial, String correo, String telefono, String direccion, Date fechaRegistro, String tipoCliente, String estado) {
        this.idCliente = idCliente;          // Asigna el idCliente al atributo correspondiente
        this.nombre = nombre;                // Asigna el nombre al atributo correspondiente
        this.apellido = apellido;            // Asigna el apellido al atributo correspondiente
        this.razonSocial = razonSocial;      // Asigna la razón social al atributo correspondiente
        this.correo = correo;                // Asigna el correo al atributo correspondiente
        this.telefono = telefono;            // Asigna el teléfono al atributo correspondiente
        this.direccion = direccion;          // Asigna la dirección al atributo correspondiente
        this.fechaRegistro = fechaRegistro;  // Asigna la fecha de registro al atributo correspondiente
        this.tipoCliente = tipoCliente;      // Asigna el tipo de cliente al atributo correspondiente
        this.estado = estado;                // Asigna el estado al atributo correspondiente
    }

    // Métodos getter y setter para acceder y modificar los atributos privados de la clase

    public Integer getIdCliente() {
        return idCliente; // Retorna el idCliente
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente; // Asigna un nuevo valor al idCliente
    }

    public String getNombre() {
        return nombre; // Retorna el nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Asigna un nuevo valor al nombre
    }

    public String getApellido() {
        return apellido; // Retorna el apellido
    }

    public void setApellido(String apellido) {
        this.apellido = apellido; // Asigna un nuevo valor al apellido
    }

    public String getRazonSocial() {
        return razonSocial; // Retorna la razón social
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial; // Asigna un nuevo valor a la razón social
    }

    public String getCorreo() {
        return correo; // Retorna el correo
    }

    public void setCorreo(String correo) {
        this.correo = correo; // Asigna un nuevo valor al correo
    }

    public String getTelefono() {
        return telefono; // Retorna el teléfono
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; // Asigna un nuevo valor al teléfono
    }

    public String getDireccion() {
        return direccion; // Retorna la dirección
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion; // Asigna un nuevo valor a la dirección
    }

    public Date getFechaRegistro() {
        return fechaRegistro; // Retorna la fecha de registro
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro; // Asigna un nuevo valor a la fecha de registro
    }

    public String getTipoCliente() {
        return tipoCliente; // Retorna el tipo de cliente
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente; // Asigna un nuevo valor al tipo de cliente
    }

    public String getEstado() {
        return estado; // Retorna el estado
    }

    public void setEstado(String estado) {
        this.estado = estado; // Asigna un nuevo valor al estado
    }
}