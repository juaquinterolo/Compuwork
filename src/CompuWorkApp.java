import javax.swing.*;
import modelo.Departamento;
import modelo.Empleado; 
import modelo.EmpleadoPermanente; 
import modelo.EmpleadoTemporal;
import vista.*;

public class CompuWorkApp extends JFrame {

    public CompuWorkApp() {
        setTitle("CompuWork - Gestión Empresarial");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Gestión de Empleados", new PanelEmpleados());
        tabbedPane.addTab("Gestión de Departamentos", new PanelDepartamentos());
        tabbedPane.addTab("Reportes de Desempeño", new PanelReportes());

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CompuWorkApp::new);
    }
}
