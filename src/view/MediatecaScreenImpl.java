package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private utils.TypeItem itemType;
	private JList<String> filteredJList = new JList<String>();
	private DefaultListModel<String> model = new DefaultListModel<String>();

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
		giveBackItem.setBounds(586, 164, 178, 27);
		giveBackItem
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		likeItem = new JButton("Mi Piace");
		likeItem.setBounds(586, 204, 178, 27);
		likeItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeBorrowedItem = new JButton("In prestito");
		seeBorrowedItem.setBounds(586, 246, 178, 27);
		seeBorrowedItem.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		borrowItem = new JButton("Prendi ");
		borrowItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		borrowItem.setBounds(586, 124, 178, 27);
		searchField = new JTextField();
		searchField.setBounds(21, 84, 521, 27);
		filterSelect = new JComboBox(utils.TypeItemInfo.values());
		filterSelect.setSelectedIndex(-1);
		filterSelect.setBounds(232, 44, 200, 27);
		filterSelect.setToolTipText("Dove ricercare");
		itemSelect = new JComboBox(utils.TypeItem.values());
		itemSelect.setSelectedIndex(-1);
		itemSelect.setBounds(21, 44, 200, 27);
		itemSelect.setToolTipText("Libro o Film");
		this.setLayout(null);
		this.add(filterSelect);
		search = new JButton("Cerca");
		search.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		search.setBounds(586, 84, 178, 27);
		// SEARCH ACTION LISTENER -> REFRESH
		search.addActionListener(e -> {
			v.giveMeFilteredList();
			v.swapView(CardName.ITEM);
		});

		backToMenu = new JButton("Torna al Menu");
		backToMenu.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		backToMenu.setBounds(586, 495, 178, 27);

		this.filteredJList.setModel(this.model);
		this.filteredJList.setBounds(21, 124, 521, 398);
		this.filteredJList
				.setVisibleRowCount(MediatecaScreenImpl.ELEMENTS_TO_SHOW);

		this.filteredJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				JList<String> list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					v.giveMeItemInfo();
					v.showItemInfo();
				}
			}
		});
		review = new JButton("Recensisci");
		review.setBounds(586, 286, 178, 27);
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

		// BACK ACTION LISTENER -> SUGGESTED LIST
		backToMenu.addActionListener(e -> {
			v.swapView(CardName.MENU);
			v.giveMeSuggestedBooks();
			v.giveMeSuggestedMovies();
		});

		this.textToSearch = searchField.getText();
		this.selectedFilter = (TypeItemInfo) filterSelect.getSelectedItem();
		this.itemType = (TypeItem) itemSelect.getSelectedItem();

		JButton seeWishlist = new JButton("Wishlist");
		seeWishlist.setBounds(586, 326, 178, 27);
		seeWishlist
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		seeWishlist.addActionListener(arg0 -> {
			v.giveMeWishlist();
			v.swapView(CardName.WISHLIST);
		});
		this.add(seeWishlist);

		JButton reviews = new JButton("Recensioni");
		reviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reviews.setBounds(586, 366, 178, 27);
		this.add(reviews);

		JLabel pres = new JLabel("Cerca tra libri e film!");
		pres.setHorizontalAlignment(SwingConstants.CENTER);
		pres.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pres.setBounds(42, 13, 722, 27);
		this.add(pres);
		reviews.addActionListener(e -> {
			v.giveMeAllItemReviews();
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
		return this.filteredJList.getSelectedValue();
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
		this.model.clear();
		for (String element : list) {
			this.model.addElement(element);
		}
	}
}