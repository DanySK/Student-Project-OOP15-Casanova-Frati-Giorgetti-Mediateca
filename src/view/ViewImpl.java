package view;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
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

	@Override
	public void startView() {
		JPanel cards = new JPanel(new CardLayout());
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Mediateca");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		JPanel card0 = new JPanel();

		UserLogin card1 = new UserLoginImpl();
		cards.add(card1.getUserLoginPanel());
	}

	@Override
	public String getUsername() {

	}

	@Override
	public String getPassword() {

	}

	@Override
	public String getSearchFilter() {

	}

	@Override
	public String getReview() {

	}

	public void setFilteredList(final List filteredList) {

	}

	public void setBorrowedItemList(final List borrowedItemsList) {

	}

	@Override
	public int getBorrowedItem() {

	}

	@Override
	public int getGivenBackItem() {

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
