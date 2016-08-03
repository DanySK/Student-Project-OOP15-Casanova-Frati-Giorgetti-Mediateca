package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuImpl extends JPanel implements Menu {
	private JPanel menuPanel = new JPanel();
	final private JButton exitProgram;
	private JLabel mainLabel;
	private JButton bookServices;
	private JButton filmServices;
	private JButton studyRoomServices;
	private JButton accountSettings;

	/**
	 * Create the panel.
	 *
	 * @param screenWidth
	 * @param screenLenght
	 */
	public MenuImpl(final int screenLenght, final int screenWidth,
			final JFrame frame) {
		this.mainLabel = new JLabel(
				"Seleziona il tipo di servizio a cui vuoi accedere:");
		this.mainLabel.setBounds(12, 13, 324, 48);
		this.exitProgram = new JButton("Esci");
		this.exitProgram.setBounds(436, 228, 153, 38);
		this.bookServices = new JButton("Biblioteca");
		this.bookServices.setBounds(296, 60, 163, 108);
		this.filmServices = new JButton("Mediateca");
		this.filmServices.setBounds(92, 60, 170, 108);
		this.studyRoomServices = new JButton("Sala studio");
		this.studyRoomServices.setBounds(501, 60, 163, 108);
		this.accountSettings = new JButton("Impostazioni Account");
		this.accountSettings.setBounds(183, 228, 153, 38);
		this.menuPanel.setLayout(null);
		this.menuPanel.setSize(screenLenght, screenWidth);
		this.menuPanel.add(this.mainLabel);
		this.menuPanel.add(this.bookServices);
		this.menuPanel.add(this.filmServices);
		this.menuPanel.add(this.studyRoomServices);
		this.menuPanel.add(this.exitProgram);
		this.menuPanel.add(this.accountSettings);
		this.exitProgram.addActionListener(e -> System.exit(0));
		this.bookServices.addActionListener(e -> frame.add(BookServices
				.getPanel()));
		this.filmServices.addActionListener(e -> frame.add(FilmServices
				.getPanel()));
		this.studyRoomServices.addActionListener(e -> frame
				.add(StudyRoomServices.getPanel()));
		this.accountSettings.addActionListener(e -> frame.add(AccountSettings
				.getPanel()));

	}

	@Override
	public JPanel getPanel() {
		return this.menuPanel;
	}

}
