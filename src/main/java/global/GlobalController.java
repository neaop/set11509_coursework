package global;

import menu.MenuController;
import share.ShareController;
import user.UserController;

import java.util.Observable;
import java.util.Observer;

public class GlobalController implements Observer {
    private UserController userController;
    private MenuController menuController;
    private ShareController shareController;

    public void runApplication() {
        userController = new UserController();
        userController.addObserver(this);

        menuController = new MenuController();
        menuController.addObserver(this);

        shareController = new ShareController();
        shareController.addObserver(this);


        userController.initialiseUi();
        userController.showUi();

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("GlobalController: " + arg);
        if (arg == GlobalControlCodes.LOG_IN) {
            userController.closeUi();
            menuController.initialiseUi();
            menuController.showUi();
        }
        if (arg == GlobalControlCodes.LOG_OFF) {
            menuController.closeUi();
            userController.initialiseUi();
            userController.showUi();
        }
        if (arg == GlobalControlCodes.SHARE) {
            menuController.closeUi();
            shareController.initialiseUi();
            shareController.showUi();
        }
        if (arg == GlobalControlCodes.SHARE_CLOSE) {
            menuController.showUi();
        }
    }
}

