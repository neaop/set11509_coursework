package global;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

    public TableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
