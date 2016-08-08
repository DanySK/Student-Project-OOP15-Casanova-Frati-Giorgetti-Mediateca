package view;

import javax.swing.JPanel;

/**
 * Interface of panel of user login.
 *
 * @author Luca
 */
public interface UserLogin {
	/**
	 * Method which returns the user login panel.
	 *
	 * @return userLoginPanel
	 */
	JPanel getUserLoginPanel();

	/**
	 * Method which returns username inserted by user.
	 *
	 * @return user username
	 */
	String getUserUsername();

	/**
	 * Method which returns password inserted by user.
	 *
	 * @return user password
	 */
	String getUserPassword();

}
