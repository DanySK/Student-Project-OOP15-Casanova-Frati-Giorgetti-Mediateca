package view;

public interface StudyRoom {
	/**
	 * return the taken sit.
	 *
	 * @return
	 */
	int getTakenSit();

	/**
	 * method which sets the current study room status (occupied sits).
	 *
	 * @param status
	 */
	void setStudyRoomStatus(int[] status);

	/**
	 * method which return the selected day.
	 *
	 * @return
	 */
	int getDateDay();

	/**
	 * method which return the selected day.
	 *
	 * @return
	 */
	int getDateMonth();

	/**
	 * method which return the selected day.
	 *
	 * @return
	 */
	int getDateYear();

	/**
	 * method which sets the list of taken sits.
	 *
	 * @param list
	 */
	void setTakenSitsList(String[] list);

	/**
	 * method which return the selected value in JustTakenSitsList.
	 *
	 * @return
	 */
	String getSelectedSit();

}
