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
	void login();

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
}
