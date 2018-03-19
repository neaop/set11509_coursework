package share;

import global.Controller;
import global.GlobalControlCodes;
import share.model.ShareModel;
import share.view.ShareView;
import track.TrackController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ShareController extends Observable implements Observer, Controller, ActionListener {
    private ShareModel shareModel;
    private ShareView shareView;

    private TrackController trackController;

    public void initialiseController() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        trackController = new TrackController();
        trackController.addObserver(this);

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

    private void linkMVC() {
        shareModel.addObserver(shareView);
    }

    private void setActionListeners() {
        shareView.setActionListeners(this);
    }

    private void populateTable() {
        shareModel.getShareData();
    }

    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
        if (arg == GlobalControlCodes.TRACK_CLOSE) {
            this.showView();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(String.valueOf(GlobalControlCodes.TRACK_OPEN))) {
            trackController.initialiseController();
            trackController.setShareId(shareView.getSelectedShareId());
            trackController.showView();

            closeView();
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_OPEN);
        }
        if (e.getActionCommand().equals(String.valueOf(GlobalControlCodes.SHARE_CLOSE))) {
            setChanged();
            notifyObservers(GlobalControlCodes.SHARE_CLOSE);
        }
    }
}

