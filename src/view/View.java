package view;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItemInfo;
import utils.UserInfo;
import view.ViewImpl.CardName;
import view.ViewImpl.OtherItemFilter;
import controller.Controller;

/**
 *
 * Interface for the view. It defines methods which can be used by the
 * controller.
 *
 * @author Luca Giorgetti
 */
public interface View {

	/**
	 * Invoke at the start of the program. It sets the main frame and the
	 * starting screen
	 */
	void startView();

	/**
	 * Return the username typed by user at login.
	 *
	 * @return username typed by user.
	 */
	String getUsername();

	/**
	 * Return the password typed by user at login.
	 *
	 * @return password typed by user.
	 */
	String getPassword();

	/**
	 * Return the text typed by user to serach in lists.
	 *
	 * @return text to search
	 */
	String getSearchText();

	/**
	 * Return the filter for filtering the list showed in Mediateca Screen.
	 *
	 * @return TypeItemInfo filter.
	 */
	utils.TypeItemInfo getSearchFilter();

	/**
	 * Return the type of item to filter (Book or Movie).
	 *
	 * @return TypeItem type.
	 */
	utils.TypeItem getItemFilter();

	/**
	 * Returns the score assigned to a review of item, item taked by
	 * getItemSelectedByUser().
	 *
	 * @return score assigned to a review
	 */
	int getScore();

	/**
	 * Calls controller for asking to get review with getScore() e getReview().
	 */
	void controllerGetReview();

	/**
	 * Multigetter which return all the information typed by user during
	 * registration.
	 *
	 * @param info
	 *            the type of info you want to get
	 * @return information typed by user
	 */
	Object getUserRegistration(utils.UserInfo info);

	/**
	 * Return the system password typed by manager.
	 *
	 * @return system password typed.
	 */
	String getMenagerPassword();

	/**
	 * Asks controller to borrow item.
	 *
	 */
	void borrowItem();

	/**
	 * Asks controller to give back item.
	 *
	 */
	void giveBackItem();

	/**
	 * Asks controller to review item.
	 *
	 */
	void reviewItem();

	/**
	 * Asks controller to put user's like to item.
	 *
	 */
	void likeItem();

	/**
	 * Sets the program controller. Necessary for the program.
	 *
	 * @param c
	 *            the controller name
	 */
	void setController(Controller c);

	/**
	 * Asks to controller to get login info.
	 */
	void sendLogin();

	/**
	 * Told controller to get modified user info with getModifiedInfo();.
	 */
	void sendUserModify();

	/**
	 * Told controller to set user info in screen with setUserModifyInfo();.
	 */
	void giveMeUserInfo();

