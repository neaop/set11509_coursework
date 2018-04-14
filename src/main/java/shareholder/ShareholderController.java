package shareholder;

import global.GlobalControlCodes;
import global.controller.Controller;
import shareholder.model.ShareholderModel;
import shareholder.view.ShareholderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 * Controller element for the shareholder module.
 */
public class ShareholderController extends Observable
        implements Controller, ActionListener, Serializable {
    private ShareholderView shareholderView;
    private ShareholderModel shareholderModel;

    @Override
    public void initialiseController() {
        shareholderView = new ShareholderView();
        shareholderModel = new ShareholderModel();

        linkMVC();
        setActionListeners();

        shareholderModel.queryShareHolders();
    }

    /**
     * Connect View and Model.
     */
    private void linkMVC() {
        shareholderModel.addObserver(shareholderView);
    }

    /**
     * Set this Controller as the View's listener.
     */
    private void setActionListeners() {
        shareholderView.setActionListeners(this);
    }

    @Override
    public void showView() {
        shareholderView.showView();
    }

    @Override
    public void closeView() {
        shareholderView.closeView();
    }

    /**
     * Retrieve the currently selected shareholder from the View.
     *
     * @return the selected shareholder from the View
     */
    public String getShareholder() {
        return shareholderView.getShareholderName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_CLOSE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHAREHOLDER_HISTORY.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHAREHOLDER_HISTORY);
        }
    }
}
