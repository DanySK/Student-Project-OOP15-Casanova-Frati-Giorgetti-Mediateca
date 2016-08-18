package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.TypeItem;
import view.ViewImpl.CardName;

/**
 * Class for ItemScreen.
 *
 * @author Luca Giorgetti
 *
 */
public class MediatecaScreenImpl extends JPanel implements MediatecaScreen {
	private static final long serialVersionUID = 1L;
	private static final int ELEMENTS_TO_SHOW = 25;
	private String textToSearch;
	private String selectedFilter;
	private String itemSelectedFromList;
	private String itemType;
	private JList<String> filteredJList = new JList<String>();

	/**
	 * Builder for MediatecaScreen.
	 *
	 * @param v
	 * @param screenLenght
	 * @param screenWidth
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MediatecaScreenImpl(final View v, final int screenLenght,
			final int screenWidth) {
		this.setSize(1280, 920);
		final JLabel mainLabel;
		final JButton borrowItem;
		final JButton giveBackItem;
		final JButton likeItem;
		final JButton seeBorrowedItem;
		final JTextField searchField;

		final JButton backToMenu;
		final JComboBox<utils.TypeItemInfo> filterSelect;
		final JComboBox<utils.TypeItem> itemSelect;
		final JButton search;
		final JButton wishItem;
		final JButton review;

		mainLabel = new JLabel(
				"Benvenuto nell'area Biblioteca, premi un pulsante");
		mainLabel.setBounds(21, 9, 281, 16);
		giveBackItem = new JButton("Restituisci ");
		giveBackItem.setBounds(302, 133, 136, 25);
		likeItem = new JButton("Mi Piace");
		likeItem.setBounds(302, 212, 136, 25);
		wishItem = new JButton("Aggiungi a WhishList");
		wishItem.setBounds(302, 187, 136, 25);
		seeBorrowedItem = new JButton("In prestito");
		seeBorrowedItem.setBounds(302, 236, 136, 25);
		this.setLayout(null);
		borrowItem = new JButton("Prendi ");
		borrowItem.setBounds(302, 106, 136, 25);
		searchField = new JTextField();
		searchField.setText("Inserisci una o pi\u00F9 parole da cercare");
		searchField.setBounds(21, 67, 269, 22);
		filterSelect = new JComboBox(utils.TypeItemInfo.values());
		filterSelect.setToolTipText("Dove ricercare");
		filterSelect.setBounds(169, 38, 136, 22);
		itemSelect = new JComboBox(utils.TypeItem.values());
		itemSelect.setToolTipText("Libro o Film");
		itemSelect.setBounds(21, 38, 136, 22);
		this.add(filterSelect);
		search = new JButton("Cerca");
		search.setBounds(302, 66, 80, 25);
		backToMenu = new JButton("Torna al Menu");
		backToMenu.setBounds(302, 292, 136, 25);
		v.giveMeFilteredList();
		this.filteredJList
				.setVisibleRowCount(MediatecaScreenImpl.ELEMENTS_TO_SHOW);
		this.filteredJList.setBounds(21, 106, 269, 181);
		this.filteredJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				JList<String> list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					v.showItemInfo((TypeItem) itemSelect.getSelectedItem());
				}
			}
		});
		review = new JButton("Recensisci");
		review.setBounds(302, 160, 136, 25);

		this.add(borrowItem);

		this.add(mainLabel);
		this.add(giveBackItem);
		this.add(likeItem);
		this.add(seeBorrowedItem);
		this.add(searchField);
		this.add(search);
		this.add(backToMenu);
		this.add(itemSelect);
		this.add(wishItem);
		this.add(this.filteredJList);
		this.add(review);

		backToMenu.addActionListener(e -> v.swapView(CardName.MENU));
		this.textToSearch = searchField.getText();
		this.selectedFilter = (String) filterSelect.getSelectedItem();
		this.itemType = (String) itemSelect.getSelectedItem();
		this.itemSelectedFromList = this.filteredJList.getSelectedValue();

		JButton seeWishlist = new JButton("Wishlist");
		seeWishlist.addActionListener(arg0 -> {
		});
		seeWishlist.setBounds(302, 262, 136, 25);
		this.add(seeWishlist);
		borrowItem.addActionListener(e -> v.borrowItem());
		likeItem.addActionListener(e -> v.likeItem());
		review.addActionListener(e -> {
			v.reviewItem();
			v.swapView(CardName.REVIEW);
		});
		seeBorrowedItem.addActionListener(e -> {
			v.swapView(CardName.BORROWED_LIST);
		});
		wishItem.addActionListener(e -> {
			v.wishItem();
		});
		seeWishlist.addActionListener(e -> v.swapView(CardName.WISHLIST));
	}

	@Override
	public String getSelectedItemFromList() {
		return this.itemSelectedFromList;
	}

	@Override
	public String getSearchText() {
		return this.textToSearch;
	}

	@Override
	public String getSearchFilter() {
		return this.selectedFilter;
	}

	@Override
	public String getItemType() {
		return this.itemType;
	}

	@Override
	public void setFilteredList(final String[] list) {
		this.filteredJList = new JList<String>(list);
	}
}