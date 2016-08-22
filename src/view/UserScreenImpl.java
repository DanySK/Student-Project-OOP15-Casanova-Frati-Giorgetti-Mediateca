package view;

import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import view.ViewImpl.CardName;

/**
 * Class which implements the UserModify interface.
 *
 * @author Luca Giorgetti
 *
 */
public class UserScreenImpl extends JPanel implements UserScreen {

	private static final long serialVersionUID = 1L;

	private JLabel presentation;
	private JButton send;
	private JTextField nameF;
	private JTextField surnameF;
	private JPasswordField passwordF;
	private JTextField usernameF;
	private JTextField emailF;
	private JTextField cellF;
	private JDatePickerImpl datePicker;
	private UtilDateModel model = new UtilDateModel();
	private JComboBox<?> bookPref1;
	private JComboBox<?> bookPref2;
	private JComboBox<?> bookPref3;
	private JComboBox<?> filmPref1;
	private JComboBox<?> filmPref2;
	private JComboBox<?> filmPref3;
	private SpringLayout springLayout = new SpringLayout();

	public enum UserScreenType {
		/**
		 *
		 */
		CREATE, MODIFY
	}

	/**
	 * Create the panel.
	 *
	 * @param v
	 *            the calling class
	 * @param type
	 *            the type of user screen
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserScreenImpl(final View v, final UserScreenType type) {

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		final JLabel nameL;
		final JLabel surnameL;
		final JLabel usernameL;
		final JLabel passwordL;
		final JLabel birthL;
		final JLabel emailL;
		final JLabel cellL;
		final JButton discarge;
		this.setLayout(null);
		final JButton deleteUser = new JButton("Elimina utente");
		deleteUser.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		deleteUser.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		deleteUser.setLocation(428, 806);

		this.nameF = new JTextField();
		this.nameF.setBounds(246, 85, 241, 25);
		this.add(this.nameF);
		this.nameF.setColumns(10);

		this.surnameF = new JTextField();
		this.surnameF.setBounds(246, 125, 241, 25);
		this.surnameF.setColumns(10);
		this.add(this.surnameF);

		this.passwordF = new JPasswordField();
		this.passwordF.setColumns(10);
		this.passwordF.setBounds(246, 205, 241, 25);
		this.add(this.passwordF);

		this.usernameF = new JTextField();
		this.usernameF.setColumns(10);
		this.usernameF.setBounds(246, 165, 241, 25);
		this.add(this.usernameF);

		this.emailF = new JTextField();
		this.emailF.setColumns(10);
		this.emailF.setBounds(246, 285, 241, 25);
		this.add(this.emailF);

		this.cellF = new JTextField();
		this.cellF.setColumns(10);
		this.cellF.setBounds(246, 325, 241, 25);
		this.add(this.cellF);

		nameL = new JLabel("Nome:");
		nameL.setBounds(47, 85, 187, 25);
		nameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(nameL);

		surnameL = new JLabel("Cognome:");
		surnameL.setBounds(47, 125, 187, 25);
		surnameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(surnameL);
		discarge = new JButton("Annulla");
		discarge.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.send = new JButton();

		if (type.equals(UserScreenType.CREATE)) {
			this.presentation = new JLabel("Inserisci qui i tuoi dati:");
			this.send.setText("Crea");
			discarge.addActionListener(e -> v.swapView(CardName.START));
			this.send.addActionListener(e -> {
				v.sendUserCreate();
				v.giveMeSuggestedBooks();
				v.giveMeSuggestedMovies();
				v.swapView(CardName.MENU);
			});
		} else if (type.equals(UserScreenType.MODIFY)) {
			this.presentation = new JLabel("Modifica qui i tuoi dati:");
			v.giveMeUserInfo();
			this.send.setText("Invio");
			this.usernameF.setEditable(false);
			discarge.addActionListener(e -> {
				v.giveMeSuggestedBooks();
				v.giveMeSuggestedMovies();
				v.swapView(CardName.MENU);
			});
			this.add(deleteUser);
			deleteUser.addActionListener(e -> {
				v.deleteUser();
				v.swapView(CardName.LOGIN);
			});
			this.send.addActionListener(e -> {
				v.sendUserModify();
				v.giveMeSuggestedBooks();
				v.giveMeSuggestedMovies();
				v.swapView(CardName.MENU);
			});
		}

		this.presentation.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.TITLE_SIZE));
		this.presentation.setHorizontalAlignment(SwingConstants.CENTER);
		this.presentation.setBounds(47, 13, 720, 58);
		this.add(this.presentation);

		discarge.setBounds(30, 496, 120, 49);
		this.add(discarge);

		this.send.setBounds(647, 496, 120, 49);
		this.send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(this.send);

		usernameL = new JLabel("Username:");
		usernameL.setBounds(47, 165, 187, 25);
		usernameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(usernameL);

		passwordL = new JLabel("Password:");
		passwordL.setBounds(47, 205, 187, 25);
		passwordL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(passwordL);

		birthL = new JLabel("Data nascita:");
		birthL.setBounds(47, 245, 187, 25);
		birthL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(birthL);

		emailL = new JLabel("E-mail:");
		emailL.setBounds(47, 285, 187, 25);
		emailL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		this.add(emailL);

		cellL = new JLabel("Recapito:");
		cellL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		cellL.setBounds(47, 325, 187, 25);
		this.add(cellL);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		this.model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(this.model, p);
		this.datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		this.springLayout.putConstraint(SpringLayout.NORTH,
				this.datePicker.getJFormattedTextField(), 0,
				SpringLayout.NORTH, this.datePicker);
		this.springLayout = (SpringLayout) this.datePicker.getLayout();
		this.springLayout.putConstraint(SpringLayout.SOUTH,
				this.datePicker.getJFormattedTextField(), 0,
				SpringLayout.SOUTH, this.datePicker);
		this.datePicker.setSize(241, 25);
		this.datePicker.setLocation(246, 245);

		this.add(this.datePicker);

		this.bookPref1 = new JComboBox(utils.ItemGenre.values());
		this.bookPref1.setSelectedIndex(-1);
		this.bookPref1.setBounds(212, 420, 177, 25);
		this.add(this.bookPref1);

		this.bookPref2 = new JComboBox(utils.ItemGenre.values());
		this.bookPref2.setSelectedIndex(-1);
		this.bookPref2.setBounds(401, 420, 177, 25);
		this.add(this.bookPref2);

		this.bookPref3 = new JComboBox(utils.ItemGenre.values());
		this.bookPref3.setSelectedIndex(-1);
		this.bookPref3.setBounds(590, 420, 177, 25);
		this.add(this.bookPref3);

		this.filmPref1 = new JComboBox(utils.ItemGenre.values());
		this.filmPref1.setSelectedIndex(-1);
		this.filmPref1.setBounds(212, 458, 177, 25);
		this.add(this.filmPref1);

		this.filmPref2 = new JComboBox(utils.ItemGenre.values());
		this.filmPref2.setSelectedIndex(-1);
		this.filmPref2.setBounds(401, 458, 177, 25);
		this.add(this.filmPref2);

		this.filmPref3 = new JComboBox(utils.ItemGenre.values());
		this.filmPref3.setSelectedIndex(-1);
		this.filmPref3.setBounds(590, 458, 177, 25);
		this.add(this.filmPref3);

		JLabel prefL = new JLabel("Preferenze generi:\r\n");
		prefL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		prefL.setBounds(72, 382, 296, 25);
		this.add(prefL);

		JLabel bookL = new JLabel("Libri:\r\n");
		bookL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		bookL.setBounds(47, 420, 147, 25);
		this.add(bookL);

		JLabel filmL = new JLabel("Film:");
		filmL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		filmL.setBounds(47, 458, 147, 25);
		this.add(filmL);
	}

	@Override
	public void setField(final String name, final String surname,
			final String username, final String password,
			final String birthDate, final String email, final String telephone) {
		this.nameF.setText(name);
		this.surnameF.setText(surname);
		this.usernameF.setText(username);
		this.passwordF.setText(password);
		this.model.setDate(1990, 01, 01);
		this.model.setSelected(true);
		this.emailF.setText(email);
		this.cellF.setText(telephone);
	}

	@Override
	public Object getInfo(final utils.UserInfo info) {
		switch (info) {
		case NAME:
			return this.nameF.getText();
		case SURNAME:
			return this.surnameF.getText();
		case USERNAME:
			return this.usernameF.getText();
		case PASSWORD:
			String pwd = new String(this.passwordF.getPassword());
			return pwd;
		case BIRTHDATE_DAY:
			return this.datePicker.getModel().getDay();
		case BIRTHDATE_MONTH:
			return this.datePicker.getModel().getDay();
		case BIRTHDATE_YEAR:
			return this.datePicker.getModel().getDay();
		case EMAIL:
			return this.emailF.getText();
		case TELEPHONE_NUMBER:
			return this.cellF.getText();
		case BOOK_PREF1:
			return this.bookPref1.getSelectedItem();
		case BOOK_PREF2:
			return this.bookPref2.getSelectedItem();
		case BOOK_PREF3:
			return this.bookPref3.getSelectedItem();
		case FILM_PREF1:
			return this.filmPref1.getSelectedItem();
		case FILM_PREF2:
			return this.filmPref2.getSelectedItem();
		case FILM_PREF3:
			return this.filmPref3.getSelectedItem();
		default:
			break;

		}
		return null;
	}
}