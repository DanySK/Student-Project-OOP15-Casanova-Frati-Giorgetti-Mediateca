package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * MenuImpl contains the implementation of methods for the Menu page.
 *
 * @author Luca Giorgetti
 *
 */
public class MenuImpl implements Menu {
	private JPanel menuPanel = new JPanel();
	private JButton exitProgram;
	private JLabel mainLabel;
	private JButton bookServices;
	private JButton filmServices;
	private JButton studyRoomServices;
	private JButton accountSettings;

	/**
	 * Builder method for MenuImpl Class.
	 */
	public MenuImpl() {
		this.mainLabel = new JLabel(
				"Seleziona il tipo di servizioa cui vuoi accedere:");
		this.exitProgram = new JButton("Esci");
		this.bookServices = new JButton("Biblioteca");
		this.filmServices = new JButton("Mediateca");
		this.studyRoomServices = new JButton("Sala studio");
		this.accountSettings = new JButton("Impostazioni Account");
		this.menuPanel.setLayout(new BorderLayout());
		this.menuPanel.add(this.mainLabel, BorderLayout.NORTH);
		this.menuPanel.add(this.bookServices, BorderLayout.CENTER);
		this.menuPanel.add(this.filmServices, BorderLayout.CENTER);
		this.menuPanel.add(this.studyRoomServices, BorderLayout.CENTER);
		this.menuPanel.add(this.exitProgram, BorderLayout.SOUTH);
		this.menuPanel.add(this.accountSettings, BorderLayout.SOUTH);

	}

	@Override
	public JPanel getMenuPanel() {
		return this.menuPanel;
	}
}
