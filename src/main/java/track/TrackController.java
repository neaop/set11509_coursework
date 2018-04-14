package track;

import global.GlobalControlCodes;
import global.controller.Controller;
import track.model.TrackModel;
import track.view.TrackView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * The Controller element for the track module.
 */
public class TrackController extends Observable implements
        Controller, ActionListener, Observer, Serializable {
    private TrackModel trackModel;
    private TrackView trackView;
    private Vector selectedShare;

    @Override
    public void initialiseController() {
        trackModel = new TrackModel();
        trackView = new TrackView();
        linkMVC();
        setListeners();
        trackModel.setShareInfo(selectedShare);
    }

    @Override
    public void showView() {
        trackView.showView();
    }

    @Override
    public void closeView() {
        trackView.closeView();
    }

    /**
     * Connect the track Model and View.
     */
    private void linkMVC() {
        trackModel.addObserver(trackView);
        trackModel.addObserver(this);
    }

    /**
     * Set this Controller as the View's listener.
     */
    private void setListeners() {
        trackView.setActionListeners(this);
    }

    /**
     * Try to track the selected share using the user's min and max values.
     */
    private void attemptTrackShare() {
        if (trackView.checkFieldsFull()) {
            trackModel.trackShare(trackView.getFieldValues());
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_SHARE);
        } else {
            trackView.displayEmptyFieldError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_SHARE.name())) {
            attemptTrackShare();
        }
    }

    /**
     * Register the selected share within the linked Model.
     *
     * @param selectedShare the selected share's information
     */
    public void setSelectedShare(Vector selectedShare) {
        this.selectedShare = selectedShare;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass().isInstance(true)) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
    }
}
