package share;

import global.Controller;
import global.CurrentUser;
import global.GlobalControlCodes;
import share.model.ShareModel;
import share.view.ShareView;
import track.TrackController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ShareController extends Observable implements Observer, Controller {
    private ShareModel shareModel;
    private ShareView shareView;
    private TrackButtonListener trackButtonListener;
    private MenuButtonListener menuButtonListener;
    private CurrentUser currentUser;
    private TrackController trackController;

    @Override
    public void initialiseController() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        trackButtonListener = new TrackButtonListener();
        menuButtonListener = new MenuButtonListener();

        trackController = new TrackController();
        trackController.addObserver(this);

        linkMVC();

        populateTable();
        showView();
    }

    @Override
    public void showView() {
        shareView.showView();
    }

    @Override
    public void closeView() {
        shareView.closeUi();
    }

    private void linkMVC() {
        shareModel.addObserver(shareView);

        shareView.setRegisterButtonController(trackButtonListener);
        shareView.setMenuButtonController(menuButtonListener);
    }

    private void populateTable() {
        shareModel.getShareData();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
        if (arg == GlobalControlCodes.TRACK_CLOSE) {
            this.showView();
        }
    }


    public class TrackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            trackController.initialiseController();
            trackController.setShareId(shareView.getSelectedShareId());
            trackController.showView();

            closeView();
            setChanged();
            notifyObservers(GlobalControlCodes.TRACK_OPEN);

//            currentUser = CurrentUser.getInstance();
//            int shareId = shareView.getSelectedShareId();
//            String message = String.format("User: %1$s has shown interest in " +
//                    "Share: %2$s", currentUser.getUserName(), shareId);
//            shareView.showRegisterDialog(message);
        }
    }

    public class MenuButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            shareView.closeUi();
            notifyObservers(GlobalControlCodes.SHARE_CLOSE);
        }
    }
}

