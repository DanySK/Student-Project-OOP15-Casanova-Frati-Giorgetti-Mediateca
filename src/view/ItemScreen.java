package view;

import javax.swing.ImageIcon;

import view.ItemScreenImpl.ItemInfo;

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
	Object getItemInfo(ItemInfo info);

	/**
	 * method which allows to resize image by passing path.
	 *
	 * @param imagePath
	 * @return
	 */
	ImageIcon resizeImage(String imagePath);

	void setCommonField(String title, String author, String manifacturer,
			String year, String genre, String imagePath);

	void setFilmField(String title, String author, String manifacturer,
			String year, String genre, String imagePath, String duration,
			String color);

	void setBookField(String title, String author, String manifacturer,
			String year, String genre, String imagePath, String isbn);

}
