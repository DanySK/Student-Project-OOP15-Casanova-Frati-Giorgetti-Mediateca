package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * class which implememts method of Item Show inteface.
 *
 * @author Luca Giorgetti
 *
 */
public class ItemShowImpl implements ItemShow {
	static final int FRAME_LENGHT = 600;
	static final int FRAME_WIDTH = 600;
	private String title;
	private String author;
	private String manifacturer;
	private String year;
	private String genre;
	private String reviewAvarage;
	private String availability;
	private String isbn;
	private String color;
	private String duration;
	private String language;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startItemShow(final View v) {
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
		titleL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		titleL.setBounds(23, 14, 480, 30);
		mainFrame.getContentPane().add(titleL);

		final JLabel authorL = new JLabel();
		authorL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		authorL.setBounds(23, 44, 480, 30);
		authorL.setText("Autore:" + this.author);
		mainFrame.getContentPane().add(authorL);

		final JLabel manifacturerL = new JLabel("Prodotto da: "
				+ this.manifacturer);
		manifacturerL.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		manifacturerL.setBounds(23, 78, 480, 30);
		mainFrame.getContentPane().add(manifacturerL);

		final JLabel yearL = new JLabel("Anno di uscita: " + this.year);
		yearL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		yearL.setBounds(23, 178, 480, 30);
		mainFrame.getContentPane().add(yearL);

		final JLabel genreL = new JLabel("Genere: " + this.genre);
		genreL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		genreL.setBounds(23, 113, 480, 30);
		mainFrame.getContentPane().add(genreL);

		final JLabel reviewAvarageL = new JLabel("Media recensioni: "
				+ this.reviewAvarage);
		reviewAvarageL.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		reviewAvarageL.setBounds(23, 148, 480, 30);
		mainFrame.getContentPane().add(reviewAvarageL);

		final JLabel availabilityL = new JLabel("Disponibile: "
				+ this.availability);
		availabilityL.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		availabilityL.setBounds(23, 210, 480, 30);
		mainFrame.getContentPane().add(availabilityL);

		final JLabel isbnCodeL = new JLabel("Codice ISBN: " + this.isbn);
		isbnCodeL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		isbnCodeL.setBounds(23, 279, 480, 30);
		mainFrame.getContentPane().add(isbnCodeL);

		final JLabel colorL = new JLabel("Colore: " + this.color);
		colorL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		colorL.setBounds(23, 313, 480, 37);
		mainFrame.getContentPane().add(colorL);

		final JLabel durationL = new JLabel("Durata: " + this.duration);
		durationL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		durationL.setBounds(23, 279, 480, 30);
		mainFrame.getContentPane().add(durationL);

		final JLabel languageL = new JLabel("Lingua:" + this.language);
		languageL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		languageL.setBounds(23, 241, 215, 37);
		mainFrame.getContentPane().add(languageL);

		mainFrame.setTitle("Oggetto Selezionato");
		authorL.setText("Regista:" + this.author);
		mainFrame.setVisible(true);

	}

	private void setCommonField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final String genreS, final String reviewAvarageS,
			final String availabilityS, final String languageS) {
		this.title = titleS;
		this.author = authorS;
		this.manifacturer = manifacturerS;
		this.year = yearS;
		this.genre = genreS;
		this.reviewAvarage = reviewAvarageS;
		this.availability = availabilityS;
		this.language = languageS;
	}

	@Override
	public void setFilmField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final String genreS, final String reviewAvarageS,
			final String availabilityS, final String durationS,
			final String colorS, final String languageS) {
		this.setCommonField(titleS, authorS, manifacturerS, yearS, genreS,
				reviewAvarageS, availabilityS, languageS);
		this.duration = durationS;
		this.color = colorS;
		this.isbn = null;
	}

	@Override
	public void setBookField(final String titleS, final String authorS,
			final String manifacturerS, final String yearS,
			final String genreS, final String reviewAvarageS,
			final String availabilityS, final String isbnS,
			final String languageS) {
		this.setCommonField(titleS, authorS, manifacturerS, yearS, genreS,
				reviewAvarageS, availabilityS, languageS);
		this.duration = null;
		this.color = null;
		this.isbn = isbnS;
	}

}
