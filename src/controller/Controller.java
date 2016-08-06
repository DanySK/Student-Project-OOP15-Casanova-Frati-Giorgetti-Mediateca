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

	void login(Integer userId, String password);
}
