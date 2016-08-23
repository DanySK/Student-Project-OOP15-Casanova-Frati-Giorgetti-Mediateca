package view;


/**
 * Interface for user show.
 *
 * @author Luca Giorgetti
 *
 */
public interface UserShow {
	/**
	 * Starts user screen.
	 *
	 * @param v
	 *            the calling View name
	 */
	void startUserShow(View v);

	/**
	 * Sets user info.
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
	 *            the user birtdate
	 * @param email
	 *            the user email
	 * @param telephone
	 *            the user telephone number
	 * @param bookPref1
	 *            the user first book genre preferred
	 * @param bookPref2
	 *            the user second book genre preferred
	 * @param bookPref3
	 *            the user third book genre preferred
	 * @param filmPref1
	 *            the user first movie genre preferred
	 * @param filmPref2
	 *            the user second movie genre preferred
	 * @param filmPref3
	 *            the user third movie genre preferred
	 */
	void setUserField(String nameS, String surnameS, String usernameS,
			String passwordS, String birthDateS, String emailS,
			String telephoneS, String bookPref1I, String bookPref2I,
			String bookPref3I, String filmPref1I, String filmPref2I,
			String filmPref3I);

}
