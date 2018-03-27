package trade;

import global.controller.Controller;
import global.controller.GlobalControlCodes;
import trade.model.TradeModel;
import trade.view.TradeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TradeController extends Observable implements Controller, ActionListener {
    private TradeModel tradeModel;
    private TradeView tradeView;

    public void initialiseController() {
        tradeModel = new TradeModel();
        tradeView = new TradeView();

        linkMVC();
        addActionListeners();
        initialiseTable("", "", "");
    }

    public void initialiseTable(String fromDate, String tillDate, String companyCode) {
        tradeModel.searchTrades(fromDate, tillDate, companyCode);

    }

    private void linkMVC() {
        tradeModel.addObserver(tradeView);
    }

    private void addActionListeners() {
        tradeView.setActionListeners(this);
    }

    public void showView() {
        tradeView.showView();
    }

    public void closeView() {
        tradeView.closeView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_SEARCH.name())) {
            tradeModel.searchTrades(tradeView.getFromValue()
                    , tradeView.getTillVaule(), tradeView.getCompanyValue());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE_CLOSE);
        }
    }
}
