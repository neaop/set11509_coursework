package share;

import share.model.ShareModel;
import share.view.ShareView;

public class ShareController {
    private ShareModel shareModel;
    private ShareView shareView;

    public void initialiseShareUi() {
        shareModel = new ShareModel();
        shareView = new ShareView();
        populateTable();
    }

    private void populateTable() {
        shareView.populateTable(shareModel.getShareData());
    }

}
