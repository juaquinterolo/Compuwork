package test;

import vista.PanelEmpleados;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelEmpleadosTest {

    @Test
    public void testInicializacionPanelEmpleados() {
        PanelEmpleados panel = new PanelEmpleados();
        Component[] components = panel.getComponents();

        boolean tieneFormulario = false;
        boolean tieneTabla = false;

        for (Component c : components) {
            if (c instanceof JScrollPane) tieneTabla = true;
            if (c instanceof JPanel && ((JPanel) c).getLayout() instanceof GridLayout) tieneFormulario = true;
        }

        assertTrue(tieneFormulario, "El panel debe tener un formulario.");
        assertTrue(tieneTabla, "El panel debe tener una tabla.");
    }
    
    @Test
    void testAgregarEmpleadoAumentaCantidadDeFilas() {
        int filasIniciales = panel.tabla.getRowCount();

        panel.txtId.setText("1001");
        panel.txtNombre.setText("Carlos");
        panel.txtApellido.setText("Lopez");
        panel.tipoEmpleado.setSelectedItem("Permanente");

        panel.btnAgregar.doClick();

        int filasFinales = panel.tabla.getRowCount();
        assertEquals(filasIniciales + 1, filasFinales);
    }

    @Test
    void testEliminarEmpleadoReduceCantidadDeFilas() {
        panel.txtId.setText("2002");
        panel.txtNombre.setText("Ana");
        panel.txtApellido.setText("Garcia");
        panel.tipoEmpleado.setSelectedItem("Temporal");
        panel.btnAgregar.doClick();

        int filasAntes = panel.tabla.getRowCount();

        panel.tabla.setRowSelectionInterval(filasAntes - 1, filasAntes - 1);

        panel.btnEliminar.doClick();

        int filasDespues = panel.tabla.getRowCount();
        assertEquals(filasAntes - 1, filasDespues);
    }
    

}
