package modelo;

public abstract class Empleado {
    protected int id;
    protected String nombre;
    protected String apellido;

    public Empleado(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public abstract String getTipo();
}
