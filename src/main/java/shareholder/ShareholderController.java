package shareholder;

import global.controller.Controller;
import global.GlobalControlCodes;
import shareholder.model.ShareholderModel;
import shareholder.view.ShareholderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ShareholderController extends Observable implements Controller, ActionListener {
    private ShareholderView shareholderView;
    private ShareholderModel shareholderModel;

    public void initialiseController() {
        shareholderView = new ShareholderView();
        shareholderModel = new ShareholderModel();

        linkMVC();
        setActionListeners();

        shareholderModel.quereyShareHolders();
    }

    private void linkMVC() {
        shareholderModel.addObserver(shareholderView);
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

    public String getShareholder() {
        return shareholderView.getShareholderName();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_CLOSE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_TRADE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_TRADE);
        }
    }
}
