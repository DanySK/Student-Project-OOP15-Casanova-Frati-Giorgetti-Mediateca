package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class which implements the user login screen.
 *
 * @author Luca
 *
 */

public class UserLoginImpl implements UserLogin {

	private final JPanel userLoginPanel = new JPanel();
	private final JLabel mainLabel;
	private final JLabel usernameLabel;
	private final JTextArea usernameTextArea;
	private final JLabel passwordLabel;
	private final JTextArea passwordTextArea;
	private final JButton send;
	private final JButton backToSelectAccount;
	private String username = new String();
	private String password = new String();

	/**
	 * Builder of login panel for user.
	 */

	public UserLoginImpl() {
		this.mainLabel = new JLabel("Inserisci qui nome username e password");
		this.usernameLabel = new JLabel("Username:");
		this.passwordLabel = new JLabel("Password:");
		this.usernameTextArea = new JTextArea();
		this.passwordTextArea = new JTextArea();
		this.send = new JButton("Invio");
		this.backToSelectAccount = new JButton("Torna alla scelta");
		this.userLoginPanel.setLayout(new BorderLayout());
		this.userLoginPanel.add(this.mainLabel, BorderLayout.NORTH);
		this.userLoginPanel.add(this.usernameLabel, BorderLayout.EAST);
		this.userLoginPanel.add(this.usernameTextArea, BorderLayout.WEST);
		this.userLoginPanel.add(this.passwordLabel, BorderLayout.EAST);
		this.userLoginPanel.add(this.passwordTextArea, BorderLayout.WEST);
		this.userLoginPanel.add(this.send, BorderLayout.SOUTH);
		this.userLoginPanel.add(this.backToSelectAccount, BorderLayout.SOUTH);

		this.username = this.usernameTextArea.getText();
		this.password = this.passwordTextArea.getText();
	}

	@Override
	public JPanel getUserLoginPanel() {
		return this.userLoginPanel;
	}

	@Override
	public String getUserUsername() {
		return this.username;
	}

	@Override
	public String getUserPassword() {
		return this.password;
	}
}
