package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ViewImpl.UserInfo;

public class UserModifyImpl extends JPanel implements UserModify {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nameF;
	private JTextField birthF;
	private JTextField surnameF;
	private JTextField passwordF;
	private JTextField usernameF;
	private JTextField emailF;
	private JTextField cellF;
	private JLabel nameL;
	private JLabel surnameL;
	private JLabel usernameL;
	private JLabel passwordL;
	private JLabel birthL;
	private JLabel emailL;
	private JLabel cellL;
	private JLabel presentation;
	private JButton discarge;
	private JButton send;

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
	public UserModifyImpl(final View v, final int screenLenght,
			final int screenWidth) {
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

		this.nameL = new JLabel("Nome:");
		this.nameL.setBounds(64, 40, 94, 16);
		this.add(this.nameL);

		this.surnameL = new JLabel("Cognome:");
		this.surnameL.setBounds(64, 66, 94, 16);
		this.add(this.surnameL);

		this.usernameL = new JLabel("Username:");
		this.usernameL.setBounds(64, 98, 94, 16);
		this.add(this.usernameL);

		this.passwordL = new JLabel("Password:");
		this.passwordL.setBounds(64, 125, 94, 16);
		this.add(this.passwordL);

		this.birthL = new JLabel("Data di nascita:");
		this.birthL.setBounds(64, 153, 94, 16);
		this.add(this.birthL);

		this.emailL = new JLabel("E-mail:");
		this.emailL.setBounds(64, 179, 94, 16);
		this.add(this.emailL);

		this.cellL = new JLabel("Recapito:");
		this.cellL.setBounds(64, 205, 94, 16);
		this.add(this.cellL);

		this.presentation = new JLabel("Modifica qui i tuoi dati:");
		this.presentation.setBounds(104, 11, 181, 16);
		this.add(this.presentation);

		this.discarge = new JButton("Annulla");
		this.discarge.setBounds(12, 262, 97, 25);
		this.add(this.discarge);

		this.send = new JButton("Invio");
		this.send.setBounds(341, 262, 97, 25);
		this.add(this.send);
		v.giveMeUserInfo();
		this.discarge.addActionListener(e -> v.swapView("User Menu Card"));

		this.send.addActionListener(e -> v.sendUserModify());
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
	public String getInfo(final UserInfo info) {
		for (UserInfo i : UserInfo.values()) {
			switch (info) {
			case name:
				return this.nameF.getText();
			case surname:
				return this.surnameF.getText();
			case username:
				return this.usernameF.getText();
			case password:
				return this.passwordF.getText();
			case birthDate:
				return this.birthF.getText();
			case email:
				return this.emailF.getText();
			case telephone:
				return this.cellF.getText();
			default:
				break;

			}
		}
		return null;
	}

}