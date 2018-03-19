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


    @Override
    public void initialiseUi() {
        trackModel = new TrackModel();
        trackView = new TrackView();
        cancelButtonListener = new CancelButtonListener();
        linkMVC();
        setListeners();
    }

    @Override
    public void showUi() {
        trackView.showView();
    }

    @Override
    public void closeUi() {
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
            closeUi();
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_CLOSE);
        }
    }
}
