class ReporteDesempeño {
    private Empleado empleado;

    public ReporteDesempeño(Empleado empleado) {
        this.empleado = empleado;
    }

    public String generarReporte() {
        int eficiencia = (int) (Math.random() * 50) + 50;
        int puntualidad = (int) (Math.random() * 50) + 50;
        int productividad = (int) (Math.random() * 50) + 50;

        return "Empleado: " + empleado.getNombre() + " " + empleado.getApellido() +
               "\nEficiencia: " + eficiencia + "%" +
               "\nPuntualidad: " + puntualidad + "%" +
               "\nProductividad: " + productividad + "%" +
               "\n---------------------------------------";
    }
}