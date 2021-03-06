package user;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import global.GlobalControlCodes;
import global.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * The View element of the user module.
 */
public class UserView extends JDialog implements Observer, View, Serializable {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JButton buttonRegister;
    private JTextField fieldName;
    private JPasswordField fieldPassword;
    private JButton buttonExit;

    /**
     * Default constructor.
     */
    public UserView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonLogin);
        pack();
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
     * Set a Controller as the View's ActionListener
     *
     * @param actionListener the Controller to listen to the View
     */
    public void setActionListeners(ActionListener actionListener) {
        setLoginButtonListener(actionListener);
        setRegisterButtonListener(actionListener);
        setExitButtonListener(actionListener);
    }

    /**
     * Sets a ActionListener for the login button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void setLoginButtonListener(ActionListener actionListener) {
        System.out.println("UserView: adding login listener");
        buttonLogin.addActionListener(actionListener);
        buttonLogin.setActionCommand(GlobalControlCodes.LOG_IN.name());
    }

    /**
     * Sets a ActionListener for the register button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void setRegisterButtonListener(ActionListener actionListener) {
        System.out.println("UserView: adding register listener");
        buttonRegister.addActionListener(actionListener);
        buttonRegister.setActionCommand(UserControlCodes.REGISTER.name());
    }

    /**
     * Sets a ActionListener for the exit button.
     *
     * @param actionListener the Controller to listen to actions
     */
    private void setExitButtonListener(ActionListener actionListener) {
        System.out.println("UserView: adding exit listener");
        buttonExit.addActionListener(actionListener);
        buttonExit.setActionCommand(GlobalControlCodes.EXIT.name());
    }

    /**
     * Retrieve text from password field.
     *
     * @return the Sting from the field
     */
    public String getUserPassword() {
        return String.valueOf(fieldPassword.getPassword());
    }

    /**
     * Retrieve text from username field.
     *
     * @return the Sting from the field
     */
    public String getUserName() {
        return fieldName.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserView: " + arg);
        if (arg == UserControlCodes.NO_SUCH_USER) {
            showInvalidLogin();
        } else if (arg == UserControlCodes.INVALID_CREDENTIAL) {
            showInvalidCredential();
        } else if (arg == UserControlCodes.USER_EXISTS) {
            showInvalidRegister();
        } else if (arg == UserControlCodes.REGISTER) {
            showValidRegister();
        }
    }

    /**
     * Display a message.
     *
     * @param message the message to be displayed
     */
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null
                , message, "Log In"
                , JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display a message if user does not exist.
     */
    private void showInvalidLogin() {
        showMessageDialog("No such User/Password");
    }

    /**
     * Display a message for invalid user credentials.
     */
    private void showInvalidCredential() {
        showMessageDialog("Invalid credentials");
    }

    /**
     * Display a message if username is already registered.
     */
    private void showInvalidRegister() {
        showMessageDialog("Username already registered");
    }

    /**
     * Display a message if registration was successful.
     */
    private void showValidRegister() {
        showMessageDialog("New user Registered");
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonLogin = new JButton();
        buttonLogin.setText("Log In");
        panel2.add(buttonLogin, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRegister = new JButton();
        buttonRegister.setText("Register");
        panel2.add(buttonRegister, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonExit = new JButton();
        buttonExit.setText("Exit");
        panel2.add(buttonExit, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("User Name");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fieldName = new JTextField();
        panel3.add(fieldName, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Password");
        panel3.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fieldPassword = new JPasswordField();
        panel3.add(fieldPassword, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        contentPane.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
