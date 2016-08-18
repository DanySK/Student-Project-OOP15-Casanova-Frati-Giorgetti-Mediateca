package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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

	/**
	 * Create the panel.
	 *
	 * @param v
	 * @param screenWidth
	 * @param screenLenght
	 */

	private JList<String> suggestedBooks = new JList<String>();
	private JList<String> suggestedMovies = new JList<String>();

	public UserMenuImpl(final View v, final int screenLenght,
			final int screenWidth) {
		this.setLayout(null);
		final JButton exitProgram;
		final JLabel mainLabel;
		final JButton mediateca;
		final JButton studyRoomServices;
		final JButton accountSettings;

		mainLabel = new JLabel(
				"Seleziona il tipo di serviziovuoi accedere: a cui vuoi accedere");
		mainLabel.setBounds(12, 25, 414, 16);
		this.add(mainLabel);
		v.giveMeSuggestedBooks();
		v.giveMeSuggestedMovies();
		this.suggestedBooks.setSize(163, 130);
		this.suggestedBooks.setLocation(32, 156);
		this.add(this.suggestedBooks);
		this.suggestedMovies.setSize(163, 130);
		this.suggestedMovies.setLocation(246, 156);

		this.add(this.suggestedMovies);

		exitProgram = new JButton("Esci");
		exitProgram.setBounds(246, 105, 163, 38);
		this.add(exitProgram);

		mediateca = new JButton("Mediateca");
		mediateca.setBounds(32, 54, 163, 38);
		this.add(mediateca);

		studyRoomServices = new JButton("Sala studio");
		studyRoomServices.setBounds(246, 54, 163, 38);
		this.add(studyRoomServices);

		accountSettings = new JButton("Impostazioni Account");
		accountSettings.addActionListener(arg0 -> {
		});
		accountSettings.setBounds(32, 105, 163, 38);
		this.add(accountSettings);

		exitProgram.addActionListener(e -> v.swapView(CardName.MAIN));
		mediateca.addActionListener(e -> v.swapView(CardName.ITEM));
		studyRoomServices.addActionListener(e -> v
				.swapView(CardName.STUDY_ROOM));
		accountSettings
		.addActionListener(e -> v.swapView(CardName.USER_MODIFY));

	}

	@Override
	public void setSuggestedBooks(final String[] bList) {
		this.suggestedBooks = new JList<String>(bList);
	}

	@Override
	public void setSuggestedMovies(final String[] mList) {
		this.suggestedMovies = new JList<String>(mList);
	}
}
