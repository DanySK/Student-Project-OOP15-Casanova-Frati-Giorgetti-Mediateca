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
	private String textToSearch;
	private String selectedFilter;
	private String filteredList[];
	private String itemSelectedFromList;
	private String itemType;

	/**
	 * Builder for MediatecaScreen.
	 *
	 * @param v
	 * @param screenLenght
	 * @param screenWidth
	 */
	public MediatecaScreenImpl(final View v, final int screenLenght,
			final int screenWidth) {
		final JLabel mainLabel;
		final JButton borrowItem;
		final JButton giveBackItem;
		final JButton likeItem;
		final JButton seeBorrowedItem;
		final JTextField searchField;

		final JButton backToMenu;
		final JList<String> filteredJList;
		final JPanel bookMenuPanel = new JPanel();
		final JComboBox filterSelect;
		final JComboBox itemSelect;
		final JButton search;
		final JButton review;

		mainLabel = new JLabel(
				"Benvenuto nell'area Biblioteca, premi un pulsante");
		mainLabel.setBounds(21, 9, 281, 16);
		giveBackItem = new JButton("Restituisci ");
		giveBackItem.setBounds(302, 133, 136, 25);
		likeItem = new JButton("Mi Piace");
		likeItem.setBounds(302, 187, 136, 25);
		seeBorrowedItem = new JButton("In prestito");
		seeBorrowedItem.setBounds(302, 236, 136, 25);
		bookMenuPanel.setLayout(null);
		borrowItem = new JButton("Prendi ");
		borrowItem.setBounds(302, 106, 136, 25);
		searchField = new JTextField();
		searchField.setText("Inserisci una o pi\u00F9 parole da cercare");
		searchField.setBounds(21, 67, 269, 22);
		filterSelect = new JComboBox();
		filterSelect.setToolTipText("Dove ricercare");
		filterSelect.setBounds(169, 38, 136, 22);
		itemSelect = new JComboBox();
		itemSelect.setToolTipText("Libro o Film");
		itemSelect.setBounds(21, 38, 136, 22);
		bookMenuPanel.add(filterSelect);
		search = new JButton("Cerca");
		search.setBounds(302, 66, 80, 25);
		backToMenu = new JButton("Torna al Menu");
		backToMenu.setBounds(302, 262, 136, 25);
		v.giveMeFilteredList();
		filteredJList = new JList<String>(this.filteredList);
		filteredJList.setVisibleRowCount(MediatecaScreenImpl.ELEMENTS_TO_SHOW);
		filteredJList.setBounds(21, 106, 269, 181);
		review = new JButton("Recensisci");
		review.setBounds(302, 160, 136, 25);

		bookMenuPanel.add(borrowItem);

		bookMenuPanel.add(mainLabel);
		bookMenuPanel.add(giveBackItem);
		bookMenuPanel.add(likeItem);
		bookMenuPanel.add(seeBorrowedItem);
		bookMenuPanel.add(searchField);
		bookMenuPanel.add(search);
		bookMenuPanel.add(backToMenu);
		bookMenuPanel.add(itemSelect);
		bookMenuPanel.add(filteredJList);
		bookMenuPanel.add(review);

		backToMenu.addActionListener(e -> v.swapView(CardName.MENU));
		this.textToSearch = searchField.getText();
		this.selectedFilter = (String) filterSelect.getSelectedItem();
		this.itemType = (String) itemSelect.getSelectedItem();
		this.itemSelectedFromList = filteredJList.getSelectedValue();
		borrowItem.addActionListener(e -> v.borrowItem());
		giveBackItem.addActionListener(e -> v.giveBackItem());
		likeItem.addActionListener(e -> v.likeItem());
		review.addActionListener(e -> {
			v.reviewItem();
			v.swapView(CardName.REVIEW);
		});
		seeBorrowedItem.addActionListener(e -> {
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
		return this.selectedFilter;
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