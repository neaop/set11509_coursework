package track;

import global.controller.Controller;
import global.GlobalControlCodes;
import track.model.TrackModel;
import track.view.NewTrackView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class TrackController extends Observable implements
        Controller, ActionListener, Observer {
    private TrackModel trackModel;
    private NewTrackView trackView;
    private Vector selectedShare;

    public void initialiseController() {
        trackModel = new TrackModel();
        trackView = new NewTrackView();
        linkMVC();
        setListeners();
        trackModel.setShareInfo(selectedShare);
    }

    public void showView() {
        trackView.showView();
    }

    public void closeView() {
        trackView.closeView();
    }

    private void linkMVC() {
        trackModel.addObserver(trackView);
        trackModel.addObserver(this);
    }

    private void setListeners() {
        trackView.setActionListeners(this);
    }

    private void attemptTrackShare() {
        if (trackView.checkFieldsFull()) {
            trackModel.trackShare(trackView.getFieldValues());
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_SHARE);
        } else {
            trackView.displayEmptyFieldError();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_SHARE.name())) {
            attemptTrackShare();
        }
    }

    public void setSelectedShare(Vector selectedShare) {
        this.selectedShare = selectedShare;
    }

    public void update(Observable o, Object arg) {
        if (arg.getClass().isInstance(true)) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
    }
}
