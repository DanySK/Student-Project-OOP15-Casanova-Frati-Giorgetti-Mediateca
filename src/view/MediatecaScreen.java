package view;

/**
 *
 * Interface of panel of Book Screen.
 *
 * @author Luca Giorgetti
 */
public interface MediatecaScreen {
	/**
	 * Returns text to search typed by user.
	 *
	 * @return the text to search
	 */
	String getSearchText();

	/**
	 * Returns the filter selected by user.
	 *
	 * @return the selected Filter
	 */
	utils.TypeItemInfo getSearchFilter();

	/**
	 * Returns the item type selected by user.
	 *
	 * @return the type of item
	 */
	utils.TypeItem getItemType();

	/**
	 * Returns the item selected from filtered list.
	 *
	 * @return the selected item
	 */
	String getSelectedItemFromList();

	/**
	 * Sets the filter list.
	 *
	 * @param list
	 *            list filtered by controller
	 */
	void setFilteredList(String[] list);
}