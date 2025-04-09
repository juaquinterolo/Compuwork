package vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import modelo.Departamento;
import modelo.Empleado; 
import modelo.EmpleadoPermanente; 
import modelo.EmpleadoTemporal;
import java.util.ArrayList;
import java.util.List;

public class PanelDepartamentos extends JPanel {
    private List<Departamento> departamentos;
    private DefaultTableModel departamentoTableModel;

    public PanelDepartamentos() {
        this.departamentos = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();

        formulario.add(new JLabel("ID Departamento:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);

        JButton btnAgregar = new JButton("Agregar Departamento");
        JButton btnEliminar = new JButton("Eliminar Departamento");
        formulario.add(btnAgregar);
        formulario.add(btnEliminar);

        add(formulario, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre"};
        departamentoTableModel = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(departamentoTableModel);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();

                Departamento d = new Departamento(id, nombre);
                departamentos.add(d);
                departamentoTableModel.addRow(new Object[]{id, nombre});

                txtId.setText("");
                txtNombre.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                departamentos.remove(fila);
                departamentoTableModel.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un departamento para eliminar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
