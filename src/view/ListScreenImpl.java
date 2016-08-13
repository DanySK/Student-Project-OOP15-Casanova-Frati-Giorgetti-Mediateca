package view;

import java.util.List;

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
	static final int FRAME_LENGHT = 1280;
	static final int FRAME_WIDTH = 920;

	private JLabel presentation;
	private JButton exit;
	private List<String> showedList;
	private JList<String> list;

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
		for (ListScreenType i : ListScreenType.values()) {
			if (i == ListScreenType.BORROWED) {
				this.presentation = new JLabel(
						"Ecco gli oggetti che hai in prestito:");
				v.giveMeBorrowList();

			} else {
				this.presentation = new JLabel(
						"Ecco gli oggetti che ti interessano:");
				v.giveMeLikeList();
			}
		}
		this.setLayout(null);
		this.presentation.setBounds(525, 33, 197, 16);
		this.add(this.presentation);
		this.list = new JList<String>();
		this.list.setBounds(71, 43, 289, 191);
		this.add(this.list);

		this.exit = new JButton("Esci");
		this.exit.setBounds(360, 262, 55, 25);
		this.add(this.exit);

		this.exit.addActionListener(e -> {
			v.swapView(CardName.ITEM);
		});

	}

	@Override
	public void setBorrowedList(final List<String> bList) {
		this.showedList = bList;
	}

	@Override
	public void setLikeList(final List<String> lList) {
		this.showedList = lList;
	}
}