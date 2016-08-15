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
	 * method which allows to set item field.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param type
	 */
	void setItemField(String title, String author, String manifacturer,
			String year, String genre, String type, String imagePath);

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

}
