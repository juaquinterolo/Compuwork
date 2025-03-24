import java.util.ArrayList;
import java.util.List;

class Departamento {
    private int id;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado " + empleado.getNombre() + " asignado a " + nombre);
    }

    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en este departamento.");
        } else {
            System.out.println("\nEmpleados en " + nombre + ":");
            for (Empleado emp : empleados) {
                System.out.println(emp.getId() + " - " + emp.getNombre() + " " + emp.getApellido());
            }
        }
    }
}