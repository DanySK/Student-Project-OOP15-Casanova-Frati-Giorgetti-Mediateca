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
	 * method which sets common fields in item show.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 */
	void setCommonField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, Language language);

	/**
	 * method which sets all field for film item.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 * @param duration
	 * @param color
	 */
	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, String duration,
			TypeColor color, Language language);

	/**
	 * method which sets all field for book item.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 * @param isbn
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String reviewAvarage,
			String availability, String imagePath, String isbn,
			Language language);

	/**
	 * method which starts item show.
	 * 
	 * @param v
	 * @param type
	 */
	void startItemShow(View v, TypeItem type);

}
