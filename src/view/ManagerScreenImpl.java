package view;

import java.awt.Font;

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
	private JList<String> list;
	private TypeList type = TypeList.USER;
	private DefaultListModel<String> model = new DefaultListModel<String>();

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

		JButton newItem = new JButton("Crea Nuovo Oggetto");
		newItem.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		newItem.setBounds(498, 39, 273, 39);
		this.add(newItem);
		JButton delete = new JButton("Elimina");

		delete.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		delete.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		JButton modify = new JButton("Modifica");
		modify.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		delete.setBounds(498, 179, 273, 39);
		this.add(delete);
		modify.setBounds(498, 224, 273, 39);
		this.add(modify);

		JButton showUserList = new JButton("Lista Utenti");
		showUserList.setBounds(498, 85, 273, 39);
		showUserList
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(showUserList);
		// SHOW ALL USER LIST -> REFRESH
		showUserList.addActionListener(e -> {
			this.type = TypeList.USER;
			v.giveMeUserList();
			delete.setEnabled(false);
			modify.setEnabled(false);
			v.swapView(CardName.MANAGER_MENU);
		});

		JButton showItemList = new JButton("Lista Oggetti");
		showItemList.setBounds(498, 132, 273, 39);
		showItemList
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		this.add(showItemList);
		// SHOW ALL ITEM LIST -> REFRESH
		showItemList.addActionListener(e -> {
			this.type = TypeList.ITEM;
			delete.setEnabled(true);
			modify.setEnabled(true);
			v.giveMeItemList();
			v.swapView(CardName.MANAGER_MENU);
		});

		JButton seeBorrowedList = new JButton("Vedi prestiti");
		seeBorrowedList.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		seeBorrowedList.setBounds(498, 268, 273, 39);
		this.add(seeBorrowedList);
		this.list = new JList();
		this.list.setModel(this.model);
		this.list.setBounds(33, 39, 414, 438);
		this.add(this.list);

		if (this.type.equals(TypeList.USER)) {
			// SEE BORROWED LIST -> REFRESH
			seeBorrowedList.addActionListener(e -> {
				v.giveManagerBorrowList();
				v.swapView(CardName.MANAGER_MENU);
			});
		} else if (this.type.equals(TypeList.ITEM)) {
			delete.addActionListener(e -> {
				v.deleteItem();
				v.giveMeItemList();
				v.swapView(CardName.MANAGER_MENU);

			});
			modify.addActionListener(e -> {
				v.giveMeItemInfo();
				v.swapView(CardName.ITEM_MODIFY);
			});
			seeBorrowedList.setEnabled(false);

		}

		JButton exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(620, 474, 151, 60);
		this.add(exit);

		exit.addActionListener(e -> v.swapView(CardName.MAIN));

		newItem.addActionListener(e -> v.swapView(CardName.ITEM_CREATE));

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

}
