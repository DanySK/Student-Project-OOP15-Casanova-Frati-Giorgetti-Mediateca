package view;

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
	private TypeList type;

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

		this.setSize(1280, 920);
		this.setLayout(null);

		JButton modifyItem = new JButton("Modifica");
		modifyItem.setBounds(847, 115, 147, 25);
		this.add(modifyItem);

		JButton newItem = new JButton("Crea Nuovo Oggetto");
		newItem.addActionListener(arg0 -> {
		});
		newItem.setBounds(847, 77, 147, 25);
		this.add(newItem);

		JButton showUserList = new JButton("Lista Utenti");
		showUserList.setBounds(847, 186, 147, 25);
		this.add(showUserList);
		showUserList.addActionListener(e -> {
			v.giveMeUserList();
			this.type = TypeList.USER;
		});

		JButton showItemList = new JButton("Lista Oggetti");
		showItemList.setBounds(847, 224, 147, 25);
		this.add(showItemList);
		showItemList.addActionListener(e -> {
			v.giveMeItemList();
			this.type = TypeList.ITEM;
		});

		JButton delete = new JButton("Elimina");
		JButton modify = new JButton("Modifica");
		delete.setBounds(847, 153, 147, 25);
		this.add(delete);
		modify.setBounds(847, 115, 147, 25);
		this.add(modify);
		if (this.type.equals(TypeList.USER)) {
			delete.setEnabled(false);
			modify.setEnabled(false);
		} else if (this.type.equals(TypeList.ITEM)) {
			delete.addActionListener(e -> {
				v.deleteItem();
			});
			modify.addActionListener(e -> {
				v.swapView(CardName.ITEM_MODIFY);
			});

		}

		this.list.setBounds(114, 877, 523, -782);
		this.add(this.list);

		JButton exit = new JButton("Esci");
		exit.setBounds(847, 262, 147, 25);
		this.add(exit);

		newItem.addActionListener(e -> v.swapView(CardName.ITEM_CREATE));

	}

	@Override
	public void setUserList(final String[] userList) {
		this.list = new JList<String>(userList);
	}

	@Override
	public void setItemList(final String[] itemList) {
		this.list = new JList<String>(itemList);
	}

	@Override
	public String getSelected() {
		return this.list.getSelectedValue();
	}

}
