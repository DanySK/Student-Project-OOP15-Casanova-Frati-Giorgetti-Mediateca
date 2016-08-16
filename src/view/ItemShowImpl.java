package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.ViewImpl.ItemType;

/**
 * class which implememts method of Item Show inteface.
 *
 * @author Luca Giorgetti
 *
 */
public class ItemShowImpl implements ItemShow {
	final int FRAME_LENGHT = 600;
	final int FRAME_WIDTH = 920;
	private String title = new String();
	private String author = new String();
	private String manifacturer = new String();
	private String year = new String();
	private String genre = new String();
	private String reviewAvarage = new String();
	private String availability = new String();
	private String isbn = new String();
	private String color = new String();
	private String duration = new String();
	private String imagePath = new String();

	public ItemShowImpl(final View v, final ItemType type) {
		v.giveMeItemInfo();
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 594, 1);
		mainFrame.setSize(this.FRAME_LENGHT, this.FRAME_WIDTH);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		final JLabel titleL = new JLabel("Titolo: " + this.title);
		titleL.setBounds(54, 64, 215, 37);
		mainFrame.getContentPane().add(titleL);

		final JLabel authorL = new JLabel();
		authorL.setBounds(54, 114, 215, 37);
		authorL.setText("Autore:" + this.author);
		mainFrame.getContentPane().add(authorL);

		final ImageIcon image = new ImageIcon(this.imagePath);
		JLabel imageSpace = new JLabel(image);
		imageSpace.setBounds(426, 46, 100, 140);
		mainFrame.getContentPane().add(imageSpace);

		final JLabel manifacturerL = new JLabel("Prodotto da: "
				+ this.manifacturer);
		manifacturerL.setBounds(54, 164, 215, 37);
		mainFrame.getContentPane().add(manifacturerL);

		final JLabel yearL = new JLabel("Anno di uscita: " + this.year);
		yearL.setBounds(54, 313, 215, 37);
		mainFrame.getContentPane().add(yearL);

		final JLabel genreL = new JLabel("Genere: " + this.genre);
		genreL.setBounds(54, 214, 215, 37);
		mainFrame.getContentPane().add(genreL);

		final JLabel reviewAvarageL = new JLabel("Media recensioni: "
				+ this.reviewAvarage);
		reviewAvarageL.setBounds(54, 264, 215, 37);
		mainFrame.getContentPane().add(reviewAvarageL);

		final JLabel availabilityL = new JLabel("Disponibile: "
				+ this.availability);
		availabilityL.setBounds(54, 363, 215, 37);
		mainFrame.getContentPane().add(availabilityL);

		final JLabel isbnCodeL = new JLabel("Codice ISBN: " + this.isbn);
		isbnCodeL.setBounds(54, 413, 215, 37);
		mainFrame.getContentPane().add(isbnCodeL);

		final JLabel colorL = new JLabel("Colore: " + this.color);
		colorL.setBounds(54, 463, 215, 37);
		mainFrame.getContentPane().add(colorL);

		final JLabel durationL = new JLabel("Durata: " + this.duration);
		durationL.setBounds(54, 413, 215, 37);
		mainFrame.getContentPane().add(durationL);

		if (type.equals(ItemType.BOOK)) {
			mainFrame.setTitle("Libro Selezionato");
			durationL.setVisible(false);
			colorL.setVisible(false);

		} else if (type.equals(ItemType.FILM)) {
			mainFrame.setName("Film Selezionato");
			authorL.setText("Regista:" + this.author);
			isbnCodeL.setVisible(false);
		}
	}

	@Override
	public void setCommonField(final String title, final String author,
			final String manifacturer, final String year, final String genre,
			final String reviewAvarage, final String availability,
			final String imagePath) {
		this.title = title;
		this.author = author;
		this.manifacturer = manifacturer;
		this.year = year;
		this.genre = genre;
		this.reviewAvarage = reviewAvarage;
		this.availability = availability;
		this.imagePath = imagePath;
	}

	@Override
	public void setFilmField(final String title, final String author,
			final String manifacturer, final String year, final String genre,
			final String reviewAvarage, final String availability,
			final String imagePath, final String duration, final String color) {
		this.setCommonField(title, author, manifacturer, year, genre,
				reviewAvarage, availability, imagePath);
		this.duration = duration;
		this.color = color;
		this.isbn = null;
	}

	@Override
	public void setBookField(final String title, final String author,
			final String manifacturer, final String year, final String genre,
			final String reviewAvarage, final String availability,
			final String imagePath, final String isbn) {
		this.setCommonField(title, author, manifacturer, year, genre,
				reviewAvarage, availability, imagePath);
		this.duration = null;
		this.color = null;
		this.isbn = isbn;
	}

}
