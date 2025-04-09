package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import modelo.Empleado; 
import modelo.EmpleadoPermanente; 
import modelo.EmpleadoTemporal;

public class PanelEmpleados extends JPanel {
    private List<Empleado> empleados;
    private DefaultTableModel empleadoTableModel;

    public PanelEmpleados() {
        this.empleados = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JComboBox<String> tipoEmpleado = new JComboBox<>(new String[]{"Permanente", "Temporal"});

        formulario.add(new JLabel("ID:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Apellido:"));
        formulario.add(txtApellido);
        formulario.add(new JLabel("Tipo de Empleado:"));
        formulario.add(tipoEmpleado);

        JButton btnAgregar = new JButton("Agregar Empleado");
        JButton btnEditar = new JButton("Editar Empleado");
        JButton btnEliminar = new JButton("Eliminar Empleado");

        formulario.add(btnAgregar);
        formulario.add(btnEditar);

        add(formulario, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Apellido", "Tipo"};
        empleadoTableModel = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(empleadoTableModel);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnEliminar);
        add(panelInferior, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String tipo = tipoEmpleado.getSelectedItem().toString();

                Empleado empleado = tipo.equals("Permanente") ? new EmpleadoPermanente(id, nombre, apellido)
                        : new EmpleadoTemporal(id, nombre, apellido);

                empleados.add(empleado);
                empleadoTableModel.addRow(new Object[]{id, nombre, apellido, tipo});

                txtId.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Nuevo ID:", tabla.getValueAt(fila, 0)));
                    String nombre = JOptionPane.showInputDialog("Nuevo Nombre:", tabla.getValueAt(fila, 1));
                    String apellido = JOptionPane.showInputDialog("Nuevo Apellido:", tabla.getValueAt(fila, 2));
                    String tipo = (String) JOptionPane.showInputDialog(this, "Nuevo Tipo:", "Editar Tipo",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"Permanente", "Temporal"}, tabla.getValueAt(fila, 3));

                    empleados.set(fila, tipo.equals("Permanente") ? new EmpleadoPermanente(id, nombre, apellido)
                            : new EmpleadoTemporal(id, nombre, apellido));

                    empleadoTableModel.setValueAt(id, fila, 0);
                    empleadoTableModel.setValueAt(nombre, fila, 1);
                    empleadoTableModel.setValueAt(apellido, fila, 2);
                    empleadoTableModel.setValueAt(tipo, fila, 3);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado para editar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                empleados.remove(fila);
                empleadoTableModel.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}