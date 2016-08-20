package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import utils.UserInfo;
import view.ItemScreenImpl.ItemScreenType;
import view.ListScreenImpl.ListScreenType;
import view.UserLoginImpl.LoginType;
import view.UserScreenImpl.UserScreenType;
import controller.Controller;

/**
 * Main class of the view. Contains the implementation of methods which can be
 * used by controller.
 *
 * @author Luca
 *
 */

public class ViewImpl implements View {

	private static final JPanel CONTAINER = new JPanel();
	private static final CardLayout CL = new CardLayout();
	static final int SCREEN_LENGHT = 800;
	static final int SCREEN_WIDTH = 600;
	static final int STUDY_ROOM_SITS = 100;
	static final int FONT_SIZE = 25;
	static final int TITLE_SIZE = 40;
	static final int SMALL_SIZE = 20;

	static final int IMAGE_LENGHT = 140;
	static final int IMAGE_WIDTH = 100;

	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(this.sw);

	private JPanel card1;
	private JPanel card2;
	private JPanel card3;
	private JPanel card4;
	private JPanel card5;
	private JPanel card6;
	private JPanel card7;
	private JPanel card8;
	private JPanel card9;
	private JPanel card10;
	private JPanel card11;
	private JPanel card12;
	private JPanel card13;
	private JPanel card14;

	private JFrame mainFrame = new JFrame();

	/**
	 * enum for List screen type
	 *
	 * @author Luca Giorgetti
	 *
	 */
	private Controller c;

	@Override
	public void setController(final Controller a) {
		this.c = a;
	}

