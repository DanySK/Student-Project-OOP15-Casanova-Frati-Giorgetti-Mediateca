package view;

import java.util.List;

/**
 * Interface for creating a new JFrame with a list printed inside.
 *
 * @author Luca Giorgetti
 */
public interface ListScreen {
	/**
	 * method which sets the borrowed list taken by controller.
	 *
	 * @param bList
	 */
	void setBorrowedList(List<String> bList);

	/**
	 * method which sets the like list taken by controller.
	 * 
	 * @param lList
	 */
	void setLikeList(List<String> lList);

}
