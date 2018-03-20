package track;

import global.Controller;
import global.GlobalControlCodes;
import track.model.TrackModel;
import track.view.TrackView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Vector;

public class TrackController extends Observable implements
        Controller, ActionListener {
    private TrackModel trackModel;
    private TrackView trackView;
    private Vector selectedShare;

    public void initialiseController() {
        trackModel = new TrackModel();
        trackView = new TrackView();
        linkMVC();
        setListeners();
        trackView.updateTable(selectedShare);
    }

    public void showView() {
        trackView.showView();
    }

    public void closeView() {
        trackView.closeView();
    }

    private void linkMVC() {
        trackModel.addObserver(trackView);
    }

    private void setListeners() {
        trackView.setActionListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRACK_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
    }

    public void setSelectedShare(Vector selectedShare) {
        this.selectedShare = selectedShare;
    }
}
