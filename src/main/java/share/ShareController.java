package share;

import share.model.ShareModel;
import share.view.ShareView;

public class ShareController {
    private ShareModel shareModel;
    private ShareView shareView;

    public void initialiseShareUi() {
        shareModel = new ShareModel();
        shareView = new ShareView();

        shareModel.addObserver(shareView);

        populateTable();
    }

    private void populateTable() {
        shareModel.getShareData();
    }

}
