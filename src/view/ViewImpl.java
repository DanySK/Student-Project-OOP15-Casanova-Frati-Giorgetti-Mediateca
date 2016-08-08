package view;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Main class of the view. Contains the implementation of methods which can be
 * used by controller.
 *
 * @author Luca
 *
 */
public class ViewImpl implements View {
	static final int SCREEN_LENGHT = 1280;
	static final int SCREEN_WIDTH = 920;
	final private JPanel container = new JPanel();
	final private CardLayout cl = new CardLayout();
	final String selectedItem;

	final JPanel card1 = new UserLoginImpl();
	final JPanel card2 = new UserMenuImpl(ViewImpl.SCREEN_LENGHT,
			ViewImpl.SCREEN_WIDTH);
	final JPanel card3 = new BookScreenImpl(ViewImpl.SCREEN_LENGHT,
			ViewImpl.SCREEN_WIDTH);

	@Override
	public void swapView(final String panelName) {
		this.cl.show(this.container, panelName);
		return;
	}

	@Override
	public void startView() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		mainFrame.setResizable(false);
		this.container.setLayout(this.cl);
		final JPanel card0 = new JPanel();
		final JLabel welcome = new JLabel(
				"Benvenuto in Mediateca: esegui il login per poter accedere!");
		final JButton login = new JButton("Login");

		card0.add(welcome, "Benvenuto");

		this.container.add(card0, "Main Card");
		this.container.add(this.card1, "Login Card");
		this.container.add(this.card2, "User Menu Card");
		this.container.add(this.card3, "Book Screen Panel");
		this.cl.show(this.container, "Main Card");
		login.addActionListener(e -> this.swapView("LoginCard"));
		mainFrame.add(this.container);
		mainFrame.setVisible(true);
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
	public String getReview() {

	}

	public void setFilteredList(final List filteredList) {

	}

	public void setBorrowedItemList(final List borrowedItemsList) {

	}
	@Override
	public void borrowBook(final String selectedItem) {
		return;

	}

	public void setItemAvailabilityList(final List availableItemList) {

	}

	@Override
	public void getUserRegistration() {

	}

	@Override
	public String getMenagerPassword() {

	}

	@Override
	public void getAddedItem() {

	}

	@Override
	public void getRemovedItem() {

	}

	@Override
	public int getStudyRoomSitsJustTaken() {

	}

	public void setStudyRoomStatus(final ArrayList studyRoomStatus) {

	}

	@Override
	public void setFilteredList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBorrowedItemList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setItemAvailabilityList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStudyRoomStatus() {
		// TODO Auto-generated method stub

	}
}
