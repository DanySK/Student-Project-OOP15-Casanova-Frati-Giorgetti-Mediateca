package controller;

import javax.swing.text.View;

/**
 * Interface for a generic controller.
 *
 * @author
 *
 */

public interface Controller {
	/**
	 *
	 * @param userName
	 *            username given by the user
	 * @param password
	 *            password given by the user
	 */
	void login(String userName, String password);

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
	 * @param inputView
	 *            view requested by the controller
	 *
	 */
	void setView(View inputView);
}
