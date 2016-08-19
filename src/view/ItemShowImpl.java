package view;

import java.awt.Font;

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
	static final int FRAME_LENGHT = 920;
	static final int FRAME_WIDTH = 920;
	static final int FONT_DIM = 30;
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

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startItemShow(final View v, final TypeItem type) {
		v.giveMeItemInfo();
		final JFrame mainFrame = new JFrame("Oggetto Selezionato");
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 594, 1);
		mainFrame.setSize(ItemShowImpl.FRAME_LENGHT, ItemShowImpl.FRAME_WIDTH);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		final JLabel titleL = new JLabel("Titolo: " + this.title);
		titleL.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		titleL.setBounds(54, 64, 480, 37);
		mainFrame.getContentPane().add(titleL);

		final JLabel authorL = new JLabel();
		authorL.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		authorL.setBounds(54, 114, 480, 37);
		authorL.setText("Autore:" + this.author);
		mainFrame.getContentPane().add(authorL);

		final ImageIcon image = new ImageIcon(this.imagePath);
		JLabel imageSpace = new JLabel(image);
		imageSpace.setBounds(734, 64, 100, 140);
		mainFrame.getContentPane().add(imageSpace);

		final JLabel manifacturerL = new JLabel("Prodotto da: "
				+ this.manifacturer);
		manifacturerL.setFont(new Font("Tahoma", Font.PLAIN,
				ItemShowImpl.FONT_DIM));
		manifacturerL.setBounds(54, 164, 480, 37);
		mainFrame.getContentPane().add(manifacturerL);

		final JLabel yearL = new JLabel("Anno di uscita: " + this.year);
		yearL.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		yearL.setBounds(54, 313, 480, 37);
		mainFrame.getContentPane().add(yearL);

		final JLabel genreL = new JLabel("Genere: " + this.genre);
		genreL.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		genreL.setBounds(54, 214, 480, 37);
		mainFrame.getContentPane().add(genreL);

		final JLabel reviewAvarageL = new JLabel("Media recensioni: "
				+ this.reviewAvarage);
		reviewAvarageL.setFont(new Font("Tahoma", Font.PLAIN,
				ItemShowImpl.FONT_DIM));
		reviewAvarageL.setBounds(54, 264, 480, 37);
		mainFrame.getContentPane().add(reviewAvarageL);

		final JLabel availabilityL = new JLabel("Disponibile: "
				+ this.availability);
		availabilityL.setFont(new Font("Tahoma", Font.PLAIN,
				ItemShowImpl.FONT_DIM));
		availabilityL.setBounds(54, 363, 480, 37);
		mainFrame.getContentPane().add(availabilityL);

		final JLabel isbnCodeL = new JLabel("Codice ISBN: " + this.isbn);
		isbnCodeL
				.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		isbnCodeL.setBounds(54, 413, 480, 37);
		mainFrame.getContentPane().add(isbnCodeL);

		final JLabel colorL = new JLabel("Colore: " + this.color);
		colorL.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		colorL.setBounds(54, 463, 480, 37);
		mainFrame.getContentPane().add(colorL);

		final JLabel durationL = new JLabel("Durata: " + this.duration);
		durationL
				.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		durationL.setBounds(54, 413, 480, 37);
		mainFrame.getContentPane().add(durationL);

		final JLabel languageL = new JLabel("Durata: " + this.language);
		languageL
				.setFont(new Font("Tahoma", Font.PLAIN, ItemShowImpl.FONT_DIM));
		languageL.setBounds(54, 413, 215, 37);
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
