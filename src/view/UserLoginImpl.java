package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserLoginImpl extends JPanel implements UserLogin {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel mainLabel;
	private final JLabel passwordLabel;
	private final JLabel usernameLabel;
	private final JTextArea passwordTextArea;
	private final JTextArea usernameTextArea;
	private final JButton backToSelectAccount;
	private final JButton send;

	private String username = new String();
	private String password = new String();

	/**
	 * Create the panel.
	 */
	public UserLoginImpl(final View v) {
		this.mainLabel = new JLabel("Inserisci qui nome username e password");
		this.mainLabel.setBounds(250, 94, 267, 16);
		this.passwordLabel = new JLabel("Password:");
		this.passwordLabel.setBounds(250, 193, 76, 34);
		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(322, 199, 181, 25);
		this.backToSelectAccount = new JButton("Torna alla scelta");
		this.backToSelectAccount.addActionListener(e -> v
				.swapView("User Menu Card"));
		this.backToSelectAccount.setBounds(626, 362, 162, 25);
		this.setSize(800, 400);
		this.setLayout(null);
		this.add(this.mainLabel);
		this.add(this.passwordLabel);
		this.add(this.passwordTextArea);
		this.add(this.backToSelectAccount);

		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setBounds(250, 137, 76, 34);
		this.add(this.usernameLabel);

		this.usernameTextArea = new JTextArea();
		this.usernameTextArea.setRows(1);
		this.usernameTextArea.setBounds(322, 143, 181, 25);
		this.add(this.usernameTextArea);

		this.send = new JButton("Invio");
		this.send.setBounds(295, 260, 162, 25);
		this.add(this.send);
		this.password = this.passwordTextArea.getText();
		this.username = this.usernameTextArea.getText();
		this.send.addActionListener(e -> v.sendLogin());

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
