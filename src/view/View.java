package view;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.UserInfo;
import view.ViewImpl.CardName;
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
	 * method which @return the string user wants to search.
	 */
	String getSearchText();

	/**
	 *
	 * method which @return the filter in which the user wants to search.
	 */
	utils.TypeItemInfo getSearchFilter();

	/**
	 *
	 * method which @return the type (Book or Movie) the user wants o search in.
	 */
	utils.TypeItem getItemFilter();

	/**
	 * method which returns the score typed by user.
	 *
	 * @return score
	 */
	int getScore();

	/**
	 *
	 * method which calls the controller for taking review.
	 */
	void controllerGetReview();

	/**
	 * method which returns informations from user registration.
	 *
	 * @param info
	 * @return
	 */
	Object getUserRegistration(utils.UserInfo info);

	/**
	 *
	 * method which @return the password typed by manager.
	 */
	String getMenagerPassword();

	/**
	 * method which asks to borrow item.
	 *
	 * @param selectedItem
	 */
	void borrowItem();

	/**
	 * method which asks to give back item.
	 *
	 * @param selectedItem
	 */
	void giveBackItem();

	/**
	 * method which asks to review item.
	 *
	 * @param selectedItem
	 */
	void reviewItem();

	/**
	 * method which asks to put user's like to item.
	 *
	 * @param selectedItem
	 */
	void likeItem();

	/**
	 * method which sets the program controller.
	 *
	 * @param c
	 */
	void setController(Controller c);

	/**
	 * method which asks to controller to get login info.
	 */
	void sendLogin();

	/**
	 * method which told to controller to get modified user info.
	 */
	void sendUserModify();

	/**
	 * method which told to controller to set user info.
	 */

	void giveMeUserInfo();

	/**
	 * method which sets in field the user info.
	 *
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 * @param birthDate
	 * @param email
	 * @param telephone
	 */
	void setUserModifyField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone);

	/**
	 * method which gets info modified by user. Needs a info type passed.
	 *
	 * @param info
	 * @return information
	 */
	Object getModifiedInfo(UserInfo info);

	/**
	 * method which allows to switch between cards by passing the card name.
	 *
	 * @param name
	 */
	void swapView(CardName name);

	/**
	 * method which allows to sets the list containin items borrowed by user.
	 *
	 * @param borrowedItemsList
	 */
	void setBorrowedItemList(String[] borrowedItemsList);

	/**
	 * method which calls controller for setting borrow list.
	 *
	 * @return
	 */
	void giveMeBorrowList();

	/**
	 * method which allows to get review typed by user.
	 *
	 * @return review
	 */
	String getReview();

	/**
	 * method which tells controller to take user creation info.
	 */
	void sendUserCreate();

	/**
	 * method which tells controller to take manager login.
	 */
	void sendManagerLogin();

	/**
	 * method which calls controller for setting filtered list.
	 */
	void giveMeFilteredList();

	/**
	 * method which allows to set filterd list.
	 *
	 * @param filteredList
	 */
	void setFilteredList(String[] filteredList);

	/**
	 * method which tells to controller to create an item.
	 */
	void sendItemCreate();

	/**
	 * method which gets item info.
	 *
	 * @param info
	 * @return
	 */
	Object getItemInfo(utils.TypeItemInfo info);

	/**
	 * method which allows to set book info.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param type
	 * @param language
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String isbn,
			Language language);

	/**
	 * method which allows to set film info.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param imagePath
	 * @param duration
	 * @param color
	 */
	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String imagePath, String duration,
			TypeColor color, Language language);

	/**
	 * method which asks to controller item info.
	 */
	void giveMeItemInfo();

	/**
	 * method which tells controller to get item modified info.
	 *
	 * @return
	 */
	void sendItemModify();

	/**
	 * method which show item info when double-clicked.
	 *
	 * @param type
	 */
	void showItemInfo(utils.TypeItem type);

	/**
	 * method which sets good login and change screen.
	 */
	void goodLogin();

	/**
	 * method which allows to show error messages.
	 *
	 * @param errorMessage
	 */
	void showError(String errorMessage);

	/**
	 * method which ask to take sit.
	 */
	void takeSit();

	/**
	 * method which asks controller the study room status.
	 */
	void giveMeStudyRoomStatus();

	/**
	 * method which sets study room current status.
	 *
	 * @param status
	 *
	 */
	void setStudyRoomStatus(int[] status);

	/**
	 * method which show a notification to user.
	 *
	 * @param message
	 */
	void showMessage(String message);

	/**
	 * method which returns the taken sits
	 *
	 * @return
	 */
	int getTakenSits();

	/**
	 * method which returns the day selected in study room.
	 *
	 * @return
	 */
	int getStudyRoomSelectedDay();

	/**
	 * method which returns the month selected in study room.
	 *
	 * @return
	 */
	int getStudyRoomSelectedMonth();

	/**
	 * method which returns the year selected in study room.
	 *
	 * @return
	 */
	int getStudyRoomSelectedYear();

	/**
	 * method which tells to controller to remove a sit.
	 */
	void removeSit();

	/**
	 * method which asks controller to set SuggestedBooksList.
	 */
	void giveMeSuggestedBooks();

	/**
	 * method which asks controller to set SuggestedMoviesList.
	 */
	void giveMeSuggestedMovies();

	/**
	 * method which asks controller to remove like form an item.
	 */
	void removeLike();

	/**
	 * method which asks controller to remove item form wishlist.
	 *
	 * @return
	 */
	void removeFromWishlist();

	/**
	 * return item to remove from Likelist, Borrowedlist or Wishlist.
	 *
	 * @return
	 */
	String getItemToRemoveFromLikeBorrowWish();

	/**
	 * method which asks controller to set wishlist.
	 */
	void giveMeWishlist();

	/**
	 * method which asks controller to set allUserList.
	 */
	void giveMeUserList();

	/**
	 * method which asks controller to set allItemList.
	 */
	void giveMeItemList();

	/**
	 * method which asks controller to delete selectedUser.
	 */
	void deleteUser();

	/**
	 * method which asks controller to delete selectedUser.
	 */
	void deleteItem();

	/**
	 * method which returns the item/user delected by manager to be
	 * deleted/modified/show.
	 *
	 * @return
	 */
	String getUserItemSelectedByManager();

	/**
	 * method which starts the screen with user info.
	 */
	void showUserInfo();

	/**
	 * send notification of forced give back for a book.
	 *
	 * @param book
	 */
	void showGiveBackMessage(String book);

	/**
	 * send notification with options for a book.
	 *
	 * @param book
	 */
	void showGiveBackOptionMessage(String book);

	/**
	 * method which tells to controller to extend borrow.
	 */
	void extendBorrow();

	/**
	 * method which sets the wishlist.
	 *
	 * @param list
	 */
	void setWishlist(String[] list);

	/**
	 * method which sets the list of all users.
	 *
	 * @param list
	 */
	void setUserList(String[] list);

	/**
	 * method which sets the list of all item.
	 *
	 * @param list
	 */
	void setItemList(String[] list);

	/**
	 * method which sets Suggested Books list.
	 *
	 * @param bList
	 */
	void setSuggestedBooks(String[] bList);

	/**
	 * method which sets Suggested Movies list.
	 *
	 * @param mList
	 */
	void setSuggestedMovies(String[] mList);

	/**
	 * method which get item to delete or modify.
	 *
	 * @return
	 */
	String getItemToRemoveModify();

	/**
	 * method which returns the item selected by user in mediateca.
	 *
	 * @return
	 */
	String getItemSelectedByUser();

}
