package share;

import global.GlobalControlCodes;
import global.controller.Controller;
import share.model.ShareModel;
import share.view.ShareView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;

/**
 * Controller element for the share module.
 */
public class ShareController extends Observable
        implements Controller, ActionListener, Serializable {
    private ShareModel shareModel;
    private ShareView shareView;

    @Override
    public void initialiseController() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        linkMVC();
        setActionListeners();

        populateTable();
        showView();
    }

    @Override
    public void showView() {
        shareView.showView();
    }

    @Override
    public void closeView() {
        shareView.closeView();
    }

    /**
     * Fetch the selected share from the View.
     *
     * @return the currently selected share
     */
    public Vector getSelectedShare() {
        return shareView.getSelectedShare();
    }

    /**
     * Connect share Model and View.
     */
    private void linkMVC() {
        shareModel.addObserver(shareView);
    }

    /**
     * Set this Controller as the View's listener.
     */
    private void setActionListeners() {
        shareView.setActionListeners(this);
    }

    /**
     * Display share data in the attached View.
     */
    private void populateTable() {
        shareModel.getShareData();
    }

    @Override
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

