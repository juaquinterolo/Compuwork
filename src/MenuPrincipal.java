import java.util.*;

class MenuPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Empleado> empleados = new ArrayList<>();
    private static List<Departamento> departamentos = new ArrayList<>();

    public static void main(String[] args) {
        departamentos.add(new Departamento(1, "Recursos Humanos"));
        departamentos.add(new Departamento(2, "Ventas"));
        departamentos.add(new Departamento(3, "IT"));

        int opcion;
        do {
            System.out.println("\n======= MENÚ DE GESTIÓN =======");
            System.out.println("1. Añadir Empleado");
            System.out.println("2. Editar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Crear Departamento");
            System.out.println("5. Modificar Departamento");
            System.out.println("6. Eliminar Departamento");
            System.out.println("7. Asignar Empleado a Departamento");
            System.out.println("8. Listar Empleados por Departamento");
            System.out.println("9. Generar Reporte de Desempeño Individual");
            System.out.println("10. Generar Reporte de Desempeño por Departamento");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> añadirEmpleado();
                case 2 -> editarEmpleado();
                case 3 -> eliminarEmpleado();
                case 4 -> crearDepartamento();
                case 5 -> modificarDepartamento();
                case 6 -> eliminarDepartamento();
                case 7 -> asignarEmpleadoADepartamento();
                case 8 -> listarEmpleadosPorDepartamento();
                case 9 -> generarReporteIndividual();
                case 10 -> generarReportePorDepartamento();
                case 11 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 11);
    }

    // ✅ 1. Añadir Empleado
    private static void añadirEmpleado() {
        System.out.println("\n-- Añadir Empleado --");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Tipo (1: Permanente, 2: Temporal): ");
        int tipo = scanner.nextInt();

        Empleado nuevoEmpleado;
        if (tipo == 1) {
            nuevoEmpleado = new EmpleadoPermanente(id, nombre, apellido);
        } else if (tipo == 2) {
            nuevoEmpleado = new EmpleadoTemporal(id, nombre, apellido);
        } else {
            System.out.println("Opción inválida. No se creó el empleado.");
            return;
        }

        empleados.add(nuevoEmpleado);
        System.out.println("Empleado agregado: " + nuevoEmpleado);
    }

    // ✅ 2. Editar Empleado
    private static void editarEmpleado() {
        System.out.println("\n-- Editar Empleado --");
        System.out.print("Ingrese el ID del empleado a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Empleado empleado = buscarEmpleado(id);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (deje en blanco para no cambiar): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) empleado.setNombre(nuevoNombre);

        System.out.print("Nuevo apellido (deje en blanco para no cambiar): ");
        String nuevoApellido = scanner.nextLine();
        if (!nuevoApellido.isEmpty()) empleado.setApellido(nuevoApellido);

        System.out.print("Cambiar tipo (1: Permanente, 2: Temporal, 0: No cambiar): ");
        int tipo = scanner.nextInt();
        if (tipo == 1 && !(empleado instanceof EmpleadoPermanente)) {
            empleados.remove(empleado);
            empleado = new EmpleadoPermanente(empleado.getId(), empleado.getNombre(), empleado.getApellido());
            empleados.add(empleado);
        } else if (tipo == 2 && !(empleado instanceof EmpleadoTemporal)) {
            empleados.remove(empleado);
            empleado = new EmpleadoTemporal(empleado.getId(), empleado.getNombre(), empleado.getApellido());
            empleados.add(empleado);
        }

        System.out.println("Empleado actualizado: " + empleado);
    }

    // ✅ 3. Eliminar Empleado
    private static void eliminarEmpleado() {
        System.out.println("\n-- Eliminar Empleado --");
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Empleado empleado = buscarEmpleado(id);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        empleados.remove(empleado);
        System.out.println("Empleado eliminado.");
    }

    // ✅ 4. Crear Departamento
    private static void crearDepartamento() {
        System.out.println("\n-- Crear Departamento --");
        System.out.print("ID del Departamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre del Departamento: ");
        String nombre = scanner.nextLine();

        Departamento nuevoDepartamento = new Departamento(id, nombre);
        departamentos.add(nuevoDepartamento);
        System.out.println("Departamento creado: " + nuevoDepartamento.getNombre());
    }

    // ✅ 5. Modificar Departamento
    private static void modificarDepartamento() {
        mostrarDepartamentos();
        System.out.print("Ingrese el ID del departamento a modificar: ");
        int idDep = scanner.nextInt();
        scanner.nextLine();
        Departamento departamento = buscarDepartamento(idDep);

        if (departamento == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre del departamento: ");
        String nuevoNombre = scanner.nextLine();
        departamento.setNombre(nuevoNombre);
        System.out.println("Departamento actualizado: " + departamento.getNombre());
    }

    // ✅ 6. Eliminar Departamento
    private static void eliminarDepartamento() {
        mostrarDepartamentos();
        System.out.print("Ingrese el ID del departamento a eliminar: ");
        int idDep = scanner.nextInt();
        scanner.nextLine();
        Departamento departamento = buscarDepartamento(idDep);

        if (departamento == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        departamentos.remove(departamento);
        System.out.println("Departamento eliminado.");
    }

    // ✅ 7. Asignar Empleado a Departamento
    private static void asignarEmpleadoADepartamento() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos disponibles.");
            return;
        }

        System.out.println("\n-- Asignar Empleado a Departamento --");
        System.out.print("Ingrese el ID del empleado: ");
        int idEmp = scanner.nextInt();
        Empleado empleado = buscarEmpleado(idEmp);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        mostrarDepartamentos();
        System.out.print("Seleccione el ID del departamento: ");
        int idDep = scanner.nextInt();
        Departamento departamento = buscarDepartamento(idDep);

        if (departamento == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        departamento.agregarEmpleado(empleado);
    }

    // ✅ 8. Listar Empleados por Departamento
    private static void listarEmpleadosPorDepartamento() {
        mostrarDepartamentos();
        System.out.print("Ingrese el ID del departamento: ");
        int idDep = scanner.nextInt();
        scanner.nextLine();
        Departamento departamento = buscarDepartamento(idDep);

        if (departamento == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        departamento.listarEmpleados();
    }
    // ✅ 9. Generar Reporte de Desempeño Individual
    private static void generarReporteIndividual() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
    
        System.out.print("\nIngrese el ID del empleado para generar su reporte: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Empleado empleado = buscarEmpleado(id);
    
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
    
        // ✅ Aseguramos que `generarReporte()` devuelve una cadena antes de imprimir
        ReporteDesempeño reporte = new ReporteDesempeño(empleado);
        String contenidoReporte = reporte.generarReporte();
        System.out.println("\n===== Reporte de Desempeño Individual =====");
        System.out.println(contenidoReporte);
    }

    // ✅ 10. Generar Reporte de Desempeño por Departamento
private static void generarReportePorDepartamento() {
    if (departamentos.isEmpty()) {
        System.out.println("No hay departamentos registrados.");
        return;
    }

    mostrarDepartamentos();
    System.out.print("\nIngrese el ID del departamento para generar su reporte: ");
    int idDep = scanner.nextInt();
    scanner.nextLine();
    Departamento departamento = buscarDepartamento(idDep);

    if (departamento == null) {
        System.out.println("Departamento no encontrado.");
        return;
    }

    System.out.println("\n===== Reporte de Desempeño del Departamento: " + departamento.getNombre() + " =====");
    for (Empleado empleado : departamento.getEmpleados()) {
        ReporteDesempeño reporte = new ReporteDesempeño(empleado);
        String contenidoReporte = reporte.generarReporte();
        System.out.println(contenidoReporte);
    }
}


    // 🔹 Métodos auxiliares
    private static Empleado buscarEmpleado(int id) {
        return empleados.stream().filter(emp -> emp.getId() == id).findFirst().orElse(null);
    }

    private static Departamento buscarDepartamento(int id) {
        return departamentos.stream().filter(dep -> dep.getId() == id).findFirst().orElse(null);
    }

    private static void mostrarDepartamentos() {
        System.out.println("\n-- Lista de Departamentos --");
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            departamentos.forEach(dep -> System.out.println(dep.getId() + " - " + dep.getNombre()));
        }
    }
}