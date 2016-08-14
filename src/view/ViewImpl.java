package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

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

	private static final JPanel container = new JPanel();
	private static final CardLayout cl = new CardLayout();
	final int SCREEN_LENGHT = 1280;
	final int SCREEN_WIDTH = 920;

	JPanel card1;
	JPanel card2;
	JPanel card3;
	JPanel card4;
	JPanel card5;
	JPanel card6;
	JPanel card7;
	JPanel card8;
	JPanel card9;

	/**
	 * enum for List screen type
	 *
	 * @author Luca Giorgetti
	 *
	 */
	private Controller c;

	@Override
	public void setController(final Controller c) {
		this.c = c;
	}

	public enum UserInfo {
		name, surname, username, password, birthDate, email, telephone
	}

	public enum CardName {
		MAIN("Main Card"), LOGIN("Login Card"), MENU("Menu Card"), ITEM(
				"Item Card"), USER_MODIFY("User Modify Card"), LIKE_LIST(
						"LikeList Screen Card"), BORROWED_LIST(
								"BorrowedList Screen Card"), REVIEW("Review Card"), USER_CREATE(
										"User Create Card"), MANAGER_LOGIN("Manager Login");

		private final String name;

		/**
		 * @param text
		 */
		private CardName(final String name) {
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
		View v = new ViewImpl();
		this.card1 = new UserLoginImpl(v, LoginType.USER, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH);
		this.card2 = new UserMenuImpl(v, this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		this.card3 = new MediatecaScreenImpl(v, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH);
		this.card4 = new UserScreenImpl(v, UserScreenType.MODIFY,
				this.SCREEN_LENGHT, this.SCREEN_LENGHT);
		this.card5 = new ListScreenImpl(v, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH, ListScreenType.LIKE);
		this.card6 = new ListScreenImpl(v, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH, ListScreenType.BORROWED);
		this.card7 = new ReviewScreenImpl(v, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH);
		this.card8 = new UserScreenImpl(v, UserScreenType.CREATE,
				this.SCREEN_LENGHT, this.SCREEN_LENGHT);
		this.card9 = new UserLoginImpl(v, LoginType.MANAGER,
				this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		mainFrame.setResizable(false);
		ViewImpl.container.setLayout(ViewImpl.cl);
		final JPanel card0 = new JPanel();
		final JLabel welcome = new JLabel("Benvenuto in Mediateca!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 40));
		welcome.setBounds(325, 63, 615, 82);
		final JButton login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, 30));
		final JButton userCreate = new JButton("Registrati");
		userCreate.setFont(new Font("Tahoma", Font.BOLD, 30));
		userCreate.setBounds(670, 173, 281, 179);
		login.setBounds(299, 173, 281, 179);

		card0.setLayout(null);
		card0.setSize(this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		card0.add(welcome);
		card0.add(login);
		card0.add(userCreate);
		this.card1.setSize(this.SCREEN_LENGHT, this.SCREEN_WIDTH);

		ViewImpl.container.add(card0, CardName.MAIN.toString());

		JLabel ManagerLogin = new JLabel("Accedi come Gestore");
		ManagerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		ManagerLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				v.swapView(CardName.MANAGER_LOGIN);
			}
		});
		ManagerLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		ManagerLogin.setBounds(40, 801, 334, 64);
		card0.add(ManagerLogin);
		ViewImpl.container.add(this.card1, CardName.LOGIN.toString());
		ViewImpl.container.add(this.card2, CardName.MENU.toString());
		ViewImpl.container.add(this.card3, CardName.ITEM.toString());
		ViewImpl.container.add(this.card4, CardName.USER_MODIFY.toString());
		ViewImpl.container.add(this.card5, CardName.LIKE_LIST.toString());
		ViewImpl.container.add(this.card6, CardName.BORROWED_LIST.toString());
		ViewImpl.container.add(this.card7, CardName.REVIEW.toString());
		ViewImpl.container.add(this.card8, CardName.USER_CREATE.toString());
		ViewImpl.container.add(this.card9, CardName.MANAGER_LOGIN.toString());

		this.swapView(CardName.MAIN);
		login.addActionListener(e -> this.swapView(CardName.LOGIN));
		userCreate.addActionListener(e -> this.swapView(CardName.USER_CREATE));
		mainFrame.getContentPane().add(ViewImpl.container);
		mainFrame.setVisible(true);
	}

	// //OK

	@Override
	public void swapView(final CardName name) {
		ViewImpl.cl.show(ViewImpl.container, name.toString());
		return;
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
	public String getSearchFilter() {
		return ((MediatecaScreen) this.card3).getSearchFilter();

	}

	// //OK
	@Override
	public String getItemFilter() {
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

	public void setFilteredList(final List<String> filteredList) {
	}

	// //OK
	@Override
	public void setBorrowedItemList(final List<String> borrowedItemsList) {
		((ListScreenImpl) this.card6).setBorrowedList(borrowedItemsList);
	}

	@Override
	public void borrowItem(final String selectedItem) {
		return;
	}

	@Override
	public void giveBackItem(final String selectedItem) {
		return;
	}

	@Override
	public void reviewItem(final String selectedItem) {
		return;
	}

	@Override
	public void likeItem(final String selecetedItem) {
		return;
	}

	@Override
	public void getUserRegistration() {

	}

	/*
	 * @Override public String getMenagerPassword() {
	 * 
	 * }
	 */
	@Override
	public void getAddedItem() {

	}

	@Override
	public void getRemovedItem() {

	}

	// //OK
	@Override
	public void sendLogin() {
		this.c.login();
	}

	@Override
	public void setStudyRoomStatus(final ArrayList studyRoomStatus) {

	}

	@Override
	public void setFilteredList() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setStudyRoomStatus() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getMenagerPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStudyRoomSitsJustTaken() {
		// TODO Auto-generated method stub
		return 0;
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
	public String getModifiedInfo(final UserInfo info) {
		return ((UserScreenImpl) this.card4).getInfo(info);
	}

	// //OK
	@Override
	public void setLikeItemList(final List<String> likeList) {
		((ListScreenImpl) this.card5).setLikeList(likeList);
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeBorrowList() {
		// ViewImpl.c.borrowList();
	}

	// //waiting CONTROLLER function name
	@Override
	public void giveMeLikeList() {
		// ViewImpl.c.likeList();
	}

	// //waiting CONTROLLER function name
	@Override
	public void controllerGetReview() {
		// ViewImpl.c.review();

	}

	// //waiting CONTROLLER function name
	@Override
	public void sendUserCreate() {
		// this.c.userCreate();

	}

	// //waiting CONTROLLER function name
	@Override
	public void sendManagerLogin() {
		// this.c.managerLogin();

	}
}
