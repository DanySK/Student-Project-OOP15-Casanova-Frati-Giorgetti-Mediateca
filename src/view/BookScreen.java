package view;

/**
 *
 * Interface of panel of Book Screen.
 *
 * @author Luca Giorgetti
 */
public interface BookScreen {
	/**
	 * method which returns text to search typed by user.
	 *
	 * @return String textToSearch
	 */
	String getSearchText();

	/**
	 * method which returns the filter selected by user.
	 *
	 * @return String selectedFilter
	 */
	String getSearchFilter();

	/**
	 * method which returns the item type selected by user.
	 *
	 * @return String itemType
	 */
	String getItemType();

	/**
	 * method which returns the string of the item selected from filtered list
	 */
	String getSelectedItemFromList();
}