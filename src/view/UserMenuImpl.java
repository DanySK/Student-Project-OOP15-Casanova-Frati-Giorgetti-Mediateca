package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.ViewImpl.CardName;

/**
 * Class which implements the UserMenu interface.
 *
 * @author Luca Giorgetti
 *
 */
public class UserMenuImpl extends JPanel implements UserMenu {
	private static final long serialVersionUID = 1L;
	private final JButton exitProgram;
	private JLabel mainLabel;
	private JButton mediateca;
	private JButton studyRoomServices;
	private JButton accountSettings;

	/**
	 * Create the panel.
	 *
	 * @param v
	 * @param screenWidth
	 * @param screenLenght
	 */
	public UserMenuImpl(final View v, final int screenLenght,
			final int screenWidth) {
		this.setLayout(null);

		this.mainLabel = new JLabel(
				"Seleziona il tipo di serviziovuoi accedere: a cui vuoi accedere");
		this.mainLabel.setBounds(12, 25, 414, 16);
		this.add(this.mainLabel);

		this.exitProgram = new JButton("Esci");
		this.exitProgram.setBounds(246, 228, 163, 38);
		this.add(this.exitProgram);

		this.mediateca = new JButton("Mediateca");
		this.mediateca.setBounds(32, 54, 163, 65);
		this.add(this.mediateca);

		this.studyRoomServices = new JButton("Sala studio");
		this.studyRoomServices.setBounds(246, 54, 163, 65);
		this.add(this.studyRoomServices);

		this.accountSettings = new JButton("Impostazioni Account");
		this.accountSettings.addActionListener(arg0 -> {
		});
		this.accountSettings.setBounds(32, 228, 163, 38);
		this.add(this.accountSettings);

		this.exitProgram.addActionListener(e -> v.swapView(CardName.MAIN));
		this.mediateca.addActionListener(e -> v.swapView(CardName.ITEM));
		// this.studyRoomServices.addActionListener(e
		// ->
		// );
		// this.accountSettings.addActionListener(e -> );

	}

}
