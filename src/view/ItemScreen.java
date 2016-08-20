package view;

import javax.swing.ImageIcon;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItemInfo;
import view.ViewImpl.OtherItemFilter;

/**
 * inteface for item screen.
 *
 * @author Luca Giorgetti
 *
 */
public interface ItemScreen {
	/**
	 * Allows to get item info by passing the info type you want.
	 *
	 * @param info
	 *            info type you want
	 * @return information requested
	 */
	Object getItemInfo(TypeItemInfo info, OtherItemFilter info2);

	/**
	 * Allows to resize image by passing path.
	 *
	 * @param imagePath
	 *            the path of the image you want to resize
	 * @return ImageIcon of the image
	 */
	ImageIcon resizeImage(String imagePath);

	/**
	 * Sets common field.
	 *
	 * @param title
	 *            the title of item
	 * @param author
	 *            the author of item
	 * @param manifacturer
	 *            the producer of item
	 * @param year
	 *            the year of release of item
	 * @param genre
	 *            the genre of item
	 * @param imagePath
	 *            the path of item image
	 * @param language
	 *            the language of item
	 */
	void setCommonField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, Language language,
			int copies, int release);

	/**
	 * Sets all film field.
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
			String year, ItemGenre genre, String imagePath, String duration,
			TypeColor color, Language language, int copies, int release);

	/**
	 * Sets all book fields.
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
	 * @param imagePath
	 *            the path of mobookvie image
	 * @param isbn
	 *            the isbn code of book
	 * @param language
	 *            the language of book
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String isbn,
			Language language, int copies, int release);

}
