package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ViewImpl.CardName;

/**
 * Class for ItemScreen
 *
 * @author Luca Giorgetti
 *
 */
public class MediatecaScreenImpl extends JPanel implements MediatecaScreen {
	private static final long serialVersionUID = 1L;
	private static final int ELEMENTS_TO_SHOW = 25;
	private final JPanel bookMenuPanel = new JPanel();
	private final JLabel mainLabel;
	private final JButton borrowItem;
	private final JButton giveBackItem;
	private final JButton likeItem;
	private final JButton seeBorrowedItem;
	private final JTextField searchField;
	private final JComboBox filterSelect;
	private final JComboBox itemSelect;
	private final JButton search;
	private final JButton review;
	private String textToSearch;
	private String selectedFilter;
	private String itemType;
	private String itemSelectedFromList;
	private final JButton backToMenu;
	private final JList<String> filteredJList;
	private String filteredList[];

	/**
	 * Builder for MediatecaScreen.
	 *
	 * @param v
	 * @param screenLenght
	 * @param screenWidth
	 */
	public MediatecaScreenImpl(final View v, final int screenLenght,
			final int screenWidth) {
		this.mainLabel = new JLabel(
				"Benvenuto nell'area Biblioteca, premi un pulsante");
		this.mainLabel.setBounds(21, 9, 281, 16);
		this.giveBackItem = new JButton("Restituisci ");
		this.giveBackItem.setBounds(302, 133, 136, 25);
		this.likeItem = new JButton("Mi Piace");
		this.likeItem.setBounds(302, 187, 136, 25);
		this.seeBorrowedItem = new JButton("In prestito");
		this.seeBorrowedItem.setBounds(302, 236, 136, 25);
		this.bookMenuPanel.setLayout(null);
		this.borrowItem = new JButton("Prendi ");
		this.borrowItem.setBounds(302, 106, 136, 25);
		this.searchField = new JTextField();
		this.searchField.setText("Inserisci una o pi\u00F9 parole da cercare");
		this.searchField.setBounds(21, 67, 269, 22);
		this.filterSelect = new JComboBox();
		this.filterSelect.setToolTipText("Dove ricercare");
		this.filterSelect.setBounds(169, 38, 136, 22);
		this.itemSelect = new JComboBox();
		this.itemSelect.setToolTipText("Libro o Film");
		this.itemSelect.setBounds(21, 38, 136, 22);
		this.bookMenuPanel.add(this.filterSelect);
		this.search = new JButton("Cerca");
		this.search.setBounds(302, 66, 80, 25);
		this.backToMenu = new JButton("Torna al Menu");
		this.backToMenu.setBounds(302, 262, 136, 25);
		v.giveMeFilteredList();
		this.filteredJList = new JList<String>(this.filteredList);
		this.filteredJList
		.setVisibleRowCount(MediatecaScreenImpl.ELEMENTS_TO_SHOW);
		this.filteredJList.setBounds(21, 106, 269, 181);
		this.review = new JButton("Recensisci");
		this.review.setBounds(302, 160, 136, 25);

		this.bookMenuPanel.add(this.borrowItem);
		this.bookMenuPanel.add(this.mainLabel);
		this.bookMenuPanel.add(this.giveBackItem);
		this.bookMenuPanel.add(this.likeItem);
		this.bookMenuPanel.add(this.seeBorrowedItem);
		this.bookMenuPanel.add(this.searchField);
		this.bookMenuPanel.add(this.search);
		this.bookMenuPanel.add(this.backToMenu);
		this.bookMenuPanel.add(this.itemSelect);
		this.bookMenuPanel.add(this.filteredJList);
		this.bookMenuPanel.add(this.review);

		this.backToMenu.addActionListener(e -> v.swapView(CardName.MENU));
		this.textToSearch = this.searchField.getText();
		this.selectedFilter = (String) this.filterSelect.getSelectedItem();
		this.itemType = (String) this.itemSelect.getSelectedItem();
		this.itemSelectedFromList = this.filteredJList.getSelectedValue();
		this.borrowItem.addActionListener(e -> v.borrowItem());
		this.giveBackItem.addActionListener(e -> v.giveBackItem());
		this.likeItem.addActionListener(e -> v.likeItem());
		this.review.addActionListener(e -> {
			v.reviewItem();
			v.swapView(CardName.REVIEW);
		});
		this.seeBorrowedItem.addActionListener(e -> {
			v.swapView(CardName.BORROWED_LIST);
		});
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
		return this.textToSearch;
	}

	@Override
	public String getItemType() {
		return this.itemType;
	}

	@Override
	public void setFilteredList(final String[] list) {
		this.filteredList = list;
	}

}