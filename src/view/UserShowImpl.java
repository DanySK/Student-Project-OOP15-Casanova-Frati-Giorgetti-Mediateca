package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
		nameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nameL.setBounds(54, 55, 475, 45);

		mainFrame.getContentPane().add(nameL);

		final JLabel surnameL = new JLabel();
		surnameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		surnameL.setBounds(54, 113, 475, 45);
		surnameL.setText("Cognome: " + this.surname);
		mainFrame.getContentPane().add(surnameL);

		final JLabel usernameL = new JLabel("Username: " + this.username);
		usernameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		usernameL.setBounds(54, 171, 475, 45);
		mainFrame.getContentPane().add(usernameL);

		final JLabel passwordL = new JLabel("Password: " + this.password);
		passwordL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		passwordL.setBounds(54, 229, 475, 45);
		mainFrame.getContentPane().add(passwordL);

		final JLabel telephoneL = new JLabel("Telefono: " + this.telephone);
		telephoneL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		telephoneL.setBounds(54, 403, 475, 45);
		mainFrame.getContentPane().add(telephoneL);

		final JLabel emailL = new JLabel("Email: " + this.email);
		emailL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		emailL.setBounds(54, 287, 475, 45);
		mainFrame.getContentPane().add(emailL);

		final JLabel birthDateL = new JLabel("Data di nascita: "
				+ this.birthDate);
		birthDateL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		birthDateL.setBounds(54, 345, 475, 45);
		mainFrame.getContentPane().add(birthDateL);

		final JLabel bookPref1L = new JLabel(this.bookPref1.toString());
		bookPref1L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref1L.setBounds(54, 519, 475, 37);
		bookPref1L.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(bookPref1L);

		final JLabel bookPref2L = new JLabel(this.bookPref2.toString());
		bookPref2L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref2L.setBounds(54, 557, 475, 37);
		bookPref2L.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(bookPref2L);

		final JLabel bookPref3L = new JLabel(this.bookPref3.toString());
		bookPref3L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref3L.setBounds(54, 596, 475, 37);
		bookPref3L.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(bookPref3L);

		final JLabel filmPref1L = new JLabel(this.filmPref1.toString());
		filmPref1L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref1L.setBounds(54, 698, 475, 37);
		filmPref1L.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(filmPref1L);

		final JLabel filmPref2L = new JLabel(this.filmPref2.toString());
		filmPref2L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref2L.setBounds(54, 737, 475, 37);
		filmPref2L.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(filmPref2L);

		final JLabel filmPref3L = new JLabel(this.filmPref3.toString());
		filmPref3L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref3L.setBounds(54, 775, 475, 37);
		filmPref3L.setFont(new Font("Tahoma", Font.PLAIN, 30));

		mainFrame.getContentPane().add(filmPref3L);

		JLabel bookPreferences = new JLabel("Preferenze genere libri:");
		bookPreferences.setBounds(54, 461, 475, 45);
		bookPreferences.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mainFrame.getContentPane().add(bookPreferences);

		JLabel filmPreferences = new JLabel("Preferenze genere film:");
		filmPreferences.setBounds(54, 646, 475, 45);
		filmPreferences.setFont(new Font("Tahoma", Font.PLAIN, 30));
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