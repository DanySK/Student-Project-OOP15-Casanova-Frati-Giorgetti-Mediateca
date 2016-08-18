package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JTextField birthF;
	private JTextField surnameF;
	private JTextField passwordF;
	private JTextField usernameF;
	private JTextField emailF;
	private JTextField cellF;

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

		this.birthF = new JTextField();
		this.birthF.setBounds(181, 147, 116, 22);
		this.birthF.setColumns(10);
		this.add(this.birthF);

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

		discarge.setBounds(12, 262, 97, 25);
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
	}

	@Override
	public void setField(final String name, final String surname,
			final String username, final String password,
			final String birthDate, final String email, final String telephone) {
		this.nameF.setText(name);
		this.surnameF.setText(surname);
		this.usernameF.setText(username);
		this.passwordF.setText(password);
		this.birthF.setText(birthDate);
		this.emailF.setText(email);
		this.cellF.setText(telephone);
	}

	@Override
	public String getInfo(final utils.UserInfo info) {
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
				return this.birthF.getText();
			case EMAIL:
				return this.emailF.getText();
			case TELEPHONE_NUMBER:
				return this.cellF.getText();
			default:
				break;

			}
		}
		return null;
	}

}