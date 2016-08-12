package view;

/**
 * Interface for the review screen.
 *
 * @author Luca Giorgetti
 *
 */

public interface ReviewScreen {
	/**
	 * method which open the frame with the review panel.
	 */
	void startReviewScreen();

	/**
	 * method which returns the score typed by user.
	 *
	 * @param scoreGroup
	 * @return int
	 */
	static int getSelectedScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * method which returns the review typed by user.
	 *
	 * @return
	 */
	static String getReview() {
		// TODO Auto-generated method stub
		return null;
	}

}
