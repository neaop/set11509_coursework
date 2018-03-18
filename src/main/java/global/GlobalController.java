package global;

import hub.HubController;
import share.ShareController;
import user.UserController;

import java.util.Observable;
import java.util.Observer;

public class GlobalController implements Observer {
    private UserController userController;
    private HubController hubController;
    private ShareController shareController;

    public void runApplication() {
        userController = new UserController();
        userController.addObserver(this);

        hubController = new HubController();
        hubController.addObserver(this);

        shareController = new ShareController();
        shareController.addObserver(this);


        userController.initialiseUserForm();

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("GlobalController: " + arg);
        if (arg == GlobalControlCodes.LOG_IN) {
            userController.closeUserForm();
            hubController.initialiseUI();
        }
        if (arg == GlobalControlCodes.LOG_OFF) {
            hubController.closeHubView();
            userController.initialiseUserForm();
        }
        if (arg == GlobalControlCodes.SHARE) {
            hubController.closeHubView();
            shareController.initialiseShareUi();
        }
        if (arg == GlobalControlCodes.SHARE_CLOSE) {
            hubController.initialiseUI();
        }
    }
}
