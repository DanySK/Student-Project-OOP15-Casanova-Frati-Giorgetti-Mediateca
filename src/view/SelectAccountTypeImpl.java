package view;

import java.awt.BorderLayout;

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
		this.user = new JButton("Utente");
		this.manager = new JButton("Gestore");
		this.backToHome = new JButton("Torna alla schermata Home");
		this.selectAccountPanel.setLayout(new BorderLayout());
		this.selectAccountPanel.add(this.mainLabel, BorderLayout.NORTH);
		this.selectAccountPanel.add(this.user, BorderLayout.EAST);
		this.selectAccountPanel.add(this.manager, BorderLayout.WEST);
		this.selectAccountPanel.add(this.backToHome, BorderLayout.SOUTH);
	}

	@Override
	public JPanel getPanelSelectAccount() {
		return this.selectAccountPanel;
	}
}
