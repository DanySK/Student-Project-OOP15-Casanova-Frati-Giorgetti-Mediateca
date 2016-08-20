package controller;

/**
 * Interface for a generic controller.
 *
 * @author
 *
 */

public interface Controller {

	/**
	 * Method who sets the View for the Controller.
	 *
	 * @param v
	 *            View requested by the controller
	 *
	 */
	void setView(view.View v);

	/**
	 * Method which lets the actual user access the program if username and
	 * password are contained into the archive.
	 */
	void userLogin();

	/**
	 * Method which lets the manager access the program.
	 */
	void managerLogin();

	/**
	 * Method which writes some users, items and the study room situation on
	 * files for debug
	 */
	void writeOnFile();

	/**
	 *
	 * Method which elaborates inputs from the user and set the list with items
	 * filtered.
	 *
	 * @throws Exception
	 */
	void itemElaboration() throws Exception;

	/**
	 * Method which adds the like of the current item selected by the actual
	 * user.
	 */
	void addLike();

	/**
	 * Method which adds the numerical vote and/or a review given by the actual
	 * user to the current selected item.
	 */
	void addReview();

	/**
	 * Method which sets the view's list of borrowed item of the actual user.
	 */
	void borrowList();

	/**
	 * Method which elaborates infos given by the user and registrates it into
	 * the archive.
	 */
	void registerNewUser();

	/**
	 * Method which uses the selected sit and the actual day to book the
	 * position in the study room.
	 *
	 * @throws Exception
	 */
	void takeSit() throws Exception;

	/**
	 * Method which removes the booking of the selected sit in the actual day.
	 *
	 * @throws Exception
	 */
	void cancelSit() throws Exception;

	/**
	 * Method which sets the view's status of the study room.
	 */
	void studyRoomStatus();

	/**
	 * Method which extends the borrowing of an object.
	 */
	void extendBorrow();

	/**
	 * Method which checks the status of the loans of the user.
	 */
	void checkDeadlines();

	/**
	 * Method which deletes the actual user from the archive.
	 */
	void deleteUser();

	/**
	 * Method which deletes an item selected by the user.
	 */
	void deleteItem();

	/**
	 * Method which sets the list of items for the View.
	 */
	void setAllItemList();

	/**
	 * Method which sets the list of users for the View.
	 */
	void setAllUserList();

	/**
	 * Method which removes the selected item from the wishlist.
	 */
	void removeFromWishList();

	/**
	 * Method which sets the wishlist of the actual user for the View.
	 */
	void setWishlist();

	/**
	 * Method which returns an item borrowed by the user.
	 * 
	 * @param item
	 *            item to be returned.
	 */
	void giveBackItem(final String item);

	/**
	 * Method which elaborates the loans of the user.
	 */
	void elaborateLoans();

	/**
	 * Method which creates an item based on its type.
	 */
	void itemCreate();
}
