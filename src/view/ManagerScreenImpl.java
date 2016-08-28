package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import view.ViewImpl.CardName;

/**
 * Class which implements the main screen for manager.
 *
 * @author Luca Giorgetti
 *
 */
public class ManagerScreenImpl extends JPanel implements ManagerScreen {

	private static final long serialVersionUID = 3947236683472052024L;
	private JList<String> list = new JList<String>();
	private TypeList type = TypeList.USER;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private String dClicked;

	/**
	 * Enum for the type of list can be.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum TypeList {
		ITEM, USER;
	}

	/**
	 * Builder for ManagerScreenImpl.
	 */
	public ManagerScreenImpl(final View v) {

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.setLayout(null);

		JButton newBook = new JButton("Crea Nuovo Libro");
		newBook.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		newBook.setBounds(498, 50, 273, 40);
		this.add(newBook);
		JButton delete = new JButton("Elimina");

		delete.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		JButton modifyBook = new JButton("Modifica Libro");
		modifyBook.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		delete.setBounds(498, 250, 273, 40);
		this.add(delete);
		modifyBook.setBounds(498, 300, 273, 40);
		this.add(modifyBook);
		JButton newMovie = new JButton("Crea Nuovo Film\r\n");
		newMovie.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		newMovie.setBounds(498, 100, 273, 40);
		this.add(newMovie);

		JButton modifyMovie = new JButton("Modifica Film");
		modifyMovie.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		modifyMovie.setBounds(498, 350, 273, 40);
		this.add(modifyMovie);
		JButton showUserList = new JButton("Lista Utenti");
		showUserList.setBounds(498, 150, 273, 40);
		showUserList
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(showUserList);
		// SHOW ALL USER LIST -> REFRESH
		showUserList.addActionListener(e -> {
			this.type = TypeList.USER;
			v.giveMeUserList();
			delete.setEnabled(false);
			modifyBook.setEnabled(false);
			modifyMovie.setEnabled(false);
			v.swapView(CardName.MANAGER_MENU);
		});

		JButton showItemList = new JButton("Lista Oggetti");
		showItemList.setBounds(498, 200, 273, 40);
		showItemList
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(showItemList);
		// SHOW ALL ITEM LIST -> REFRESH
		showItemList.addActionListener(e -> {
			this.type = TypeList.ITEM;
			delete.setEnabled(true);
			modifyBook.setEnabled(true);
			modifyMovie.setEnabled(true);
			v.giveMeItemList();
			v.swapView(CardName.MANAGER_MENU);
		});

		JButton seeBorrowedList = new JButton("Vedi prestiti");
		seeBorrowedList.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		seeBorrowedList.setBounds(498, 400, 273, 40);
		this.add(seeBorrowedList);
		this.list = new JList<String>();
		this.list.setModel(this.model);
		this.add(this.list);

		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> elist = (JList<String>) evt.getSource();
				if ((evt.getClickCount() == 2)
						&& (ManagerScreenImpl.this.type == TypeList.USER)) {
					System.out.println("Cliccato utente"
							+ ((JList) evt.getSource()).getSelectedValue()
									.toString());
					ManagerScreenImpl.this.dClicked = ((JList) evt.getSource())
							.getSelectedValue().toString();
					v.showUserInfo();
				} else if ((evt.getClickCount() == 2)
						&& (ManagerScreenImpl.this.type == TypeList.ITEM)) {
					System.out.println("Cliccato utente"
							+ ((JList) evt.getSource()).getSelectedValue()
									.toString());
					ManagerScreenImpl.this.dClicked = ((JList) evt.getSource())
							.getSelectedValue().toString();
					v.showItemInfo();
				}
			}
		});
		this.list.setBounds(33, 50, 414, 484);

		delete.addActionListener(e -> {
			System.out.println("Elimina Selezionato");
			v.deleteItem();
			v.giveMeItemList();
			v.swapView(CardName.MANAGER_MENU);

		});
		modifyBook.addActionListener(e -> {
			System.out.println("Modifica Libro Selezionato");
			v.giveMeItemInfoFromManager();
			v.swapView(CardName.BOOK_MODIFY);
		});
		modifyMovie.addActionListener(e -> {
			System.out.println("Modifica Film Selezionato");
			v.giveMeItemInfoFromManager();
			v.swapView(CardName.FILM_MODIFY);
		});
		if (this.type.equals(TypeList.USER)) {
			// SEE BORROWED LIST -> REFRESH
			if (this.list.isSelectionEmpty()) {
				seeBorrowedList.setEnabled(false);
				v.swapView(CardName.MANAGER_MENU);
			}
			seeBorrowedList.addActionListener(e -> {
				v.giveManagerBorrowList();
				v.swapView(CardName.MANAGER_MENU);
			});
		} else if (this.type.equals(TypeList.ITEM)) {

			seeBorrowedList.setEnabled(false);

		}

		JButton exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(620, 474, 151, 60);
		this.add(exit);

		exit.addActionListener(e -> v.swapView(CardName.MANAGER_LOGIN));

		newMovie.addActionListener(e -> v.swapView(CardName.FILM_CREATE));
		newBook.addActionListener(e -> v.swapView(CardName.BOOK_CREATE));

	}

	@Override
	public void setUserList(final String[] userList) {
		this.model.clear();
		for (String element : userList) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setItemList(final String[] itemList) {
		this.model.clear();
		for (String element : itemList) {
			this.model.addElement(element);
		}
	}

	@Override
	public void setUserBorrowedList(final String[] borrowedList) {
		this.model.clear();
		for (String element : borrowedList) {
			this.model.addElement(element);
		}
	}

	@Override
	public String getSelected() {
		return this.list.getSelectedValue();
	}

	@Override
	public String getDClicked() {
		return this.dClicked;
	}
}
