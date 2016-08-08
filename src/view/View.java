package view;

/**
 *
 * Interface for the view. It defines methods which can be used by the
 * controller.
 *
 * @author Luca Giorgetti
 */
public interface View {

	/**
	 *
	 * method to invoke at the start of the program. It sets the main frame and
	 * the starting screen
	 */
	void startView();

	/**
	 *
	 * method which @return the typed Username in login.
	 */
	String getUsername();

	/**
	 *
	 * method which @return the typed Password in login.
	 */
	String getPassword();

	/**
	 *
	 * method which @return the word which the user wants to search.
	 */
	String getSearchFilter();

	/**
	 *
	 * method which @return the review typed by the user.
	 */
	String getReview();

	/**
	 *
	 * method which sets the list of items filtered by the word typed .
	 */
	void setFilteredList();

	/**
	 *
	 * method which sets the list of items borrowed by user.
	 */
	void setBorrowedItemList();

	/**
	 *
	 * method which @return the id of borrowed by user.
	 */
	int getBorrowedItem();

	/**
	 *
	 * method which @return the id of given back by user.
	 */
	int getGivenBackItem();

	/**
	 *
	 * method which sets the list of available items.
	 */
	void setItemAvailabilityList();

	/**
	 *
	 * method which @return informations from user registration.
	 */
	void getUserRegistration();

	/**
	 *
	 * method which @return the password typed by manager.
	 */
	String getMenagerPassword();

	/**
	 *
	 * method which @return information from item creation.
	 */
	void getAddedItem();

	/**
	 *
	 * method which @return information from item removal.
	 */
	void getRemovedItem();

	/**
	 *
	 * method which @return the numeber of taken sit.
	 */
	int getStudyRoomSitsJustTaken();

	/**
	 *
	 * method which sets the status of study room.
	 */
	void setStudyRoomStatus();
}
