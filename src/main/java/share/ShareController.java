package share;

import global.controller.Controller;
import global.GlobalControlCodes;
import share.model.ShareModel;
import share.view.ShareView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;

public class ShareController extends Observable
        implements Controller, ActionListener, Serializable {
    private ShareModel shareModel;
    private ShareView shareView;

    public void initialiseController() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        linkMVC();
        setActionListeners();

        populateTable();
        showView();
    }

    public void showView() {
        shareView.showView();
    }

    public void closeView() {
        shareView.closeView();
    }

    public Vector getSelectedShare() {
        return shareView.getSelectedShare();
    }

    private void linkMVC() {
        shareModel.addObserver(shareView);
    }

    private void setActionListeners() {
        shareView.setActionListeners(this);
    }

    private void populateTable() {
        shareModel.getShareData();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_OPEN.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_OPEN);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.SHARE_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHARE_CLOSE);
        }
    }
}

