package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UserShowImpl implements UserShow {
	final int FRAME_LENGHT = 600;
	final int FRAME_WIDTH = 920;
	private String name;
	private String surname;
	private String password;
	private String username;
	private utils.ItemGenre bookPref1;
	private utils.ItemGenre bookPref2;
	private utils.ItemGenre bookPref3;
	private utils.ItemGenre filmPref1;
	private utils.ItemGenre filmPref2;
	private utils.ItemGenre filmPref3;
	private String telephone;
	private String email;
	private String birthDate;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startUserShow(final View v) {
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

		final JLabel nameL = new JLabel("Name: " + this.name);
		nameL.setBounds(54, 64, 215, 37);
		mainFrame.getContentPane().add(nameL);

		final JLabel surnameL = new JLabel();
		surnameL.setBounds(54, 114, 215, 37);
		surnameL.setText("Cognome: " + this.surname);
		mainFrame.getContentPane().add(surnameL);

		final JLabel usernameL = new JLabel("Username: " + this.username);
		usernameL.setBounds(54, 164, 215, 37);
		mainFrame.getContentPane().add(usernameL);

		final JLabel passwordL = new JLabel("Password: " + this.password);
		passwordL.setBounds(54, 214, 215, 37);
		mainFrame.getContentPane().add(passwordL);

		final JLabel telephoneL = new JLabel("Telefono: " + this.telephone);
		telephoneL.setBounds(54, 357, 215, 37);
		mainFrame.getContentPane().add(telephoneL);

		final JLabel emailL = new JLabel("Email: " + this.email);
		emailL.setBounds(54, 264, 215, 37);
		mainFrame.getContentPane().add(emailL);

		final JLabel birthDateL = new JLabel("Data di nascita: "
				+ this.birthDate);
		birthDateL.setBounds(54, 314, 215, 37);
		mainFrame.getContentPane().add(birthDateL);

		final JLabel bookPref1L = new JLabel(this.bookPref1.toString());
		bookPref1L.setBounds(202, 407, 168, 37);
		mainFrame.getContentPane().add(bookPref1L);

		final JLabel bookPref2L = new JLabel(this.bookPref2.toString());
		bookPref2L.setBounds(202, 446, 215, 37);
		mainFrame.getContentPane().add(bookPref2L);

		final JLabel bookPref3L = new JLabel(this.bookPref3.toString());
		bookPref3L.setBounds(202, 483, 215, 37);
		mainFrame.getContentPane().add(bookPref3L);

		final JLabel filmPref1L = new JLabel(this.filmPref1.toString());
		filmPref1L.setBounds(202, 523, 215, 37);
		mainFrame.getContentPane().add(filmPref1L);

		final JLabel filmPref2L = new JLabel(this.filmPref2.toString());
		filmPref2L.setBounds(202, 560, 215, 37);
		mainFrame.getContentPane().add(filmPref2L);

		final JLabel filmPref3L = new JLabel(this.filmPref3.toString());
		filmPref3L.setBounds(202, 598, 215, 37);
		mainFrame.getContentPane().add(filmPref3L);

		JLabel bookPreferences = new JLabel("Preferenze genere libri:");
		bookPreferences.setBounds(54, 407, 136, 37);
		mainFrame.getContentPane().add(bookPreferences);

		JLabel filmPreferences = new JLabel("Preferenze genere film:");
		filmPreferences.setBounds(54, 523, 136, 37);
		mainFrame.getContentPane().add(filmPreferences);

	}

	@Override
	public void setUserField(final String name, final String surname,
			final String username, final String password,
			final String birthDate, final String email, final String telephone,
			final utils.ItemGenre bookPref1, final utils.ItemGenre bookPref2,
			final utils.ItemGenre bookPref3, final utils.ItemGenre filmPref1,
			final utils.ItemGenre filmPref2, final utils.ItemGenre filmPref3) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email;
		this.telephone = telephone;
		this.bookPref1 = bookPref1;
		this.bookPref2 = bookPref2;
		this.bookPref3 = bookPref3;
		this.filmPref1 = filmPref1;
		this.filmPref2 = filmPref2;
		this.filmPref3 = filmPref3;

	}

}