package test;

import vista.PanelDepartamentos;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelDepartamentosTest {

    @Test
    public void testInicializacionPanelDepartamentos() {
        PanelDepartamentos panel = new PanelDepartamentos();
        Component[] components = panel.getComponents();

        boolean tieneFormulario = false;
        boolean tieneTabla = false;

        for (Component c : components) {
            if (c instanceof JScrollPane) tieneTabla = true;
            if (c instanceof JPanel && ((JPanel) c).getLayout() instanceof GridLayout) tieneFormulario = true;
        }

        assertTrue(tieneFormulario, "El panel debe tener campos de entrada.");
        assertTrue(tieneTabla, "El panel debe tener una tabla.");
    }

    
    @Test
    void testAgregarDepartamentoNoDuplicaId() {
        panel.txtId.setText("500");
        panel.txtNombre.setText("Contabilidad");
        panel.btnAgregar.doClick();

        panel.txtId.setText("500");
        panel.txtNombre.setText("Duplicado");
        panel.btnAgregar.doClick();

        long conteo = panel.modelo.getDataVector().stream()
                .filter(row -> row.get(0).equals(500))
                .count();

        assertEquals(1, conteo, "No debería haber dos departamentos con el mismo ID");
    }

    @Test
    void testEditarDepartamentoCambiaDatos() {
        panel.txtId.setText("888");
        panel.txtNombre.setText("Temporal");
        panel.btnAgregar.doClick();

        int fila = panel.tabla.getRowCount() - 1;
        panel.tabla.setRowSelectionInterval(fila, fila);
        panel.btnEditar.doClick();

        String nuevoNombre = (String) panel.tabla.getValueAt(fila, 1);
        assertNotEquals("Temporal", nuevoNombre, "El nombre debería haber cambiado tras la edición.");
    }
}
