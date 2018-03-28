package global.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class ShareTraderTable extends JTable {

    public void updateTable(Vector data, Vector columnNames) {
        TableModel tableModel = new ShareTraderTableModel(data, columnNames);
        this.setModel(tableModel);
        this.setSelectionModel(new ForcedListSelectionModel());
        this.setRowSelectionInterval(0, 0);
        this.setAutoCreateRowSorter(true);
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

    class ForcedListSelectionModel extends DefaultListSelectionModel {

        ForcedListSelectionModel() {
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        @Override
        public void clearSelection() {
        }

        @Override
        public void removeSelectionInterval(int index0, int index1) {
        }
    }
}
