package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserMenuImpl extends JPanel implements UserMenu {
	private JPanel UserMenuPanel = new JPanel();
	private View toViewClass = new ViewImpl();
	final private JButton exitProgram;
	private JLabel mainLabel;
	private JButton bookServices;
	private JButton studyRoomServices;
	private JButton accountSettings;

	/**
	 * Create the panel.
	 *
	 * @param screenWidth
	 * @param screenLenght
	 */
	public UserMenuImpl(final int screenLenght, final int screenWidth) {
		this.mainLabel = new JLabel(
				"Seleziona il tipo di servizio a cui vuoi accedere:");
		this.mainLabel.setBounds(12, 13, 324, 48);
		this.exitProgram = new JButton("Esci");
		this.exitProgram.setBounds(436, 228, 153, 38);
		this.bookServices = new JButton("Biblioteca");
		this.bookServices.setBounds(296, 60, 163, 108);
		this.studyRoomServices = new JButton("Sala studio");
		this.studyRoomServices.setBounds(501, 60, 163, 108);
		this.accountSettings = new JButton("Impostazioni Account");
		this.accountSettings.setBounds(183, 228, 153, 38);
		this.UserMenuPanel.setLayout(null);
		this.UserMenuPanel.setSize(screenLenght, screenWidth);
		this.UserMenuPanel.add(this.mainLabel);
		this.UserMenuPanel.add(this.bookServices);
		this.UserMenuPanel.add(this.studyRoomServices);
		this.UserMenuPanel.add(this.exitProgram);
		this.UserMenuPanel.add(this.accountSettings);
		this.exitProgram.addActionListener(e -> System.exit(0));
		this.bookServices.addActionListener(e -> this.toViewClass
				.swapView("Book Panel"));
		// this.studyRoomServices.addActionListener(e -> );
		// this.accountSettings.addActionListener(e -> );

	}

	@Override
	public JPanel getUserMenuPanel() {
		return this.UserMenuPanel;
	}

}
