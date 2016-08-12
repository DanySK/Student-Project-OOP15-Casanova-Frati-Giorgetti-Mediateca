package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserModifyImpl extends JPanel implements UserModify {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel userModifyPanel = new JPanel();
	final private JButton exitProgram;
	private JLabel mainLabel;

	private JButton accountSettings;

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
		this.mainLabel = new JLabel("M:");
		this.mainLabel.setBounds(12, 13, 324, 48);
		this.exitProgram = new JButton("Esci");
		this.exitProgram.setBounds(436, 228, 153, 38);

		this.userModifyPanel.setLayout(null);
		this.userModifyPanel.setSize(screenLenght, screenWidth);
		this.userModifyPanel.add(this.accountSettings);

	}

}
