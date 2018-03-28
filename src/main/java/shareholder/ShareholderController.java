package shareholder;

import global.controller.Controller;
import global.controller.GlobalControlCodes;
import shareholder.view.ShareholderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareholderController extends Observable implements Controller, ActionListener {
    private ShareholderView shareholderView;

    public void initialiseController() {
        shareholderView = new ShareholderView();
        setActionListeners();
    }

    private void setActionListeners() {
        shareholderView.setActionListeners(this);
    }

    public void showView() {
        shareholderView.showView();
    }

    public void closeView() {
        shareholderView.closeView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_CLOSE);
        }
    }
}
