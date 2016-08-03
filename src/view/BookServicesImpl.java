package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookServicesImpl implements BookServices {
	private final JPanel bookMenuPanel = new JPanel();
	private final JLabel mainLabel;
	private final JButton borrowBook;
	private final JButton giveBackBook;
	private final JButton reviewBook;
	private final JButton seeBorrowedBook;
	private final JButton checkBookAvailability;
	private final JButton searchBook;

	public BookServicesImpl(final int screenLenght, final int screenWidth,
			final JFrame frame) {
		this.mainLabel = new JLabel(
				"Benvenuto nell'area Biblioteca, premi un pulsante");
		this.mainLabel.setBounds(21, 9, 281, 16);
		this.giveBackBook = new JButton("Restituisci Libro");
		this.giveBackBook.setBounds(160, 89, 121, 25);
		this.reviewBook = new JButton("Recensisci Libro");
		this.reviewBook.setBounds(158, 127, 123, 25);
		this.seeBorrowedBook = new JButton("Libri in prestito");
		this.seeBorrowedBook.setBounds(165, 203, 117, 25);
		this.checkBookAvailability = new JButton(
				"Controlla disponibilità Libro");
		this.checkBookAvailability.setBounds(126, 241, 187, 25);
		this.searchBook = new JButton("Cerca Libro");
		this.searchBook.addActionListener(arg0 -> {
		});
		this.searchBook.setBounds(178, 165, 97, 25);
		this.bookMenuPanel.setLayout(null);
		this.bookMenuPanel.add(this.mainLabel);
		this.bookMenuPanel.add(this.giveBackBook);
		this.bookMenuPanel.add(this.reviewBook);
		this.bookMenuPanel.add(this.seeBorrowedBook);
		this.borrowBook = new JButton("Prendi Libro");
		this.borrowBook.setBounds(165, 59, 101, 25);
		this.bookMenuPanel.add(this.borrowBook);
		this.bookMenuPanel.add(this.checkBookAvailability);
		this.bookMenuPanel.add(this.searchBook);
		this.borrowBook.addActionListener(e -> frame.add(BorrowBookService
				.getPanel()));
		this.giveBackBook.addActionListener(e -> frame.add(GiveBackBookService
				.getPanel()));
		this.reviewBook.addActionListener(e -> frame.add(ReviewBookService
				.getPanel()));
		this.seeBorrowedBook.addActionListener(e -> frame
				.add(SeeBorrowedBookService.getPanel()));
		this.checkBookAvailability.addActionListener(e -> frame
				.add(CheckBookAvailabilityService.getPanel()));
		this.searchBook.addActionListener(e -> frame.add(SearchBookService
				.getPanel()));

	}

	public JPanel getPanel() {
		return this.bookMenuPanel;
	}

}