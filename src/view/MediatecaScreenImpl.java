package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.TypeItem;
import utils.TypeItemInfo;
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
	private utils.TypeItemInfo selectedFilter;
	private String itemSelectedFromList;
	private utils.TypeItem itemType;
	private JList<String> filteredJList = new JList<String>();

	/**
	 * Builder for MediatecaScreen.
	 *
	 * @param v
	 *            the calling view
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MediatecaScreenImpl(final View v) {
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		final JButton borrowItem;
		final JButton giveBackItem;
		final JButton likeItem;
		final JButton seeBorrowedItem;
		final JTextField searchField;

		final JButton backToMenu;
		final JComboBox<utils.TypeItemInfo> filterSelect;
		final JComboBox<utils.TypeItem> itemSelect;
		final JButton search;
		final JButton review;
		giveBackItem = new JButton("Restituisci ");
		giveBackItem.setBounds(586, 188, 178, 27);
		giveBackItem
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		likeItem = new JButton("Mi Piace");
		likeItem.setBounds(586, 228, 178, 27);
		likeItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeBorrowedItem = new JButton("In prestito");
		seeBorrowedItem.setBounds(586, 270, 178, 27);
		seeBorrowedItem.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		borrowItem = new JButton("Prendi ");
		borrowItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		borrowItem.setBounds(586, 148, 178, 27);
		searchField = new JTextField();
		searchField.setBounds(21, 84, 521, 27);
		searchField.setText("Inserisci una o pi\u00F9 parole da cercare");
		filterSelect = new JComboBox(utils.TypeItemInfo.values());
		filterSelect.setBounds(232, 44, 200, 27);
		filterSelect.setToolTipText("Dove ricercare");
		itemSelect = new JComboBox(utils.TypeItem.values());
		itemSelect.setBounds(21, 44, 200, 27);
		itemSelect.setToolTipText("Libro o Film");
		this.setLayout(null);
		this.add(filterSelect);
		search = new JButton("Cerca");
		search.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		search.setBounds(586, 84, 178, 27);
		backToMenu = new JButton("Torna al Menu");
		backToMenu.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		backToMenu.setBounds(586, 539, 178, 27);
		v.giveMeFilteredList();
		this.filteredJList.setBounds(21, 148, 521, 418);
		this.filteredJList
				.setVisibleRowCount(MediatecaScreenImpl.ELEMENTS_TO_SHOW);
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
		review.setBounds(586, 310, 178, 27);
		review.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));

		this.add(borrowItem);
		this.add(giveBackItem);
		this.add(likeItem);
		this.add(seeBorrowedItem);
		this.add(searchField);
		this.add(search);
		this.add(backToMenu);
		this.add(itemSelect);
		this.add(this.filteredJList);
		this.add(review);

		backToMenu.addActionListener(e -> v.swapView(CardName.MENU));
		this.textToSearch = searchField.getText();
		this.selectedFilter = (TypeItemInfo) filterSelect.getSelectedItem();
		this.itemType = (TypeItem) itemSelect.getSelectedItem();
		this.itemSelectedFromList = this.filteredJList.getSelectedValue();

		JButton seeWishlist = new JButton("Wishlist");
		seeWishlist.setBounds(586, 350, 178, 27);
		seeWishlist
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeWishlist.addActionListener(arg0 -> {
		});
		this.add(seeWishlist);

		JButton reviews = new JButton("Recensioni");
		reviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reviews.setBounds(586, 390, 178, 27);
		this.add(reviews);
		reviews.addActionListener(e -> {
			v.swapView(CardName.ALL_REVIEWS);
		});
		borrowItem.addActionListener(e -> v.borrowItem());
		likeItem.addActionListener(e -> v.likeItem());
		review.addActionListener(e -> {
			v.swapView(CardName.REVIEW);
		});
		seeBorrowedItem.addActionListener(e -> {
			v.swapView(CardName.BORROWED_LIST);
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
	public TypeItemInfo getSearchFilter() {
		return this.selectedFilter;
	}

	@Override
	public TypeItem getItemType() {
		return this.itemType;
	}

	@Override
	public void setFilteredList(final String[] list) {
		this.filteredJList = new JList<String>(list);
	}
}