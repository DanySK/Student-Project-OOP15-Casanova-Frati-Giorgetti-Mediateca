package view;

/**
 * Interface of panel of user login.
 *
 * @author Luca
 */
public interface UserLogin {

	/**
	 * Method which returns username inserted by user.
	 *
	 * @return username
	 */
	String getUserUsername();

	/**
	 * Method which returns password inserted by user.
	 *
	 * @return password
	 */
	String getUserPassword();

	/**
	 * Method which returns password inserted by manager.
	 *
	 * @return managerPassword
	 */
	String getManagerPassword();

}
