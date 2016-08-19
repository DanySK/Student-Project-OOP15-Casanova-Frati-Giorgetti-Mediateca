package view;

/**
 * Interface whith methods for setting and getting information from UserModify
 * card.
 *
 * @author Luca Giorgetti
 *
 */
public interface UserScreen {
	/**
	 * Mets user information in JTextFields in UserModifyImpl. Needs String for
	 * each field.
	 *
	 * @param name
	 *            the user name
	 * @param surname
	 *            the user surname
	 * @param username
	 *            the user username
	 * @param password
	 *            the user password
	 * @param birthDate
	 *            the user birthDate
	 * @param email
	 *            the user email
	 * @param telephone
	 *            the user telephone number
	 */
	void setField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone);

	/**
	 * Method which returns the info requested by passing a info type.
	 *
	 * @param info
	 *            the type of info you want
	 * @return user information requestd
	 */
	Object getInfo(utils.UserInfo info);

}
