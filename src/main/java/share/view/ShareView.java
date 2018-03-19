package share.view;

import share.ShareController;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ShareView extends Observable implements Observer {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JPanel panelTable;
    private JButton buttonRegister;
    private JButton buttonMenu;
    private JPanel panelButton;
    private JScrollPane panelScroll;

    TableModel tableModel;
    private String[] columnNames = {
            "Share ID",
            "Trade Code",
            "Company Name",
            "Share Price",
            "Share Value",
            "Share Quantity"};


    public ShareView() {
        frame = new JFrame("ShareView");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void populateTable(Vector data) {
        tableModel = new global.TableModel(data
                , new Vector<>(Arrays.asList(columnNames)));
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionInterval(0, 0);
    }

    public void showRegisterDialog(String message) {
        showMessageDialog(message);
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null
                , message, "Shares"
                , JOptionPane.INFORMATION_MESSAGE);
    }

    public void setRegisterButtonController(ShareController.TrackButtonListener
                                                    registerButtonController) {
        buttonRegister.addActionListener(registerButtonController);
    }

    public void setMenuButtonController(ActionListener listener) {
        buttonMenu.addActionListener(listener);
    }

    public int getSelectedShareId() {
        int selectedRow;
        selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            selectedRow = 0;
        }
        return (int) table.getValueAt(selectedRow, 0);
    }

    @Override
    public void update(Observable o, Object arg) {
        populateTable((Vector) arg);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout(0, 0));
        panel.add(panelTable, BorderLayout.CENTER);
        panelScroll = new JScrollPane();
        panelScroll.setHorizontalScrollBarPolicy(31);
        panelScroll.setVerticalScrollBarPolicy(22);
        panelTable.add(panelScroll, BorderLayout.CENTER);
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(1000, 250));
        table.setRowSelectionAllowed(true);
        table.setShowVerticalLines(true);
        panelScroll.setViewportView(table);
        panelButton = new JPanel();
        panelButton.setLayout(new GridBagLayout());
        panel.add(panelButton, BorderLayout.SOUTH);
        buttonRegister = new JButton();
        buttonRegister.setText("Track Share");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButton.add(buttonRegister, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButton.add(spacer1, gbc);
        buttonMenu = new JButton();
        buttonMenu.setText("Main Menu");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButton.add(buttonMenu, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
