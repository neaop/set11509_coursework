package global;

import java.util.Observer;

public interface View extends Observer {

    void showView();

    void closeView();
}
