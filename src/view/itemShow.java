package view;

/**
 * interface for showing item.
 * 
 * @author Luca Giorgetti
 *
 */
public interface ItemShow {
	/**
	 * method which sets common fields in item show.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 */
	void setCommonField(String title, String author, String manifacturer,
			String year, String genre, String reviewAvarage,
			String availability, String imagePath);

	/**
	 * method which sets all field for film item.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 * @param duration
	 * @param color
	 */
	void setFilmField(String title, String author, String manifacturer,
			String year, String genre, String reviewAvarage,
			String availability, String imagePath, String duration, String color);

	/**
	 * method which sets all field for book item.
	 *
	 * @param title
	 * @param author
	 * @param manifacturer
	 * @param year
	 * @param genre
	 * @param reviewAvarage
	 * @param availability
	 * @param imagePath
	 * @param isbn
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, String genre, String reviewAvarage,
			String availability, String imagePath, String isbn);

}
