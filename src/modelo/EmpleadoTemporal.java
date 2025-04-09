package modelo;

public class EmpleadoTemporal extends Empleado {
    public EmpleadoTemporal(int id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    @Override
    public String getTipo() {
        return "Temporal";
    }
}
