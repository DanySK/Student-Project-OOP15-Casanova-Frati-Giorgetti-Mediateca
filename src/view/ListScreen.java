package view;

/**
 * Interface for creating a new JFrame with a list printed inside.
 *
 * @author Luca Giorgetti
 */
public interface ListScreen {
	/**
	 * method which sets the borrowed list taken by controller.
	 *
	 * @param bList
	 */
	void setBorrowedList(String[] bList);

	/**
	 * method which sets the like list taken by controller.
	 *
	 * @param lList
	 */
	void setLikeList(String[] lList);

	/**
	 * method which sets the like list taken by controller.
	 *
	 * @param whishlist
	 */
	void setWishlist(String[] wishlist);

	/**
	 * return selected item.
	 * 
	 * @return
	 */
	String getSelectedItem();

}
