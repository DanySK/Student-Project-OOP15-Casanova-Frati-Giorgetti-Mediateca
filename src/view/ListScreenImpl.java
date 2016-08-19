package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JButton exit_1;

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
		remove.addActionListener(arg0 -> {
		});
		remove.setFont(new Font("Tahoma", Font.PLAIN, 30));
		for (final ListScreenType i : ListScreenType.values()) {
			if (i.equals(ListScreenType.BORROWED)) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				remove.addActionListener(e -> v.giveBackItem());
				remove.setText("Restituisci");
				v.giveMeBorrowList();

			} else if (i.equals(ListScreenType.WISH)) {
				this.presentation = new JLabel("Ecco gli oggetti che desideri");
				remove.addActionListener(e -> v.removeFromWishlist());
				remove.setText("Rimuovi da WishList");
				v.giveMeWishlist();
			}
		}

		this.presentation.setHorizontalAlignment(SwingConstants.CENTER);
		this.presentation.setFont(new Font("Tahoma", Font.PLAIN, 40));

		this.presentation.setBounds(173, 13, 898, 50);
		this.add(this.presentation);

		this.list.setBounds(173, 76, 898, 528);
		this.add(this.list);

		this.exit_1 = new JButton("Esci");
		this.exit_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.exit_1.setBounds(1054, 810, 166, 70);
		this.add(this.exit_1);
		remove.setBounds(411, 617, 464, 46);
		this.add(remove);
		this.exit_1.addActionListener(e -> {
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
