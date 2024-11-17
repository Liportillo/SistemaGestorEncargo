package estudiopsa;

import estudiopsa.gestiondocumentos.GestorDocumento;
import estudiopsa.gestionencargos.GestorEncargo;
import estudiopsa.gestionencargos.Encargo;
import estudiopsa.gestionreportes.GestorReporte;
import estudiopsa.gestionreportes.Reporte;
import estudiopsa.gestionusuarios.Administrador;
import estudiopsa.gestionusuarios.Cliente;
import estudiopsa.gestionusuarios.GestorUsuario;
import estudiopsa.gestionusuarios.Profesional;
import estudiopsa.gestionusuarios.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class EstudioPSA { // Clase principal

    public static void main(String[] args) {
        // Instancia de gestores para manejar documentos, encargos, reportes y usuarios
        GestorDocumento gestorDocumento = new GestorDocumento();
        GestorReporte gestorReporte = new GestorReporte();
        GestorEncargo gestorEncargo = new GestorEncargo();
        GestorUsuario gestorUsuario = new GestorUsuario();

        Scanner scanner = new Scanner(System.in); // Scanner para entrada de datos
        int opcion = 0; // Variable para la opción del menú

        do { // Bucle principal del menú
            try {
                // Mostrar menú de opciones
                System.out.println("Menu:");
                System.out.println("1. Iniciar Sesion");
                System.out.println("2. Salir");
                opcion = scanner.nextInt(); // Leer opción
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        iniciarSesion(scanner, gestorUsuario, gestorEncargo, gestorDocumento, gestorReporte); // Iniciar sesión
                        break;
                    case 2:
                        System.out.println("Saliendo del sistema..."); // Salir del programa
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo."); // Opción inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico."); // Manejo de excepciones
                scanner.next();
            }
        } while (opcion != 2); // Continuar hasta que el usuario elija salir
        scanner.close(); // Cerrar el scanner
    }

    private static void iniciarSesion(Scanner scanner, GestorUsuario gestorUsuario, GestorEncargo gestorEncargo, GestorDocumento gestorDocumento, GestorReporte gestorReporte) {
        // Solicitar credenciales y validar el inicio de sesión
        System.out.println("Ingrese correo electronico:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese contrasena:");
        String contraseña = scanner.nextLine();

        Usuario usuarioActual = gestorUsuario.iniciarSesion(correo, contraseña); // Iniciar sesión
        if (usuarioActual != null) {
            menuUsuario(scanner, usuarioActual, gestorUsuario, gestorEncargo, gestorDocumento, gestorReporte); // Mostrar menú de usuario
        } else {
            System.out.println("Error: Usuario no encontrado o datos incorrectos."); // Mensaje de error
        }
    }

    private static void menuUsuario(Scanner scanner, Usuario usuarioActual, GestorUsuario gestorUsuario, GestorEncargo gestorEncargo, GestorDocumento gestorDocumento, GestorReporte gestorReporte) {
        int opcion = 0; // Variable para la opción del menú

        do { // Bucle del menú del usuario
            try {
                // Mostrar menú de opciones para el usuario
                System.out.println("Menu:");
                System.out.println("1. Gestionar Usuarios");
                System.out.println("2. Asignar Tarea");
                System.out.println("3. Encargos y Documentos");
                System.out.println("4. Reportes");
                System.out.println("5. Registrar Cliente");
                System.out.println("6. Ver Clientes");
                System.out.println("7. Ver profesionales");
                System.out.println("8. Cerrar Sesion");
                opcion = scanner.nextInt(); // Leer opción
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        if (usuarioActual instanceof Administrador) {
                            gestionarUsuarios(scanner, gestorUsuario); // Solo administradores pueden gestionar usuarios
                        } else {
                            System.out.println("Acceso denegado. Solo los administradores pueden gestionar usuarios.");
                        }
                        break;
                    case 2:
                        asignarTarea(scanner, gestorUsuario); // Asignar tarea a un profesional
                        break;
                    case 3:
                        gestionarEncargos(scanner, gestorEncargo); // Gestionar encargos y documentos
                        break;
                    case 4:
                        gestionarReportes(scanner, gestorReporte); // Gestionar reportes
                        break;
                    case 5:
                        registrarCliente(gestorUsuario, scanner); // Registrar un nuevo cliente
                        break;
                    case 6:
                        gestorUsuario.verClientes(); // Ver lista de clientes
                        break;
                    case 7:
                        gestorUsuario.verProfesionales(); // Ver lista de profesionales
                        break;
                    case 8:
                        break; // Cerrar sesión
                    default:
                        System.out.println("Opción no válida. Intente de nuevo."); // Opción inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico."); // Manejo de excepciones
                scanner.next();
            }
        } while (opcion != 8); // Continuar hasta que el usuario elija cerrar sesión
    }

    private static void gestionarUsuarios(Scanner scanner, GestorUsuario gestorUsuario) {
        int opcion = 0; // Variable para la opción del menú
        do {
            try {
                // Mostrar menú de gestión de usuarios
                System.out.println("1. Registrar usuario");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Eliminar usuario");
                System.out.println("4. Ver administradores");
                System.out.println("5. Volver");
                opcion = scanner.nextInt(); // Leer opción
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarUsuario(scanner, gestorUsuario); // Registrar un nuevo usuario
                        break;
                    case 2:
                        modificarUsuario(scanner, gestorUsuario); // Modificar un usuario existente
                        break;
                    case 3:
                        eliminarUsuario(scanner, gestorUsuario); // Eliminar un usuario
                        break;
                    case 4:
                        List<Usuario> usuarios = gestorUsuario.getUsuarios(); // Obtener lista de usuarios
                        for (Usuario usuario : usuarios) {
                            System.out.println("ID: " + usuario.getIdUsuario() + ", Nombre: " + usuario.getNombre() + " " + usuario.getApellido() + ", Correo: " + usuario.getCorreo() + ", Rol: " + usuario.getRol());
                        }
                        break;
                    case 5:
                        break; // Volver al menú anterior
                    default:
                        System.out.println("Opción inválida. Por favor, elija una opción válida."); // Opción inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico."); // Manejo de excepciones
                scanner.next();
            }
        } while (opcion != 5); // Continuar hasta que el usuario elija volver
    }

    private static void registrarUsuario(Scanner scanner, GestorUsuario gestorUsuario) {
        // Solicitar datos para registrar un nuevo usuario
        System.out.println("Ingrese nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese correo electronico:");
        String correoNuevo = scanner.nextLine();
        System.out.println("Ingrese contrasena:");
        String contraseñaNueva = scanner.nextLine();

        System.out.println("Seleccione rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Profesional");
        int rolOpcion = scanner.nextInt();
        scanner.nextLine();

        String rol = rolOpcion == 1 ? "administrador" : "profesional"; // Asignar rol

        Usuario nuevoUsuario = new Usuario(null, nombre, apellido, correoNuevo, contraseñaNueva, rol); // Crear nuevo usuario
        gestorUsuario.registrarUsuario(nuevoUsuario); // Registrar usuario
        nuevoUsuario.mostrarConfirmacion(); // Mostrar confirmación
    }

    private static void modificarUsuario(Scanner scanner, GestorUsuario gestorUsuario) {
        // Solicitar ID y nuevos datos para modificar un usuario
        System.out.println("Ingrese ID de usuario:");
        Integer idUsuario = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nuevo nombre:");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese nuevo apellido:");
        String nuevoApellido = scanner.nextLine();
        System.out.println("Ingrese nuevo correo electronico:");
        String nuevoCorreo = scanner.nextLine();
        System.out.println("Ingrese nueva contrasena:");
        String nuevaContraseña = scanner.nextLine();
        System.out.println("Ingrese nuevo rol:");
        String nuevoRol = scanner.nextLine();

        Usuario usuarioModificado = new Usuario(idUsuario, nuevoNombre, nuevoApellido, nuevoCorreo, nuevaContraseña, nuevoRol); // Crear usuario modificado
        gestorUsuario.modificarUsuario(usuarioModificado); }

    private static void eliminarUsuario(Scanner scanner, GestorUsuario gestorUsuario) {
        // Solicitar ID de usuario para eliminar
        System.out.println("Ingrese ID de usuario:");
        Integer idUsuarioEliminar = scanner.nextInt();
        gestorUsuario.eliminarUsuario(idUsuarioEliminar); // Eliminar usuario
    }

    private static void asignarTarea(Scanner scanner, GestorUsuario gestorUsuario) {
        // Asignar tarea a un profesional
        System.out.println("Ingrese ID de profesional al que desea asignarle la tarea:");
        Integer idUsuarioAsignar = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese tarea:");
        String tarea = scanner.nextLine();
        for (Usuario usuario : gestorUsuario.getUsuarios()) {
            if (usuario.getIdUsuario().equals(idUsuarioAsignar)) {
                if (usuario instanceof Profesional) {
                    ((Profesional) usuario).asignarTarea(tarea); // Asignar tarea
                    System.out.println("Tarea asignada exitosamente.");
                } else {
                    System.out.println("El usuario no es un profesional."); // Mensaje de error
                }
            }
        }
    }

    private static void gestionarEncargos(Scanner scanner, GestorEncargo gestorEncargo) {
        int opcion = 0; // Variable para la opción del menú
        do {
            try {
                // Mostrar menú de gestión de encargos
                System.out.println("1. Registrar encargo");
                System.out.println("2. Modificar encargo");
                System.out.println("3. Cerrar encargo");
                System.out.println("4. Obtener encargos");
                System.out.println("5. Volver");
                opcion = scanner.nextInt(); // Leer opción
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        registrarEncargo(scanner, gestorEncargo); // Registrar un nuevo encargo
                        break;
                    case 2:
                        modificarEncargo(scanner, gestorEncargo); // Modificar un encargo existente
                        break;
                    case 3:
                        cerrarEncargo(scanner, gestorEncargo); // Cerrar un encargo
                        break;
                    case 4:
                        obtenerEncargos(gestorEncargo); // Obtener lista de encargos
                        break;
                    case 5:
                        break; // Volver al menú anterior
                    default:
                        System.out.println("Opción inválida. Por favor, elija una opción válida."); // Opción inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico."); // Manejo de excepciones
                scanner.next();
            }
        } while (opcion != 5); // Continuar hasta que el usuario elija volver
    }

    private static void registrarEncargo(Scanner scanner, GestorEncargo gestorEncargo) {
        // Solicitar datos para registrar un nuevo encargo
        System.out.println("Ingrese fecha (YYYY-MM-DD):");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese descripción:");
        String descripcion = scanner.nextLine();

        System.out.println("Seleccione prioridad:");
        System.out.println("1. Urgente");
        System.out.println("2. Común");
        int prioridadOpcion = scanner.nextInt();
        scanner.nextLine();
        String prioridad = (prioridadOpcion == 1) ? "urgente" : "común"; // Asignar prioridad

        System.out.println("Seleccione estado:");
        System.out.println("1. Pendiente");
        System.out.println("2. En revisión");
        System.out.println("3. Finalizado");
        int estadoOpcion = scanner.nextInt();
        scanner.nextLine();
        String estado = "";
        switch (estadoOpcion) {
            case 1:
                estado = "pendiente";
                break;
            case 2:
                estado = "en revisión";
                break;
            case 3:
                estado = "finalizado";
                break;
            default:
                System.out.println("Opción no válida. Se asignará estado como 'pendiente'.");
                estado = "pendiente";
                break;
        }

        System.out.println("Ingrese ID del cliente:");
        Integer idCliente = scanner.nextInt();
        scanner.nextLine();

        Encargo nuevoEncargo = new Encargo(null, fecha, descripcion, prioridad, estado, idCliente); // Crear nuevo encargo
        gestorEncargo.registrarEncargo(nuevoEncargo); // Registrar encargo
    }

    private static void modificarEncargo(Scanner scanner, GestorEncargo gestorEncargo) {
        // Solicitar ID y nuevos datos para modificar un encargo
        System.out.println("Ingrese ID de encargo:");
        Integer idEncargo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nueva fecha (YYYY-MM-DD):");
        String nuevaFecha = scanner.nextLine();
        System.out.println("Ingrese nueva descripción:");
        String nuevaDescripcion = scanner.nextLine();
        System.out.println("Ingrese nueva prioridad:");
        String nuevaPrioridad = scanner.nextLine();
        System.out.println("Ingrese nuevo estado:");
        String nuevoEstado = scanner.nextLine();
        System.out.println("Ingrese ID del cliente:");
        Integer idClienteModificado = scanner.nextInt();
        scanner.nextLine();

        Encargo encargoModificado = new Encargo(idEncargo, nuevaFecha, nuevaDescripcion, nuevaPrioridad, nuevoEstado, idClienteModificado); // Crear encargo modificado
        gestorEncargo.modificarEncargo(encargoModificado); // Modificar encargo
    }

    private static void cerrarEncargo(Scanner scanner, GestorEncargo gestorEncargo) {
        // Solicitar ID de encargo para cerrar
        System.out.println("Ingrese ID de encargo:");
        Integer idEncargoCerrar = scanner.nextInt();
        gestorEncargo.cerrarEncargo(idEncargoCerrar); // Cerrar encargo
    }

    private static void obtenerEncargos(GestorEncargo gestorEncargo) {
        List<Encargo> listaEncargos = gestorEncargo.obtenerEncargos(); // Obtener lista de encargos
        System.out.println("Lista de Encargos:");
        for (Encargo encargo : listaEncargos) {
            System.out.println("ID: " + encargo.getIdEncargo() + ", Descripción: " + encargo.getDescripcion()
                    + ", Prioridad: " + encargo.getPrioridad() + ", Estado: " + encargo.getEstado()
                    + ", ID Cliente: " + encargo.getIdCliente()); // Mostrar detalles de cada encargo
        }
    }

    private static void gestionarReportes(Scanner scanner, GestorReporte gestorReporte) {
        int opcion = 0; // Variable para la opción del menú
        do {
            try {
                // Mostrar menú de gestión de reportes
                System.out.println("1. Generar reporte");
                System.out.println("2. Obtener reporte");
                System.out.println("3. Eliminar reporte");
                System.out.println("4. Volver");
                opcion = scanner.nextInt(); // Leer opción
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        generarReporte(scanner, gestorReporte); // Generar un nuevo reporte
                        break;
                    case 2:
                        System.out.println("Ingrese ID de reporte:");
                        Integer idReporte = scanner.nextInt();
                        Reporte reporteObtenido = gestorReporte.obtenerReporte(idReporte); // Obtener reporte por ID
                        if (reporteObtenido != null) {
                            reporteObtenido.mostrarFormulario(); // Mostrar detalles del reporte
                        } else {
                            System.out.println("Reporte no encontrado."); // Mensaje de error
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese ID de reporte:");
                        Integer idReporteEliminar = scanner.nextInt();
                        gestorReporte.eliminarReporte(idReporteEliminar); // Eliminar reporte
                        break;
                    case 4:
                        break; // Volver al menú anterior
                    default:
                        System.out.println("Opción inválida. Por favor, elija una opción válida."); // Opción inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico."); // Manejo de excepciones
                scanner.next();
            }
        } while (opcion != 4); // Continuar hasta que el usuario elija volver
    }

    private static void generarReporte(Scanner scanner, GestorReporte gestorReporte) {
        // Solicitar datos para generar un nuevo reporte
        System.out.println("Seleccione el tipo de reporte:");
        System.out.println("1. Reporte jurídico");
        System.out.println("2. Reporte contable");
        System.out.println("3. Reporte jurídico-contable");
        int tipoReporteOpcion = scanner.nextInt();
        scanner.nextLine();

        String formatoReporte = "";
        switch (tipoReporteOpcion) {
            case 1:
                formatoReporte = "Reporte jurídico"; // Asignar tipo de reporte
                break;
            case 2:
                formatoReporte = "Reporte contable"; // Asignar tipo de reporte
                break;
            case 3:
                formatoReporte = "Reporte jurídico-contable"; // Asignar tipo de reporte
                break;
            default:
                System.out.println("Opción no válida. Se asignará tipo de reporte como 'Reporte jurídico'.");
                formatoReporte = "Reporte jurídico"; // Valor por defecto
                break;
        }
        System.out.println("Ingrese fecha de gener acion:");
        String fechaGeneracion = scanner.nextLine();
        System.out.println("Ingrese datos del reporte:");
        String datosReporte = scanner.nextLine();

        System.out.println("Ingrese el ID del encargo:");
        Integer idEncargo = scanner.nextInt();
        scanner.nextLine();

        Reporte nuevoReporte = new Reporte(null, formatoReporte, fechaGeneracion, datosReporte); // Crear nuevo reporte
        nuevoReporte.setIdEncargo(idEncargo); // Asignar ID del encargo
        gestorReporte.generarReporte(nuevoReporte); // Generar reporte
    }

    private static void registrarCliente(GestorUsuario gestorUsuario, Scanner scanner) {
        // Solicitar datos para registrar un nuevo cliente
        System.out.println("Seleccione tipo de cliente:");
        System.out.println("1. Persona");
        System.out.println("2. Empresa");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        String nombre = null;
        String apellido = null;
        String razonSocial = null;

        if (tipoCliente == 1) { // Persona
            System.out.println("Ingrese nombre:");
            nombre = scanner.nextLine();
            System.out.println("Ingrese apellido:");
            apellido = scanner.nextLine();
        } else if (tipoCliente == 2) { // Empresa
            System.out.println("Ingrese razón social:");
            razonSocial = scanner.nextLine();
        } else {
            System.out.println("Opción no válida.");
            return; // Salir si la opción es inválida
        }

        System.out.println("Ingrese correo:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese dirección:");
        String direccion = scanner.nextLine();

        System.out.println("Seleccione estado:");
        System.out.println("1. Activo");
        System.out.println("2. Inactivo");
        int estadoOpcion = scanner.nextInt();
        String estado = (estadoOpcion == 1) ? "activo" : "inactivo"; // Asignar estado

        Cliente nuevoCliente = new Cliente(null, nombre, apellido, razonSocial, correo, telefono, direccion, java.sql.Date.valueOf(java.time.LocalDate.now()), tipoCliente == 1 ? "persona" : "empresa", estado); // Crear nuevo cliente
        gestorUsuario.registrarCliente(nuevoCliente); // Registrar cliente
    }
}