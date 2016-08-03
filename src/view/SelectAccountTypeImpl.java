package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Class which contains the panel for choosing the account type (Master or
 * User).
 *
 */

public class SelectAccountTypeImpl implements SelectAccountType {

	private final JPanel selectAccountPanel = new JPanel();
	private final JLabel mainLabel;
	private final JButton user;
	private final JButton manager;
	private final JButton backToHome;

	/**
	 *
	 * Builder method for SelectAccountTypeImpl.
	 *
	 */

	public SelectAccountTypeImpl() {

		this.mainLabel = new JLabel(
				"Scegli la modalità con cui vuoi accedere a MEDIATECA:");
		this.mainLabel.setBounds(0, 0, 450, 16);
		this.user = new JButton("Utente");
		this.user.setBounds(232, 16, 210, 259);
		this.manager = new JButton("Gestore");
		this.manager.setBounds(10, 16, 210, 259);
		this.backToHome = new JButton("Torna alla schermata Home");
		this.backToHome.setBounds(0, 275, 450, 25);
		this.selectAccountPanel.setLayout(null);
		this.selectAccountPanel.add(this.mainLabel);
		this.selectAccountPanel.add(this.user);
		this.selectAccountPanel.add(this.manager);
		this.selectAccountPanel.add(this.backToHome);
	}

	@Override
	public JPanel getPanel() {
		return this.selectAccountPanel;
	}
}
