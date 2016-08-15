package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import view.ViewImpl.CardName;

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

	private JLabel presentation;
	private String[] showedList;

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
		JButton exit;
		final JList<String> list;
		for (final ListScreenType i : ListScreenType.values()) {
			if (i.equals(ListScreenType.BORROWED)) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				v.giveMeBorrowList();

			} else if (i.equals(ListScreenType.LIKE)) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che ti interessano:");
				v.giveMeLikeList();
			}
		}

		this.presentation.setBounds(525, 33, 197, 16);
		this.add(this.presentation);
		list = new JList<String>(this.showedList);
		list.setBounds(71, 43, 289, 191);
		this.add(list);

		exit = new JButton("Esci");
		exit.setBounds(360, 262, 55, 25);
		this.add(exit);

		exit.addActionListener(e -> {
			v.swapView(CardName.ITEM);
		});
		this.setSize(screenLenght, screenWidth);
		this.setLayout(null);

	}

	@Override
	public void setBorrowedList(final String[] bList) {
		this.showedList = bList;
	}

	@Override
	public void setLikeList(final String[] lList) {
		this.showedList = lList;
	}
}