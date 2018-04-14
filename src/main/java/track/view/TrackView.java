package track.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import global.GlobalControlCodes;
import global.view.ShareTraderTable;
import global.view.View;
import global.view.ViewStrings;
import track.TrackErrorCodes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Vector;

/**
 * The View used by the track module.
 */
public class TrackView extends JDialog implements View, Serializable {
    private JPanel contentPane;
    private JButton buttonConfirm;
    private JButton buttonCancel;
    private JTable table;
    private JFormattedTextField fieldMin;
    private JFormattedTextField fieldMax;

    /**
     * The default constructor.
     */
    public TrackView() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonConfirm);
        pack();
    }

    /**
     * Validates that View's text fields contain text.
     *
     * @return true if the fields contain text, false otherwise
     */
    public boolean checkFieldsFull() {
        return (!fieldMin.getText().trim().isEmpty()
                || !fieldMax.getText().trim().isEmpty());
    }

    /**
     * Retrieve the text the View's text fields.
     *
     * @return a vector containing the values of the text fields
     */
    public int[] getFieldValues() {
        int[] values = new int[2];
        values[0] = getMinValue();
        values[1] = getMaxValue();
        return values;
    }

    /**
     * Retrieve the value of the min text field.
     *
     * @return the int value of the min text field
     * @throws NumberFormatException if error converting String to int
     */
    private int getMinValue() throws NumberFormatException {
        return Integer.parseInt(fieldMin.getText()
                .replaceAll(",", ""));
    }

    /**
     * Retrieve the value of the max text field.
     *
     * @return the int value of max text field
     * @throws NumberFormatException if error converting String to int
     */
    private int getMaxValue() throws NumberFormatException {
        return Integer.parseInt(fieldMax.getText()
                .replaceAll(",", ""));
    }

    /**
     * Show error message if a text field is empty.
     */
    public void displayEmptyFieldError() {
        String message = "Please input value for both Minimum and Maximum";
        displayError(message);
    }

    /**
     * Show error message if min value is invalid.
     */
    private void displayInvalidMinValueError() {
        String message = "Minimum value must be between 0 and current price";
        displayError(message);
    }

    /**
     * Show error message if max value is invalid.
     */
    private void displayInvalidMaxValueError() {
        String message = " Maximum value must be larger than current price";
        displayError(message);
    }

    /**
     * Show an error message.
     *
     * @param message the message to be displayed
     */
    private void displayError(String message) {
        JOptionPane.showMessageDialog(null, message
                , "Track Share", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showView() {
        setVisible(true);
    }

    @Override
    public void closeView() {
        dispose();
    }

    /**
     * Update the View's JTable to display the selected share's data.
     *
     * @param shareData the selected share's information
     */
    private void populateTable(Vector shareData) {
        Vector columns = ViewStrings.getShareColumnNames();
        ((ShareTraderTable) table).updateTable(shareData, columns);
    }

    /**
     * Set a Controller as the View's ActionListener
     *
     * @param actionListener the Controller to listen to the View
     */
    public void setActionListeners(ActionListener actionListener) {
        setTrackButtonListener(actionListener);
        setCancelButtonListener(actionListener);
    }

    /**
     * Sets a ActionListener for the track button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void setTrackButtonListener(ActionListener actionListener) {
        System.out.println("TrackView: adding track listener");
        buttonConfirm.addActionListener(actionListener);
        buttonConfirm.setActionCommand(GlobalControlCodes.TRACK_SHARE.name());
    }

    /**
     * Sets a ActionListener for the cancel button.
     *
     * @param actionListener the Controller to listen for actions
     */
    private void setCancelButtonListener(ActionListener actionListener) {
        System.out.println("TrackView: adding cancel listener");
        buttonCancel.addActionListener(actionListener);
        buttonCancel.setActionCommand(GlobalControlCodes.TRACK_CLOSE.name());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Vector) {
            populateTable((Vector) arg);
        }
        if (arg == TrackErrorCodes.MIN_INVALID) {
            displayInvalidMinValueError();
        }
        if (arg == TrackErrorCodes.MAX_INVALID) {
            displayInvalidMaxValueError();
        }
    }


    /**
     * Initialisation method for custom UI components.
     */
    private void createUIComponents() {
        fieldMin = new JFormattedTextField(NumberFormat.getNumberInstance());
        fieldMax = new JFormattedTextField(NumberFormat.getNumberInstance());
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
        contentPane.setLayout(new GridLayoutManager(4, 3, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonConfirm = new JButton();
        buttonConfirm.setText("Confirm");
        panel2.add(buttonConfirm, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 35));
        scrollPane1.setViewportView(table);
        final JLabel label1 = new JLabel();
        label1.setText("Minimum Value:");
        contentPane.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Maximum Value:");
        contentPane.add(label2, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPane.add(fieldMax, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        contentPane.add(fieldMin, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
