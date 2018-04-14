package trade;

import global.GlobalControlCodes;
import global.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 * The Controller element for the trade module.
 */
public class TradeController extends Observable
        implements Controller, ActionListener, Serializable {
    private TradeModel tradeModel;
    private TradeViewForm tradeView;

    @Override
    public void initialiseController() {
        tradeModel = new TradeModel();
        tradeView = new TradeViewForm();

        linkMVC();
        addActionListeners();
    }

    /**
     * Connect the Model and View.
     */
    private void linkMVC() {
        tradeModel.addObserver(tradeView);
    }

    /**
     * Set this Controller as the View's listener.
     */
    private void addActionListeners() {
        tradeView.setActionListeners(this);
    }

    @Override
    public void showView() {
        tradeView.showView();
    }

    @Override
    public void closeView() {
        tradeView.closeView();
    }

    /**
     * Populate the table in the connected View.
     *
     * @param fromDate    the starting date
     * @param tillDate    the ending date
     * @param companyCode the trading code of the company
     * @param sellerBuyer the name of the buyer/seller
     * @param broker      the name of the broker
     */
    public void initialiseTable(String fromDate, String tillDate,
                                String companyCode, String sellerBuyer,
                                String broker) {
        tradeModel.searchTrades(fromDate, tillDate, companyCode,
                sellerBuyer, broker);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_SEARCH.name())) {
            tradeModel.searchTrades(tradeView.getFromValue(),
                    tradeView.getTillValue(), tradeView.getCompanyValue(),
                    tradeView.getSellerBuyerValue(), tradeView.getBrokerValue());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE_CLOSE);
        }
    }
}
