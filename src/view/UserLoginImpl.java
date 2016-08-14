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
	private final JLabel mainLabel;
	private JLabel passwordLabel;
	private final JLabel usernameLabel;
	private final JTextArea passwordTextArea;
	private final JTextArea usernameTextArea;
	private final JButton backToSelectAccount;
	private final JButton send;

	private String username = new String();
	private String managerPassword = new String();
	private String password = new String();

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
		this.mainLabel = new JLabel("Esegui l'accesso:");
		this.mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.mainLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.mainLabel.setBounds(250, 44, 480, 62);
		this.send = new JButton("Invio");
		this.send.setBounds(295, 260, 162, 25);
		this.add(this.send);
		this.usernameTextArea = new JTextArea();
		this.usernameLabel = new JLabel("Username:");
		this.passwordLabel = new JLabel("Password:");
		if (type.equals(LoginType.USER)) {
			this.passwordLabel.setBounds(250, 193, 76, 34);
			this.usernameLabel.setBounds(250, 137, 76, 34);
			this.add(this.usernameLabel);
			this.usernameTextArea.setRows(1);
			this.usernameTextArea.setBounds(322, 143, 181, 25);
			this.add(this.usernameTextArea);
			this.send.addActionListener(e -> v.sendLogin());
			this.username = this.usernameTextArea.getText();
		} else if (type.equals(LoginType.MANAGER)) {
			this.usernameTextArea.setVisible(false);
			this.passwordLabel = new JLabel("Password di Sistema:");
			this.passwordLabel.setBounds(250, 193, 76, 34);
			this.send.addActionListener(e -> v.sendManagerLogin());
		}
		this.passwordLabel.setBounds(250, 193, 76, 34);
		this.passwordTextArea = new JTextArea();
		this.passwordTextArea.setRows(1);
		this.passwordTextArea.setBounds(322, 199, 181, 25);
		this.backToSelectAccount = new JButton("Torna alla scelta");
		this.backToSelectAccount.addActionListener(e -> v
				.swapView(CardName.MAIN));
		this.backToSelectAccount.setBounds(626, 362, 162, 25);
		this.setLayout(null);
		this.add(this.mainLabel);
		this.add(this.passwordLabel);
		this.add(this.passwordTextArea);
		this.add(this.backToSelectAccount);

		this.password = this.passwordTextArea.getText();
		this.setSize(1280, 920);

	}

	@Override
	public String getUserUsername() {
		return this.username;
	}

	@Override
	public String getUserPassword() {
		return this.password;
	}

	@Override
	public String getManagerPassword() {
		return this.managerPassword;
	}

}
