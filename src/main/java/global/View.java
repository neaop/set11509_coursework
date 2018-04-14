package global;

import java.util.Observer;

/**
 * Interface to describe default View methods.
 */
public interface View extends Observer {

    /**
     * Display the View.
     */
    void showView();

    /**
     * Hide the View.
     */
    void closeView();
}
