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
	private JList<String> list = new JList<String>();

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
		BORROWED, WISH
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
		JButton exit = new JButton();
		JButton remove = new JButton();
		for (final ListScreenType i : ListScreenType.values()) {
			if (i.equals(ListScreenType.BORROWED)) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				remove.addActionListener(e -> v.giveBackItem());
				v.giveMeBorrowList();

			} else if (i.equals(ListScreenType.WISH)) {
				this.presentation = new JLabel("Ecco gli oggetti che desideri");
				remove.addActionListener(e -> v.removeFromWishlist());
				v.giveMeWishlist();
			}
		}

		this.presentation.setBounds(525, 33, 197, 16);
		this.add(this.presentation);

		this.list.setBounds(71, 43, 289, 191);
		this.add(this.list);

		exit = new JButton("Esci");
		exit.setBounds(390, 312, 55, 25);
		this.add(exit);
		remove.setBounds(360, 262, 55, 25);
		this.add(remove);
		exit.addActionListener(e -> {
			v.swapView(CardName.ITEM);
		});

		this.setSize(1280, 920);
		this.setLayout(null);

	}

	@Override
	public void setBorrowedList(final String[] bList) {
		this.list = new JList<String>(bList);
	}

	@Override
	public void setWishlist(final String[] wishlist) {
		this.list = new JList<String>(wishlist);
	}

	@Override
	public String getSelectedItem() {
		return this.list.getSelectedValue();
	}
}