	/**
	 * enum for iteminfo.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum OtherItemFilter {
		RELEASE_NUMBER, COPIES_NUMBER
	}

	/**
	 * enum for card name.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum CardName {
		MAIN("Main Card"), LOGIN("Login Card"), MENU("Menu Card"), ITEM(
				"Item Card"), USER_MODIFY("User Modify Card"), LIKE_LIST(
				"LikeList Screen Card"), BORROWED_LIST(
				"BorrowedList Screen Card"), REVIEW("Review Card"), USER_CREATE(
				"User Create Card"), MANAGER_LOGIN("Manager Login"), ITEM_CREATE(
				"Item Create Card"), MANAGER_MENU("Manager Menu Card"), STUDY_ROOM(
				"Study Room Card"), WISHLIST("Wishlist Card"), ITEM_MODIFY(
				"Item Modify Card"), ALL_REVIEWS("All Reviews Card");

		private final String name;

		/**
		 * @param text
		 */
		CardName(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startView() {

		this.card1 = new UserLoginImpl(this, LoginType.USER);
		this.card2 = new UserMenuImpl(this);
		this.card3 = new MediatecaScreenImpl(this);
		this.card4 = new UserScreenImpl(this, UserScreenType.MODIFY);
		this.card5 = new ListScreenImpl(this, ListScreenType.WISH);
		this.card6 = new ListScreenImpl(this, ListScreenType.BORROWED);
		this.card7 = new ReviewScreenImpl(this);
		this.card8 = new UserScreenImpl(this, UserScreenType.CREATE);
		this.card9 = new UserLoginImpl(this, LoginType.MANAGER);
		this.card10 = new ItemScreenImpl(this, ItemScreenType.CREATE);
		this.card11 = new StudyRoomImpl(this);
		this.card13 = new ItemScreenImpl(this, ItemScreenType.MODIFY);
		this.card12 = new ManagerScreenImpl(this);
		this.card14 = new ListScreenImpl(this, ListScreenType.REVIEWS);

		this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.mainFrame.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.mainFrame.setResizable(false);
		ViewImpl.CONTAINER.setLayout(ViewImpl.CL);
		final JPanel card0 = new JPanel();
		final JLabel welcome = new JLabel("Benvenuto in Mediateca!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.TITLE_SIZE));
		welcome.setBounds(12, 13, 776, 82);
		final JButton login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		final JButton userCreate = new JButton("Registrati");
		userCreate.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		userCreate.setBounds(435, 164, 281, 138);
		login.setBounds(70, 164, 281, 138);

		card0.setLayout(null);
		card0.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		card0.add(welcome);
		card0.add(login);
		card0.add(userCreate);
		this.card1.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

		ViewImpl.CONTAINER.add(card0, CardName.MAIN.toString());

		JLabel managerLogin = new JLabel("Accedi come Gestore");
		managerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		managerLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				ViewImpl.this.swapView(CardName.MANAGER_LOGIN);
			}
		});
		managerLogin.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.FONT_SIZE));
		managerLogin.setBounds(27, 490, 334, 64);
		card0.add(managerLogin);
		ViewImpl.CONTAINER.add(this.card1, CardName.LOGIN.toString());
		ViewImpl.CONTAINER.add(this.card2, CardName.MENU.toString());
		ViewImpl.CONTAINER.add(this.card3, CardName.ITEM.toString());
		ViewImpl.CONTAINER.add(this.card4, CardName.USER_MODIFY.toString());
		ViewImpl.CONTAINER.add(this.card5, CardName.WISHLIST.toString());
		ViewImpl.CONTAINER.add(this.card6, CardName.BORROWED_LIST.toString());
		ViewImpl.CONTAINER.add(this.card7, CardName.REVIEW.toString());
		ViewImpl.CONTAINER.add(this.card8, CardName.USER_CREATE.toString());
		ViewImpl.CONTAINER.add(this.card9, CardName.MANAGER_LOGIN.toString());
		ViewImpl.CONTAINER.add(this.card10, CardName.ITEM_CREATE.toString());
		ViewImpl.CONTAINER.add(this.card11, CardName.STUDY_ROOM.toString());
		ViewImpl.CONTAINER.add(this.card13, CardName.ITEM_MODIFY.toString());
		ViewImpl.CONTAINER.add(this.card12, CardName.MANAGER_MENU.toString());
		ViewImpl.CONTAINER.add(this.card14, CardName.ALL_REVIEWS.toString());

		this.swapView(CardName.MAIN);
		login.addActionListener(e -> this.swapView(CardName.LOGIN));
		userCreate.addActionListener(e -> this.swapView(CardName.USER_CREATE));
		this.mainFrame.getContentPane().add(ViewImpl.CONTAINER);
		this.mainFrame.setVisible(true);
	}

	// //OK

	@Override
	public void swapView(final CardName name) {
		ViewImpl.CL.show(ViewImpl.CONTAINER, name.toString());

	}

	// //OK
	@Override
	public String getUsername() {
		return ((UserLogin) this.card1).getUserUsername();
	}

	// //OK
	@Override
	public String getPassword() {
		return ((UserLogin) this.card1).getUserPassword();

	}

	// //OK
	@Override
	public utils.TypeItemInfo getSearchFilter() {
		return ((MediatecaScreen) this.card3).getSearchFilter();

	}

	// //OK
	@Override
	public utils.TypeItem getItemFilter() {
		return ((MediatecaScreen) this.card3).getItemType();

	}

	// //OK
	@Override
	public String getSearchText() {
		return ((MediatecaScreen) this.card3).getSearchText();

	}

	// //OK
	@Override
	public int getScore() {
		return ((ReviewScreen) this.card7).getSelectedScore();
	}

	// //OK
	@Override
	public String getReview() {
		return ((ReviewScreen) this.card7).getReview();
	}

	// //OK
	@Override
	public void setFilteredList(final String[] filteredList) {
		((MediatecaScreenImpl) this.card3).setFilteredList(filteredList);
	}

	// //OK
	@Override
	public void setBorrowedItemList(final String[] borrowedItemsList) {
		((ListScreenImpl) this.card6).setBorrowedList(borrowedItemsList);
	}

	@Override
	public void borrowItem() {
		// c.borrow();
	}

	@Override
	public void giveBackItem() {
		// c.giveBack();
	}

	// //OK
	@Override
	public void reviewItem() {
		this.c.addReview();
	}

	// //OK
	@Override
	public void likeItem() {
		this.c.addLike();
	}

	// //OK
	@Override
	public Object getUserRegistration(final utils.UserInfo info) {
		return ((UserScreenImpl) this.card8).getInfo(info);

	}

	// //OK
	@Override
	public void sendLogin() {
		this.c.userLogin();
	}

	// //OK
	@Override
	public String getMenagerPassword() {
		return ((UserLoginImpl) this.card9).getManagerPassword();
	}

	// //waiting CONTROLLER Function Name
	@Override
	public void sendUserModify() {
		// ViewImpl.c.userModify();
	}

	// //OK
	@Override
	public void setUserModifyField(final String name, final String surname,
			final String username, final String password,
			final String birthDate, final String email, final String telephone) {
		((UserScreenImpl) this.card4).setField(name, surname, username,
				password, birthDate, email, telephone);
	}

	// //OK
	@Override
	public void giveMeUserInfo() {

	}

	// //OK
	@Override
	public Object getModifiedInfo(final UserInfo info) {
		return ((UserScreenImpl) this.card4).getInfo(info);
	}

	@Override
	public void giveMeBorrowList() {
		this.c.borrowList();
	}

	// //waiting CONTROLLER function name
	@Override
	public void controllerGetReview() {
		// ViewImpl.c.review();

	}

	// //OK
	@Override
	public void sendUserCreate() {
		this.c.registerNewUser();

	}

	// //waiting CONTROLLER function name
	@Override
	public void sendManagerLogin() {
		// this.c.managerLogin();

	}

	// //OK
	@Override
	public void giveMeFilteredList() {
		try {
			this.c.itemElaboration();
		} catch (Exception e) {

			e.printStackTrace(this.pw);
			this.showError(this.sw.toString());
		}
	}

	// //waiting CONTROLLER function name
	@Override
	public void sendItemCreate() {
		// this.c.itemCreate();

	}

	// //OK
	@Override
	public Object getItemInfo(final utils.TypeItemInfo info,
			final view.ViewImpl.OtherItemFilter info2) {
		return ((ItemScreenImpl) this.card10).getItemInfo(info, info2);
	}

	// //OK
	@Override
	public void setBookField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String imagePath,
			final String isbn, final utils.Language language, final int copies,
			final int release) {
		((ItemScreenImpl) this.card10).setBookField(title, author,
				manifacturer, year, genre, imagePath, isbn, language, copies,
				release);
	}

	// //OK
	@Override
	public void setFilmField(final String title, final String author,
			final String manifacturer, final String year,
			final utils.ItemGenre genre, final String imagePath,
			final String duration, final utils.TypeColor color,
			final utils.Language language, final int copies, final int release) {
		((ItemScreenImpl) this.card10).setFilmField(title, author,
				manifacturer, year, genre, imagePath, duration, color,
				language, copies, release);
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeItemInfo() {
		// this.c.setItemInfo();

	}

	@Override
	public void sendItemModify() {
		// this.c.itemModify();
	}

	@Override
	public void showItemInfo(final utils.TypeItem type) {
		ItemShow itemScreen = new ItemShowImpl();
		itemScreen.startItemShow(this, type);
	}

	@Override
	public void goodLogin() {
		this.swapView(CardName.MENU);
	}

	@Override
	public void showError(final String errorMessage) {
		JOptionPane.showMessageDialog(this.mainFrame, errorMessage);
	}

	@Override
	public void showMessage(final String message) {
		JOptionPane.showMessageDialog(this.mainFrame, message);
	}

	// //OK
	@Override
	public void takeSit() {
		try {
			this.c.takeSit();
		} catch (Exception e) {

			e.printStackTrace(this.pw);
			this.showError(this.sw.toString());
		}
	}

	// //OK
	@Override
	public void giveMeStudyRoomStatus() {
		this.c.studyRoomStatus();
	}

	@Override
	public void setStudyRoomStatus(final int[] status) {
		((StudyRoomImpl) this.card11).setStudyRoomStatus(status);

	}

	@Override
	public int getTakenSits() {
		return ((StudyRoomImpl) this.card11).getTakenSit();
	}

	@Override
	public int getStudyRoomSelectedDay() {
		return ((StudyRoomImpl) this.card11).getDateDay();
	}

	@Override
	public int getStudyRoomSelectedMonth() {
		return ((StudyRoomImpl) this.card11).getDateMonth();
	}

	@Override
	public int getStudyRoomSelectedYear() {
		return ((StudyRoomImpl) this.card11).getDateYear();
	}

	// //OK
	@Override
	public void removeSit() {
		try {
			this.c.itemElaboration();
		} catch (Exception e) {

			e.printStackTrace(this.pw);
			this.showError(this.sw.toString());
		}

	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeSuggestedBooks() {
		// c.suggestedBooks();

	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeSuggestedMovies() {
		// c.suggestedMovies();

	}

	// //waiting CONTROLLER function name
	@Override
	public void removeLike() {
		// c.removeLike();

	}

	// //waiting CONTROLLER function name
	@Override
	public void removeFromWishlist() {
		// c.removeFromWishlist();

	}

	@Override
	public String getItemToRemoveFromLikeBorrowWish() {
		return ((ListScreenImpl) this.card5).getSelectedItem();
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeWishlist() {
		// c.wishlist();

	}

	@Override
	public void setWishlist(final String[] list) {
		((ListScreenImpl) this.card5).setWishlist(list);
	}

	@Override
	public void setSuggestedBooks(final String[] bList) {
		((UserMenuImpl) this.card2).setSuggestedBooks(bList);
	}

	@Override
	public void setSuggestedMovies(final String[] mList) {
		((UserMenuImpl) this.card2).setSuggestedMovies(mList);
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeUserList() {
		// c.setAllUserList();

	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeItemList() {
		// c.setAllItemList();

	}

	// //waiting CONTROLLER function name
	@Override
	public void deleteUser() {
		// this.c.deleteUser();

	}

	// //waiting CONTROLLER function name
	@Override
	public void deleteItem() {
		// c.deleteItem();

	}

	@Override
	public String getUserItemSelectedByManager() {
		return ((ManagerScreenImpl) this.card12).getSelected();
	}

	@Override
	public void showUserInfo() {
		UserShow userScreen = new UserShowImpl();
		userScreen.startUserShow(this);
	}

	@Override
	public void showGiveBackOptionMessage(final String book) {
		// Custom button text
		final Object[] options = { "Consegna",
				"Aumenta il prestito di un altro mese" };
		int choose = JOptionPane.showOptionDialog(this.mainFrame,
				"Dovresti consegare il seguente libro:" + book
				+ "Cosa vuoi fare?", "Notifica di consegna",
				JOptionPane.YES_NO_CANCEL_OPTION, 0, null, options, options[0]);

		if (choose == 0) {
			this.giveBackItem();
		} else if (choose == 1) {
			this.extendBorrow();
		}

	}

	// //waiting CONTROLLER function name
	@Override
	public void extendBorrow() {
		// c.extendBorrow();

	}

	// //OK

	@Override
	public void showGiveBackMessage(final String book) {

		JOptionPane.showMessageDialog(this.mainFrame,
				"Devi consegare il seguente libro:" + book,
				"Notifica di consegna", JOptionPane.WARNING_MESSAGE);

	}

	// //OK
	@Override
	public void setUserList(final String[] list) {
		((ManagerScreenImpl) this.card12).setUserList(list);
	}

	// //OK
	@Override
	public void setItemList(final String[] list) {
		((ManagerScreenImpl) this.card12).setItemList(list);
	}

	// //OK
	@Override
	public String getItemToRemoveModify() {
		return ((ManagerScreenImpl) this.card12).getSelected();
	}

	// //OK
	@Override
	public String getItemSelectedByUser() {
		return ((MediatecaScreenImpl) this.card3).getSelectedItemFromList();
	}

	// //OK
	@Override
	public String getSelectedSit() {
		return ((StudyRoomImpl) this.card11).getSelectedSit();
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveManagerBorrowList() {
		// c.userBorrowList();

	}

	@Override
	public void setManagerBorrowList(final String[] borrowedList) {
		((ManagerScreenImpl) this.card12).setUserBorrowedList(borrowedList);
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeAllItemReviews() {
		// c.itemReviews();
	}

	@Override
	public void setItemReviewsList(final String[] reviewsList) {
		((ListScreenImpl) this.card14).setReviewslist(reviewsList);
	}

	@Override
	public void giveBackItemAfterNotify(final String item) {
		// c.giveBack(item);
	}
}
