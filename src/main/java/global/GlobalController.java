package global;

import hub.HubController;
import user.UserController;

import java.util.Observable;
import java.util.Observer;

public class GlobalController implements Observer {
    UserController userController;
    HubController hubController;

    public void runApplication() {
        userController = new UserController();
        userController.addObserver(this);

        hubController = new HubController();
        hubController.addObserver(this);
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
    }
}
