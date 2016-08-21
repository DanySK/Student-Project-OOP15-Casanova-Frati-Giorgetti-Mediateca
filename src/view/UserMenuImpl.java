package view;

import java.awt.Font;

import javax.swing.DefaultListModel;
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
	String[] prova = { "baci", "baci" };

	DefaultListModel model;
	private JList<String> suggestedBooks = new JList<String>();
	private JList<String> suggestedMovies = new JList<String>();

	public UserMenuImpl(final View v) {
		this.model = new DefaultListModel();
		this.model.addElement("gatto");
		this.model.addElement("two");
		this.suggestedBooks.setModel(this.model);
		this.setLayout(null);
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		final JButton exitProgram;
		final JLabel mainLabel;
		final JButton mediateca;
		final JButton studyRoomServices;
		final JButton accountSettings;

		mainLabel = new JLabel("Accedi ai nostri servizi\r\n");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.TITLE_SIZE));
		mainLabel.setBounds(27, 24, 761, 49);
		this.add(mainLabel);
		v.giveMeSuggestedBooks();

		v.giveMeSuggestedMovies();
		this.suggestedBooks.setModel(this.model);
		this.suggestedBooks.setSize(375, 289);
		this.suggestedBooks.setLocation(27, 197);
		this.add(this.suggestedBooks);
		this.suggestedMovies.setSize(349, 289);
		this.suggestedMovies.setLocation(439, 197);

		this.add(this.suggestedMovies);

		exitProgram = new JButton("Esci");
		exitProgram.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exitProgram.setBounds(616, 519, 172, 49);
		this.add(exitProgram);

		mediateca = new JButton("Mediateca");
		mediateca.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		mediateca.setBounds(102, 86, 300, 64);
		this.add(mediateca);

		studyRoomServices = new JButton("Sala studio");
		studyRoomServices.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		studyRoomServices.setBounds(424, 86, 300, 64);
		this.add(studyRoomServices);

		accountSettings = new JButton("Impostazioni Account");
		accountSettings.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		accountSettings.addActionListener(arg0 -> {
		});
		accountSettings.setBounds(27, 519, 348, 49);
		this.add(accountSettings);

		JLabel suggestedBooksLabel = new JLabel("Libri consigliati:");
		suggestedBooksLabel.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		suggestedBooksLabel.setBounds(27, 151, 300, 49);
		this.add(suggestedBooksLabel);

		JLabel suggestedMoviesLabel = new JLabel("Film consigliati:");
		suggestedMoviesLabel.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		suggestedMoviesLabel.setBounds(439, 151, 300, 49);
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
		for (String element : bList) {
			this.model.addElement("pesce");
		}
	}

	@Override
	public void setSuggestedMovies(final String[] mList) {
		this.suggestedMovies = new JList<String>(mList);
	}
}
