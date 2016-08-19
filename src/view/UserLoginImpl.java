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
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.TITLE_SIZE));
		mainLabel.setBounds(12, 13, 776, 62);
		send = new JButton("Invio");
		send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		send.setBounds(297, 293, 230, 45);
		this.add(send);
		this.usernameTextArea = new JTextArea();
		usernameLabel = new JLabel("Username:");
		usernameLabel
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		passwordLabel = new JLabel("Password:");
		passwordLabel
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		this.add(passwordLabel);
		if (type.equals(LoginType.USER)) {
			passwordLabel.setBounds(154, 194, 162, 45);
			usernameLabel.setBounds(154, 136, 162, 45);
			this.add(usernameLabel);
			this.usernameTextArea.setRows(1);
			this.usernameTextArea.setBounds(328, 136, 329, 45);
			this.add(this.usernameTextArea);
			send.addActionListener(e -> v.sendLogin());
		} else if (type.equals(LoginType.MANAGER)) {
			this.usernameTextArea.setVisible(false);
			passwordLabel = new JLabel("Password di Sistema:");
			passwordLabel.setFont(new Font("Tahoma", Font.PLAIN,
					ViewImpl.FONT_SIZE));
			passwordLabel.setBounds(109, 124, 275, 45);
			send.addActionListener(e -> v.sendManagerLogin());
		}

		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(393, 124, 329, 45);
		backToSelectAccount = new JButton("Torna alla scelta");
		backToSelectAccount.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		backToSelectAccount.addActionListener(e -> v.swapView(CardName.MAIN));
		backToSelectAccount.setBounds(505, 521, 252, 45);
		this.setLayout(null);
		this.add(mainLabel);
		this.add(passwordLabel);
		this.add(this.passwordTextArea);
		this.add(backToSelectAccount);

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

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
