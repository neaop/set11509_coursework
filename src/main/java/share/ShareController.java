package share;

import global.CurrentUser;
import global.GlobalControlCodes;
import share.model.ShareModel;
import share.view.ShareView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ShareController extends Observable implements Observer {
    private ShareModel shareModel;
    private ShareView shareView;
    private RegisterListener registerListener;
    private MenuListener menuListner;
    private CurrentUser currentUser;

    public void initialiseShareUi() {
        shareModel = new ShareModel();
        shareView = new ShareView();
        registerListener = new RegisterListener();
        menuListner = new MenuListener();

        linkMVC();

        populateTable();
    }

    private void linkMVC() {
        shareModel.addObserver(shareView);

        shareView.setRegisterButtonController(registerListener);
        shareView.setMenuButtonController(menuListner);

        registerListener.addObserver(this);
        menuListner.addObserver(this);
    }

    private void populateTable() {
        shareModel.getShareData();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }


    public class RegisterListener extends Observable implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(GlobalControlCodes.INTREST);

            currentUser = CurrentUser.getInstance();
            int shareId = shareView.getSelectedShare();
            String message = String.format("User: %1$s has shown interest in " +
                    "Share: %2$s", currentUser.getUserName(), shareId);
            shareView.showRegisterDialog(message);
        }
    }

    public class MenuListener extends Observable implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            shareView.closeWindow();
            notifyObservers(GlobalControlCodes.SHARE_CLOSE);
        }
    }
}

