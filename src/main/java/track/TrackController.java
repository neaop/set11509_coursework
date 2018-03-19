package track;

import global.Controller;
import global.GlobalControlCodes;
import track.model.TrackModel;
import track.view.TrackView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TrackController extends Observable implements Controller {
    private TrackModel trackModel;
    private TrackView trackView;
    private CancelButtonListener cancelButtonListener;

    public void initialiseController() {
        trackModel = new TrackModel();
        trackView = new TrackView();
        cancelButtonListener = new CancelButtonListener();
        linkMVC();
        setListeners();
    }

    public void showView() {
        trackView.showView();
    }

    public void closeView() {
        trackView.closeView();
    }

    public void setShareId(int shareId) {
        trackModel.getShareFromId(shareId);
    }

    private void linkMVC() {
        trackModel.addObserver(trackView);
    }

    private void setListeners() {
        trackView.setCancelButtonListener(cancelButtonListener);
    }

    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeView();
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
    }
}
