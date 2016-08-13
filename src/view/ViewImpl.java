package view;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Controller;

/**
 * Main class of the view. Contains the implementation of methods which can be
 * used by controller.
 *
 * @author Luca
 *
 */

public class ViewImpl implements View {

	final private static JPanel container = new JPanel();
	final private static CardLayout cl = new CardLayout();
	final int SCREEN_LENGHT = 1280;
	final int SCREEN_WIDTH = 920;

	public ReviewScreen r = new ReviewScreenImpl();
	public ListScreen l = new ListScreenImpl();
	JPanel card1;
	JPanel card2;
	JPanel card3;
	JPanel card4;

	/**
	 * enum for List screen type
	 *
	 * @author Luca Giorgetti
	 *
	 */
	private static Controller c;

	@Override
	public void setController(final Controller c) {
		ViewImpl.c = c;
	}

	public enum UserInfo {
		name, surname, username, password, birthDate, email, telephone
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startView() {
		View v = new ViewImpl();
		this.card1 = new UserLoginImpl(v);
		this.card2 = new UserMenuImpl(v, this.l, this.r, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH);
		this.card3 = new BookScreenImpl(v, this.l, this.r, this.SCREEN_LENGHT,
				this.SCREEN_WIDTH);
		this.card4 = new UserModifyImpl(v, this.SCREEN_LENGHT,
				this.SCREEN_LENGHT);
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		mainFrame.setResizable(false);
		ViewImpl.container.setLayout(ViewImpl.cl);
		final JPanel card0 = new JPanel();
		final JLabel welcome = new JLabel(
				"Benvenuto in Mediateca: esegui il login per poter accedere!");
		welcome.setBounds(52, 13, 339, 16);
		final JButton login = new JButton("Login");
		login.setBounds(118, 67, 194, 148);
		card0.setLayout(null);

		card0.add(welcome);
		card0.add(login);

		ViewImpl.container.add(card0, "Main Card");
		ViewImpl.container.add(this.card1, "Login Card");
		ViewImpl.container.add(this.card2, "User Menu Card");
		ViewImpl.container.add(this.card3, "Book Screen Panel");
		ViewImpl.container.add(this.card4, "User Modify Card");
		ViewImpl.cl.show(ViewImpl.container, "Main Card");
		login.addActionListener(e -> this.swapView("Login Card"));
		mainFrame.getContentPane().add(ViewImpl.container);
		mainFrame.setVisible(true);
	}

	// //OK
	@Override
	public void swapView(final String panelName) {
		ViewImpl.cl.show(ViewImpl.container, panelName);
		return;
	}

	// //OK
	@Override
	public String getUsername() {
		return ((UserLoginImpl) this.card1).getUserUsername();
	}

	// //OK
	@Override
	public String getPassword() {
		return ((UserLoginImpl) this.card1).getUserPassword();

	}

	// //OK
	@Override
	public String getSearchFilter() {
		return ((BookScreenImpl) this.card3).getSearchFilter();

	}

	// //OK
	@Override
	public String getItemFilter() {
		return ((BookScreenImpl) this.card3).getItemType();

	}

	// //OK
	@Override
	public String getSearchText() {
		return ((BookScreenImpl) this.card3).getSearchText();

	}

	// //OK
	@Override
	public int getScore() {
		return ReviewScreen.getSelectedScore();
	}

	// //OK
	@Override
	public String getReview() {
		return ReviewScreen.getReview();
	}

	public void setFilteredList(final List filteredList) {

	}

	public void setBorrowedItemList(final List borrowedItemsList) {

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

	public void setItemAvailabilityList(final List availableItemList) {

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
		ViewImpl.c.login();
	}

	@Override
	public void setStudyRoomStatus(final ArrayList studyRoomStatus) {

	}

	@Override
	public void setFilteredList() {
		// TODO Auto-generated method stub
	}

	/*
	 * @Override public List setBorrowedItemList() { return listaPrestiti; //
	 * TODO Auto-generated method stub }
	 */

	@Override
	public void setStudyRoomStatus() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> setBorrowedItemList() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public List<String> setLikeList() {
		return null;
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
		((UserModifyImpl) this.card4).setField(name, surname, username,
				password, birthDate, email, telephone);
	}

	// //OK
	@Override
	public void giveMeUserInfo() {

	}

	// //OK
	@Override
	public String getModifiedInfo(final UserInfo info) {
		return ((UserModifyImpl) this.card4).getInfo(info);
	}

}
