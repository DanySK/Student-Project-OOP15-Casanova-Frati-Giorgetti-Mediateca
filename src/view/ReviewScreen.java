package view;

/**
 * Interface for the review screen.
 *
 * @author Luca Giorgetti
 *
 */

public interface ReviewScreen {
	/**
	 * method which returns the score typed by user.
	 *
	 * @param scoreGroup
	 * @return int
	 */
	int getSelectedScore();

	/**
	 * method which returns the review typed by user.
	 *
	 * @return
	 */
	String getReview();
}
