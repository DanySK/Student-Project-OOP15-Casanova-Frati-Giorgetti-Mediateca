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
	private final JPanel userLoginPanel = new JPanel();
	// private View toViewClass = new ViewImpl();
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
	public UserLoginImpl() {
		this.mainLabel = new JLabel("Inserisci qui nome username e password");
		this.mainLabel.setBounds(250, 94, 267, 16);
		this.passwordLabel = new JLabel("Password:");
		this.passwordLabel.setBounds(250, 193, 76, 34);
		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(322, 199, 181, 25);
		this.backToSelectAccount = new JButton("Torna alla scelta");
		this.backToSelectAccount.addActionListener(e -> ViewImpl
				.swapView("User Menu Card"));
		this.backToSelectAccount.setBounds(626, 362, 162, 25);
		this.userLoginPanel.setSize(800, 400);
		this.userLoginPanel.setLayout(null);
		this.userLoginPanel.add(this.mainLabel);
		this.userLoginPanel.add(this.passwordLabel);
		this.userLoginPanel.add(this.passwordTextArea);
		this.userLoginPanel.add(this.backToSelectAccount);

		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setBounds(250, 137, 76, 34);
		this.userLoginPanel.add(this.usernameLabel);

		this.usernameTextArea = new JTextArea();
		this.usernameTextArea.setRows(1);
		this.usernameTextArea.setBounds(322, 143, 181, 25);
		this.userLoginPanel.add(this.usernameTextArea);

		this.send = new JButton("Invio");
		this.send.setBounds(295, 260, 162, 25);
		this.userLoginPanel.add(this.send);
		this.password = this.passwordTextArea.getText();
		this.username = this.usernameTextArea.getText();
		this.send.addActionListener(e -> View.sendLogin());

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
