package menu.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import global.GlobalControlCodes;
import global.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class MenuView implements View {
    private JFrame frame;
    private JPanel panel;
    private JButton buttonShare;
    private JButton buttonTrade;
    private JButton buttonLogoff;
    private JButton buttonBroker;
    private JButton buttonShareholders;

    public MenuView() {
        frame = new JFrame("MenuView");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void showView() {
        frame.setVisible(true);
    }

    @Override
    public void closeView() {
        frame.dispose();
    }

    public void setActionListeners(ActionListener actionListener) {
        setLogoffButtonListener(actionListener);
        setShareButtonListener(actionListener);
        setTradeButtonListener(actionListener);
        setBrokerButtonListener(actionListener);
        setShareholderButtonListener(actionListener);
    }

    private void setLogoffButtonListener(ActionListener actionListener) {
        System.out.println("MenuView: adding logoff listener");
        buttonLogoff.addActionListener(actionListener);
        buttonLogoff.setActionCommand(GlobalControlCodes.LOG_OFF.name());
    }

    private void setShareButtonListener(ActionListener actionListener) {
        System.out.println("MenuView: adding share listener");
        buttonShare.addActionListener(actionListener);
        buttonShare.setActionCommand(GlobalControlCodes.SHARE_OPEN.name());

    }

    private void setTradeButtonListener(ActionListener actionListener) {
        System.out.println("MenuView: adding trade listener");
        buttonTrade.addActionListener(actionListener);
        buttonTrade.setActionCommand(GlobalControlCodes.TRADE_OPEN.name());
    }

    private void setBrokerButtonListener(ActionListener actionListener) {
        System.out.println("MenuView: adding broker listener");
        buttonBroker.addActionListener(actionListener);
        buttonBroker.setActionCommand(GlobalControlCodes.BROKER_OPEN.name());
    }

    private void setShareholderButtonListener(ActionListener actionListener) {
        System.out.println("MenuView: adding shareholder listener");
        buttonShareholders.addActionListener(actionListener);
        buttonShareholders.setActionCommand(GlobalControlCodes.SHAREHOLDER_OPEN.name());
    }

    public void update(Observable o, Object arg) {

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
        panel.setLayout(new GridLayoutManager(3, 7, new Insets(10, 10, 10, 10), -1, -1));
        buttonLogoff = new JButton();
        buttonLogoff.setText("Log Off");
        panel.add(buttonLogoff, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonShareholders = new JButton();
        buttonShareholders.setText("Shareholders");
        panel.add(buttonShareholders, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonBroker = new JButton();
        buttonBroker.setText("Brokers");
        panel.add(buttonBroker, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonShare = new JButton();
        buttonShare.setText("Shares");
        panel.add(buttonShare, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTrade = new JButton();
        buttonTrade.setText("Trades");
        panel.add(buttonTrade, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
