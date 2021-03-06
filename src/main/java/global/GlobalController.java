package global;

import alert.AlertModel;
import broker.BrokerController;
import menu.MenuController;
import share.ShareController;
import shareholder.ShareholderController;
import track.TrackController;
import trade.TradeController;
import user.UserController;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * The global Control class to link other modules.
 */
public class GlobalController implements Observer, Serializable {
    private UserController userController;
    private MenuController menuController;
    private ShareController shareController;
    private TrackController trackController;
    private TradeController tradeController;
    private BrokerController brokerController;
    private ShareholderController shareholderController;
    private AlertModel shareMonitor;


    /**
     * Starts the Share Trader System.
     */
    public void runApplication() {
        instantiateControllers();

        userController.initialiseController();
        userController.showView();
    }

    /**
     * Creates and links the required module controllers.
     */
    private void instantiateControllers() {
        userController = new UserController();
        userController.addObserver(this);

        menuController = new MenuController();
        menuController.addObserver(this);

        shareController = new ShareController();
        shareController.addObserver(this);

        trackController = new TrackController();
        trackController.addObserver(this);

        tradeController = new TradeController();
        tradeController.addObserver(this);

        brokerController = new BrokerController();
        brokerController.addObserver(this);

        shareholderController = new ShareholderController();
        shareholderController.addObserver(this);

        shareMonitor = new AlertModel();
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
        if (arg == GlobalControlCodes.SHARE_OPEN) {
            menuController.closeView();
            shareController.initialiseController();
            shareController.showView();
        }
        if (arg == GlobalControlCodes.SHARE_CLOSE) {
            shareController.closeView();
            menuController.showView();
        }
        if ((arg == GlobalControlCodes.TRACK_OPEN)) {
            menuController.closeView();
            trackController.setSelectedShare(shareController.getSelectedShare());
            trackController.initialiseController();
            trackController.showView();
        }
        if (arg == GlobalControlCodes.TRACK_CLOSE) {
            trackController.closeView();
        }
        if (arg == GlobalControlCodes.TRADE_OPEN) {
            menuController.closeView();
            tradeController.initialiseController();
            tradeController.initialiseTable("", "", "", "", "");
            tradeController.showView();
        }
        if (arg == GlobalControlCodes.TRADE_CLOSE) {
            tradeController.closeView();
            menuController.showView();
        }
        if (arg == GlobalControlCodes.BROKER_OPEN) {
            menuController.closeView();
            brokerController.initialiseController();
            brokerController.showView();
        }
        if (arg == GlobalControlCodes.BROKER_HISTORY) {
            tradeController.initialiseController();
            tradeController.initialiseTable("", "", "", "",
                    brokerController.getSelectedBrokerName());
            tradeController.showView();
            brokerController.closeView();
        }
        if (arg == GlobalControlCodes.BROKER_CLOSE) {
            brokerController.closeView();
            menuController.showView();
        }
        if (arg == GlobalControlCodes.SHAREHOLDER_OPEN) {
            menuController.closeView();
            shareholderController.initialiseController();
            shareholderController.showView();
        }
        if (arg == GlobalControlCodes.SHAREHOLDER_HISTORY) {
            tradeController.initialiseController();
            tradeController.initialiseTable("", "",
                    "", shareholderController.getShareholder(), "");
            tradeController.showView();
            shareholderController.closeView();
        }

        if (arg == GlobalControlCodes.SHAREHOLDER_CLOSE) {
            shareholderController.closeView();
            menuController.showView();
        }
        if (arg == GlobalControlCodes.EXIT) {
            userController.closeView();
            System.exit(0);
        }
        shareMonitor.checkTrackedShares();
    }

}
