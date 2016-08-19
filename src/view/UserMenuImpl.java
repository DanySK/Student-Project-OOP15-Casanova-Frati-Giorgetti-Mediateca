package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		this.setSize(1280, 920);
		final JButton exitProgram;
		final JLabel mainLabel;
		final JButton mediateca;
		final JButton studyRoomServices;
		final JButton accountSettings;

		mainLabel = new JLabel("Accedi ai nostri servizi\r\n");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		mainLabel.setBounds(48, 36, 1186, 49);
		this.add(mainLabel);
		v.giveMeSuggestedBooks();
		v.giveMeSuggestedMovies();
		this.suggestedBooks.setSize(437, 322);
		this.suggestedBooks.setLocation(151, 318);
		this.add(this.suggestedBooks);
		this.suggestedMovies.setSize(437, 322);
		this.suggestedMovies.setLocation(611, 318);

		this.add(this.suggestedMovies);

		exitProgram = new JButton("Esci");
		exitProgram.setFont(new Font("Tahoma", Font.PLAIN, 30));
		exitProgram.setBounds(1062, 843, 172, 49);
		this.add(exitProgram);

		mediateca = new JButton("Mediateca");
		mediateca.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mediateca.setBounds(288, 117, 300, 64);
		this.add(mediateca);

		studyRoomServices = new JButton("Sala studio");
		studyRoomServices.setFont(new Font("Tahoma", Font.PLAIN, 30));
		studyRoomServices.setBounds(611, 117, 300, 64);
		this.add(studyRoomServices);

		accountSettings = new JButton("Impostazioni Account");
		accountSettings.setFont(new Font("Tahoma", Font.PLAIN, 30));
		accountSettings.addActionListener(arg0 -> {
		});
		accountSettings.setBounds(27, 843, 348, 49);
		this.add(accountSettings);

		JLabel suggestedBooksLabel = new JLabel("Libri consigliati:");
		suggestedBooksLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		suggestedBooksLabel.setBounds(151, 256, 300, 49);
		this.add(suggestedBooksLabel);

		JLabel suggestedMoviesLabel = new JLabel("Film consigliati:");
		suggestedMoviesLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		suggestedMoviesLabel.setBounds(611, 256, 300, 49);
		this.add(suggestedMoviesLabel);

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
