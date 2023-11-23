package controller;

/**
 * The {@code BaseController} interface is responsible for setting master variables for all controllers.
 * It is implemented by all controllers.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
 */

 // This interface is used to ensure that all controllers have a setMasterVariables() method.
public interface BaseController {
    /***
     * Abstract function to set the master variables for a controller.
     */
    void setMasterVariables();
}