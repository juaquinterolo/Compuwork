package modelo;

public class EmpleadoPermanente extends Empleado {
    public EmpleadoPermanente(int id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    @Override
    public String getTipo() {
        return "Permanente";
    }
}

