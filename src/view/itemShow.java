package view;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItem;

/**
 * interface for showing item.
 *
 * @author Luca Giorgetti
 *
 */
public interface ItemShow {
	/**
	 * Sets common fields in item show.
	 *
	 * @param title
	 *            the title of item
	 * @param author
	 *            the authot of item
	 * @param manifacturer
	 *            the producer of item
	 * @param year
	 *            the year of release of item
	 * @param genre
	 *            the genre of item
	 * @param reviewAvarage
	 *            the avarage review of item
	 * @param availability
	 *            the availability of item
	 * @param imagePath
	 *            the path of item image
	 * @param language
	 *            the language of item
	 */
	void setCommonField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, Language language);

	/**
	 * Sets all field for film item.
	 *
	 * @param title
	 *            the title of movie
	 * @param author
	 *            the author of movie
	 * @param manifacturer
	 *            the producer of movie
	 * @param year
	 *            the year of release of movie
	 * @param genre
	 *            the genre of movie
	 * @param reviewAvarage
	 *            the avarage review of movie
	 * @param availability
	 *            the availability of movie
	 * @param imagePath
	 *            the path of movie image
	 * @param duration
	 *            the duration of movie
	 * @param color
	 *            the color type of movie
	 * @param language
	 *            the language of movie
	 */
	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, String duration,
			TypeColor color, Language language);

	/**
	 * Sets all field for book item.
	 *
	 * @param title
	 *            the title of book
	 * @param author
	 *            the author of book
	 * @param manifacturer
	 *            the producer of book
	 * @param year
	 *            the year of release of book
	 * @param genre
	 *            the genre of book
	 * @param reviewAvarage
	 *            the avarage review of book
	 * @param availability
	 *            the availability of book
	 * @param imagePath
	 *            the path of book image
	 * @param isbn
	 *            the isbn code of book
	 * @param language
	 *            the language of movie
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, String isbn,
			Language language);

	/**
	 * Starts item show.
	 *
	 * @param v
	 *            the name of the calling View
	 * @param type
	 *            the item type
	 */
	void startItemShow(View v, TypeItem type);

}
