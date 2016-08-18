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
	 * Method which sets user information in JTextFields in UserModifyImpl.
	 * Needs String for each field.
	 *
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 * @param birthDate
	 * @param email
	 * @param telephone
	 */
	void setField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone);

	/**
	 * Method which returns the info requested by passing a info type.
	 *
	 * @param info
	 * @return userInfo
	 */
	Object getInfo(utils.UserInfo info);

}
