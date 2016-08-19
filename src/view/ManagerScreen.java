package view;

/**
 * Interface for the Manager Scren Panel.
 *
 * @author Luca Giorgetti
 *
 */
public interface ManagerScreen {
	/**
	 * method which allow to set the list of all user.
	 *
	 * @param userList
	 */
	void setUserList(String[] userList);

	/**
	 * method which allow to set the list of all item.
	 *
	 * @param itemList
	 */
	void setItemList(String[] itemList);

	/**
	 * method which allows to get the selected User/Item.
	 *
	 * @return
	 */
	String getSelected();

}
