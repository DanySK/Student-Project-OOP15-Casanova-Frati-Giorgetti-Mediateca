package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Class implementing the interface for ListScreen.
 *
 * @author Luca Giorgetti
 *
 */
public class ListScreenImpl extends JPanel implements ListScreen {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static final int FRAME_LENGHT = 1280;
	static final int FRAME_WIDTH = 920;

	private static JLabel presentation;
	private static JList<String> jList;
	private static JButton exit;
	private static List<String> showedList;

	/**
	 * Enumeration with types of list which can be showed.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum ListScreenType {
		/**
		 *
		 */
		BORROWED, LIKE
	}

	/**
	 * builder for ListScreen.
	 *
	 * @param v
	 * @param screenLenght
	 * @param screenWidth
	 * @param a
	 */
	public ListScreenImpl(final View v, final int screenLenght,
			final int screenWidth, final ListScreenType a) {
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		for (ListScreenType i : ListScreenType.values()) {
			if (i == ListScreenType.BORROWED) {
				mainFrame.setTitle("Oggetti in prestito");
				ListScreenImpl.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				v.giveMeBorrowList();

			}
		}
		mainFrame.setTitle("My Likes");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ReviewScreenImpl.FRAME_LENGHT,
				ReviewScreenImpl.FRAME_WIDTH);
		mainFrame.setResizable(false);
		v.giveMeLikeList();
		ListScreenImpl.presentation = new JLabel(
				"Ecco gli oggetti che ti interessano:");
		ListScreenImpl.presentation.setBounds(525, 33, 197, 16);
		mainPanel.setLayout(null);
		mainPanel.add(ListScreenImpl.presentation);

		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		ListScreenImpl.jList.setBounds(51, 41, 349, 228);
		mainPanel.add(ListScreenImpl.jList);

		ListScreenImpl.jList = new JList<String>();
		ListScreenImpl.jList.setBounds(129, 62, 1004, 694);
		mainPanel.add(ListScreenImpl.jList);

		ListScreenImpl.exit = new JButton("Esci");
		ListScreenImpl.exit.setBounds(921, 778, 212, 94);
		mainPanel.add(ListScreenImpl.exit);
		ListScreenImpl.exit.addActionListener(e -> {
			mainFrame.setVisible(false);
			mainFrame.dispose();
		});

	}

	@Override
	public void setBorrowedList(final List<String> bList) {
		ListScreenImpl.showedList = bList;
	}

	@Override
	public void setLikeList(final List<String> lList) {
		ListScreenImpl.showedList = lList;
	}

}