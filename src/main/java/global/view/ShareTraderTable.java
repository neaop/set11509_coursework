package global.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class ShareTraderTable extends JTable {

    public void updateTable(Vector data, Vector columnNames) {
        TableModel tableModel = new ShareTraderTableModel(data, columnNames);
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowSelectionInterval(0, 0);
    }

    class ShareTraderTableModel extends DefaultTableModel {

        ShareTraderTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

}