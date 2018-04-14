package broker;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import global.GlobalControlCodes;
import global.ShareTraderTable;
import global.View;
import global.ViewStrings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;

/**
 * View utilised by the Broker module to display broker data.
 */
public class BrokerViewForm implements View, Serializable {
    private JFrame frame;
    private JPanel contentPane;
    private JTable table;
    private JButton buttonHistory;
    private JButton buttonMenu;

    /**
     * Default constructor of broker View.
     */
    public BrokerViewForm() {
        frame = new JFrame("MenuView");
        $$$setupUI$$$();
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    /**
     * Updates the View's JTable with broker data.
     *
     * @param data the Vector of data to be displayed
     */
    private void populateTable(Vector data) {
        Vector columnNames = ViewStrings.getBrokerColumnNames();
        ((ShareTraderTable) table).updateTable(data, columnNames);
    }

    /**
     * Sets a Controller as the View's ActionListener.
     *
     * @param actionListener the Controller used to listen to the View
     */
    public void addActionListeners(ActionListener actionListener) {
        addCloseListener(actionListener);
        addHistoryListener(actionListener);
    }

    /**
     * Sets a ActionListener for the close button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void addCloseListener(ActionListener actionListener) {
        System.out.println("BrokerView: add close listener");
        buttonMenu.addActionListener(actionListener);
        buttonMenu.setActionCommand(GlobalControlCodes.BROKER_CLOSE.name());
    }

    /**
     * Sets a ActionListener for the history button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void addHistoryListener(ActionListener actionListener) {
        System.out.println("BrokerView: add history listener");
        buttonHistory.addActionListener(actionListener);
        buttonHistory.setActionCommand(GlobalControlCodes.BROKER_HISTORY.name());
    }

    /**
     * Extracts the name of the currently selected broker.
     *
     * @return the selected broker's name
     */
    public String getBrokerName() {
        return (String) table.getValueAt(table.getSelectedRow(), 1);
    }

    @Override
    public void showView() {
        frame.setVisible(true);
    }

    @Override
    public void closeView() {
        frame.dispose();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Vector) {
            populateTable((Vector) arg);
        }
    }

    /**
     * Instantiation method for custom UI components.
     */
    private void createUIComponents() {
        table = new ShareTraderTable();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonMenu = new JButton();
        buttonMenu.setText("Menu");
        panel1.add(buttonMenu, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonHistory = new JButton();
        buttonHistory.setText("History");
        panel1.add(buttonHistory, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table.setPreferredScrollableViewportSize(new Dimension(750, 200));
        scrollPane1.setViewportView(table);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
