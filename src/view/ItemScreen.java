package view;

import javax.swing.ImageIcon;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItemInfo;

/**
 * inteface for item screen.
 *
 * @author Luca Giorgetti
 *
 */
public interface ItemScreen {
	/**
	 * method which allows to get item info by passing the info type you want.
	 *
	 * @param info
	 * @return
	 */
	Object getItemInfo(TypeItemInfo info);

	/**
	 * method which allows to resize image by passing path.
	 *
	 * @param imagePath
	 * @return
	 */
	ImageIcon resizeImage(String imagePath);

	/**
	 * method which sets common field.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param imagePath
	 * @param language
	 */
	void setCommonField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, Language language);

	/**
	 * method which sets all film field.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param imagePath
	 * @param duration
	 * @param color
	 * @param language
	 */

	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String duration,
			TypeColor color, Language language);

	/**
	 * method which sets all book fields.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param imagePath
	 * @param isbn
	 * @param language
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String isbn,
			Language language);

}
