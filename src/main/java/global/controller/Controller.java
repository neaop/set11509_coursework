package global.controller;

/**
 * Interface to describe default Controller methods.
 */
public interface Controller {

    /**
     * Instantiate and link MVC component
     */
    void initialiseController();

    /**
     * Display linked View.
     */
    void showView();

    /**
     * Hide linked View.
     */
    void closeView();

}
