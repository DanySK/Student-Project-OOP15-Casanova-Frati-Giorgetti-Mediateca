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
	View v;
	public ReviewScreen r = new ReviewScreenImpl();
	public ListScreen l = new ListScreenImpl();

	public void View() {
		this.v = new ViewImpl();

	}

	final JPanel card1 = new UserLoginImpl(this.v);
	final JPanel card2 = new UserMenuImpl(this.v, this.l, this.r,
			this.SCREEN_LENGHT, this.SCREEN_WIDTH);
	final JPanel card3 = new BookScreenImpl(this.v, this.l, this.r,
			this.SCREEN_LENGHT, this.SCREEN_WIDTH);

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

	@Override
	public void startView() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(this.SCREEN_LENGHT, this.SCREEN_WIDTH);
		mainFrame.setResizable(false);
		ViewImpl.container.setLayout(ViewImpl.cl);
		final JPanel card0 = new JPanel();
		final JLabel welcome = new JLabel(
				"Benvenuto in Mediateca: esegui il login per poter accedere!");
		final JButton login = new JButton("Login");

		card0.add(welcome, "Benvenuto");

		ViewImpl.container.add(card0, "Main Card");
		ViewImpl.container.add(this.card1, "Login Card");
		ViewImpl.container.add(this.card2, "User Menu Card");
		ViewImpl.container.add(this.card3, "Book Screen Panel");
		ViewImpl.cl.show(ViewImpl.container, "Main Card");
		login.addActionListener(e -> ViewImpl.cl.show(ViewImpl.container,
				"LoginCard"));
		mainFrame.add(ViewImpl.container);
		mainFrame.setVisible(true);
	}

	@Override
	public void swapView(final String panelName) {
		ViewImpl.cl.show(ViewImpl.container, panelName);
		return;
	}

	@Override
	public String getUsername() {
		return ((UserLoginImpl) this.card1).getUserUsername();
	}

	@Override
	public String getPassword() {
		return ((UserLoginImpl) this.card1).getUserPassword();

	}

	@Override
	public String getSearchFilter() {
		return ((BookScreenImpl) this.card3).getSearchFilter();

	}

	@Override
	public String getItemFilter() {
		return ((BookScreenImpl) this.card3).getItemType();

	}

	@Override
	public String getSearchText() {
		return ((BookScreenImpl) this.card3).getSearchText();

	}

	@Override
	public int getScore() {
		return ReviewScreen.getSelectedScore();
	}

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

	@Override
	public void sendLogin() {
		ViewImpl.c.login();
	}

	/*
	 * @Override public int getStudyRoomSitsJustTaken() {
	 *
	 * }
	 */
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
}
