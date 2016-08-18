package view;

import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	JDatePickerImpl datePicker;
	UtilDateModel model = new UtilDateModel();
	private JComboBox bookPref1;
	private JComboBox bookPref2;
	private JComboBox bookPref3;
	private JComboBox filmPref1;
	private JComboBox filmPref2;
	private JComboBox filmPref3;

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
		this.nameF.setBounds(181, 34, 116, 22);
		this.add(this.nameF);
		this.nameF.setColumns(10);

		this.surnameF = new JTextField();
		this.surnameF.setBounds(181, 63, 116, 22);
		this.surnameF.setColumns(10);
		this.add(this.surnameF);

		this.passwordF = new JTextField();
		this.passwordF.setColumns(10);
		this.passwordF.setBounds(181, 119, 116, 22);
		this.add(this.passwordF);

		this.usernameF = new JTextField();
		this.usernameF.setColumns(10);
		this.usernameF.setBounds(181, 92, 116, 22);
		this.add(this.usernameF);

		this.emailF = new JTextField();
		this.emailF.setColumns(10);
		this.emailF.setBounds(181, 173, 116, 22);
		this.add(this.emailF);

		this.cellF = new JTextField();
		this.cellF.setColumns(10);
		this.cellF.setBounds(181, 199, 116, 22);
		this.add(this.cellF);

		nameL = new JLabel("Nome:");
		nameL.setBounds(64, 40, 94, 16);
		this.add(nameL);

		surnameL = new JLabel("Cognome:");
		surnameL.setBounds(64, 66, 94, 16);
		this.add(surnameL);
		discarge = new JButton("Annulla");
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

		this.presentation.setBounds(104, 11, 181, 16);
		this.add(this.presentation);

		discarge.setBounds(12, 709, 97, 25);
		this.add(discarge);

		this.send.setBounds(341, 262, 97, 25);
		this.add(this.send);

		usernameL = new JLabel("Username:");
		usernameL.setBounds(64, 98, 94, 16);
		this.add(usernameL);

		passwordL = new JLabel("Password:");
		passwordL.setBounds(64, 125, 94, 16);
		this.add(passwordL);

		birthL = new JLabel("Data di nascita:");
		birthL.setBounds(64, 153, 94, 16);
		this.add(birthL);

		emailL = new JLabel("E-mail:");
		emailL.setBounds(64, 179, 94, 16);
		this.add(emailL);

		cellL = new JLabel("Recapito:");
		cellL.setBounds(64, 205, 94, 16);
		this.add(cellL);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		datePicker.setSize(206, 22);
		datePicker.setLocation(181, 147);

		this.add(datePicker);

		JComboBox bookPref1 = new JComboBox(utils.ItemGenre.values());
		bookPref1.setSelectedIndex(-1);
		bookPref1.setBounds(181, 234, 116, 22);
		this.add(bookPref1);

		JComboBox bookPref2 = new JComboBox(utils.ItemGenre.values());
		bookPref2.setSelectedIndex(-1);
		bookPref2.setBounds(309, 234, 116, 22);
		this.add(bookPref2);

		JComboBox bookPref3 = new JComboBox(utils.ItemGenre.values());
		bookPref3.setSelectedIndex(-1);
		bookPref3.setBounds(437, 234, 116, 22);
		this.add(bookPref3);

		JComboBox filmPref1 = new JComboBox(utils.ItemGenre.values());
		filmPref1.setSelectedIndex(-1);
		filmPref1.setBounds(181, 269, 116, 22);
		this.add(filmPref1);

		JComboBox filmPref2 = new JComboBox(utils.ItemGenre.values());
		filmPref2.setSelectedIndex(-1);
		filmPref2.setBounds(309, 269, 116, 22);
		this.add(filmPref2);

		JComboBox filmPref3 = new JComboBox(utils.ItemGenre.values());
		filmPref3.setSelectedIndex(-1);
		filmPref3.setBounds(437, 269, 116, 22);
		this.add(filmPref3);
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