package broker.view;

import global.controller.GlobalControlCodes;
import global.view.ShareTraderTable;
import global.view.View;
import global.view.ViewStrings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Vector;

public class BrokerViewForm implements View {
    private JFrame frame;
    private JPanel contentPane;
    private JButton buttonMainMenu;
    private JTable table;
    private JButton buttonOK;

    public BrokerViewForm() {
        frame = new JFrame("MenuView");
        $$$setupUI$$$();
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void populateTable(Vector data) {
        Vector columnNames = ViewStrings.getBrokerColumnNames();
        ((ShareTraderTable) table).updateTable(data, columnNames);
    }

    public void addActionListeners(ActionListener actionListener) {
        addCloseListener(actionListener);
    }

    private void addCloseListener(ActionListener actionListener) {
        System.out.println("BrokerView: add close listener");
        buttonMainMenu.addActionListener(actionListener);
        buttonMainMenu.setActionCommand(GlobalControlCodes.BROKER_CLOSE.name());
    }

    public void showView() {
        frame.setVisible(true);
    }

    public void closeView() {
        frame.dispose();
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof Vector) {
            populateTable((Vector) arg);
        }
    }

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
        contentPane.setLayout(new BorderLayout(0, 0));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        table.setPreferredScrollableViewportSize(new Dimension(700, 400));
        scrollPane1.setViewportView(table);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        contentPane.add(panel1, BorderLayout.SOUTH);
        buttonOK = new JButton();
        buttonOK.setText("OK");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        buttonMainMenu = new JButton();
        buttonMainMenu.setText("Main Menu");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonMainMenu, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
