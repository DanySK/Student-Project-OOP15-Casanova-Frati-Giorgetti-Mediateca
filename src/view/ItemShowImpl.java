package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.ViewImpl.ItemType;

public class ItemShowImpl implements ItemShow {
	final int FRAME_LENGHT = 600;
	final int FRAME_WIDTH = 920;

	public ItemShowImpl(final ItemType type) {
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 594, 1);
		mainFrame.setSize(this.FRAME_LENGHT, this.FRAME_WIDTH);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		String title = new String();
		String author = new String();
		String manifacturer = new String();
		String year = new String();
		String genre = new String();
		String reviewAvarage = new String();
		String availability = new String();
		String isbn = new String();
		String color = new String();
		String duration = new String();
		String imagePath = new String();

		// if (type.equals(ItemType.BOOK)) {
		mainFrame.setTitle("Libro Selezionato");

		JLabel titleL = new JLabel("Titolo: " + title);
		titleL.setBounds(54, 64, 215, 37);
		mainFrame.getContentPane().add(titleL);
		JLabel authorL = new JLabel();

		authorL.setBounds(54, 114, 215, 37);
		authorL.setText("Autore:" + author);
		mainFrame.getContentPane().add(authorL);

		JLabel imageSpace = new JLabel();
		imageSpace.setBounds(426, 46, 100, 140);
		/* HAVE TO SET IMAGE BY PATH */

		mainFrame.getContentPane().add(imageSpace);

		JLabel manifacturerL = new JLabel("Prodotto da: " + manifacturer);
		manifacturerL.setBounds(54, 164, 215, 37);
		mainFrame.getContentPane().add(manifacturerL);

		JLabel yearL = new JLabel("Anno di uscita: " + year);
		yearL.setBounds(54, 313, 215, 37);
		mainFrame.getContentPane().add(yearL);

		JLabel genreL = new JLabel("Genere: " + genre);
		genreL.setBounds(54, 214, 215, 37);
		mainFrame.getContentPane().add(genreL);

		JLabel reviewAvarageL = new JLabel("Media recensioni: " + reviewAvarage);
		reviewAvarageL.setBounds(54, 264, 215, 37);
		mainFrame.getContentPane().add(reviewAvarageL);

		JLabel availabilityL = new JLabel("Disponibile: " + availability);
		availabilityL.setBounds(54, 363, 215, 37);
		mainFrame.getContentPane().add(availabilityL);

		JLabel isbnCodeL = new JLabel("Codice ISBN: " + isbn);
		isbnCodeL.setBounds(54, 413, 215, 37);
		mainFrame.getContentPane().add(isbnCodeL);

		JLabel colorL = new JLabel("Colore: " + color);
		colorL.setBounds(54, 463, 215, 37);
		mainFrame.getContentPane().add(colorL);

		JLabel durationL = new JLabel("Durata: " + duration);
		durationL.setBounds(54, 413, 215, 37);
		mainFrame.getContentPane().add(durationL);

		durationL.setVisible(false);

		colorL.setVisible(false);

		/*
		 * } else if (type.equals(ItemType.FILM)) {
		 * mainFrame.setName("Film Selezionato"); authorL.setText("Regista:" +
		 * author); isbnCode.setVisible(false); }
		 */

	}
}
