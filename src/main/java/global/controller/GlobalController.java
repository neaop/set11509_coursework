package global.controller;

import global.model.TrackEngine;
import menu.MenuController;
import share.ShareController;
import track.TrackController;
import user.UserController;

import java.util.Observable;
import java.util.Observer;

public class GlobalController implements Observer {
    private UserController userController;
    private MenuController menuController;
    private ShareController shareController;
    private TrackController trackController;
    private TrackEngine trackEngine;

    public void runApplication() {
        userController = new UserController();
        userController.addObserver(this);

        menuController = new MenuController();
        menuController.addObserver(this);

        shareController = new ShareController();
        shareController.addObserver(this);

        trackController = new TrackController();
        trackController.addObserver(this);

        userController.initialiseController();
        userController.showView();

        trackEngine = new TrackEngine();

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("GlobalController: " + arg);
        if (arg == GlobalControlCodes.LOG_IN) {
            userController.closeView();
            menuController.initialiseController();
            menuController.showView();
        }
        if (arg == GlobalControlCodes.LOG_OFF) {
            menuController.closeView();
            userController.initialiseController();
            userController.showView();
        }
        if (arg == GlobalControlCodes.SHARE) {
            menuController.closeView();
            shareController.initialiseController();
            shareController.showView();
        }
        if (arg == GlobalControlCodes.SHARE_CLOSE) {
            shareController.closeView();
            menuController.showView();
        }
        if ((arg == GlobalControlCodes.TRACK_OPEN)) {
            shareController.closeView();
            trackController.setSelectedShare(shareController.getSelectedShare());
            trackController.initialiseController();
            trackController.showView();
        }
        if (arg == GlobalControlCodes.TRACK_CLOSE) {
            trackController.closeView();
            shareController.showView();
        }
        trackEngine.checkTrackedShares();
    }
}

