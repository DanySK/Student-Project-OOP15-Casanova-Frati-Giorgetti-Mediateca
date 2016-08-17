package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import view.ViewImpl.CardName;

/**
 * Class for the Login panel, implements Userlogin.
 *
 * @author Luca Giorgetti
 *
 */
public class UserLoginImpl extends JPanel implements UserLogin {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final JTextArea passwordTextArea;
	private final JTextArea usernameTextArea;

	/**
	 * enumeration for choosing login type.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum LoginType {
		/**
		 *
		 */
		USER, MANAGER
	}

	/**
	 * Create the panel. Need the calling class
	 *
	 * @param v
	 * @param screenWidth
	 * @param screenLenght
	 */
	public UserLoginImpl(final View v, final LoginType type,
			final int screenLenght, final int screenWidth) {
		final JLabel mainLabel;
		JLabel passwordLabel;
		final JLabel usernameLabel;
		final JButton backToSelectAccount;
		final JButton send;

		mainLabel = new JLabel("Esegui l'accesso:");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		mainLabel.setBounds(250, 44, 480, 62);
		send = new JButton("Invio");
		send.setBounds(295, 260, 162, 25);
		this.add(send);
		this.usernameTextArea = new JTextArea();
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		if (type.equals(LoginType.USER)) {
			passwordLabel.setBounds(250, 193, 76, 34);
			usernameLabel.setBounds(250, 137, 76, 34);
			this.add(usernameLabel);
			this.usernameTextArea.setRows(1);
			this.usernameTextArea.setBounds(322, 143, 181, 25);
			this.add(this.usernameTextArea);
			send.addActionListener(e -> v.sendLogin());
		} else if (type.equals(LoginType.MANAGER)) {
			this.usernameTextArea.setVisible(false);
			passwordLabel = new JLabel("Password di Sistema:");
			passwordLabel.setBounds(250, 193, 76, 34);
			send.addActionListener(e -> v.sendManagerLogin());
		}
		passwordLabel.setBounds(250, 193, 76, 34);
		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(322, 199, 181, 25);
		backToSelectAccount = new JButton("Torna alla scelta");
		backToSelectAccount.addActionListener(e -> v
				.swapView(CardName.MAIN));
		backToSelectAccount.setBounds(626, 362, 162, 25);
		this.setLayout(null);
		this.add(mainLabel);
		this.add(passwordLabel);
		this.add(this.passwordTextArea);
		this.add(backToSelectAccount);

		this.setSize(1280, 920);

	}

	@Override
	public String getUserUsername() {
		return this.usernameTextArea.getText();
	}

	@Override
	public String getUserPassword() {
		return this.passwordTextArea.getText();
	}

	@Override
	public String getManagerPassword() {
		return this.passwordTextArea.getText();
	}

}
