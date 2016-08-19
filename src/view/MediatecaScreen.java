package view;

/**
 *
 * Interface of panel of Book Screen.
 *
 * @author Luca Giorgetti
 */
public interface MediatecaScreen {
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
	utils.TypeItemInfo getSearchFilter();

	/**
	 * method which returns the item type selected by user.
	 *
	 * @return String itemType
	 */
	utils.TypeItem getItemType();

	/**
	 * method which returns the string of the item selected from filtered list.
	 *
	 * @return selectedItem
	 */
	String getSelectedItemFromList();

	/**
	 * method which sets the filter list.
	 *
	 * @param list
	 */
	void setFilteredList(String[] list);
}