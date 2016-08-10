package controller;

/**
 * Interface for a generic controller.
 *
 * @author
 *
 */

public interface Controller {

	/**
	 * Method who sets the View for the Controller.
	 *
	 * @param v
	 *            View requested by the controller
	 *
	 */
	void setView(view.View v);

	/**
	 * Method who lets the actual user access the program if username and
	 * password are contained into the archive.
	 */
	void login();

}
