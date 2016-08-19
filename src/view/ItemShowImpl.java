package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.TypeItem;

/**
 * class which implememts method of Item Show inteface.
 *
 * @author Luca Giorgetti
 *
 */
public class ItemShowImpl implements ItemShow {
	static final int FRAME_LENGHT = 600;
	static final int FRAME_WIDTH = 920;
	private String title;
	private String author;
	private String manifacturer;
	private String year;
	private utils.ItemGenre genre;
	private String reviewAvarage;
	private String availability;
	private String isbn;
	private utils.TypeColor color;
	private String duration;
	private utils.Language language;
	private String imagePath;

	@Override
	public void startItemShow(final View v, final TypeItem type) {
		v.giveMeItemInfo();
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 594, 1);
		mainFrame.setSize(ItemShowImpl.FRAME_LENGHT, ItemShowImpl.FRAME_WIDTH);
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

		final JLabel languageL = new JLabel("Durata: " + this.language);
		durationL.setBounds(54, 413, 215, 37);
		mainFrame.getContentPane().add(languageL);

		if (type.equals(TypeItem.BOOK)) {
			mainFrame.setTitle("Libro Selezionato");
			durationL.setVisible(false);
			colorL.setVisible(false);

		} else if (type.equals(TypeItem.MOVIE)) {
			mainFrame.setName("Film Selezionato");
			authorL.setText("Regista:" + this.author);
			isbnCodeL.setVisible(false);
		}
	}

	@Override
	public void setCommonField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final utils.ItemGenre genreS, final String reviewAvarageS,
			final String availabilityS, final String imagePathS,
			final utils.Language languageS) {
		this.title = titleS;
		this.author = authorS;
		this.manifacturer = manifacturerS;
		this.year = yearS;
		this.genre = genreS;
		this.reviewAvarage = reviewAvarageS;
		this.availability = availabilityS;
		this.imagePath = imagePathS;
		this.language = languageS;
	}

	@Override
	public void setFilmField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final utils.ItemGenre genreS, final String reviewAvarageS,
			final String availabilityS, final String imagePathS,
			final String durationS, final utils.TypeColor colorS,
			final utils.Language languageS) {
		this.setCommonField(titleS, authorS, manifacturerS, yearS, genreS,
				reviewAvarageS, availabilityS, imagePathS, languageS);
		this.duration = durationS;
		this.color = colorS;
		this.isbn = null;
	}

	@Override
	public void setBookField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final utils.ItemGenre genreS, final String reviewAvarageS,
			final String availabilityS, final String imagePathS,
			final String isbnS, final utils.Language languageS) {
		this.setCommonField(titleS, authorS, manifacturerS, yearS, genreS,
				reviewAvarageS, availabilityS, imagePathS, languageS);
		this.duration = null;
		this.color = null;
		this.isbn = isbnS;
	}

}
