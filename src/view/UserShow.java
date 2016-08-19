package view;

import utils.ItemGenre;

public interface UserShow {
	/**
	 * method which starts user screen.
	 *
	 * @param v
	 */
	void startUserShow(View v);

	/**
	 * method which sets user info.
	 *
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 * @param birthDate
	 * @param email
	 * @param telephone
	 * @param bookPref1
	 * @param bookPref2
	 * @param bookPref3
	 * @param filmPref1
	 * @param filmPref2
	 * @param filmPref3
	 */
	void setUserField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone,
			ItemGenre bookPref1, ItemGenre bookPref2, ItemGenre bookPref3,
			ItemGenre filmPref1, ItemGenre filmPref2, ItemGenre filmPref3);

}
