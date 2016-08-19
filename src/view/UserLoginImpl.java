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
	private JTextArea usernameTextArea;

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
		mainLabel.setBounds(35, 45, 1233, 62);
		send = new JButton("Invio");
		send.setFont(new Font("Tahoma", Font.PLAIN, 30));
		send.setBounds(512, 295, 313, 45);
		this.add(send);
		this.usernameTextArea = new JTextArea();
		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));

		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));

		this.add(passwordLabel);
		if (type.equals(LoginType.USER)) {
			passwordLabel.setBounds(373, 182, 223, 45);
			usernameLabel.setBounds(373, 124, 223, 45);
			this.add(usernameLabel);
			this.usernameTextArea.setRows(1);
			this.usernameTextArea.setBounds(608, 124, 329, 45);
			this.add(this.usernameTextArea);
			send.addActionListener(e -> v.sendLogin());
		} else if (type.equals(LoginType.MANAGER)) {
			this.usernameTextArea.setVisible(false);
			passwordLabel = new JLabel("Password di Sistema:");
			passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			passwordLabel.setBounds(250, 182, 329, 45);
			send.addActionListener(e -> v.sendManagerLogin());
		}

		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(608, 182, 329, 45);
		backToSelectAccount = new JButton("Torna alla scelta");
		backToSelectAccount.setFont(new Font("Tahoma", Font.PLAIN, 30));
		backToSelectAccount.addActionListener(e -> v.swapView(CardName.MAIN));
		backToSelectAccount.setBounds(905, 833, 342, 45);
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
