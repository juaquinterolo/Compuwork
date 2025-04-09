package vista;
import javax.swing.*;
import java.awt.*;
import modelo.Departamento;
import modelo.Empleado; 
import modelo.EmpleadoPermanente; 
import modelo.EmpleadoTemporal;

public class PanelReportes extends JPanel {
    public PanelReportes() {
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnReporteIndividual = new JButton("Generar Reporte Individual");
        JButton btnReporteDepartamento = new JButton("Generar Reporte por Departamento");

        btnReporteIndividual.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del empleado:");
            if (nombre != null && !nombre.isBlank()) {
                JOptionPane.showMessageDialog(this, "Generando reporte de desempeño para: " + nombre);
            }
        });

        btnReporteDepartamento.addActionListener(e -> {
            String departamento = JOptionPane.showInputDialog(this, "Ingrese el nombre del departamento:");
            if (departamento != null && !departamento.isBlank()) {
                JOptionPane.showMessageDialog(this, "Generando reporte de desempeño para el departamento: " + departamento);
            }
        });

        add(btnReporteIndividual);
        add(btnReporteDepartamento);
    }
}