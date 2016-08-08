package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookScreenImpl extends JPanel implements BookScreen {
	private static final long serialVersionUID = 1L;
	private static final int ELEMENTS_TO_SHOW = 25;
	private final JPanel bookMenuPanel = new JPanel();
	private View toViewClass = new ViewImpl();
	private final JLabel mainLabel;
	private final JButton borrowBook;
	private final JButton giveBackBook;
	private final JButton reviewBook;
	private final JButton seeBorrowedBook;
	private final JTextField searchField;
	private final JComboBox filterSelect;
	private final JComboBox itemSelect;
	private final JButton search;
	private String textToSearch;
	private String selectedFilter;
	private String itemType;
	private String itemSelectedFromList;
	private final JButton backToMenu;
	private final JList FilteredList;

	public BookScreenImpl(final int screenLenght, final int screenWidth) {
		this.mainLabel = new JLabel(
				"Benvenuto nell'area Biblioteca, premi un pulsante");
		this.mainLabel.setBounds(21, 9, 281, 16);
		this.giveBackBook = new JButton("Restituisci Libro");
		this.giveBackBook.setBounds(302, 133, 136, 25);
		this.reviewBook = new JButton("Recensisci Libro");
		this.reviewBook.setBounds(302, 160, 136, 25);
		this.seeBorrowedBook = new JButton("Libri in prestito");
		this.seeBorrowedBook.setBounds(302, 230, 136, 25);
		this.bookMenuPanel.setLayout(null);
		this.borrowBook = new JButton("Prendi Libro");
		this.borrowBook.setBounds(302, 106, 136, 25);
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
		this.FilteredList = new JList();
		this.FilteredList.setVisibleRowCount(BookScreenImpl.ELEMENTS_TO_SHOW);
		this.FilteredList.setBounds(21, 106, 269, 181);

		this.bookMenuPanel.add(this.borrowBook);
		this.bookMenuPanel.add(this.mainLabel);
		this.bookMenuPanel.add(this.giveBackBook);
		this.bookMenuPanel.add(this.reviewBook);
		this.bookMenuPanel.add(this.seeBorrowedBook);
		this.bookMenuPanel.add(this.searchField);
		this.bookMenuPanel.add(this.search);
		this.bookMenuPanel.add(this.backToMenu);
		this.bookMenuPanel.add(this.itemSelect);
		this.bookMenuPanel.add(this.FilteredList);

		this.backToMenu.addActionListener(e -> this.toViewClass
				.swapView("Menu Panel"));
		this.textToSearch = this.searchField.getText();
		this.selectedFilter = (String) this.filterSelect.getSelectedItem();
		this.itemType = (String) this.itemSelect.getSelectedItem();
		this.itemSelectedFromList = (String) this.FilteredList
				.getSelectedValue();
		this.borrowBook.addActionListener(e -> this.toViewClass
				.borrowBook(this.itemSelectedFromList));

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
}