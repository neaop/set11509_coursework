package share;

import share.model.ShareModel;
import share.view.ShareView;

import java.util.Observable;
import java.util.Observer;

public class ShareController extends Observable implements Observer {
    private ShareModel shareModel;
    private ShareView shareView;

    public void initialiseShareUi() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        shareModel.addObserver(shareView);
        shareView.addObserver(this);

        populateTable();
    }

    private void populateTable() {
        shareModel.getShareData();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
