package controller;

/**
 * Interface for a generic controller.
 *
 * @author
 *
 */

public interface Controller {

	/**
	 *
	 */
	void getUserUsername();

	/**
	 *
	 */
	void setUserUsername();

	/**
	 * Method who sets the View for the Controller.
	 *
	 * @param v
	 *            view requested by the controller
	 *
	 */
	void setView(view.View v);

	void login(String username, String password);

}
