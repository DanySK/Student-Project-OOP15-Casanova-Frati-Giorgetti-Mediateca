package view;

import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JTextField passwordF;
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
	private SpringLayout springLayout;

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
	 * @param r
	 * @param l
	 *
	 * @param screenWidth
	 * @param screenLenght
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserScreenImpl(final View v, final UserScreenType type,
			final int screenLenght, final int screenWidth) {
		this.setSize(1280, 920);
		final JLabel nameL;
		final JLabel surnameL;
		final JLabel usernameL;
		final JLabel passwordL;
		final JLabel birthL;
		final JLabel emailL;
		final JLabel cellL;
		final JButton discarge;
		this.setLayout(null);

		this.nameF = new JTextField();
		this.nameF.setBounds(282, 99, 289, 38);
		this.add(this.nameF);
		this.nameF.setColumns(10);

		this.surnameF = new JTextField();
		this.surnameF.setBounds(282, 150, 289, 37);
		this.surnameF.setColumns(10);
		this.add(this.surnameF);

		this.passwordF = new JTextField();
		this.passwordF.setColumns(10);
		this.passwordF.setBounds(282, 252, 289, 38);
		this.add(this.passwordF);

		this.usernameF = new JTextField();
		this.usernameF.setColumns(10);
		this.usernameF.setBounds(282, 201, 289, 38);
		this.add(this.usernameF);

		this.emailF = new JTextField();
		this.emailF.setColumns(10);
		this.emailF.setBounds(282, 352, 289, 38);
		this.add(this.emailF);

		this.cellF = new JTextField();
		this.cellF.setColumns(10);
		this.cellF.setBounds(282, 403, 289, 38);
		this.add(this.cellF);

		nameL = new JLabel("Nome:");
		nameL.setBounds(64, 99, 206, 38);
		nameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(nameL);

		surnameL = new JLabel("Cognome:");
		surnameL.setBounds(64, 150, 206, 38);
		surnameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(surnameL);
		discarge = new JButton("Annulla");
		discarge.setFont(new Font("Tahoma", Font.PLAIN, 30));

		if (type.equals(UserScreenType.CREATE)) {
			this.presentation = new JLabel("Inserisci qui i tuoi dati:");
			this.send = new JButton("Crea");
			discarge.addActionListener(e -> v.swapView(CardName.MAIN));
			this.send.addActionListener(e -> v.sendUserCreate());
		} else if (type.equals(UserScreenType.MODIFY)) {
			this.presentation = new JLabel("Modifica qui i tuoi dati:");
			v.giveMeUserInfo();
			this.send = new JButton("Invio");
			this.usernameF.setEditable(false);
			discarge.addActionListener(e -> v.swapView(CardName.MENU));

			this.send.addActionListener(e -> v.sendUserModify());
		}

		this.presentation.setFont(new Font("Tahoma", Font.PLAIN, 40));
		this.presentation.setHorizontalAlignment(SwingConstants.CENTER);
		this.presentation.setBounds(12, 11, 1256, 52);
		this.add(this.presentation);

		discarge.setBounds(68, 806, 202, 74);
		this.add(discarge);

		this.send.setBounds(1024, 806, 202, 74);
		this.send.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(this.send);

		usernameL = new JLabel("Username:");
		usernameL.setBounds(64, 201, 206, 38);
		usernameL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(usernameL);

		passwordL = new JLabel("Password:");
		passwordL.setBounds(64, 252, 206, 38);
		passwordL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(passwordL);

		birthL = new JLabel("Data nascita:");
		birthL.setBounds(64, 303, 206, 38);
		birthL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(birthL);

		emailL = new JLabel("E-mail:");
		emailL.setBounds(64, 352, 206, 38);
		emailL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(emailL);

		cellL = new JLabel("Recapito:");
		cellL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cellL.setBounds(64, 402, 206, 38);
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
		this.datePicker.setSize(289, 38);
		this.datePicker.setLocation(282, 303);

		this.add(this.datePicker);

		this.bookPref1 = new JComboBox(utils.ItemGenre.values());
		this.bookPref1.setSelectedIndex(-1);
		this.bookPref1.setBounds(282, 552, 289, 38);
		this.add(this.bookPref1);

		this.bookPref2 = new JComboBox(utils.ItemGenre.values());
		this.bookPref2.setSelectedIndex(-1);
		this.bookPref2.setBounds(583, 552, 289, 38);
		this.add(this.bookPref2);

		this.bookPref3 = new JComboBox(utils.ItemGenre.values());
		this.bookPref3.setSelectedIndex(-1);
		this.bookPref3.setBounds(884, 552, 289, 38);
		this.add(this.bookPref3);

		this.filmPref1 = new JComboBox(utils.ItemGenre.values());
		this.filmPref1.setSelectedIndex(-1);
		this.filmPref1.setBounds(282, 603, 289, 38);
		this.add(this.filmPref1);

		this.filmPref2 = new JComboBox(utils.ItemGenre.values());
		this.filmPref2.setSelectedIndex(-1);
		this.filmPref2.setBounds(583, 603, 289, 38);
		this.add(this.filmPref2);

		this.filmPref3 = new JComboBox(utils.ItemGenre.values());
		this.filmPref3.setSelectedIndex(-1);
		this.filmPref3.setBounds(884, 603, 289, 38);
		this.add(this.filmPref3);

		JLabel prefL = new JLabel("Preferenze generi:\r\n");
		prefL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		prefL.setBounds(64, 500, 449, 38);
		this.add(prefL);

		JLabel bookL = new JLabel("Libri:\r\n");
		bookL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		bookL.setBounds(64, 552, 206, 38);
		this.add(bookL);

		JLabel filmL = new JLabel("Film:");
		filmL.setFont(new Font("Tahoma", Font.PLAIN, 30));
		filmL.setBounds(64, 603, 206, 38);
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
		for (final utils.UserInfo i : utils.UserInfo.values()) {
			switch (info) {
			case NAME:
				return this.nameF.getText();
			case SURNAME:
				return this.surnameF.getText();
			case USERNAME:
				return this.usernameF.getText();
			case PASSWORD:
				return this.passwordF.getText();
			case BIRTHDATE:
				return this.datePicker.getModel().getValue();
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
		}
		return null;
	}
}