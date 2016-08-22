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

	private DefaultListModel<String> modelM = new DefaultListModel<String>();
	private DefaultListModel<String> modelB = new DefaultListModel<String>();
	private JList<String> suggestedBooks = new JList<String>();
	private JList<String> suggestedMovies = new JList<String>();

	/**
	 * Builder for User Menu.
	 *
	 * @param v
	 *            the calling class
	 */
	public UserMenuImpl(final View v) {
		this.suggestedBooks.setModel(this.modelB);
		this.suggestedMovies.setModel(this.modelM);
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
		mainLabel.setBounds(12, 24, 761, 49);
		this.add(mainLabel);
		this.suggestedBooks.setSize(375, 122);
		this.suggestedBooks.setLocation(12, 197);
		this.add(this.suggestedBooks);
		this.suggestedMovies.setSize(349, 122);
		this.suggestedMovies.setLocation(424, 197);

		this.add(this.suggestedMovies);

		exitProgram = new JButton("Esci");
		exitProgram.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exitProgram.setBounds(601, 485, 172, 49);
		this.add(exitProgram);

		mediateca = new JButton("Mediateca");
		mediateca.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		mediateca.setBounds(12, 86, 377, 64);
		this.add(mediateca);

		studyRoomServices = new JButton("Sala studio");
		studyRoomServices.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		studyRoomServices.setBounds(424, 86, 349, 64);
		this.add(studyRoomServices);

		accountSettings = new JButton("Impostazioni Account");
		accountSettings.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		accountSettings.setBounds(12, 485, 306, 49);
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

		exitProgram.addActionListener(e -> {
			v.logOut();
			v.swapView(CardName.LOGIN);
		});
		mediateca.addActionListener(e -> {
			v.giveMeFilteredList();
			v.swapView(CardName.ITEM);
		});
		studyRoomServices.addActionListener(e -> {
			v.swapView(CardName.STUDY_ROOM);
		});

		accountSettings.addActionListener(e -> {
			v.giveMeUserInfo();
			v.swapView(CardName.USER_MODIFY);
		});
	}

	@Override
	public void setSuggestedBooks(final String[] bList) {
		this.modelB.clear();
		for (String element : bList) {
			this.modelB.addElement(element);
		}

	}

	@Override
	public void setSuggestedMovies(final String[] mList) {
		this.modelM.clear();
		for (String element : mList) {
			this.modelM.addElement(element);
		}

	}
}
