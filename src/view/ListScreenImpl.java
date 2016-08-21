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
	private DefaultListModel<String> model = new DefaultListModel<String>();
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
		BORROWED, WISH, REVIEWS
	}

	/**
	 * builder for ListScreen.
	 *
	 * @param v
	 * @param screenLenght
	 * @param screenWidth
	 * @param a
	 */
	public ListScreenImpl(final View v, final ListScreenType a) {
		JButton exit = new JButton();
		JButton remove = new JButton();
		remove.addActionListener(arg0 -> {
		});
		remove.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		for (final ListScreenType i : ListScreenType.values()) {
			if (i.equals(ListScreenType.BORROWED)) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				remove.addActionListener(e -> {
					v.giveBackItem();
					v.giveMeBorrowList();
					v.swapView(CardName.BORROWED_LIST);
				});
				remove.setText("Restituisci");

			} else if (i.equals(ListScreenType.WISH)) {
				this.presentation = new JLabel("Ecco gli oggetti che desideri");
				remove.setText("Rimuovi da Wishlist");
				remove.addActionListener(e -> {
					v.removeFromWishlist();
					v.giveMeBorrowList();
					v.swapView(CardName.WISHLIST);
				});

			} else if (i.equals(ListScreenType.REVIEWS)) {
				this.presentation = new JLabel("Ecco tutte le recensioni");
				remove.setVisible(false);

			}
		}

		this.presentation.setHorizontalAlignment(SwingConstants.CENTER);
		this.presentation.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));

		this.presentation.setBounds(12, 13, 776, 50);
		this.add(this.presentation);

		this.list.setModel(this.model);
		this.list.setBounds(42, 76, 704, 376);
		this.add(this.list);

		exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(603, 548, 166, 39);
		this.add(exit);
		remove.setBounds(215, 462, 353, 35);
		this.add(remove);
		exit.addActionListener(e -> {
			v.giveMeFilteredList();
			v.swapView(CardName.ITEM);
		});

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.setLayout(null);

	}

	@Override
	public void setBorrowedList(final String[] bList) {
		this.model.clear();
		for (String element : bList) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setWishlist(final String[] wishlist) {
		this.model.clear();
		for (String element : wishlist) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setReviewslist(final String[] reviewsList) {
		this.model.clear();
		for (String element : reviewsList) {
			this.model.addElement(element);
		}
	}

	@Override
	public String getSelectedItem() {
		return this.list.getSelectedValue();
	}
}
