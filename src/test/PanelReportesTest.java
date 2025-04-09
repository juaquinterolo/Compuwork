package test;

import vista.PanelReportes;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;

public class PanelReportesTest {

    @Test
    public void testBotonesReportesExisten() {
        PanelReportes panel = new PanelReportes();
        boolean tieneBotonIndividual = false;
        boolean tieneBotonDepartamento = false;

        for (Component c : panel.getComponents()) {
            if (c instanceof JPanel) {
                for (Component sub : ((JPanel) c).getComponents()) {
                    if (sub instanceof JButton) {
                        String texto = ((JButton) sub).getText();
                        if (texto.contains("Individual")) tieneBotonIndividual = true;
                        if (texto.contains("Departamento")) tieneBotonDepartamento = true;
                    }
                }
            }
        }
        assertTrue(tieneBotonIndividual, "Debe haber un botón para reporte individual.");
        assertTrue(tieneBotonDepartamento, "Debe haber un botón para reporte por departamento.");
    }
    
    @Test
    void testBotonGenerarIndividualFunciona() {
        assertDoesNotThrow(() -> panel.btnGenerarIndividual.doClick());
    }

    @Test
    void testBotonGenerarPorDepartamentoFunciona() {
        assertDoesNotThrow(() -> panel.btnGenerarPorDepartamento.doClick());
    }
    

}