package view;

import utils.ItemGenre;

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
	void setUserField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone,
			ItemGenre bookPref1, ItemGenre bookPref2, ItemGenre bookPref3,
			ItemGenre filmPref1, ItemGenre filmPref2, ItemGenre filmPref3);

}
