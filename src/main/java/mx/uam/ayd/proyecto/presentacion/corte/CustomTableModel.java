package mx.uam.ayd.proyecto.presentacion.corte;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{
	private boolean[] editableColumns; // Array para almacenar la información de edición de cada columna
    private Object[] defaultValues; // Array para almacenar los valores predeterminados de cada columna

    public CustomTableModel(Object[][] data, Object[] columnNames, boolean[] editableColumns, Object[] defaultValues) {
        super(data, columnNames);
        this.editableColumns = editableColumns;
        this.defaultValues = defaultValues;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna el tipo de datos para cada columna del JTable
    	if(columnIndex == 0)
    		return String.class;
    	else
    		return Double.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Verifica si la celda es editable o no basado en el array editableColumns
        return editableColumns[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        // Retorna el valor predeterminado si la celda está deshabilitada
        if (!editableColumns[column]) {
            return defaultValues[column];
        }
        return super.getValueAt(row, column);
    } 
}
