package global;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.Serializable;
import java.util.Vector;

/**
 * Custom extension of the JTable class to ensure consistency.
 *
 * @see JTable
 */
public class ShareTraderTable extends JTable implements Serializable {

    /**
     * Updates the table to display data and column headers.
     *
     * @param data        the information to displayed
     * @param columnNames the data column titles
     */
    public void updateTable(Vector data, Vector columnNames) {
        try {
            TableModel tableModel = new ShareTraderTableModel(data, columnNames);
            this.setModel(tableModel);
            this.setSelectionModel(new ForcedListSelectionModel());
            this.setRowSelectionInterval(0, 0);
            this.setAutoCreateRowSorter(true);
        } catch (IllegalArgumentException e) {
            System.out.println("ShareTraderTable: No data to display");
        }

    }

    /**
     * Custom extension of DefaultTableModel to ensure consistency.
     *
     * @see DefaultTableModel
     */
    class ShareTraderTableModel extends DefaultTableModel {
        ShareTraderTableModel(Vector data, Vector columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    /**
     * Custom extension of the DefaultListSelectionModel to ensure consistency.
     *
     * @see DefaultListSelectionModel
     */
    class ForcedListSelectionModel extends DefaultListSelectionModel {

        /**
         * Default class constructor.
         */
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