	/**
	 * Sets user inforation in screen.
	 *
	 * @param name
	 *            the user name
	 * @param surname
	 *            the user surname
	 * @param username
	 *            the user username
	 * @param password
	 *            the user password
	 * @param birthDate
	 *            the user birthDate
	 * @param email
	 *            the user email
	 * @param telephone
	 *            the user telephone number
	 */
	void setUserModifyField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone);

	/**
	 * Gets information by UserScreen. Needs a parameter for the information
	 * type.
	 *
	 * @param info
	 *            the type of info you want
	 * @return the information requested
	 */
	Object getModifiedInfo(UserInfo info);

	/**
	 * Allow to change screen by passing the panel name.
	 *
	 * @param name
	 *            panel name present in CardName
	 */
	void swapView(CardName name);

	/**
	 * Sets the list of borrowed item asked with giveMeBorrowList();.
	 *
	 * @param borrowedItemsList
	 *            the list of borrowed item by a user
	 */
	void setBorrowedItemList(String[] borrowedItemsList);

	/**
	 * Asks controller for setting borrow list.
	 */
	void giveMeBorrowList();

	/**
	 * Allows to get review typed by user.
	 *
	 * @return review typed by user
	 */
	String getReview();

	/**
	 * Tells controller to take user creation info with getUserRegistration().
	 */
	void sendUserCreate();

	/**
	 * Tells controller to take manager login with getManagerPassword().
	 */
	void sendManagerLogin();

	/**
	 * Calls controller for setting list.
	 */
	void giveMeFilteredList();

	/**
	 * method which allows to set list filtered with filter from
	 * getSearchFilter, getSearchText(), getItemType().
	 *
	 * @param filteredList
	 *            list filterd with taken filters.
	 */
	void setFilteredList(String[] filteredList);

	/**
	 * Tells controller to create an item.
	 */
	void sendItemCreate();

	/**
	 * Gets item info after creation or modify.
	 *
	 * @param info
	 *            the type of info you want
	 * @return the information requested
	 */
	Object getItemInfo(TypeItemInfo info);

	/**
	 * Sets information of books requested with giveMeItemInfo().
	 *
	 * @param title
	 *            the title of item
	 * @param author
	 *            the author of item
	 * @param manifacturer
	 *            the producer of item
	 * @param year
	 *            the year of release of item
	 * @param genre
	 *            the genre of item
	 * @param imagePath
	 *            the path of item image.
	 * @param isbn
	 *            the isbn code of item
	 * @param language
	 *            the language of item
	 * @param copies
	 *            the copies number of item
	 * @param release
	 *            the release number of item
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String isbn,
			Language language, int copies, int release);

	/**
	 * Sets information of movies requested with giveMeItemInfo().
	 *
	 * @param title
	 *            the title of item
	 * @param author
	 *            the author of item
	 * @param manifacturer
	 *            the producer of item
	 * @param year
	 *            the year of release of item
	 * @param genre
	 *            the genre of item
	 * @param imagePath
	 *            the path of item image
	 * @param duration
	 *            the duration of item
	 * @param color
	 *            the color type of item
	 * @param language
	 *            the language of item
	 * @param copies
	 *            the copies number of item
	 * @param release
	 *            the release number of item
	 */
	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String duration,
			TypeColor color, Language language, int copies, int release);

	/**
	 * Asks controller to set item info.
	 */
	void giveMeItemInfo();

	/**
	 * Tells controller to get item modified info.
	 *
	 */
	void sendItemModify();

	/**
	 * Show item info when double-clicked.
	 *
	 * @param type
	 *            the type of item (book or movie)
	 */
	void showItemInfo(utils.TypeItem type);

	/**
	 * Sets good login and change screen.
	 */
	void goodLogin();

	/**
	 * Allows to show error messages.
	 *
	 * @param errorMessage
	 *            the error message you want to show
	 */
	void showError(String errorMessage);

	/**
	 * Asks controller to take sit.
	 */
	void takeSit();

	/**
	 * Asks controller to set the study room status.
	 */
	void giveMeStudyRoomStatus();

	/**
	 * Sets the status (array of taken sits) in a determined day.
	 *
	 * @param status
	 *            array of taken sits
	 */
	void setStudyRoomStatus(int[] status);

	/**
	 * Allows to show a notification to user.
	 *
	 * @param message
	 *            the message you want to show
	 */
	void showMessage(String message);

	/**
	 * Returns the sit taken by user.
	 *
	 * @return just taken sit
	 */
	int getTakenSits();

	/**
	 * Returns the day selected in study room.
	 *
	 * @return selected day
	 */
	int getStudyRoomSelectedDay();

	/**
	 * Returns the month selected in study room.
	 *
	 * @return selected month
	 */
	int getStudyRoomSelectedMonth();

	/**
	 * Returns the year selected in study room.
	 *
	 * @return selected year
	 */
	int getStudyRoomSelectedYear();

	/**
	 * Tells controller to remove the sits selected.
	 */
	void removeSit();

	/**
	 * Asks controller to set SuggestedBooksList.
	 */
	void giveMeSuggestedBooks();

	/**
	 * Asks controller to set SuggestedMoviesList.
	 */
	void giveMeSuggestedMovies();

	/**
	 * Asks controller to remove from wishlist an item getted by
	 * getItemToremoveFromLikeBorrowWish().
	 *
	 */
	void removeFromWishlist();

	/**
	 * Returns item to remove from Likelist, Borrowedlist or Wishlist.
	 *
	 * @return item to remove
	 */
	String getItemToRemoveFromLikeBorrowWish();

	/**
	 * Asks controller to set wishlist.
	 */
	void giveMeWishlist();

	/**
	 * Asks controller to set allUserList.
	 */
	void giveMeUserList();

	/**
	 * Asks controller to set allItemList.
	 */
	void giveMeItemList();

	/**
	 * Asks controller to delete the current user.
	 */
	void deleteUser();

	/**
	 * Asks controller to delete getted by getUserItemSelectedByManager().
	 */
	void deleteItem();

	/**
	 * Returns the item/user selected by manager to be deleted/modified/shown.
	 *
	 * @return selected item by manager
	 */
	String getUserItemSelectedByManager();

	/**
	 * Starts the screen with user info.
	 */
	void showUserInfo();

	/**
	 * Send notification of forced give back for a book.
	 *
	 * @param book
	 *            the book the user have to give back
	 */
	void showGiveBackMessage(String book);

	/**
	 * Send notification with options for a book.
	 *
	 * @param book
	 *            the book the user should give back
	 */
	void showGiveBackOptionMessage(String book);

	/**
	 * Tells controller to extend borrow.
	 */
	void extendBorrow();

	/**
	 * Sets the wishlist.
	 *
	 * @param list
	 *            the list of item liked by user.
	 */
	void setWishlist(String[] list);

	/**
	 * Sets the list of all users.
	 *
	 * @param list
	 *            the list of all users.
	 */
	void setUserList(String[] list);

	/**
	 * Sets the list of all item.
	 *
	 * @param list
	 *            the list of all item.
	 */
	void setItemList(String[] list);

	/**
	 * Sets Suggested Books list.
	 *
	 * @param bList
	 *            the list of book suggested
	 */
	void setSuggestedBooks(String[] bList);

	/**
	 * Sets Suggested Movies list.
	 *
	 * @param mList
	 *            the list of movie suggested
	 */
	void setSuggestedMovies(String[] mList);

	/**
	 * Get item to delete or modify.
	 *
	 * @return item to remove or modify
	 */
	String getItemToRemoveModify();

	/**
	 * Returns the item selected by user in Mediateca.
	 *
	 * @return selected item
	 */
	String getItemSelectedByUser();

	/**
	 * Returns the sit to remove.
	 *
	 * @return the sits selected
	 */
	String getSelectedSit();

	/**
	 * Tells controller to sets the list of item borrowed by user.
	 */
	void giveManagerBorrowList();

	/**
	 * Sets the list of item borrowed by user taken with
	 * getUserItemSelectedByManager().
	 *
	 * @param borrowedList
	 *            the list of item borrowed by selected user
	 */
	void setManagerBorrowList(String[] borrowedList);

	/**
	 * Asks controller to sets all reviews of item getted from
	 * getItemSelectedByUser().
	 */
	void giveMeAllItemReviews();

	/**
	 * Sets the reviews list of item.
	 *
	 * @param reviewsList
	 *            the list of reviews of selected item
	 */
	void setItemReviewsList(String[] reviewsList);

	/**
	 * Tell to controller to give back selected item.
	 *
	 * @param item
	 *            item you have to give back
	 */
	void giveBackItemAfterNotify(String item);

	/**
	 * Returns the item information about release or copies number.
	 *
	 * @param info2
	 *            the type of info (release or copies number)
	 * @return the requeste information
	 */
	Object getOtherItemInfo(OtherItemFilter info2);

	/**
	 * Asks to controller to set taken sits list.
	 */
	void giveMeTakenSits();

	/**
	 * Allows to set the list of just taken sits.
	 *
	 * @param sitslist
	 *            the list of sits just tken by user
	 */
	void setTakenSitsList(String[] sitslist);

}
