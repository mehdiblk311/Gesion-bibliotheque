package JUnitTests;

import org.junit.Test;
import static org.junit.Assert.*;
import Utility.*;

public class ModelTableauTest {

    @Test
    public void testGetColumnCount() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        assertEquals(2, model.getColumnCount());
    }

    @Test
    public void testGetRowCount() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        assertEquals(2, model.getRowCount());
    }

    @Test
    public void testGetValueAt() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        assertEquals("b", model.getValueAt(1, 0));
        assertEquals(2, model.getValueAt(1, 1));
    }

    @Test
    public void testGetColumnName() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        assertEquals("Col1", model.getColumnName(0));
        assertEquals("Col2", model.getColumnName(1));
    }

    @Test
    public void testIsCellEditable() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        assertFalse(model.isCellEditable(0, 0));
        assertFalse(model.isCellEditable(1, 1));
    }

    @Test
    public void testRemoveRow() {
        Object[][] data = {{"a", 1}, {"b", 2}, {"c", 3}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        model.removeRow(1);
        assertEquals(2, model.getRowCount());
        assertEquals("a", model.getValueAt(0, 0));
        assertEquals(1, model.getValueAt(0, 1));
        assertEquals("c", model.getValueAt(1, 0));
        assertEquals(3, model.getValueAt(1, 1));
    }

    @Test
    public void testAddRow() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau model = new ModelTableau(data, title);
        model.addRow(new Object[]{"c", 3});
        assertEquals(3, model.getRowCount());
        assertEquals("c", model.getValueAt(0, 0));
        assertEquals(3, model.getValueAt(0, 1));
        assertEquals("a", model.getValueAt(1, 0));
        assertEquals(1, model.getValueAt(1, 1));
        assertEquals("b", model.getValueAt(2, 0));
        assertEquals(2, model.getValueAt(2, 1));
    }

    @Test
    public void testUpdateRow() {
        Object[][] data = {{"a", 1}, {"b", 2}};
        String[] title = {"Col1", "Col2"};
        ModelTableau modelTableau = new ModelTableau(data, title);
        Object[] updatedRow = {"c", 3};
        int rowToUpdate = 0;
        modelTableau.updateRow(updatedRow, rowToUpdate);
        Object[][] expectedData = {{"c", 3}, {"b", 2}};
        assertArrayEquals(expectedData, modelTableau.getData());
    }


    
    }
